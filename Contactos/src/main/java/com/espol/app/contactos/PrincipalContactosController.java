package com.espol.app.contactos;

import com.espol.app.contactos.modelo.Atributo;
import com.espol.app.contactos.modelo.Contacto;
import com.espol.app.contactos.modelo.Persona;
import com.espol.app.contactos.modelo.Usuario;
import com.espol.app.contactos.modelo.UsuarioSingleton;
import com.espol.app.contactos.utilidades.List;
import com.espol.app.contactos.utilidades.ManejoArchivos;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        userLogIn = UsuarioSingleton.getInstancia();
        List<Contacto> listaContactos = userLogIn.getContactos();
        txtUsuario.setText(userLogIn.getNombre());
        System.out.println("El usuario que inicio se sesion es ");
        System.out.println(userLogIn.getNombre());
        System.out.println(userLogIn.getUser());
        System.out.println(userLogIn.getContactos());
        contactos.setSpacing(8);
        actualizarLista(listaContactos);

        

    }
    private void actualizarLista(List<Contacto> listaContactos){
        for (Contacto c : listaContactos) {
            HBox caja = new HBox();
            Separator sp = new Separator(Orientation.HORIZONTAL);
            sp.setPrefWidth(contactos.getWidth());

            String nombre_completo = c.getNombre();
            Label nombre = new Label(nombre_completo);
            nombre.setOnMouseEntered((event) -> {nombre.setEffect(new DropShadow());});
            nombre.setOnMouseExited((event) -> {nombre.setEffect(null);contactos.requestFocus();});
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

                contactos.getChildren().addAll(caja,sp);
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

                contactos.getChildren().addAll(caja,sp);
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
    
    
    private void filtrarLista(){
        String parametro = txtBuscar.getText();
        Comparator<Contacto> comparador;
        switch (parametro){
            case "apellido":
                comparador = new Comparator<Contacto>() {
            @Override
            public int compare(Contacto c1, Contacto c2) {
                if(c1 instanceof Persona && c2 instanceof Persona){
                    Persona p1 = (Persona) c1;
                    Persona p2 = (Persona) c2;
                }
                return 0;
            }
        };
                
                break;
            
        }
        
   
        
        
        
    }
    
    
    
}
