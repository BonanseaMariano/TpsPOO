package models;

import app.Heladeria;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class EmpleadoCaja implements Runnable {
    private final BlockingQueue<Cliente> colaCaja;
    private final BlockingQueue<Cliente> colaAtencion;
    private final AtomicInteger totalClientesAtendidos;
    private long tiempoTotalTrabajado = 0;

    public EmpleadoCaja(BlockingQueue<Cliente> colaCaja, BlockingQueue<Cliente> colaAtencion, AtomicInteger totalClientesAtendidos) {
        this.colaCaja = colaCaja;
        this.colaAtencion = colaAtencion;
        this.totalClientesAtendidos = totalClientesAtendidos;
    }

    @Override
    public void run() {
        try {
            while (totalClientesAtendidos.get() < Heladeria.NUM_CLIENTES) {
                Cliente cliente = colaCaja.poll(1, TimeUnit.SECONDS);
                if (cliente != null) {
                    long startTime = System.currentTimeMillis();
                    Thread.sleep((1 + Heladeria.RANDOM.nextInt(2)) * 1000); // Cajero tarda entre 1 y 2 minutos
                    colaAtencion.add(cliente);
                    tiempoTotalTrabajado += (System.currentTimeMillis() - startTime);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("EmpleadoCaja trabajó " + tiempoTotalTrabajado / 1000 + " segundos.");
        }
    }
}