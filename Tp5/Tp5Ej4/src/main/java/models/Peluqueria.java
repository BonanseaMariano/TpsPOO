package models;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Peluqueria {
    private final int sillasEspera;
    private int sillasDisponibles;
    private final Lock lock;
    private final Condition condicionPeluquero;
    private final Condition condicionCliente;
    private boolean peluqueroDurmiendo;

    public Peluqueria(int sillasEspera) {
        this.sillasEspera = sillasEspera;
        this.sillasDisponibles = sillasEspera;
        this.lock = new ReentrantLock();
        this.condicionPeluquero = lock.newCondition();
        this.condicionCliente = lock.newCondition();
        this.peluqueroDurmiendo = true;
    }

    public void cortarCabello() throws InterruptedException {
        lock.lock();
        try {
            if (sillasDisponibles == 0) {
                System.out.println("No hay sillas disponibles. El cliente se va.");
                return;
            }

            sillasDisponibles--;
            System.out.println("El cliente se sienta en la sala de espera. Sillas disponibles: " + sillasDisponibles);

            while (peluqueroDurmiendo) {
                condicionPeluquero.signal();
                condicionCliente.await();
            }

            sillasDisponibles++;
            System.out.println("El cliente está recibiendo un corte de cabello. Sillas disponibles: " + sillasDisponibles);
        } finally {
            lock.unlock();
        }
    }

    public void cortarCabelloCliente() throws InterruptedException {
        lock.lock();
        try {
            while (sillasDisponibles == sillasEspera) {
                System.out.println("El peluquero está durmiendo.");
                peluqueroDurmiendo = true;
                condicionPeluquero.await();
            }

            peluqueroDurmiendo = false;
            System.out.println("El peluquero está cortando el cabello.");
            Thread.sleep(5000); // Simula el tiempo que toma cortar el cabello
            condicionCliente.signal();
        } finally {
            lock.unlock();
        }
    }
}