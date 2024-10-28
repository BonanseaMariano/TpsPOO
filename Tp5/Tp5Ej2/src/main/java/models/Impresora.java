package models;

import java.util.concurrent.TimeUnit;

public class Impresora implements Runnable {
  private final ColaImpresion cola;
  private final String nombre;

  public Impresora(ColaImpresion cola, String nombre) {
    this.cola = cola;
    this.nombre = nombre;
  }

  @Override
  public void run() {
    try {
      Documento doc = cola.obtenerDocumento();
      while (doc != null) {
        imprimirDocumento(doc);
        doc = cola.obtenerDocumento();
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  private void imprimirDocumento(Documento doc) throws InterruptedException {
    for (int i = 0; i < doc.getCopias(); i++) {
      for (int j = 0; j < doc.getPaginas(); j++) {
        TimeUnit.SECONDS.sleep(1); // Simular impresión de página cada segundo
        System.out.println(
            nombre
                + " está imprimiendo página "
                + (j + 1)
                + " de "
                + doc.getNombre()
                + " (copia "
                + (i + 1)
                + ")");
      }
    }
    System.out.println(nombre + " terminó de imprimir " + doc.getNombre());
  }
}
