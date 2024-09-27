package models;

import java.util.concurrent.ArrayBlockingQueue;

public class CintaDeEmpaque {
    private final ArrayBlockingQueue<Producto> buffer;

    public CintaDeEmpaque() {
        buffer = new ArrayBlockingQueue<Producto>(12);
    }

    public void set(Producto producto) throws InterruptedException {
        buffer.put(producto);
    }

    public Producto get() throws InterruptedException {
        Producto producto;
        producto = buffer.take();
        return producto;
    }

    public boolean isEmpty() {
        return buffer.isEmpty();
    }

}
