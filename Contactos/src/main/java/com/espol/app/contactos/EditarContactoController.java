/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.app.contactos;

import com.espol.app.contactos.modelo.Atributo;
import com.espol.app.contactos.modelo.Contacto;
import com.espol.app.contactos.modelo.Empresa;
import com.espol.app.contactos.modelo.Etiqueta;
import com.espol.app.contactos.modelo.Foto;
import com.espol.app.contactos.modelo.Persona;
import com.espol.app.contactos.modelo.Tipo;
import static com.espol.app.contactos.modelo.Tipo.CORREO;
import static com.espol.app.contactos.modelo.Tipo.DIRECCION;
import static com.espol.app.contactos.modelo.Tipo.FECHA;
import static com.espol.app.contactos.modelo.Tipo.REDSOCIAL;
import static com.espol.app.contactos.modelo.Tipo.TELEFONO;
import com.espol.app.contactos.modelo.Usuario;
import com.espol.app.contactos.modelo.UsuarioSingleton;
import com.espol.app.contactos.utilidades.List;
import com.espol.app.contactos.utilidades.ManejoArchivos;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
public class EditarContactoController implements Initializable {
    @FXML
    private VBox contentPrincipal;    
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
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tftApellido;
    @FXML
    private VBox contentTelf;
    @FXML
    private VBox contentCorreo;
    @FXML
    private VBox contentDic;
    @FXML
    private VBox contentFecha;        
    @FXML
    private Button btnTelf;
    @FXML
    private Button btnCorreo;
    @FXML
    private Button btnDic;
    @FXML
    private Button btnFecha;
    @FXML
    private CheckBox esFavorito;
    @FXML
    private VBox contentRedSocial;
    @FXML
    private Button btnRedSocial;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox default3;
    @FXML
    private ComboBox<Etiqueta> boxEtiqueta;
    
    private int MAXTELF = 3;
    private int MAXCORREO = 3;
    private int MAXDIC = 3;
    private int MAXFECHA = 3;
    private int MAXREDSOCIAL = 3;    
    
    private Usuario usuarioLogeado;
    
    static Contacto contacto;
    List<Foto> fotos = contacto.copy();
    
    Foto fotoActual = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuarioLogeado = UsuarioSingleton.getInstancia();                
        boxEtiqueta.getItems().add(Etiqueta.Amigos);
        boxEtiqueta.getItems().add(Etiqueta.Familia);
        boxEtiqueta.getItems().add(Etiqueta.Trabajo);        
                
        if (fotos.size()>0) {
            fotoActual = fotos.get(0);
            Image imagen = new Image(fotoActual.getUrl());
            fotoView.setImage(imagen);
        }
                
        nextFoto.setOnAction(ec -> {
            if (fotoActual != null){
                fotoActual = fotos.getNext(fotoActual);
                Image imagenNext = new Image(fotoActual.getUrl());
                fotoView.setImage(imagenNext);
            }
        });

        farmerFoto.setOnAction(eh -> {
            if (fotoActual != null){
                fotoActual = fotos.getPrevious(fotoActual);            
                Image imagenNext = new Image(fotoActual.getUrl());
                fotoView.setImage(imagenNext);
            }
        });
                
        btnEliminarFoto.setOnAction(eh ->{
            if (fotoActual != null){
                Foto eliminar = fotoActual;
                //ARREGLA LOGICA PARA CUANDO SE ELIIMINE LA ÚLTIMA FOTO
                fotoActual = fotos.getNext(fotoActual);
                Image imagenNext = new Image(fotoActual.getUrl());
                fotoView.setImage(imagenNext); 
                fotos.remove(eliminar);
            }
        });        
        
        btnAgregarFoto.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent eh) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg"));
                
                File archivoSeleccionado = fileChooser.showOpenDialog(null);
                                
                if (archivoSeleccionado != null) {
                    String url = archivoSeleccionado.toURI().toString();                    
                    fotoView.setImage(new Image(url));                                        
                    Foto f = new Foto (url);            
                    fotos.add(f);
                    fotoActual = f;
                }
            }
        });
        
        this.cargarDatos();
    }
    
    private void cargarDatos(){
        System.out.println(contacto.isEsEmpresa());
        if (!contacto.isEsEmpresa()){
            Persona persona = (Persona) contacto;       
            System.out.println("-----"+persona.getNombre());
            tfNombre.setText(persona.getNombre());
            default3.setPrefHeight(64);
            tftApellido = new TextField(persona.getApellidos());
            default3.getChildren().add(tftApellido);
        } else {
            Empresa empresa = (Empresa) contacto;
            tfNombre.setText(empresa.getNombre());
        }
        
        esFavorito.setSelected(contacto.isEsFavorito());
        
        List<Atributo> atributos = contacto.getAtributos();

        for (Atributo at : atributos) {
            HBox atributosBox = new HBox();

            Tipo tipo = at.getTipo();
            String etiqueta;
            String valor;
            Label atributo;

            if (null != tipo) {
                switch (tipo) {
                    case TELEFONO:
                        this.aggTelfContacto(at);
                        break;
                    case REDSOCIAL:
                        this.aggRedSocialContacto(at);
                        break;
                    case FECHA:
                        this.aggFechaContacto(at);
                        break;
                    case DIRECCION:
                        this.aggDicContacto(at);
                        break;
                    case CORREO:
                        this.aggCorreoContacto(at);
                        break;
                    default:
                        break;
                }
            }
        }                        
    }
    
    @FXML
    private void regresar() throws IOException {
        ManejoArchivos.guardarDatos(usuarioLogeado);
        Platform.runLater(() -> {
            try {
                App.setRoot("principalContactos");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }    
    
    @FXML
    private void guardarContacto() throws IOException {
        if (fotos.size()<2) {
            this.alerta();
        } else {        
            if (!contacto.isEsEmpresa()) {
                Persona persona = (Persona) contacto;
                String nombre = tfNombre.getText();
                System.out.println("-----"+nombre);
                String apellidos = tftApellido.getText();
                persona.setNombre(nombre);
                persona.setApellidos(apellidos);
            } else {
                Empresa empresa = (Empresa) contacto;
                String nombre = tfNombre.getText();
                empresa.setNombre(nombre);
            }   

            Etiqueta etiqueta = boxEtiqueta.getValue();
            contacto.setEtiqueta(etiqueta);            
            
            contacto.setFotos(fotos);       

            Boolean favorito = esFavorito.isSelected();

            contacto.setEsFavorito(favorito);

            // Agregar atributos (teléfonos, correos, direcciones, etc.) al contacto
            contacto.getAtributos().clear();
            this.agregarAtributos(contacto);

            this.regresar();
        }
    }
    
    @FXML
    private void aggTelf() throws IOException {
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
    
    private void aggTelfContacto(Atributo at) {
        if (MAXTELF > 0) {
            MAXTELF--;
            
            HBox hb = new HBox();
            hb.setSpacing(10);
            
            TextField tfEtiqueta = new TextField();
            tfEtiqueta.setText(at.getNombre());
            tfEtiqueta.setMaxWidth(60);
            
            TextField telefono = new TextField();
            telefono.setText(at.getValor());
            
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

    private void aggCorreoContacto(Atributo at) {
        if (MAXCORREO > 0) {            
            MAXCORREO--;
            
            HBox hb = new HBox();
            hb.setSpacing(10);
            
            TextField tfEtiqueta = new TextField();
            tfEtiqueta.setText(at.getNombre());
            tfEtiqueta.setMaxWidth(60);
            
            TextField correo = new TextField();
            correo.setText(at.getValor());
            
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
    
    private void aggDicContacto(Atributo at) {
        if (MAXDIC > 0) {
            MAXDIC--;
            
            HBox hb = new HBox();
            hb.setSpacing(10);
            
            TextField tfEtiqueta = new TextField();
            tfEtiqueta.setText(at.getNombre());
            tfEtiqueta.setMaxWidth(60);
            
            TextField calle1 = new TextField();
            calle1.setText(at.getValor());

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
    
    private void aggFechaContacto(Atributo at) {
        if (MAXFECHA > 0) {
            MAXFECHA--;
            
            HBox hb = new HBox();
            hb.setSpacing(10);
            
            TextField tfEtiqueta = new TextField();
            tfEtiqueta.setText(at.getNombre());
            tfEtiqueta.setMaxWidth(60);
            
            DatePicker fecha = new DatePicker();
            String[] f = at.getValor().split("-");
            LocalDate fechaPorDefecto = LocalDate.of(Integer.parseInt(f[0]), Integer.parseInt(f[1]), Integer.parseInt(f[2]));
            fecha.setValue(fechaPorDefecto);
            
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
    
    private void aggRedSocialContacto(Atributo at) {
        if (MAXREDSOCIAL > 0) {
            MAXREDSOCIAL--;
            
            HBox hb = new HBox();
            hb.setSpacing(10);
            
            TextField tfEtiqueta = new TextField();
            tfEtiqueta.setText(at.getNombre());
            tfEtiqueta.setMaxWidth(60);
            
            TextField usuario = new TextField();
            usuario.setText(at.getValor());
            
            hb.getChildren().addAll(tfEtiqueta, usuario);
            
            contentRedSocial.getChildren().addAll(hb);
            
            ajustarAlturaVBox();
        }
    }    

    private void ajustarAlturaVBox() {
        contentPrincipal.setPrefHeight(contentPrincipal.getPrefHeight() + 20); // Ajusta según sea necesario
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

    }    

    private void alerta() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Advertencia!!");
        alert.setHeaderText(null);
        alert.setContentText("Debe ingresar por lo menos dos fotos");       
        alert.showAndWait();
    }     
}