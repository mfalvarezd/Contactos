/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.app.contactos;

import com.espol.app.contactos.modelo.Contacto;
import com.espol.app.contactos.modelo.Foto;
import com.espol.app.contactos.modelo.Usuario;
import com.espol.app.contactos.utilidades.List;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 *
 * @author ander
 */
public class EditarContactoController implements Initializable {
    @FXML
    private ImageView fotoView;    
    @FXML
    private Button nextFoto;    
    @FXML
    private Button farmerFoto;
    @FXML
    private Button btnEliminarFoto;
    @FXML
    private Button btnAgregarFoto;
    
    static Contacto contacto;
    Foto fotoActual;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Foto> fotos = contacto.getFotos();
        
        fotoActual = fotos.get(0);
        Image imagen = new Image(fotoActual.getUrl());
        fotoView.setImage(imagen);
        
        
        nextFoto.setOnAction(ec -> {                        
            fotoActual = fotos.getNext(fotoActual);
            Image imagenNext = new Image(fotoActual.getUrl());
            fotoView.setImage(imagenNext);              
          
        });          
        
        farmerFoto.setOnAction(eh -> {
            fotoActual = fotos.getPrevious(fotoActual);            
            Image imagenNext = new Image(fotoActual.getUrl());
            fotoView.setImage(imagenNext);
        });
        
        btnEliminarFoto.setOnAction(eh ->{
            Foto eliminar = fotoActual;
            
            fotoActual = fotos.getNext(fotoActual);
            Image imagenNext = new Image(fotoActual.getUrl());
            fotoView.setImage(imagenNext); 
                        
            fotos.remove(eliminar);
        });
        
        btnAgregarFoto.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent eh) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg"));
                
                File archivoSeleccionado = fileChooser.showOpenDialog(null);
                
                // Verificar si se seleccionó un archivo
                if (archivoSeleccionado != null) {
                    String url = archivoSeleccionado.toURI().toString();                    
                    fotoView.setImage(new Image(url));                                        
                    Foto f = new Foto (url);            
                    fotos.add(f);
                }
            }
        });
        
        
    }
    
    @FXML
    private void regresar() throws IOException {
        App.setRoot("principalContactos");
    }    
    
    @FXML
    private void guardarContacto() throws IOException {
        App.setRoot("principalContactos");
    }

    
}
