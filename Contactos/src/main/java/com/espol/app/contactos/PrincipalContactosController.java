package com.espol.app.contactos;

import com.espol.app.contactos.modelo.Atributo;
import com.espol.app.contactos.modelo.Contacto;
import com.espol.app.contactos.modelo.Usuario;
import com.espol.app.contactos.modelo.UsuarioSingleton;
import com.espol.app.contactos.utilidades.ManejoArchivos;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userLogIn = UsuarioSingleton.getInstancia();
        txtUsuario.setText(userLogIn.getNombre());
        System.out.println("El usuario que inicio se sesion es ");
        System.out.println(userLogIn.getNombre());
        System.out.println(userLogIn.getUser());
        System.out.println(userLogIn.getContactos());
        contactos.setSpacing(10);

        for (Contacto c : userLogIn.getContactos()) {
            HBox caja = new HBox();

            String nombre_completo = c.getNombre();
            Label nombre = new Label(nombre_completo);
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

                contactos.getChildren().add(caja);
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

                contactos.getChildren().add(caja);
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
        App.setRoot("tertiary");
        System.out.println(userLogIn.getContactos());
    }

    @FXML
    private void salir() throws IOException {
        ManejoArchivos.guardarDatos(userLogIn);
        App.setRoot("primary");
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
}
