package models;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
  public static void main(String[] args) {
    SharedResource resource = new SharedResource();
    ReadWriteLock lock = new ReadWriteLock();
    ExecutorService executor = Executors.newFixedThreadPool(12);

    // Create 10 reader threads
    for (int i = 0; i < 10; i++) {
      executor.submit(new Reader(resource, lock, i));
    }

    // Create 2 writer threads
    for (int i = 0; i < 2; i++) {
      executor.submit(new Writer(resource, lock, i));
    }

    // Shutdown the executor
    // Initiates an orderly shutdown of the executor service.
    executor.shutdown();
    try {
      // Waits for the executor service to terminate within the given timeout.
      if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
        // If the executor service did not terminate in the specified time, force shutdown.
        System.out.println("Simulacion terminada");
        executor.shutdownNow();
      }
    } catch (InterruptedException e) {
      // If the current thread is interrupted while waiting, re-interrupt the current thread.
      Thread.currentThread().interrupt();
    }
  }
}
