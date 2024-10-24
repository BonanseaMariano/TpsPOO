package app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import models.ColaImpresion;
import models.Impresora;
import models.Productor;

public class SimulacionImpresion {

  public static void main(String[] args) throws InterruptedException {
    // Crear la cola de impresión con capacidad para 5 documentos
    ColaImpresion colaImpresion = new ColaImpresion(5);

    // Crear las dos impresoras
    Impresora impresora1 = new Impresora("Impresora 1", colaImpresion);
    Impresora impresora2 = new Impresora("Impresora 2", colaImpresion);

    // Crear el productor de documentos
    Productor productor = new Productor(colaImpresion);

    // Crear un pool de hilos con CachedThreadPool
    ExecutorService executor = Executors.newCachedThreadPool();

    // Ejecutar las impresoras
    executor.execute(impresora1);
    executor.execute(impresora2);

    // Ejecutar el productor
    executor.execute(productor);

    // Cerrar el executor
    executor.shutdown();
  }
}
