package com.espol.app.contactos;

import com.espol.app.contactos.modelo.Atributo;
import com.espol.app.contactos.modelo.Contacto;
import com.espol.app.contactos.modelo.Empresa;
import com.espol.app.contactos.modelo.Etiqueta;
import com.espol.app.contactos.modelo.Persona;
import com.espol.app.contactos.modelo.Usuario;
import com.espol.app.contactos.modelo.UsuarioSingleton;
import com.espol.app.contactos.utilidades.ArrayList;
import com.espol.app.contactos.utilidades.DoublyCircularLinkedList;
import com.espol.app.contactos.utilidades.List;
import com.espol.app.contactos.utilidades.ManejoArchivos;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

public class PrincipalContactosController implements Initializable {

    @FXML
    private VBox contactos;
    @FXML
    private Button visualizar;
    @FXML
    private Label txtUsuario;
    @FXML
    private Button salir;
    @FXML
    private TextField txtBuscar;
    @FXML
    private ComboBox<String> cmbFiltros;

    private static Usuario userLogIn;
    private List<Contacto> listaContactos;
    protected static List<Contacto> visualizador;
    private List<Contacto> filtrada;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> opciones = FXCollections.observableArrayList(
                "Apellido y primer nombre", "Empresa", "Amigos", "Familia", "Trabajo", "Tipo Contacto", "Default"
        );

        cmbFiltros.setItems(opciones);

        userLogIn = UsuarioSingleton.getInstancia();
        listaContactos = userLogIn.getContactos();

        txtUsuario.setText("Bienvenido " + userLogIn.getNombre());

        Font nuevaFuente = new Font("Georgia", 15);
        txtUsuario.setFont(nuevaFuente);

        System.out.println("El usuario que inicio se sesion es ");
        System.out.println(userLogIn.getNombre());
        System.out.println(userLogIn.getUser());
        System.out.println(userLogIn.getContactos());
        contactos.setSpacing(8);
        actualizarLista(listaContactos);
        cmbFiltros.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue != null) {

                switch (newValue) {
                    case "Apellido y primer nombre":
                        filtrarLista("nombres");
                        System.out.println("Entro aqui");
                        break;
                    case "Empresa":
                        System.out.println("Entro a empresa");
                        filtrarLista("Empresa");
                        break;
                    case "Tipo Contacto":
                        filtrarLista("Tipo");
                        break;
                    case "Default":
                        contactos.getChildren().clear();
                        actualizarLista(listaContactos);
                        visualizador = listaContactos;
                        break;
                    case "Familia":
                        filtrarLista("Familia");
                        break;
                    case "Amigos":
                        filtrarLista("Amigos");
                        break;
                    case "Trabajo":
                        filtrarLista("Trabajo");
                        break;
                }
            }
        });

    }

    private void actualizarLista(List<Contacto> lista) {
        contactos.getChildren().clear();
        String nombre_completo;
        for (Contacto c : lista) {
            if (c instanceof Persona) {
                Persona p = (Persona) c;
                nombre_completo = c.getNombre() + " " + p.getApellidos();
            } else {
                nombre_completo = c.getNombre();
            }
            HBox caja = new HBox();
            Separator sp = new Separator(Orientation.HORIZONTAL);
            sp.setPrefWidth(contactos.getWidth());

            Label nombre = new Label(nombre_completo);
            nombre.setOnMouseEntered((event) -> {
                nombre.setEffect(new DropShadow());
            });
            nombre.setOnMouseExited((event) -> {
                nombre.setEffect(null);
                contactos.requestFocus();
            });
            nombre.setPadding(new Insets(7, 0, 0, 0));

            if (c.getFotos() != null && !c.getFotos().isEmpty()) {
                ImageView foto = new ImageView(c.getFotos().get(0).getUrl());
                foto.setFitHeight(30);
                foto.setFitWidth(30);

                Circle clip = new Circle(15, 15, 15);
                foto.setClip(clip);

                caja.setSpacing(10);
                caja.setPadding(new Insets(0, 0, 0, 10));
                caja.getChildren().addAll(foto, nombre);

                contactos.getChildren().addAll(caja, sp);
                ajustarAlturaVBox();
            } else {
                System.out.println("No tiene foto");
                ImageView foto = new ImageView();
                foto.setFitHeight(30);
                foto.setFitWidth(30);

                Circle clip = new Circle(15, 15, 15);
                foto.setClip(clip);

                caja.setSpacing(10);
                caja.setPadding(new Insets(0, 0, 0, 10));
                caja.getChildren().addAll(foto, nombre);

                contactos.getChildren().addAll(caja, sp);
                ajustarAlturaVBox();
                // Lógica para manejar cuando no hay fotos disponibles
            }

            nombre.setOnMouseClicked(eh -> {
                try {
                    this.visualizador = userLogIn.getContactos();
                    VisualizadorController.c = c;
                    App.setRoot("visualizador");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
        }
    }

    @FXML
    private void agregarP() throws IOException {
        System.out.println("HOLA MUNDO");
        App.setRoot("aggPersona");
    }

    @FXML
    private void visualizar() throws IOException {
        if (userLogIn.getContactos().size() == 0) {
            this.alerta();
        } else {
            this.visualizador = userLogIn.getContactos();
            VisualizadorController.c = null;
            App.setRoot("visualizador");
        }
    }

    @FXML
    private void salir() throws IOException {
        ManejoArchivos.guardarDatos(userLogIn);
        App.setRoot("logIn");
    }

    @FXML
    private void agregarE() throws IOException {
        System.out.println("HOLA MUNDO");
        App.setRoot("aggEmpresa");
    }

    private void filtrarLista(String parametro) {
        Comparator<Contacto> comparador;
        List<Contacto> nuevaLista = new DoublyCircularLinkedList<>();
        Queue<Contacto> pcola;
        switch (parametro) {
            case "nombres":
                List<Contacto> personas = userLogIn.getPersonas();
                comparador = (Contacto c1, Contacto c2) -> {
                    if (c1 instanceof Persona && c2 instanceof Persona) {
                        Persona p1 = (Persona) c1;
                        Persona p2 = (Persona) c2;
                        String[] nombres1 = p1.getNombre().split(" ");
                        String[] nombres2 = p2.getNombre().split(" ");
                        String[] apellido1 = p1.getApellidos().split(" ");
                        String[] apellido2 = p2.getApellidos().split(" ");
                        int apellido = apellido1[0].compareTo(apellido2[0]);
                        int nombre = nombres1[0].compareTo(nombres2[0]);
                        if (apellido == 0) {
                            return nombre;
                        }
                        return apellido;
                    }
                    return -1;
                };
                pcola = new PriorityQueue<>(comparador);
                for (Contacto c : personas) {
                    pcola.offer(c);
                }
                while (!pcola.isEmpty()) {
                    nuevaLista.add(pcola.poll());
                }

                filtrada = nuevaLista;
                actualizarLista(filtrada);
                visualizador = filtrada;

                break;
            case "Empresa":
                List<Contacto> empresas = userLogIn.getEmpresas();
                comparador = (Contacto c1, Contacto c2) -> {
                    return c1.getNombre().compareTo(c2.getNombre());

                };
                pcola = new PriorityQueue<>(comparador);
                for (Contacto c : empresas) {
                    pcola.offer(c);
                }
                while (!pcola.isEmpty()) {
                    nuevaLista.add(pcola.poll());
                }

                filtrada = nuevaLista;
                actualizarLista(filtrada);
                visualizador = filtrada;
                break;
            case "Tipo":
                comparador = (Contacto c1, Contacto c2) -> Boolean.compare(c1.isEsEmpresa(), c2.isEsEmpresa());
                pcola = new PriorityQueue<>(comparador);
                for (Contacto c : listaContactos) {
                    pcola.offer(c);
                }
                while (!pcola.isEmpty()) {
                    nuevaLista.add(pcola.poll());
                }

                filtrada = nuevaLista;
                actualizarLista(filtrada);
                visualizador = filtrada;

                break;
            case "Familia":

                List<Contacto> familia = new DoublyCircularLinkedList<>();
                for (Contacto c : userLogIn.getContactos()) {
                    if (c.getEtiqueta()==(Etiqueta.Familia)) {
                        familia.add(c);
                    }
                }
                comparador = (c1, c2) -> {
                    return c1.getNombre().compareTo(c2.getNombre());
                };
                pcola = new PriorityQueue<>(comparador);
                for (Contacto c : familia) {
                    pcola.offer(c);
                }
                while (!pcola.isEmpty()) {
                    nuevaLista.add(pcola.poll());
                }

                filtrada = nuevaLista;
                actualizarLista(filtrada);
                visualizador = filtrada;
                break;
            case "Amigos":
                List<Contacto> amigos = new DoublyCircularLinkedList<>();
                for (Contacto c : userLogIn.getContactos()) {
                    if (c.getEtiqueta()==(Etiqueta.Amigos)) {
                        amigos.add(c);
                    }
                }
                comparador = (c1, c2) -> {
                    return c1.getNombre().compareTo(c2.getNombre());
                };
                pcola = new PriorityQueue<>(comparador);
                for (Contacto c : amigos) {
                    pcola.offer(c);
                }
                while (!pcola.isEmpty()) {
                    nuevaLista.add(pcola.poll());
                }

                filtrada = nuevaLista;
                actualizarLista(filtrada);
                visualizador = filtrada;
                break;
            case "Trabajo":
                List<Contacto> trabajo = new DoublyCircularLinkedList<>();
                for (Contacto c : userLogIn.getContactos()) {
                    if (c.getEtiqueta()==(Etiqueta.Trabajo)) {
                        trabajo.add(c);
                    }
                }
                comparador = (c1, c2) -> {
                    return c1.getNombre().compareTo(c2.getNombre());
                };
                pcola = new PriorityQueue<>(comparador);
                for (Contacto c : trabajo) {
                    pcola.offer(c);
                }
                while (!pcola.isEmpty()) {
                    nuevaLista.add(pcola.poll());
                }

                filtrada = nuevaLista;
                actualizarLista(filtrada);
                visualizador = filtrada;
                break;
        }
    }

    @FXML
    private void Filtrar(ActionEvent event) {
    }

    @FXML
    private void listas(ActionEvent event) {
        Separator sp = new Separator(Orientation.HORIZONTAL);
        sp.setPrefWidth(contactos.getWidth());
        contactos.getChildren().clear();
        HBox favoritos = new HBox();
        favoritos.setOnMouseClicked((E) -> {
            mostrarFavoritos();
        });
        Label favText = new Label("Favoritos");
        favText.setOnMouseEntered((evt) -> {
            favText.setEffect(new DropShadow());
        });
        favText.setOnMouseExited((evt) -> {
            favText.setEffect(null);
            contactos.requestFocus();
        });
        favText.setPadding(new Insets(7, 0, 0, 0));

        favoritos.getChildren().add(favText);
        HBox general = new HBox();
        Label generalText = new Label("Todos mis contactos");
        generalText.setOnMouseEntered((evt) -> {
            generalText.setEffect(new DropShadow());
        });
        generalText.setOnMouseExited((evt) -> {
            generalText.setEffect(null);
            contactos.requestFocus();
        });
        generalText.setPadding(new Insets(7, 0, 0, 0));
        favText.setOnMouseClicked((evento) -> {
            mostrarFavoritos();
        });
        general.setOnMouseClicked((ev) -> {
            actualizarLista(userLogIn.getContactos());
        });
        general.getChildren().add(generalText);
        contactos.getChildren().addAll(favoritos, sp, general);
    }

    private void ajustarAlturaVBox() {
        contactos.setPrefHeight(contactos.getPrefHeight() + 1); // Ajusta según sea necesario
    }

    private void mostrarFavoritos() {
        listaContactos = userLogIn.getFavoritos();
        actualizarLista(listaContactos);
    }

    private void alerta() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Advertencia!!");
        alert.setHeaderText(null);
        alert.setContentText("No hay contactos");
        alert.showAndWait();
    }
}
