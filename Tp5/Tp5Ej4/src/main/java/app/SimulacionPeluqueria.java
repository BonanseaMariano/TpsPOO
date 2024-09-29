package app;

import models.Cliente;
import models.Peluqueria;
import models.Peluquero;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulacionPeluqueria {
    public static void main(String[] args) {
        Peluqueria peluqueria = new Peluqueria(3); // 3 sillas de espera
        Peluquero peluquero = new Peluquero(peluqueria);

        ExecutorService executorService = Executors.newFixedThreadPool(4); // 1 peluquero + 3 clientes

        executorService.submit(peluquero);

        for (int i = 0; i < 10; i++) {
            Cliente cliente = new Cliente(peluqueria);
            executorService.submit(cliente);
            try {
                Thread.sleep(1000); // Simula el tiempo entre la llegada de clientes
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}