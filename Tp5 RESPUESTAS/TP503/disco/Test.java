package disco;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
Realizar una aplicación donde se crean 10 hilos que leen y 2 hilos que escriben sobre el mismo recurso.
Cuando un lector comienza la lectura llama a lockRead y cuando termina llama a unlockRead.
Cuando un escritor comienza la escritura llama a lockWrite y cuando termina llama a unlockWrite.
Los lectores y los escritores tardan entre 3 a 5 segundos en realizar su tarea.
*/

public class Test {

    public static void main(String[] args) {

        ReadWriteLock rwl = new ReadWriteLock();
        Read lec[] = new Read[10]; // 10 lectores
        Write esc[] = new Write[2]; // 2 escritores

        // Crear 10 hilos lectores
        for (int i = 0; i < lec.length; i++)
            lec[i] = new Read(rwl, i + 1);
        // Crear 2 hilos escritores
        for (int i = 0; i < esc.length; i++)
            esc[i] = new Write(rwl, i + 1);
        // Crear un pool de hilos
        ExecutorService ejecutorSubprocesos = Executors.newCachedThreadPool();
        // Ejecutar los hilos lectores
        for (int i = 0; i < lec.length; i++)
            ejecutorSubprocesos.execute(lec[i]);
        // Ejecutar los hilos escritores
        for (int i = 0; i < esc.length; i++)
            ejecutorSubprocesos.execute(esc[i]);
        // Terminar los hilos
        ejecutorSubprocesos.shutdown();

    }

}
