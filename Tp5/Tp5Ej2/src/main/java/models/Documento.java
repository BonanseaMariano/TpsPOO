package models;

public class Documento {
  private String nombre;
  private int cantidadHojas;
  private int cantidadCopias;

  public Documento(String nombre, int cantidadHojas, int cantidadCopias) {
    this.nombre = nombre;
    this.cantidadHojas = cantidadHojas;
    this.cantidadCopias = cantidadCopias;
  }

  public String getNombre() {
    return nombre;
  }

  public int getCantidadHojas() {
    return cantidadHojas;
  }

  public int getCantidadCopias() {
    return cantidadCopias;
  }
}
