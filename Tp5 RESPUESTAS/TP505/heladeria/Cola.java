package heladeria;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

// Segundo Buffer para la cola de la caja y la atención al público
public class Cola implements Buffer {
    private final ArrayBlockingQueue<Cliente> buffer;

    /**
     * Constructor para la clase Cola.
     *
     * @param cantidad la capacidad de la cola
     */
    public Cola(int cantidad) {
        buffer = new ArrayBlockingQueue<Cliente>(cantidad);
    }

    /**
     * Agrega un cliente a la cola.
     *
     * @param value el cliente a agregar
     * @throws InterruptedException si es interrumpido mientras espera
     */
    public void set(Cliente value) throws InterruptedException {
        buffer.put(value);
    }

    /**
     * Recupera y elimina un cliente de la cola, esperando hasta 20 segundos si es necesario.
     *
     * @return el cliente de la cola, o null si el tiempo de espera especificado transcurre antes de que haya un elemento disponible
     * @throws InterruptedException si es interrumpido mientras espera
     */
    public Cliente get() throws InterruptedException {
        return buffer.poll(20000, TimeUnit.MILLISECONDS); // Retorna null si no hay elementos en 20 segundos y es lo que identifica el fin de la ejecución
    }

}
