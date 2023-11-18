/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.app.contactos;

import com.espol.app.contactos.modelo.Atributo;
import com.espol.app.contactos.modelo.Contacto;
import com.espol.app.contactos.modelo.Foto;
import com.espol.app.contactos.modelo.Tipo;
import com.espol.app.contactos.utilidades.List;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author ander
 */
public class TertiaryController implements Initializable{
    
    @FXML
    private VBox informacion;
    
    @FXML
    private ImageView fotoView;
    
    @FXML
    private Button nextFoto;
    
    @FXML
    private Button farmerFoto;
    
    @FXML
    private Button siguiente;
    
    @FXML
    private Button anterior;
    
    static List<Contacto> contactos = SecondaryController.userLogIn.getContactos();

    Foto fotoActual;
    Contacto c;
        
    Label nombre = new Label();
    HBox caja = new HBox();
    HBox favorito = new HBox();
    Label descripcionL = new Label();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {    
        if (!contactos.isEmpty()){
            informacion.getChildren().add(2,nombre);
            informacion.getChildren().add(3, caja);
            informacion.getChildren().add(4,favorito);
            informacion.getChildren().add(5, descripcionL);                      
            
            c = contactos.get(0);

                            
            siguiente.setOnAction( ev -> {                                
                c = contactos.getNext(c);
                actualizar(c);                
            });    
        
            anterior.setOnAction(eh -> {                
                c = contactos.getPrevious(c);                
                actualizar(c);
            });
        }
    }
    
    public void actualizar(Contacto c) {        
        System.out.println(c.toString());
                        
        nombre.setText(c.getNombre()+" "+c.getApellidos());        
                
        List<Foto> fotos = c.getFotos();                
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
        
        List<Atributo> atributos = c.getAtributos();
        
        caja.getChildren().clear();
        
        for (Atributo at : atributos) {
            Tipo tipo = at.getTipo();            
            Label etiqueta;
            Label valor;
            Label tipoL;
            
            if (tipo == Tipo.TELEFONO) {                
                tipoL = new Label ("Telefono: ");
                etiqueta = new Label (at.getNombre());
                valor = new Label (at.getValor());   
                caja.getChildren().addAll(tipoL, etiqueta, valor);
            } else if (tipo == Tipo.REDSOCIAL) {
                tipoL = new Label ("Red Social: ");
                etiqueta = new Label (at.getNombre());
                valor = new Label (at.getValor());   
                caja.getChildren().addAll(tipoL, etiqueta, valor);
            } else if (tipo == Tipo.FECHA) {
                tipoL = new Label ("Telefono: ");
                etiqueta = new Label (at.getNombre());
                valor = new Label (at.getValor());          
                caja.getChildren().addAll(tipoL, etiqueta, valor);
            } else if (tipo == Tipo.DIRECCION) {
                tipoL = new Label ("Direccion: ");
                etiqueta = new Label (at.getNombre());
                valor = new Label (at.getValor());          
                caja.getChildren().addAll(tipoL, etiqueta, valor);
            } else if (tipo == Tipo.CORREO) {
                tipoL = new Label ("Correo: ");
                etiqueta = new Label (at.getNombre());
                valor = new Label (at.getValor());          
                caja.getChildren().addAll(tipoL, etiqueta, valor);
            }
            System.out.println("TODO BIEN");
        }
        
        
        
        //List<Contacto> contactosRelacionados = c.getContactos_relacionados();                
        
        boolean esFavorito = c.isEsFavorito();
                
        favorito.getChildren().clear();
        
        if (esFavorito) {            
            ImageView favoritoView = new ImageView("file:imagenes\\estrella.png");
            favoritoView.setFitHeight(40);
            favoritoView.setFitWidth(40);
            favorito.getChildren().add(favoritoView);
        } else {            
            ImageView favoritoView = new ImageView("file:imagenes\\estrella2.png");
            favoritoView.setFitHeight(40);
            favoritoView.setFitWidth(40);
            favorito.getChildren().add(favoritoView);
        }
                
        
        String descripción = c.getDescripcion();
        descripcionL.setText(descripción);
    }
    
    
    @FXML
    private void regresar() throws IOException {
        App.setRoot("principalContactos");
    }        
 
}
