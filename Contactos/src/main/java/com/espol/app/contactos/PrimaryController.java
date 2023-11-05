package com.espol.app.contactos;

import com.espol.app.contactos.utilidades.ManejoArchivos;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PrimaryController implements Initializable{    
    @FXML
    private Button ingresar;        
    @FXML
    private ImageView logo;
    @FXML
    private TextField tfusuario;
    @FXML
    private Label mensaje;
    @FXML
    private Button registrarseButton;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private VBox pContenido;
    @FXML
    private HBox buttonBox;
    
    @FXML
    private void ingresar() throws IOException {
        String usuario = tfusuario.getText();
        String password = pfPassword.getText();
        System.out.println(usuario+password);
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

    @FXML
    private void registrarse(ActionEvent event) {
        pContenido.getChildren().clear();
        
        pContenido.setSpacing(10);
        buttonBox.getChildren().clear();
        Label lblRegistro = new Label("Registro");

        Label lblUsuario = new Label("Ingrese un Usuario:");
        TextField tFuser = new TextField();
        tFuser.setMaxWidth(250);

        Label lblPassword = new Label("Ingrese una Contraseña:");
        TextField tFpassword = new TextField();
        PasswordField pw = new PasswordField();
        pw.setMaxWidth(250);
        Label lblpw = new Label("Ingrese nuevamente la Contraseña");
        PasswordField pw2 = new PasswordField();
        pw2.setMaxWidth(250);
        Label lblmr = new Label();
        lblmr.setVisible(false);
        Button btnRegistrarse = new Button("Registrarse");
        Label lblNombre = new Label("Ingrese un Nombre y Apellido");
        TextField tfNombre = new TextField();
        tfNombre.setMaxWidth(250);
        pContenido.getChildren().addAll(lblRegistro, lblUsuario, tFuser, lblPassword, pw, lblpw, pw2, lblNombre, tfNombre, lblmr, btnRegistrarse);
        Button btnSalir = new Button("Salir");
        Button btnVolver = new Button("Volver");
        btnRegistrarse.setOnAction(ev -> {
            if (ManejoArchivos.validarUsuario(tFuser.getText())) {
                lblmr.setVisible(true);
                lblmr.setText("El usuario ya existe, intente otro nombre de usuario");

            } else {
                if (tFuser.getText().isBlank() || pw.getText().isBlank()) {
                    lblmr.setVisible(true);
                    lblmr.setText("Por favor, complete todos los recuadros");
                } else {
                    if (pw.getText().equals(pw2.getText())) {
                        lblmr.setVisible(true);
                        lblmr.setText("Usuario registrado exitosamente");
                        ManejoArchivos.agregarUsuario(tFuser.getText(), pw.getText(), tfNombre.getText());

                    } else {
                        lblmr.setVisible(true);
                        lblmr.setText("Las contraseñas no coinciden");

                    }

                }

            }

        });
        btnVolver.setOnAction(ev -> {
            try {
                App.setRoot("primary");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        btnSalir.setOnAction(ev -> System.exit(0));
        buttonBox.getChildren().addAll(btnSalir, btnVolver);

    }
}
