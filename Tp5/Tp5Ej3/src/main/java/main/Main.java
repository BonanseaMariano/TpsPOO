package main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import models.ReadWriteLock;

public class Main {
  public static void main(String[] args) {
    ReadWriteLock lock = new ReadWriteLock();
    ExecutorService executor = Executors.newFixedThreadPool(12);

    // Crear y ejecutar 10 hilos de lectura
    for (int i = 0; i < 10; i++) {
      executor.execute(
          () -> {
            try {
              lock.lockRead();
              System.out.println(Thread.currentThread().getName() + " comenzo a leer");
              Thread.sleep((long) (3000 + Math.random() * 2000));
              System.out.println(Thread.currentThread().getName() + " termino de leer");
              lock.unlockRead();
            } catch (InterruptedException e) {
              Thread.currentThread().interrupt();
            }
          });
    }

    // Crear y ejecutar 2 hilos de escritura
    for (int i = 0; i < 2; i++) {
      executor.execute(
          () -> {
            try {
              lock.lockWrite();
              System.out.println(Thread.currentThread().getName() + " comenzo a escribir");
              Thread.sleep((long) (3000 + Math.random() * 2000));
              System.out.println(Thread.currentThread().getName() + " termino de escribir");
              lock.unlockWrite();
            } catch (InterruptedException e) {
              Thread.currentThread().interrupt();
            }
          });
    }

    executor.shutdown();
    try {
      executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
