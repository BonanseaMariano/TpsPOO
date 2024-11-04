package disco;

public class ReadWriteLock {

    private int readers = 0;
    private int writers = 0;
    private int writeRequests = 0;

    /**
     * Adquiere el bloqueo de lectura. Si hay escritores o solicitudes de escritura,
     * el hilo actual espera hasta que sea seguro proceder.
     *
     * @throws InterruptedException si el hilo actual es interrumpido
     *                              mientras espera.
     */
    public synchronized void lockRead() throws InterruptedException {
        while (writers > 0 || writeRequests > 0) {
            wait(); // Espera hasta que sea seguro proceder
        }
        readers++;
    }

    /**
     * Libera el bloqueo de lectura y notifica a todos los hilos en espera.
     */
    public synchronized void unlockRead() {
        readers--;
        notifyAll(); // Notifica a todos los hilos en espera
    }

    /**
     * Adquiere el bloqueo de escritura. Si hay lectores o escritores,
     * el hilo actual espera hasta que sea seguro proceder.
     *
     * @throws InterruptedException si el hilo actual es interrumpido
     *                              mientras espera.
     */
    public synchronized void lockWrite() throws InterruptedException {
        writeRequests++;

        while (readers > 0 || writers > 0) {
            wait(); // Espera hasta que sea seguro proceder
        }
        writeRequests--;
        writers++;
    }

    /**
     * Libera el bloqueo de escritura y notifica a todos los hilos en espera.
     *
     * @throws InterruptedException si el hilo actual es interrumpido
     *                              mientras espera.
     */
    public synchronized void unlockWrite() throws InterruptedException {
        writers--;
        notifyAll(); // Notifica a todos los hilos en espera
    }
}
