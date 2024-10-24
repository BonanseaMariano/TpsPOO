package models;

public class Impresora implements Runnable {
  private final String nombreImpresora;
  private final ColaImpresion colaImpresion;

  public Impresora(String nombreImpresora, ColaImpresion colaImpresion) {
    this.nombreImpresora = nombreImpresora;
    this.colaImpresion = colaImpresion;
  }

  @Override
  public void run() {
    try {
      // Retirar un documento de la cola de impresión
      Documento documento = colaImpresion.retirarDocumento();
      while (!documento.getNombre().equals("FIN")) {
        // Imprimir el documento
        for (int copia = 1; copia <= documento.getNumCopias(); copia++) {
          for (int pagina = 1; pagina <= documento.getNumPaginas(); pagina++) {
            System.out.println(
                nombreImpresora
                    + " está imprimiendo "
                    + documento.getNombre()
                    + " (Copia "
                    + copia
                    + ") Página "
                    + pagina);
            Thread.sleep(1000); // Simular 1 segundo por página
          }
        }

        System.out.println(nombreImpresora + " terminó de imprimir " + documento.getNombre());
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      System.out.println(nombreImpresora + " se ha detenido.");
    }
    System.out.println("Se han terminado de imprimir todos los documentos.");
  }
}
