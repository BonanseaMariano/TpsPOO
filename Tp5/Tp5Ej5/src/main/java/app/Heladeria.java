package app;

import models.Cliente;
import models.EmpleadoAtencion;
import models.EmpleadoCaja;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Random;

public class Heladeria {
    public static final int NUM_CLIENTES = 120;
    private static final int NUM_EMPLEADOS_CAJA = 2;
    private static final int NUM_EMPLEADOS_ATENCION = 6;
    public static final Random RANDOM = new Random();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService cajaPool = Executors.newFixedThreadPool(NUM_EMPLEADOS_CAJA);
        ExecutorService atencionPool = Executors.newFixedThreadPool(NUM_EMPLEADOS_ATENCION);
        BlockingQueue<Cliente> colaCaja = new LinkedBlockingQueue<>();
        BlockingQueue<Cliente> colaAtencion = new LinkedBlockingQueue<>();
        AtomicInteger totalClientesAtendidos = new AtomicInteger(0);

        for (int i = 1; i <= NUM_CLIENTES; i++) {
            Cliente cliente = new Cliente(i);
            colaCaja.add(cliente);
            Thread.sleep(RANDOM.nextInt(3) * 1000); // Clientes llegan cada 1 a 3 minutos
        }

        for (int i = 0; i < NUM_EMPLEADOS_CAJA; i++) {
            cajaPool.execute(new EmpleadoCaja(colaCaja, colaAtencion, totalClientesAtendidos));
        }

        for (int i = 0; i < NUM_EMPLEADOS_ATENCION; i++) {
            atencionPool.execute(new EmpleadoAtencion(colaAtencion, totalClientesAtendidos));
        }

        cajaPool.shutdown();
        atencionPool.shutdown();
        cajaPool.awaitTermination(1, TimeUnit.HOURS);
        atencionPool.awaitTermination(1, TimeUnit.HOURS);

        System.out.println("Todos los clientes han sido atendidos.");
    }
}