package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ConversorUnidadesController implements Initializable {

  @javafx.fxml.FXML private TextField tf2;
  @javafx.fxml.FXML private TextField tf1;
  @javafx.fxml.FXML private ComboBox<String> cb2;
  @javafx.fxml.FXML private ComboBox<String> cb1;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    // Define los valores para los ComboBox
    String[] unidades = {"Celsius", "Fahrenheit", "Kelvin"};

    // Carga los valores en los ComboBox
    cb1.getItems().addAll(unidades);
    cb2.getItems().addAll(unidades);
  }

  @javafx.fxml.FXML
  public void conversionA() {
    try {
      double valor = Double.parseDouble(tf1.getText());
      String unidad1 = cb1.getValue();
      String unidad2 = cb2.getValue();

      double resultado = 0;

      switch (unidad1 + " a " + unidad2) {
        case "Celsius a Fahrenheit":
          resultado = models.ConversorUnidades.celsiusToFahrenheit(valor);
          break;
        case "Celsius a Kelvin":
          resultado = models.ConversorUnidades.celciusToKelvin(valor);
          break;
        case "Fahrenheit a Celsius":
          resultado = models.ConversorUnidades.fahrenheitToCelsius(valor);
          break;
        case "Fahrenheit a Kelvin":
          resultado = models.ConversorUnidades.fahrenheitToKelvin(valor);
          break;
        case "Kelvin a Celsius":
          resultado = models.ConversorUnidades.kelvinToCelsius(valor);
          break;
        case "Kelvin a Fahrenheit":
          resultado = models.ConversorUnidades.kelvinToFahrenheit(valor);
          break;
        default:
          return;
      }

      tf2.setText(String.valueOf(resultado));
    } catch (Exception e) {
    }
  }

  @javafx.fxml.FXML
  public void conversionDesde() {
    try {
      double valor = Double.parseDouble(tf2.getText());
      String unidad1 = cb1.getValue();
      String unidad2 = cb2.getValue();

      double resultado = 0;

      switch (unidad2 + " a " + unidad1) {
        case "Celsius a Fahrenheit":
          resultado = models.ConversorUnidades.celsiusToFahrenheit(valor);
          break;
        case "Celsius a Kelvin":
          resultado = models.ConversorUnidades.celciusToKelvin(valor);
          break;
        case "Fahrenheit a Celsius":
          resultado = models.ConversorUnidades.fahrenheitToCelsius(valor);
          break;
        case "Fahrenheit a Kelvin":
          resultado = models.ConversorUnidades.fahrenheitToKelvin(valor);
          break;
        case "Kelvin a Celsius":
          resultado = models.ConversorUnidades.kelvinToCelsius(valor);
          break;
        case "Kelvin a Fahrenheit":
          resultado = models.ConversorUnidades.kelvinToFahrenheit(valor);
          break;
        default:
          return;
      }

      tf1.setText(String.valueOf(resultado));
    } catch (Exception e) {
    }
  }
}
