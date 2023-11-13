package com.espol.app.contactos;

import com.espol.app.contactos.modelo.Atributo;
import com.espol.app.contactos.modelo.Contacto;
import com.espol.app.contactos.modelo.Usuario;
import com.espol.app.contactos.utilidades.ManejoArchivos;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class SecondaryController implements Initializable{
    static Usuario userLogIn;
    
    @FXML
    private VBox contactos;
    
    @FXML
    private Button ingresar;
    
    @FXML
    private Button visualizar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       userLogIn=PrimaryController.usuarioLogeado;
        System.out.println("El usuario que inicio se sesion es ");
        System.out.println(userLogIn.getNombre());
         System.out.println(userLogIn.getUser());
    }

    @FXML
    private void agregar() throws IOException {
        System.out.println("HOLA MUNDO");
        App.setRoot("aggContacts");        
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
}