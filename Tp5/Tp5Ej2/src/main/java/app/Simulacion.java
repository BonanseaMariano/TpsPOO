// src/main/java/app/Simulacion.java
package app;

import models.ColaImpresion;
import models.Documento;
import models.Impresora;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Simulacion {
    public static void main(String[] args) {
        ColaImpresion cola = new ColaImpresion();

        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.execute(new Impresora("Impresora 1", cola));
        executor.execute(new Impresora("Impresora 2", cola));

        try {
            cola.encolarDocumento(new Documento("Doc1", 10, 1));
            cola.encolarDocumento(new Documento("Doc2", 5, 2));
            cola.encolarDocumento(new Documento("Doc3", 3, 3));
            cola.encolarDocumento(new Documento("Doc4", 7, 1));
            cola.encolarDocumento(new Documento("Doc5", 2, 4));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        executor.shutdown();
    }
}