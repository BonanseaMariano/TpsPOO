package models;

import java.util.LinkedList;
import java.util.Queue;

public class ColaImpresion {
    private Queue<Documento> cola = new LinkedList<>();
    private final int MAX_CAPACITY = 5;

    public synchronized void encolarDocumento(Documento doc) throws InterruptedException {
        while (cola.size() == MAX_CAPACITY) {
            wait();
        }
        cola.add(doc);
        System.out.println("Documento encolado: " + doc.getNombre());
        notifyAll();
    }

    public synchronized Documento obtenerDocumento() throws InterruptedException {
        while (cola.isEmpty()) {
            wait();
        }
        Documento doc = cola.poll();
        notifyAll();
        return doc;
    }
}