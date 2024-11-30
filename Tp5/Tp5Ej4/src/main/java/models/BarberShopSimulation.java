package models;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BarberShopSimulation {
  static final int MAX_CUSTOMERS = 30;

  public static void main(String[] args) {
    Random random = new Random();
    int clientCount = random.nextInt(MAX_CUSTOMERS) + 1;
    BarberShop shop = new BarberShop(3);
    Barber barber = new Barber(shop);
    ExecutorService executorService = Executors.newCachedThreadPool();

    executorService.execute(barber);

    for (int i = 0; i < clientCount; i++) {
      Customer customer = new Customer(shop);
      executorService.execute(customer);
      try {
        Thread.sleep(1000); // Simulate time between customer arrivals
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }

    executorService.shutdown();
    try {
      if (!executorService.awaitTermination(30, java.util.concurrent.TimeUnit.SECONDS)) {
        executorService.shutdownNow();
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
