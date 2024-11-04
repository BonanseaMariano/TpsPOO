package heladeria;

import java.util.Random;

/** Clase que representa la atención en la heladería. */
public class Atencion implements Runnable {
  private static final Random generator = new Random();
  private final Buffer colaAtencion;
  private final String nombre;
  private int cantidad;

  /**
   * Constructor para la clase Atencion.
   *
   * @param nombre el nombre de la atención
   * @param colaAtencion el buffer para la cola de atención
   */
  public Atencion(String nombre, Buffer colaAtencion) {
    this.nombre = nombre;
    this.colaAtencion = colaAtencion;
    this.cantidad = 0;
  }

  /**
   * Método que ejecuta el hilo de atención. Recupera clientes de la cola de atención, simula el
   * tiempo de atención y calcula el tiempo total de atención.
   */
  public void run() {
    Cliente cliente;
    long tiempo;
    try {
      while ((cliente = colaAtencion.get())
          != null) { // Mientras haya clientes en la cola de atención (el poll no retorne null)
        System.out.println("Cliente: " + cliente.getId() + " Atencion: " + nombre);
        Thread.sleep(generator.nextInt(2000) + 2000); // Simula el tiempo de atención
        tiempo =
            System.currentTimeMillis()
                - cliente.getIngreso(); // Calcula el tiempo total de atención
        System.out.println("Cliente: " + cliente.getId() + " Tiempo: " + tiempo);
        cantidad++; // Incrementa la cantidad de clientes atendidos para saber cuántos atendió cada
        // empleado
      }
    } catch (InterruptedException exception) {
      exception.printStackTrace();
    }
    System.out.println("Fin atencion: " + nombre);
  }

  /**
   * Obtiene el nombre de la atención.
   *
   * @return el nombre de la atención
   */
  public String getNombre() {
    return nombre;
  }

  /**
   * Obtiene la cantidad de clientes atendidos.
   *
   * @return la cantidad de clientes atendidos
   */
  public int getCantidad() {
    return cantidad;
  }
}
