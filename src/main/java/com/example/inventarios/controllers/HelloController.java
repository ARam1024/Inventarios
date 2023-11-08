package com.example.inventarios.controllers;

import com.example.inventarios.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML private FontIcon SuperiorIcon;
    @FXML private Label SenaladorLabel;
    @FXML private BorderPane bp;


    public void IniciarPantallaInicio(MouseEvent event){
        loadPage("inicio-view");
    }
    public void IniciarPantallaQoptima(MouseEvent event) {loadPage("eoq-view"); SenaladorLabel.setText("EOQ"); SuperiorIcon.setIconLiteral("bi-clipboard-data");}
    public void IniciarPantallaDescuentos(ActionEvent event) {
    }

    public void IniciarPantallaQproduccion(ActionEvent event) {
    }

    public void IniciarPantallaABC(ActionEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadPage("inicio-view");
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