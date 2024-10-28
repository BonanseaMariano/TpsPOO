package models;

public class Documento {
  private final String nombre;
  private final int paginas;
  private final int copias;

  public Documento(String nombre, int paginas, int copias) {
    this.nombre = nombre;
    this.paginas = paginas;
    this.copias = copias;
  }

  public String getNombre() {
    return nombre;
  }

  public int getPaginas() {
    return paginas;
  }

  public int getCopias() {
    return copias;
  }
}
