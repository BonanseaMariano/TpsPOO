package models;

import java.util.ArrayList;
import java.util.List;

public class Empacadora implements Runnable {
  private CintaDeEmpaque cintaDeEmpaque;

  public Empacadora(CintaDeEmpaque cintaDeEmpaque) {
    this.cintaDeEmpaque = cintaDeEmpaque;
  }

  @Override
  public void run() {
    while (true) {
      List<Producto> productos = new ArrayList<>();
      try {
        for (int count = 1; count <= 6; count++) {
          Producto producto = cintaDeEmpaque.get();
          productos.add(producto);
          System.out.println("Empacadora saca producto: " + producto);
          Thread.sleep(1000); // 1 second per product
        }
        Thread.sleep(3000); // 3 seconds to pack the box
        for (Producto producto : productos) {
          System.out.println("Empacadora termina de empacar " + producto);
        }
      } catch (InterruptedException exception) {
        exception.printStackTrace();
      }
    }
  }
}
