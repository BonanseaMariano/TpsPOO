package models;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

public class Productor implements Runnable {
  private final String nombreEquipo;
  private final CintaEmpaque cintaEmpaque;
  private final CountDownLatch latch;

  public Productor(String nombreEquipo, CintaEmpaque cintaEmpaque, CountDownLatch latch) {
    this.nombreEquipo = nombreEquipo;
    this.cintaEmpaque = cintaEmpaque;
    this.latch = latch;
  }

  @Override
  public void run() {
    int totalProductos =
        ThreadLocalRandom.current().nextInt(5, 10); // Número aleatorio de productos
    for (int i = 1; i <= totalProductos; i++) {
      try {
        // Esperar entre 2 y 3 segundos antes de agregar un producto
        int tiempoProduccion = ThreadLocalRandom.current().nextInt(2000, 3000);
        Thread.sleep(tiempoProduccion);

        // Crear un nuevo producto con nombre y descripción
        Producto producto =
            new Producto(
                nombreEquipo + " - Producto " + i,
                "Este es el producto número " + i + " producido por " + nombreEquipo);

        cintaEmpaque.agregarProducto(producto); // Usar el método de la clase CintaEmpaque
        System.out.println(nombreEquipo + " produjo " + producto);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
    latch.countDown(); // Señal de que el equipo ha terminado de producir
  }
}
