module com.espol.app.contactos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.espol.app.contactos to javafx.fxml;
    exports com.espol.app.contactos;
}
