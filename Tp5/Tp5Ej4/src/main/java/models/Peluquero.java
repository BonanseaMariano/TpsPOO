package models;

public class Peluquero implements Runnable {
  private final Peluqueria peluqueria;

  public Peluquero(Peluqueria peluqueria) {
    this.peluqueria = peluqueria;
  }

  @Override
  public void run() {
    try {
      while (true) {
        peluqueria.cortarCabelloCliente();
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
