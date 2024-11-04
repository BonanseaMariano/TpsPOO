package heladeria;

public interface Buffer {
    /**
     * Agrega un cliente a la cola.
     *
     * @param value el cliente a agregar
     * @throws InterruptedException si es interrumpido mientras espera
     */
    public void set(Cliente value) throws InterruptedException;

    /**
     * Recupera y elimina un cliente de la cola, esperando hasta 20 segundos si es necesario.
     *
     * @return el cliente de la cola, o null si el tiempo de espera especificado transcurre antes de que haya un elemento disponible
     * @throws InterruptedException si es interrumpido mientras espera
     */
    public Cliente get() throws InterruptedException;
}
