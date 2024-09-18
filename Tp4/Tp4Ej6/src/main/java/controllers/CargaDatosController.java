package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import managers.StageManager;
import models.Empleado;

public class CargaDatosController implements Initializable {

  @FXML private TextField nombreField;

  @FXML private TextField apellidoField;

  @FXML private TextField documentoField;

  @FXML private DatePicker fechaNacimientoPicker;

  @FXML private TextField salarioField;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    validacionNombre();
    validacionApellido();
    validacionDNI();
    validacionSalario();
  }

  @FXML
  private void handleAgregar() {
    List<String> camposFaltantes = new ArrayList<>();

    if (nombreField.getText().isEmpty()) {
      camposFaltantes.add("Nombre");
    }
    if (apellidoField.getText().isEmpty()) {
      camposFaltantes.add("Apellido");
    }
    if (documentoField.getText().isEmpty()) {
      camposFaltantes.add("Documento");
    }
    if (fechaNacimientoPicker.getValue() == null) {
      camposFaltantes.add("Fecha de Nacimiento");
    }
    if (salarioField.getText().isEmpty()) {
      camposFaltantes.add("Salario");
    }

    if (!camposFaltantes.isEmpty()) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Campos incompletos");
      alert.setContentText(
          "Por favor, complete los siguientes campos: " + String.join(", ", camposFaltantes));
      alert.showAndWait();
      return;
    }

    String nombre = nombreField.getText();
    String apellido = apellidoField.getText();
    String documento = documentoField.getText();
    java.util.Date fechaNacimiento = java.sql.Date.valueOf(fechaNacimientoPicker.getValue());
    // El único caso inválido posible para el salario es que solo se coloque un . por eso se asigna
    // un 0
    double salario = 0;
    try {
      salario = Double.parseDouble(salarioField.getText());
    } catch (NumberFormatException e) {
    }

    Empleado empleado = new Empleado(nombre, apellido, documento, fechaNacimiento, salario);
    // Logic to handle the new Empleado object
    System.out.println(empleado);
    StageManager.agregarEmpleado(empleado);
  }

  @FXML
  public void validacionDNI() {
    documentoField
        .textProperty()
        .addListener(
            new ChangeListener<String>() {
              @Override
              public void changed(
                  ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("[0-9.]*")) {
                  documentoField.setText(newValue.replaceAll("[^0-9.]", ""));
                }
              }
            });
  }

  @FXML
  public void validacionApellido() {
    apellidoField
        .textProperty()
        .addListener(
            new ChangeListener<String>() {
              @Override
              public void changed(
                  ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("[a-zA-Z]*")) {
                  apellidoField.setText(newValue.replaceAll("[^a-zA-Z]", ""));
                }
              }
            });
  }

  @FXML
  public void validacionNombre() {
    nombreField
        .textProperty()
        .addListener(
            new ChangeListener<String>() {
              @Override
              public void changed(
                  ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("[a-zA-Z]*")) {
                  nombreField.setText(newValue.replaceAll("[^a-zA-Z]", ""));
                }
              }
            });
  }

  @FXML
  public void validacionSalario() {
    salarioField
        .textProperty()
        .addListener(
            new ChangeListener<String>() {
              @Override
              public void changed(
                  ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("[0-9.]*")) {
                  salarioField.setText(newValue.replaceAll("[^0-9.]", ""));
                }
              }
            });
  }
}
