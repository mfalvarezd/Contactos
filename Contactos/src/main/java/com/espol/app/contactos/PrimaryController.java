package com.espol.app.contactos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class PrimaryController implements Initializable{    
    @FXML
    private Button ingresar;        
    
    @FXML
    private void ingresar() throws IOException {
        //Logica para iniciar sección
        System.out.println("Hola Mundo");
        App.setRoot("secondary2");
    }            

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*
        ingresar.setOnAction(event -> {
            System.out.println("Hola Mundoxd");
        });
        */
    }
}
