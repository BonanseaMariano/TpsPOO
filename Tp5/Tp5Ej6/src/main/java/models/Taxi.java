package models;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Taxi implements Runnable {
  private final ParadaDeTaxis parada;
  private final int id;
  private final Random random = new Random();

  public Taxi(int id, ParadaDeTaxis parada) {
    this.id = id;
    this.parada = parada;
  }

  @Override
  public void run() {
    while (true) {
      try {
        // Simula el tiempo de viaje con un pasajero
        int travelTime = random.nextInt(8) + 1;
        System.out.println(
            "Taxi " + id + " llevando pasajero. Tiempo de viaje: " + travelTime + " minutos.");
        TimeUnit.MINUTES.sleep(travelTime);

        // Simula el tiempo de regreso vacío
        System.out.println(
            "Taxi " + id + " regresando vacío. Tiempo de regreso: " + travelTime + " minutos.");
        TimeUnit.MINUTES.sleep(travelTime);

        // Taxi regresa a la parada
        parada.regresarTaxi(this);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        break;
      }
    }
  }

  public int getId() {
    return id;
  }
}
