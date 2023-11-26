/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.app.contactos;

import com.espol.app.contactos.modelo.Atributo;
import com.espol.app.contactos.modelo.Contacto;
import com.espol.app.contactos.modelo.Empresa;
import com.espol.app.contactos.modelo.Foto;
import com.espol.app.contactos.modelo.Tipo;
import com.espol.app.contactos.modelo.Usuario;
import com.espol.app.contactos.utilidades.DoublyCircularLinkedList;
import com.espol.app.contactos.utilidades.ManejoArchivos;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

/**
 *
 * @author ander
 */
public class AggEmpresaController implements Initializable {

    @FXML
    private VBox contentPrincipal;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnListo;
    @FXML
    private ImageView imgFoto;
    @FXML
    private Button btnAgregarFoto;
    @FXML
    private TextField tfNombre;
    private TextField tfApellidos;
    @FXML
    private TextField tfEmpresa;
    @FXML
    private VBox contentTelf;
    @FXML
    private VBox contentCorreo;
    @FXML
    private VBox contentDic;
    @FXML
    private VBox contentFecha;
    @FXML
    private TextField tftApellido;
    @FXML
    private Button btnTelf;
    @FXML
    private Button btnCorreo;
    @FXML
    private Button btnDic;
    @FXML
    private Button btnFecha;
    @FXML
    private HBox default1;
    @FXML
    private HBox default2;
    @FXML
    private VBox default3;
    @FXML
    private CheckBox esFavorito;
    
    private int MAXTELF = 3;
    private int MAXCORREO = 3;
    private int MAXDIC = 3;
    private int MAXFECHA = 3;
    private int MAXREDSOCIAL = 3;
    
    @FXML
    private VBox contentRedSocial;
    @FXML
    private Button btnRedSocial;
    @FXML
    private ScrollPane scrollPane;
    
    private Usuario usuarioLogeado;
    
    private DoublyCircularLinkedList<Foto> fotos = new DoublyCircularLinkedList<Foto>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {                                                
        usuarioLogeado = PrimaryController.usuarioLogeado;        

        btnAgregarFoto.setOnAction(eh->{
            this.abrirFoto();           
        });        
    }
        
    private void abrirFoto() {        
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg"));
        
        File archivoSeleccionado = fileChooser.showOpenDialog(null);

        // Verificar si se seleccionó un archivo
        if (archivoSeleccionado != null) {            
            String url = archivoSeleccionado.toURI().toString();           
            Image imagen = new Image(url);            
            imgFoto.setImage(imagen);
            imgFoto.setFitWidth(200);
            imgFoto.setFitHeight(150);
            
            Foto f = new Foto (url);
            fotos.add(f);                        
        }
    }    

    @FXML
    private void aggTelf(ActionEvent event) {               
        if (MAXTELF > 0) {
            MAXTELF--;
            HBox hb = new HBox();
            hb.setSpacing(10);
            TextField tfEtiqueta = new TextField();

            tfEtiqueta.setPromptText("Etiqueta");
            tfEtiqueta.setMaxWidth(60);
            TextField telefono = new TextField();
            telefono.setPromptText("Telefono");
            hb.getChildren().addAll(tfEtiqueta, telefono);
            contentTelf.getChildren().addAll(hb);
            ajustarAlturaVBox();
        }
    }

    @FXML
    private void aggCorreo(ActionEvent event) {
        if (MAXCORREO > 0) {
            MAXCORREO--;
            HBox hb = new HBox();
            hb.setSpacing(10);
            TextField tfEtiqueta = new TextField();

            tfEtiqueta.setPromptText("Etiqueta");
            tfEtiqueta.setMaxWidth(60);
            TextField correo = new TextField();

            correo.setPromptText("Correo");
            hb.getChildren().addAll(tfEtiqueta, correo);
            contentCorreo.getChildren().addAll(hb);
            ajustarAlturaVBox();
        }
    }

    @FXML
    private void aggDic(ActionEvent event) {
        if (MAXDIC > 0) {
            MAXDIC--;
            HBox hb = new HBox();
            hb.setSpacing(10);
            TextField tfEtiqueta = new TextField();

            tfEtiqueta.setPromptText("Etiqueta");
            tfEtiqueta.setMaxWidth(60);
            TextField calle1 = new TextField();
            calle1.setPromptText("Calle 1");

            hb.getChildren().addAll(tfEtiqueta, calle1);
            
            contentDic.getChildren().addAll(hb);
            ajustarAlturaVBox();
        }
    }

    @FXML
    private void aggFecha(ActionEvent event) {
        if (MAXFECHA > 0) {
            MAXFECHA--;
            HBox hb = new HBox();
            hb.setSpacing(10);
            TextField tfEtiqueta = new TextField();

            tfEtiqueta.setPromptText("Etiqueta");
            tfEtiqueta.setMaxWidth(60);
            DatePicker fecha = new DatePicker();

            fecha.setPromptText("Fecha");
            hb.getChildren().addAll(tfEtiqueta, fecha);
            contentFecha.getChildren().addAll(hb);
            ajustarAlturaVBox();
        }
    }

    @FXML
    private void aggRedSocial(ActionEvent event) {
        if (MAXREDSOCIAL > 0) {
            MAXREDSOCIAL--;
            HBox hb = new HBox();
            hb.setSpacing(10);
            TextField tfEtiqueta = new TextField();

            tfEtiqueta.setPromptText("Etiqueta");
            tfEtiqueta.setMaxWidth(60);
            TextField usuario = new TextField();

            usuario.setPromptText("Usuario");
            hb.getChildren().addAll(tfEtiqueta, usuario);
            contentRedSocial.getChildren().addAll(hb);
            ajustarAlturaVBox();
        }
    }

    private void ajustarAlturaVBox() {
        contentPrincipal.setPrefHeight(contentPrincipal.getPrefHeight() + 10); // Ajusta según sea necesario
    }

    @FXML
    private void regresar() throws IOException {
        App.setRoot("principalContactos");
    }

    @FXML
    private void agregarContacto(ActionEvent event) throws IOException {
        // Obtener la información de la interfaz gráfica        
        
        String empresaNombre = tfEmpresa.getText();

        // Crear una instancia de Contacto
        Empresa nuevoContacto = new Empresa();
        
        nuevoContacto.setNombre(empresaNombre);                  
       
        nuevoContacto.setFotos(fotos);  
        
        Boolean favorito = esFavorito.isSelected();
        
        nuevoContacto.setEsFavorito(favorito);
        
        // Agregar atributos (teléfonos, correos, direcciones, etc.) al contacto
        this.agregarAtributos(nuevoContacto);

        // Agregar el nuevo contacto al usuario logueado
        usuarioLogeado.addContacto(nuevoContacto);

        // Restablecer la interfaz gráfica o realizar otras acciones según sea necesario
        //limpiarInterfaz();
    }

    private void agregarAtributos(Contacto contacto) throws IOException {
        // Agregar teléfonos
        for (int i = 1; i < contentTelf.getChildren().size(); i++) {
            Node node = contentTelf.getChildren().get(i);
            if (node instanceof HBox) {
                HBox hbox = (HBox) node;
                TextField etiqueta = (TextField) hbox.getChildren().get(0);
                TextField telefono = (TextField) hbox.getChildren().get(1);

                // Validar que ambos campos no estén vacíos antes de agregar el teléfono
                if (!etiqueta.getText().isEmpty() && !telefono.getText().isEmpty()) {
                    contacto.addAtributo(new Atributo(etiqueta.getText(), telefono.getText(), Tipo.TELEFONO));
                }
            }
        }

        // Agregar correos
        for (int i = 1; i < contentCorreo.getChildren().size(); i++) {
            Node node = contentCorreo.getChildren().get(i);
            if (node instanceof HBox) {
                HBox hbox = (HBox) node;
                TextField etiqueta = (TextField) hbox.getChildren().get(0);
                TextField correo = (TextField) hbox.getChildren().get(1);

                // Validar que ambos campos no estén vacíos antes de agregar el correo
                if (!etiqueta.getText().isEmpty() && !correo.getText().isEmpty()) {
                    contacto.addAtributo(new Atributo(etiqueta.getText(), correo.getText(), Tipo.CORREO));
                }
            }
        }

        // Agregar direcciones
        for (int i = 1; i < contentDic.getChildren().size(); i++) {
            Node node = contentDic.getChildren().get(i);
            if (node instanceof HBox) {
                HBox hbox = (HBox) node;
                TextField etiqueta = (TextField) hbox.getChildren().get(0);
                TextField calle1 = (TextField) hbox.getChildren().get(1);

                // Agregar más campos de dirección según sea necesario
                // Validar que ambos campos no estén vacíos antes de agregar la dirección
                if (!etiqueta.getText().isEmpty() && !calle1.getText().isEmpty()) {
                    // Puedes crear una clase específica para representar direcciones y manejarla aquí
                    // Por ahora, solo se agrega un atributo con la etiqueta y la calle1
                    contacto.addAtributo(new Atributo(etiqueta.getText(), calle1.getText(), Tipo.DIRECCION));
                }
            }
        }

        // Agregar fechas
        for (int i = 1; i < contentFecha.getChildren().size(); i++) {
            Node node = contentFecha.getChildren().get(i);
            if (node instanceof HBox) {
                HBox hbox = (HBox) node;
                TextField etiqueta = (TextField) hbox.getChildren().get(0);
                DatePicker fecha = (DatePicker) hbox.getChildren().get(1);

                // Validar que ambos campos no estén vacíos antes de agregar la fecha
                if (!etiqueta.getText().isEmpty() && fecha.getValue() != null) {
                    // Puedes manejar la fecha según tus necesidades
                    // Por ahora, solo se agrega un atributo con la etiqueta y la fecha en formato String
                    contacto.addAtributo(new Atributo(etiqueta.getText(), fecha.getValue().toString(), Tipo.FECHA));
                }
            }
        }

        // Agregar redes sociales
        for (int i = 1; i < contentRedSocial.getChildren().size(); i++) {
            Node node = contentRedSocial.getChildren().get(i);
            if (node instanceof HBox) {
                HBox hbox = (HBox) node;
                TextField etiqueta = (TextField) hbox.getChildren().get(0);
                TextField usuarioRedSocial = (TextField) hbox.getChildren().get(1);

                // Validar que ambos campos no estén vacíos antes de agregar la red social
                if (!etiqueta.getText().isEmpty() && !usuarioRedSocial.getText().isEmpty()) {
                    contacto.addAtributo(new Atributo(etiqueta.getText(), usuarioRedSocial.getText(), Tipo.REDSOCIAL));
                }
            }
        }
        this.regresar();
        ManejoArchivos.guardarDatos(usuarioLogeado);
    }
}