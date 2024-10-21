package models;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

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
        producto = buffer.poll(5, TimeUnit.SECONDS);
        return producto;
    }

    public boolean isEmpty() {
        return buffer.isEmpty();
    }

}
