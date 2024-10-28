package simulacion;

import models.ColaImpresion;
import models.Productor;
import models.Impresora;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Simulacion {
    public static void main(String[] args) throws InterruptedException {
        ColaImpresion cola = new ColaImpresion();

        ExecutorService executor = Executors.newCachedThreadPool();

        executor.submit(new Impresora(cola, "Impresora 1"));
        executor.submit(new Impresora(cola, "Impresora 2"));
        executor.submit(new Productor(cola));


        executor.shutdown(); // No se aceptan más tareas
        if (executor.awaitTermination(1, TimeUnit.MINUTES)) {
            System.out.println("Todas las impresoras temrinaron de imprimir todos los documentos");
        } else {
            System.out.println("Se agotó el tiempo esperando a que terminen las tareas de impresion");
        }
    }
}