package models;

import java.util.Date;
import java.util.Objects;

public class Empleado {
  private String nombre;
  private String apellido;
  private String documento;
  private Date fechaNacimiento;
  private double salario;

  public Empleado(
      String nombre, String apellido, String documento, Date fechaNacimiento, double salario) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.documento = documento;
    this.fechaNacimiento = fechaNacimiento;
    this.salario = salario;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getDocumento() {
    return documento;
  }

  public void setDocumento(String documento) {
    this.documento = documento;
  }

  public Date getFechaNacimiento() {
    return fechaNacimiento;
  }

  public void setFechaNacimiento(Date fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }

  public double getSalario() {
    return salario;
  }

  public void setSalario(double salario) {
    this.salario = salario;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Empleado empleado)) return false;
    return Objects.equals(documento, empleado.documento);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(documento);
  }

  @Override
  public String toString() {
    return "Empleado{"
        + "nombre='"
        + nombre
        + '\''
        + ", apellido='"
        + apellido
        + '\''
        + ", documento='"
        + documento
        + '\''
        + ", fechaNacimiento="
        + fechaNacimiento
        + ", salario="
        + salario
        + '}';
  }
}
