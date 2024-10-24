package models;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

public class ColaImpresion {
    private final BlockingQueue<Documento> colaImpresion;

    public ColaImpresion(int capacidad) {
        // La cola tiene una capacidad fija para 5 documentos
        this.colaImpresion = new ArrayBlockingQueue<>(capacidad);
    }

    // Método para agregar un documento a la cola
    public void agregarDocumento(Documento documento) throws InterruptedException {
        colaImpresion.put(documento);
        System.out.println(documento + " agregado a la cola de impresión.");
    }

    // Método para retirar un documento de la cola
    public Documento retirarDocumento() throws InterruptedException {
        return colaImpresion.take();
    }
}

