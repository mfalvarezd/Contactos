/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.app.contactos;

//import com.espol.app.contactos.utilidades.List;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author mfalvarez
 */
public class AggContactsController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        tfNombre.setOnKeyPressed(event -> {
            if (event.getCode().toString().equals("TAB")) {
                tfEmpresa.setDisable(true);
                if (tfNombre.getText().length() == 0 && tftApellido.getText().length() == 0) {
                    tfEmpresa.setDisable(false);
                }
                tftApellido.requestFocus(); // Mover el foco al siguiente TextField
                event.consume(); // Consumir el evento para evitar que el Tab se inserte en el TextField
            }
        });
        tftApellido.setOnKeyPressed(event -> {
            if (event.getCode().toString().equals("TAB")) {
                tfEmpresa.setDisable(true);
                if (tfNombre.getText().length() == 0 && tftApellido.getText().length() == 0) {
                    tfEmpresa.setDisable(false);
                }

            }
        });
        tfEmpresa.setOnKeyPressed(event -> {
            if (event.getCode().toString().equals("TAB")) {
                tfNombre.setText("");
                tftApellido.setText("");
                tfNombre.setDisable(true);

                tftApellido.setDisable(true);
                if (tfEmpresa.getText().length() == 0) {
                    tfNombre.setDisable(false);
                    tftApellido.setDisable(false);
                }

            }
        });

    }

    @FXML
    private void ingresoEmpresa(ActionEvent event) {

        if (tfEmpresa.getText().length() > 0) {
            tfNombre.setText("");
            tftApellido.setText("");
            tfNombre.setDisable(true);

            tftApellido.setDisable(true);

        } else if (tfEmpresa.getText().length() == 0) {
            tfNombre.setDisable(false);
            tftApellido.setDisable(false);
        }
    }

    @FXML
    private void ingresoNombre(ActionEvent event) {
        if (tftApellido.getText().length() > 0 || tfNombre.getText().length() > 0) {
            tfEmpresa.setDisable(true);
        } else if (tftApellido.getText().length() == 0 && tfNombre.getText().length() == 0) {
            tfEmpresa.setDisable(false);
        }
    }

    @FXML
    private void ingresoApellido(ActionEvent event) {
        if (tftApellido.getText().length() > 0 || tfNombre.getText().length() > 0) {
            tfEmpresa.setDisable(true);
        } else if (tftApellido.getText().length() == 0 && tfNombre.getText().length() == 0) {
            tfEmpresa.setDisable(false);
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
            TextField calle2 = new TextField();
            calle2.setPromptText("Calle 2");
            TextField calleP = new TextField();
            calleP.setPromptText("Calle principal");
            TextField ciudad = new TextField();
            ciudad.setPromptText("Ciudad");
            TextField pais = new TextField();
            pais.setPromptText("Pais");
            HBox hb1= new HBox(new Label("Pais "),pais);
            
            
//            // Obtener una lista de códigos de países
//            String[] countryCodes = Locale.getISOCountries();
//
//            // Crear una lista de países ordenada alfabéticamente
//            List<String> countryList = Arrays.asList(countryCodes);
//            Collections.sort(countryList);

            // Crear el ComboBox y establecer la lista de países
////            ComboBox<String> countryComboBox = new ComboBox<>(FXCollections.observableArrayList(countryList));

//            telefono.setPromptText("Telefono");
            hb.getChildren().addAll(tfEtiqueta, calle1);
            contentDic.getChildren().addAll(hb,calle2,calleP,ciudad,hb1);

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
        contentPrincipal.setPrefHeight(contentPrincipal.getPrefHeight() + 50); // Ajusta según sea necesario
    }

}
