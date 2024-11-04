package heladeria;

import java.util.Random;

/**
 * Primer buffer para la cola de la caja.
 */
public class Caja implements Runnable {
    private final static Random generator = new Random();
    private final Buffer colaCaja;
    private final Buffer colaAtencion;
    private final String nombre;

    /**
     * Constructor para la clase Caja.
     *
     * @param nombre       el nombre de la caja
     * @param colaCaja     el buffer para la cola de la caja
     * @param colaAtencion el buffer para la cola de atención
     */
    public Caja(String nombre, Buffer colaCaja, Buffer colaAtencion) {
        this.nombre = nombre;
        this.colaCaja = colaCaja;
        this.colaAtencion = colaAtencion;
    }

    /**
     * Método que ejecuta el hilo de la caja.
     * Recupera clientes de la cola de la caja, simula el tiempo de atención y los pasa a la cola (Buffer) de atención.
     */
    @Override
    public void run() {
        Cliente cliente;
        try {
            while ((cliente = colaCaja.get()) != null) { // Mientras haya clientes en la cola de la caja (el poll no retorne null)
                System.out.println("Cliente: " + cliente.getId() + " Caja: " + nombre);
                Thread.sleep(generator.nextInt(1000) + 1000); // Simula el tiempo de atención
                colaAtencion.set(cliente); // Pasa el cliente a la cola de atención
            }
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}