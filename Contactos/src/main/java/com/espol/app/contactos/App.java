package com.espol.app.contactos;



import com.espol.app.contactos.modelo.Usuario;
import com.espol.app.contactos.utilidades.DoublyCircularLinkedList;
import com.espol.app.contactos.utilidades.List;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Iterator;

import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {
    
    private static Scene scene;    

    @Override
    public void start(Stage stage) throws IOException {       
        scene = new Scene(loadFXML("primary"), 338, 588);
        stage.setScene(scene);
        stage.setTitle("Contactos");        
        Image imagen = new Image("file:imagenes\\logo.png");                
        stage.getIcons().add(imagen);
        stage.show();       
        
        List<String> as = new DoublyCircularLinkedList<>();
        as.add("1");
        as.add("2");
        as.add("3");
        as.add("4");
        as.add("5");
        System.out.println(as.getNext("2"));
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
