package models;

public class Productor implements Runnable {
    private final ColaImpresion colaImpresion;

    public Productor(ColaImpresion colaImpresion) {
        this.colaImpresion = colaImpresion;
    }

    @Override
    public void run() {
        try {
            // Simular la llegada de hasta 5 documentos a la cola
            for (int i = 1; i <= 2; i++) {
                // Crear un documento con un nombre, número de páginas y copias
                Documento documento = new Documento(
                        "Documento " + i,
                        (int) (Math.random() * 10 + 1), // Número de páginas entre 1 y 10
                        1 //(int) (Math.random() * 3 + 1)   // Número de copias entre 1 y 3
                );

                // Agregar el documento a la cola de impresión
                colaImpresion.agregarDocumento(documento);

                // Simular un intervalo entre la llegada de documentos
                Thread.sleep(2000); // Cada 2 segundos llega un nuevo documento
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("El productor ha sido interrumpido.");
        }

        try {
            // Agregar un documento especial para indicar que el productor ha terminado
            Documento fin = new Documento("FIN", 0, 0);
            colaImpresion.agregarDocumento(fin);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("El productor ha sido interrumpido.");
        }
    }
}
