/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.app.contactos;

import java.io.IOException;
import javafx.fxml.FXML;

/**
 *
 * @author ander
 */
public class TertiaryController {
    
    @FXML
    private void regresar() throws IOException {
        App.setRoot("secondary2");
    }
    
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary2");
    }
    
    
    
}
