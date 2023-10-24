module com.example.inventarios {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.inventarios to javafx.fxml;
    exports com.example.inventarios;
}