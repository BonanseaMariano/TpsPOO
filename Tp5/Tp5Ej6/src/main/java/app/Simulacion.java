package app;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import models.ParadaDeTaxis;
import models.Pasajero;
import models.Taxi;

public class Simulacion {
  public static void main(String[] args) {
    ParadaDeTaxis parada = new ParadaDeTaxis();
    ExecutorService executor = Executors.newCachedThreadPool();

    // Crear y agregar taxis a la parada
    for (int i = 1; i <= 5; i++) {
      Taxi taxi = new Taxi(i, parada);
      parada.agregarTaxi(taxi);
    }

    // Simular la llegada de pasajeros
    Random random = new Random();
    for (int i = 1; i <= 25; i++) {
      executor.execute(new Pasajero(i, parada));
      try {
        TimeUnit.MINUTES.sleep(random.nextInt(2) + 1);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        break;
      }
    }

    executor.shutdown();
  }
}
