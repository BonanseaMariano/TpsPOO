package models;

public class Impresora implements Runnable {
  private String nombre;
  private ColaImpresion cola;

  public Impresora(String nombre, ColaImpresion cola) {
    this.nombre = nombre;
    this.cola = cola;
  }

  @Override
  public void run() {
    try {
      while (true) {
        Documento doc = cola.obtenerDocumento();
        int totalPaginas = doc.getCantidadHojas() * doc.getCantidadCopias();
        for (int i = 0; i < totalPaginas; i++) {
          Thread.sleep(1000); // Simula la impresión de una página por segundo
        }
        System.out.println(nombre + " terminó de imprimir: " + doc.getNombre());
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
