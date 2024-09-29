package models;

public class Pasajero implements Runnable {
  private final ParadaDeTaxis parada;
  private final int id;

  public Pasajero(int id, ParadaDeTaxis parada) {
    this.id = id;
    this.parada = parada;
  }

  @Override
  public void run() {
    try {
      System.out.println("Pasajero " + id + " esperando taxi.");
      Taxi taxi = parada.tomarTaxi();
      System.out.println("Pasajero " + id + " tomó el taxi " + taxi.getId() + ".");
      new Thread(taxi).start();
    } catch (InterruptedException e) {
      System.out.println("Pasajero " + id + " se retiró después de esperar.");
    }
  }
}
