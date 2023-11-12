package com.espol.app.contactos;

import com.espol.app.contactos.modelo.Atributo;
import com.espol.app.contactos.modelo.Contacto;
import com.espol.app.contactos.modelo.Foto;
import com.espol.app.contactos.modelo.Persona;
import com.espol.app.contactos.modelo.Tipo;
import com.espol.app.contactos.modelo.Usuario;
import com.espol.app.contactos.utilidades.*;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * JavaFX App
 */
public class App extends Application {
    
    private static Scene scene;
    public static Usuario user;

    @Override
    public void start(Stage stage) throws IOException {       
        scene = new Scene(loadFXML("primary"), 338, 588);
        stage.setScene(scene);
        stage.setTitle("Contactos");        
        Image imagen = new Image("file:imagenes\\logo.png");                
        stage.getIcons().add(imagen);
        stage.show();
        
        user = new Usuario("carfgonz", "123", "Carlos Flores");
        
        Atributo<String> at = new Atributo<String>("Carlos", "09890283", Tipo.TELEFONO);
        Foto fot = new Foto("FOTO");
        
        List<Atributo<String>> atributos = new ArrayList<>();
        atributos.add(at);
        
        List<Foto> fotos = new ArrayList<>();
        fotos.add(fot);
        Date da = new Date(23,23,23);
        
        Persona p1 = new Persona("Lopez", da, atributos, fotos);
        Persona p2 = new Persona("Flores", da, atributos, fotos);
        Persona p3 = new Persona("Martinez", da, atributos, fotos);
        Persona p4 = new Persona("Prueba", da, atributos, fotos);
        Persona p5 = new Persona("Azul", da, atributos, fotos);                                
        
        user.getContactos().add(p1);
        user.getContactos().add(p2);
        user.getContactos().add(p3);
        user.getContactos().add(p4);
        user.getContactos().add(p5);        
        
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
