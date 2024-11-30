package models;

import java.util.Random;

public class Productor implements Runnable {
  private final int MAX_DOCUMENTOS = 5;
  private final ColaImpresion cola;
  private static final int MAX_HOJAS = 10; // Valor máximo configurable para hojas
  private static final int MAX_COPIAS = 5; // Valor máximo configurable para copias
  private final Random random = new Random();

  public Productor(ColaImpresion cola) {
    this.cola = cola;
  }

  @Override
  public void run() {
    int cantidadDocumentos =
        random.nextInt(MAX_DOCUMENTOS) + 1; // Número aleatorio entre 1 y MAX_DOCUMENTOS
    try {
      for (int i = 0; i < cantidadDocumentos; i++) {
        int paginas = random.nextInt(MAX_HOJAS) + 1; // Número aleatorio entre 1 y MAX_HOJAS
        int copias = random.nextInt(MAX_COPIAS) + 1; // Número aleatorio entre 1 y MAX_COPIAS
        cola.agregarDocumento(new Documento("Doc" + i, paginas, copias));
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
