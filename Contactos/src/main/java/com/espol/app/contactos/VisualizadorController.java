/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.app.contactos;

import com.espol.app.contactos.modelo.Atributo;
import com.espol.app.contactos.modelo.Contacto;
import com.espol.app.contactos.modelo.Foto;
import com.espol.app.contactos.modelo.Persona;
import com.espol.app.contactos.modelo.Tipo;
import static com.espol.app.contactos.modelo.Tipo.CORREO;
import static com.espol.app.contactos.modelo.Tipo.DIRECCION;
import static com.espol.app.contactos.modelo.Tipo.FECHA;
import static com.espol.app.contactos.modelo.Tipo.REDSOCIAL;
import static com.espol.app.contactos.modelo.Tipo.TELEFONO;
import com.espol.app.contactos.modelo.UsuarioSingleton;
import com.espol.app.contactos.utilidades.List;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
public class VisualizadorController implements Initializable {

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

    private static List<Contacto> contactos = PrincipalContactosController.visualizador;

    private Foto fotoActual;
    private Contacto c;

    Label nombre = new Label();
    VBox caja = new VBox();
    HBox favorito = new HBox();
    Label descripcionL = new Label("Descipción: ");
    Label mapa = new Label();
    @FXML
    private Button regresar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (!contactos.isEmpty()) {
            informacion.getChildren().add(2, nombre);
            informacion.getChildren().add(3, caja);
            informacion.getChildren().add(4, favorito);
            informacion.getChildren().add(5, descripcionL);
            informacion.getChildren().add(6, mapa);

            c = contactos.get(0);
            this.actualizar(c);

            siguiente.setOnAction(ev -> {
                c = contactos.getNext(c);
                this.actualizar(c);
            });

            anterior.setOnAction(eh -> {
                c = contactos.getPrevious(c);
                this.actualizar(c);
            });
        }
    }

    public void actualizar(Contacto c) {
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

        caja.getChildren().clear();
        caja.setSpacing(10);

        /*
        VBox telefonos = new VBox();
        telefonos.getChildren().add(new Label("Telefonos:"));
        
        VBox redesSociales = new VBox();
        redesSociales.getChildren().add(new Label("Redes Sociales:"));
        
        VBox fechas = new VBox();
        fechas.getChildren().add(new Label("Fechas:"));
        
        VBox direcciones = new VBox();
        direcciones.getChildren().add(new Label("Direcciones:"));
        
        VBox correos = new VBox();
        correos.getChildren().add(new Label("Correos:"));

        caja.getChildren().addAll(telefonos, redesSociales, fechas, direcciones, correos);*/

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

        String descripcion = c.getDescripcion();
        if (descripcion != null) {            
            Label mapas = new Label("CASA");
            mapa.setText("CASA");
        }
        

        /*
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        // URL del mapa de Google Maps
        String mapUrl = "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d...."; // Sustituye esto con la URL de tu mapa

        // Cargar el mapa en el WebView
        webEngine.load(mapUrl);*/
    }

    @FXML
    private void regresar() throws IOException {
        App.setRoot("principalContactos");
    }

    @FXML
    private void editarContacto(ActionEvent event) {
    }
}