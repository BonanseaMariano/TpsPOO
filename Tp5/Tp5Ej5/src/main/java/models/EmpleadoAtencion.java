package models;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import app.Heladeria;

public class EmpleadoAtencion implements Runnable {
    private final BlockingQueue<Cliente> colaAtencion;
    private final AtomicInteger totalClientesAtendidos;
    private long tiempoTotalTrabajado = 0;

    public EmpleadoAtencion(BlockingQueue<Cliente> colaAtencion, AtomicInteger totalClientesAtendidos) {
        this.colaAtencion = colaAtencion;
        this.totalClientesAtendidos = totalClientesAtendidos;
    }

    @Override
    public void run() {
        try {
            while (totalClientesAtendidos.get() < Heladeria.NUM_CLIENTES) {
                Cliente cliente = colaAtencion.poll(1, TimeUnit.SECONDS);
                if (cliente != null) {
                    long startTime = System.currentTimeMillis();
                    Thread.sleep((2 + Heladeria.RANDOM.nextInt(3)) * 1000); // Empleado tarda entre 2 y 4 minutos
                    long tiempoTotal = System.currentTimeMillis() - cliente.getTiempoLlegada();
                    System.out.println("Cliente " + cliente.getId() + " atendido en " + tiempoTotal / 1000 + " segundos.");
                    totalClientesAtendidos.incrementAndGet();
                    tiempoTotalTrabajado += (System.currentTimeMillis() - startTime);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("EmpleadoAtencion trabajó " + tiempoTotalTrabajado / 1000 + " segundos.");
        }
    }
}