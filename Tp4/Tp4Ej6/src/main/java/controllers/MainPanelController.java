package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPanelController implements Initializable {

    @FXML
    private StackPane contentPane;

    @FXML
    private void showCargaDatos() {
        loadView("/views/CargaDatos.fxml");
    }

    @FXML
    private void showTablaEmpleados() {
        loadView("/views/TablaEmpleados.fxml");
    }

    private void loadView(String fxmlPath) {
        try {
            Node view = FXMLLoader.load(getClass().getResource(fxmlPath));
            contentPane.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showCargaDatos(); // Cargar CargaDatos por defecto
    }
}