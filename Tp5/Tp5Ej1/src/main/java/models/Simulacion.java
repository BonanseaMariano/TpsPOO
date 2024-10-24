package models;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Simulacion {
  public static void main(String[] args) {
    ExecutorService aplicacion = Executors.newCachedThreadPool();

    CintaDeEmpaque cintaDeEmpaque = new CintaDeEmpaque();

    aplicacion.execute(new Equipo(cintaDeEmpaque, "Equipo 1"));
    aplicacion.execute(new Equipo(cintaDeEmpaque, "Equipo 2"));
    aplicacion.execute(new Equipo(cintaDeEmpaque, "Equipo 3"));
    aplicacion.execute(new Empacadora(cintaDeEmpaque));

    try {
      aplicacion.awaitTermination(5, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    aplicacion.shutdown();
    System.out.println("Simulación terminada.");
  }
}
