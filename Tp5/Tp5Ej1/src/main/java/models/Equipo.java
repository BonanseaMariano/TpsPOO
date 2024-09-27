package models;

import java.util.Random;

public class Equipo implements Runnable {
  private String nombre;
  private CintaDeEmpaque cintaDeEmpaque;
  private static final Random generator = new Random();

  public Equipo(CintaDeEmpaque cintaDeEmpaque, String nombre) {
    this.cintaDeEmpaque = cintaDeEmpaque;
    this.nombre = nombre;
  }

  @Override
  public void run() {
    for (int count = 1; count <= 24; count++) {
      try {
        Thread.sleep(generator.nextInt(1000) + 2000); // 2 to 3 seconds
        Producto producto = new Producto(nombre, count);
        cintaDeEmpaque.set(producto);
        System.out.println(nombre + " ingresa producto: " + producto);
      } catch (InterruptedException exception) {
        exception.printStackTrace();
      }
    }
  }
}
