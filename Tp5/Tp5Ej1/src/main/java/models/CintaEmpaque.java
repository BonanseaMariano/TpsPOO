package models;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class CintaEmpaque {
  private final BlockingQueue<Producto> cintaEmpaque;

  public CintaEmpaque(int capacidad) {
    // Inicializa la cinta de empaque con la capacidad especificada
    this.cintaEmpaque = new ArrayBlockingQueue<>(capacidad);
  }

  // Método para agregar un producto a la cinta de empaque
  public void agregarProducto(Producto producto) throws InterruptedException {
    cintaEmpaque.put(producto);
    if (!producto.getNombre().equals("FIN")) {
      System.out.println(producto + " agregado a la cinta de empaque.");
    } else {
      System.out.println("Fin de producción señalizado.");
    }
  }

  // Método para retirar un producto de la cinta de empaque
  public Producto retirarProducto() throws InterruptedException {
    return cintaEmpaque.take();
  }
}
