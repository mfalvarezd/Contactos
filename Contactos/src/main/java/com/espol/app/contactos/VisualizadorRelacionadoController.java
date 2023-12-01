package com.espol.app.contactos;


import com.espol.app.contactos.App;
import com.espol.app.contactos.EditarContactoController;
import static com.espol.app.contactos.VisualizadorController.c;
import com.espol.app.contactos.modelo.Atributo;
import com.espol.app.contactos.modelo.Contacto;
import com.espol.app.contactos.modelo.Foto;
import com.espol.app.contactos.modelo.Persona;
import com.espol.app.contactos.modelo.Relacion;
import com.espol.app.contactos.modelo.Tipo;
import static com.espol.app.contactos.modelo.Tipo.CORREO;
import static com.espol.app.contactos.modelo.Tipo.DIRECCION;
import static com.espol.app.contactos.modelo.Tipo.FECHA;
import static com.espol.app.contactos.modelo.Tipo.REDSOCIAL;
import static com.espol.app.contactos.modelo.Tipo.TELEFONO;
import com.espol.app.contactos.utilidades.List;
import com.espol.app.contactos.utilidades.ManejoArchivos;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ander
 */
public class VisualizadorRelacionadoController implements Initializable {
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
    
    private Foto fotoActual;
    public static Relacion relacion;
    protected static Contacto respaldo;
    
    Label nombre = new Label();
    VBox caja = new VBox();
    HBox favorito = new HBox();    
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        informacion.getChildren().add(nombre);        
        informacion.getChildren().add(favorito);      
        informacion.getChildren().add(caja);
        
        Contacto c = relacion.getContacto();
        
        if (!c.isEsEmpresa()){
            Persona persona = (Persona) c;
            nombre.setText(persona.getNombre()+" "+persona.getApellidos());
        } else {            
            nombre.setText(c.getNombre());
        }        
        
        List<Foto> fotos = c.getFotos();
        fotoActual = fotos.get(0);
        
        if (fotoActual != null) {
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
        }else{
            System.out.println("No tiene fotos");
        }

        List<Atributo> atributos = c.getAtributos();
        List<Relacion> relaciones = c.getContactos_relacionados();
        
        caja.getChildren().clear();
        caja.setSpacing(10);

        for (Atributo at : atributos) {
            HBox atributosBox = new HBox();

            Tipo tipo = at.getTipo();
            String etiqueta;
            String valor;
            Label atributo;

            if (null != tipo) {
                switch (tipo) {
                    case TELEFONO:
                        VBox telefonos = new VBox();
                        telefonos.getChildren().add(new Label("Telefonos:"));
                        caja.getChildren().add(telefonos);
                        
                        etiqueta = at.getNombre();
                        valor = at.getValor();
                        atributo = new Label(etiqueta + " - " + valor);
                        atributosBox.getChildren().addAll(atributo);
                        telefonos.getChildren().add(atributosBox);
                        break;
                    case REDSOCIAL:
                        VBox redesSociales = new VBox();
                        redesSociales.getChildren().add(new Label("Redes Sociales:"));
                        caja.getChildren().add(redesSociales);
                        
                        etiqueta = at.getNombre();
                        valor = at.getValor();
                        atributo = new Label(etiqueta + " - " + valor);
                        atributosBox.getChildren().addAll(atributo);
                        redesSociales.getChildren().add(atributosBox);
                        break;
                    case FECHA:
                        VBox fechas = new VBox();
                        fechas.getChildren().add(new Label("Fechas:"));
                        caja.getChildren().add(fechas);
                        
                        etiqueta = at.getNombre();
                        valor = at.getValor();
                        atributo = new Label(etiqueta + " - " + valor);
                        atributosBox.getChildren().addAll(atributo);
                        fechas.getChildren().add(atributosBox);
                        break;
                    case DIRECCION:
                        VBox direcciones = new VBox();
                        direcciones.getChildren().add(new Label("Direcciones:"));
                        caja.getChildren().add(direcciones);
                        
                        etiqueta = at.getNombre();
                        valor = at.getValor();
                        atributo = new Label(etiqueta + " - " + valor);
                        atributosBox.getChildren().addAll(atributo);
                        direcciones.getChildren().add(atributosBox);
                        break;
                    case CORREO:
                        VBox correos = new VBox();
                        correos.getChildren().add(new Label("Correos:"));
                        caja.getChildren().add(correos);
                        
                        etiqueta = at.getNombre();
                        valor = at.getValor();
                        atributo = new Label(etiqueta + " - " + valor);
                        atributosBox.getChildren().addAll(atributo);
                        correos.getChildren().add(atributosBox);
                        break;
                    default:
                        break;
                }
            }
        }
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
    }
    
    @FXML
    private void regresar() throws IOException {       
        Platform.runLater(() -> {
            try {
                VisualizadorController.c = respaldo;
                App.setRoot("visualizador");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });        

    }    

    @FXML
    private void eliminarRelacionado(ActionEvent event) throws IOException {
        VisualizadorController.c.eliminarRelacion(relacion);        
        this.regresar();
    }    
}
