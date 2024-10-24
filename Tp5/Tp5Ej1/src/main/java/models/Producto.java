package models;

public class Producto {
  private String nombre;
  private String descripcion;

  // Constructor
  public Producto(String nombre, String descripcion) {
    this.nombre = nombre;
    this.descripcion = descripcion;
  }

  // Getters
  public String getNombre() {
    return nombre;
  }

  public String getDescripcion() {
    return descripcion;
  }

  // Setters
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  // Método toString para imprimir el producto de forma legible
  @Override
  public String toString() {
    return "Producto: " + nombre + " | Descripción: " + descripcion;
  }
}
