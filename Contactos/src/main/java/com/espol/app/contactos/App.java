package com.espol.app.contactos;



import com.espol.app.contactos.modelo.Usuario;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {
    
    private static Scene scene;    

    @Override
    public void start(Stage stage) throws IOException {       
        scene = new Scene(loadFXML("primary"), 350, 588);
        stage.setScene(scene);
        stage.setTitle("Contactos");        
        Image imagen = new Image("file:imagenes\\logo.png");                
        stage.getIcons().add(imagen);
        stage.show();                              
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
