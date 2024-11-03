package models;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ColaImpresion {
    private final int MAX_SIZE = 5;
    private final BlockingQueue<Documento> cola = new LinkedBlockingQueue<>(MAX_SIZE);

    public void agregarDocumento(Documento documento) throws InterruptedException {
        cola.put(documento);
        System.out.println("Documento agregado a la cola: " + documento.getNombre() + " con " + documento.getPaginas() + " páginas y " + documento.getCopias() + " copias.");
    }

    public Documento obtenerDocumento() throws InterruptedException {
        return cola.poll(5, TimeUnit.SECONDS); // Espera 5 segundos para obtener un documento
    }

}
