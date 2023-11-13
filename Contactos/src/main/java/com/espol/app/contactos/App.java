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
        scene = new Scene(loadFXML("primary"), 350, 588);
        stage.setScene(scene);
        stage.setTitle("Contactos");        
        Image imagen = new Image("file:imagenes\\logo.png");                
        stage.getIcons().add(imagen);
        stage.show();
        
        user = new Usuario("carfgonz", "123", "Carlos Flores");
      
        
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
