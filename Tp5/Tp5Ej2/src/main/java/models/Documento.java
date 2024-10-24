package models;

public class Documento {
    private String nombre;
    private int numPaginas;
    private int numCopias;

    // Constructor
    public Documento(String nombre, int numPaginas, int numCopias) {
        this.nombre = nombre;
        this.numPaginas = numPaginas;
        this.numCopias = numCopias;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public int getNumPaginas() {
        return numPaginas;
    }

    public int getNumCopias() {
        return numCopias;
    }

    // Método toString para imprimir los detalles del documento
    @Override
    public String toString() {
        return "Documento: " + nombre + " | Páginas: " + numPaginas + " | Copias: " + numCopias;
    }
}

