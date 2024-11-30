package models;

import java.util.concurrent.CountDownLatch;

public class Empacadora implements Runnable {
  private final CintaEmpaque cintaEmpaque;
  private final CountDownLatch latch;

  public Empacadora(CintaEmpaque cintaEmpaque, CountDownLatch latch) {
    this.cintaEmpaque = cintaEmpaque;
    this.latch = latch;
  }

  @Override
  public void run() {
    try {
      while (latch.getCount() > 0) {
        Producto producto = cintaEmpaque.retirarProducto(); // Usar método de la clase CintaEmpaque
        // Empacar el producto
        System.out.println("Empacadora empacó " + producto);
        Thread.sleep(1000); // Empacar un producto cada segundo
      }
      latch.await(); // Esperar a que todos los equipos terminen
      System.out.println("Todos los equipos han finalizado. Empacadora se detiene.");
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
