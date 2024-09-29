package models;

import java.util.LinkedList;
import java.util.Queue;

public class ParadaDeTaxis {
  private final Queue<Taxi> taxis = new LinkedList<>();

  public synchronized void agregarTaxi(Taxi taxi) {
    taxis.add(taxi);
    notifyAll();
  }

  public synchronized Taxi tomarTaxi() throws InterruptedException {
    while (taxis.isEmpty()) {
      wait();
    }
    return taxis.poll();
  }

  public synchronized void regresarTaxi(Taxi taxi) {
    taxis.add(taxi);
    notifyAll();
    System.out.println("Taxi " + taxi.getId() + " ha regresado a la parada.");
  }
}
