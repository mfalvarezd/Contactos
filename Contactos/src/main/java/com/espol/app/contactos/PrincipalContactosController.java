package com.espol.app.contactos;

import com.espol.app.contactos.modelo.Atributo;
import com.espol.app.contactos.modelo.Contacto;
import com.espol.app.contactos.modelo.Empresa;
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

public class PrincipalContactosController implements Initializable {

    private static Usuario userLogIn;

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
    private List<Contacto> listaContactos;
    private List<Contacto> filtrada;
    @FXML
    private ComboBox<String> cmbFiltros;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<String> opciones = FXCollections.observableArrayList(
                "Apellido y primer nombre", "Empresa", "Tipo Contacto","Default"
        );

        cmbFiltros.setItems(opciones);
        
        

        userLogIn = UsuarioSingleton.getInstancia();
        listaContactos = userLogIn.getContactos();
        txtUsuario.setText(userLogIn.getNombre());
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
                        break;
                    
                }
            }
        });
        

    }

    private void actualizarLista(List<Contacto> lista) {
        contactos.getChildren().clear();
        for (Contacto c : lista) {
            HBox caja = new HBox();
            Separator sp = new Separator(Orientation.HORIZONTAL);
            sp.setPrefWidth(contactos.getWidth());

            String nombre_completo = c.getNombre();
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
                // Lógica para manejar cuando no hay fotos disponibles
            }

            nombre.setOnMouseClicked(eh -> {
                try {
                    EditarContactoController.contacto = c;
                    App.setRoot("editarContacto");
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
        //Button button1 = new Button("Botón A");

        /*
        for (int i=0; i<10; i++){
        HBox nuevoContacto = new HBox();
        Image imagen = new Image("file:imagenes\\logo.png");
        ImageView imagenContenedor = new ImageView(imagen);
        imagenContenedor.setFitWidth(20);
        imagenContenedor.setFitHeight(20);
        Label nombre = new Label("NUEVO CONTACTO");
        
        nuevoContacto.getChildren().addAll(imagenContenedor, nombre);
        
        contactos.getChildren().add(nuevoContacto);       
        }
         */
    }

    @FXML
    private void visualizar() throws IOException {
        App.setRoot("visualizador");
        System.out.println(userLogIn.getContactos());
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
        //Button button1 = new Button("Botón A");

        /*
        for (int i=0; i<10; i++){
        HBox nuevoContacto = new HBox();
        Image imagen = new Image("file:imagenes\\logo.png");
        ImageView imagenContenedor = new ImageView(imagen);
        imagenContenedor.setFitWidth(20);
        imagenContenedor.setFitHeight(20);
        Label nombre = new Label("NUEVO CONTACTO");
        
        nuevoContacto.getChildren().addAll(imagenContenedor, nombre);
        
        contactos.getChildren().add(nuevoContacto);       
        }
         */
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
                        if(apellido==0){
                            return nombre;
                        }
                        
                        return apellido;

                    }
                    return -1;
                };
                pcola = new PriorityQueue<>(comparador);
                for(Contacto c: personas){
                    pcola.offer(c);
                }
                while(!pcola.isEmpty()){
                    nuevaLista.add(pcola.poll());
                }
                
                filtrada = nuevaLista;
                actualizarLista(filtrada);
                

                break;
            case "Empresa":
                List<Contacto> empresas = userLogIn.getEmpresas();
                comparador = (Contacto c1, Contacto c2)->{
                  return c1.getNombre().compareTo(c2.getNombre());
                    
                };
                pcola = new PriorityQueue<>(comparador);
                for(Contacto c: empresas){
                    pcola.offer(c);
                }
                 while(!pcola.isEmpty()){
                    nuevaLista.add(pcola.poll());
                }
                
                filtrada = nuevaLista;
                actualizarLista(filtrada);
                break;
            case "Tipo":
                comparador = (Contacto c1, Contacto c2) -> Boolean.compare(c1.isEsEmpresa(), c2.isEsEmpresa());
                pcola = new PriorityQueue<>(comparador);
                for(Contacto c: listaContactos){
                    pcola.offer(c);
                }
                 while(!pcola.isEmpty()){
                    nuevaLista.add(pcola.poll());
                }
                
                filtrada = nuevaLista;
                actualizarLista(filtrada);
                
                
                
                break;

        }

    }

    @FXML
    private void Filtrar(ActionEvent event) {
    }

}
