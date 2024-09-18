package models;

public class ConversorUnidades {
  public static double fahrenheitToCelsius(double fahrenheit) {
    return (fahrenheit - 32) * 5 / 9;
  }

  public static double celsiusToFahrenheit(double celsius) {
    return (celsius * 9 / 5) + 32;
  }

  public static double celciusToKelvin(double celsius) {
    return celsius + 273.15;
  }

  public static double kelvinToCelsius(double kelvin) {
    return kelvin - 273.15;
  }

  public static double fahrenheitToKelvin(double fahrenheit) {
    return celciusToKelvin(fahrenheitToCelsius(fahrenheit));
  }

  public static double kelvinToFahrenheit(double kelvin) {
    return celsiusToFahrenheit(kelvinToCelsius(kelvin));
  }
}
