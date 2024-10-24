package models;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LineaDeProduccion {

    public static void main(String[] args) {
        // Crear la cinta de empaque con capacidad para 12 productos
        CintaEmpaque cintaEmpaque = new CintaEmpaque(12);

        int numEquipos = 3; // Número de equipos productores

        // Crear la empacadora y los equipos productores
        Empacadora empacadora = new Empacadora(cintaEmpaque, numEquipos);
        Productor equipo1 = new Productor("Equipo 1", cintaEmpaque);
        Productor equipo2 = new Productor("Equipo 2", cintaEmpaque);
        Productor equipo3 = new Productor("Equipo 3", cintaEmpaque);

        // Crear un CachedThreadPool
        ExecutorService executor = Executors.newCachedThreadPool();

        // Ejecutar las tareas
        executor.execute(empacadora);
        executor.execute(equipo1);
        executor.execute(equipo2);
        executor.execute(equipo3);

        // Apagar el executor cuando terminen las tareas
        executor.shutdown();
    }
}

