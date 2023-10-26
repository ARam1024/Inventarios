module com.example.inventarios {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.inventarios to javafx.fxml;
    exports com.example.inventarios;
    exports com.example.inventarios.controllers;
    opens com.example.inventarios.controllers to javafx.fxml;
    exports com.example.inventarios.auxiliar_class;
    opens com.example.inventarios.auxiliar_class to javafx.fxml;
}