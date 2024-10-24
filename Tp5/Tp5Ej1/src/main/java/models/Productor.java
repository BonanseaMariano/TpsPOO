package models;

import java.util.concurrent.ThreadLocalRandom;

public class Productor implements Runnable {
  private final String nombreEquipo;
  private final CintaEmpaque cintaEmpaque;

  public Productor(String nombreEquipo, CintaEmpaque cintaEmpaque) {
    this.nombreEquipo = nombreEquipo;
    this.cintaEmpaque = cintaEmpaque;
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
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }

    // Señal de que el equipo ha terminado de producir
    try {
      cintaEmpaque.agregarProducto(
          new Producto("FIN", "")); // Usamos producto FIN como señal de fin
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
