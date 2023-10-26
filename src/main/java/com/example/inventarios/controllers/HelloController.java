package com.example.inventarios.controllers;

import com.example.inventarios.HelloApplication;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private AnchorPane ap;
    @FXML
    private BorderPane bp;
    /*@FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }*/

    @FXML
    public void IniciarPantallaQoptima(ActionEvent event) {
        loadPage("eoq-view");
    }

    public void IniciarPantallaDescuentos(ActionEvent event) {
    }

    public void IniciarPantallaQproduccion(ActionEvent event) {
    }

    public void IniciarPantallaABC(ActionEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void loadPage(String page) {
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(page+".fxml"));;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        bp.setCenter(root);
    }

}