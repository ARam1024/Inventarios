package com.example.inventarios.controllers;

import com.example.inventarios.HelloApplication;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PreloadViewController implements Initializable {
    @FXML
    public ImageView fondo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FadeTransition transition = new FadeTransition(Duration.millis(3000),fondo);
        transition.setFromValue(1.0);
        transition.setToValue(1.0);
        transition.play();

        transition.setOnFinished(event -> {
            Stage ventana = (Stage) fondo.getScene().getWindow();
            ventana.hide();
            Stage ventanaApp = new Stage();
            FXMLLoader fxmlLoader = null;
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(),1280,768);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ventanaApp.setScene(scene);
            ventanaApp.setTitle("Pantalla principal");
            ventanaApp.show();
        });
    }
}
