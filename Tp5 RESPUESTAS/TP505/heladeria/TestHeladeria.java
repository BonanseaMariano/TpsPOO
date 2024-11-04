package heladeria;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
   Utilizar hilos para simular el funcionamiento de una heladería.
   Los clientes pasan por la caja donde realizan el pedido y pagan la compra, luego son atendidos por los empleados que les entregan el pedido.
   Para la simulación considere que se atenderán a 120 clientes, hay 2 empleados que atienden la caja y 6 empleados de atención al público.
   Los clientes llegan cada 1 a 3 minutos a la heladería y se ubican en la cola para realizar el pedido y pagar el mismo, el cajero tarda entre 1 y 2 minutos en cobrar y emitir el ticket.
   Luego los clientes hacen la cola para recibir el pedido, en la misma es atendido por uno de los empleados que les arma el pedido (solicita los gustos, etc.), tarda entre 2 a 4 minutos en entregarlo.
   La aplicación debe ser fácilmente parametrizable (empleados en la caja, empleados en la atención al público, cantidad de clientes, etc.)
   En la simulación mostrar por pantalla el número de cliente y el tiempo total que tardó en ser atendido.
   Cuando todos los hilos terminan su ejecución mostrar por pantalla el tiempo total que trabajó cada empleado tanto los de la caja como los de atención al público.
*/

public class TestHeladeria {

  public static void main(String[] args) {
    simular(2, 6, 12);
  }

  /**
   * Simula el funcionamiento de una heladería con el número dado de cajas, empleados de atención al
   * público y clientes.
   *
   * @param nroCajas el número de cajas
   * @param nroAtencion el número de empleados de atención al público
   * @param clientes el número de clientes
   */
  public static void simular(int nroCajas, int nroAtencion, int clientes) {

    // Crear un pool de hilos para gestionar la ejecución de tareas
    ExecutorService application = Executors.newCachedThreadPool();

    // Crear buffers para las colas en las cajas y la atención al público
    Buffer colaCaja = new Cola(clientes);
    Buffer colaAtencion = new Cola(clientes);

    // Listas para llevar el seguimiento de los empleados de caja y atención al público
    List<Caja> cajas = new ArrayList<Caja>();
    List<Atencion> atenciones = new ArrayList<Atencion>();

    // Iniciar la tarea del generador de clientes
    application.execute(new GeneradorCliente(colaCaja, clientes));

    // Crear e iniciar tareas para cada empleado de caja
    Caja c; // Hilo para la caja
    for (int i = 1; i <= nroCajas; i++) {
      c = new Caja("C" + i, colaCaja, colaAtencion);
      cajas.add(c);
      application.execute(c);
    }

    // Crear e iniciar tareas para cada empleado de atención al público
    Atencion a;
    for (int i = 1; i <= nroAtencion; i++) {
      a = new Atencion("A" + i, colaAtencion);
      atenciones.add(a);
      application.execute(a);
    }

    // Apagar el pool de hilos
    application.shutdown();

    try {
      // Esperar a que todas las tareas terminen o se agote el tiempo después de 120 segundos
      boolean tareasTerminaron = application.awaitTermination(120, TimeUnit.SECONDS);
      if (tareasTerminaron) {
        // Imprimir el número de clientes atendidos por cada empleado de atención al público
        for (Atencion atencion : atenciones)
          System.out.println(
              "Puesto atencion: "
                  + atencion.getNombre()
                  + " - atendio "
                  + atencion.getCantidad()
                  + " clientes");
      }
    } catch (InterruptedException ex) {
      // Manejar la interrupción mientras se espera a que terminen las tareas
      System.out.println("Hubo una interrupcion mientras esperaba a que terminaran las tareas.");
    }
  }
}
