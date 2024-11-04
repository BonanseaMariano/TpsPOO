package barber;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Bshop {
  private int nchair;
  private List<Customer> listCustomer;

  public Bshop() {
    nchair = 3; // Número de sillas en la peluquería
    listCustomer = new LinkedList<Customer>(); // Cola de clientes (Buffer)
  }

  /**
   * Este método simula al peluquero cortando el cabello de un cliente. Espera a que haya un cliente
   * disponible en la cola, luego procesa el corte de cabello. El método está sincronizado para
   * garantizar la seguridad de los hilos al acceder a la cola de clientes.
   */
  public void cutHair() {
    Customer customer;
    System.out.println("El peluquero está esperando el bloqueo.");
    // Sincronizar el bloqueo
    synchronized (listCustomer) {

      // Esperar hasta que haya un cliente en la cola
      while (listCustomer.size() == 0) {
        System.out.println("El peluquero está esperando a un cliente.");
        try {
          listCustomer.wait(); // Esperar a que llegue un cliente
        } catch (InterruptedException iex) {
          iex.printStackTrace();
        }
      }
      System.out.println("El peluquero encontró un cliente en la cola.");
      customer =
          (Customer) ((LinkedList<?>) listCustomer).poll(); // Obtener el primer cliente de la cola
    }
    long duration = 0;
    try {
      // Simular el tiempo que toma cortar el cabello del cliente
      System.out.println("Cortando el cabello del cliente: " + customer.getName());
      duration = (long) (Math.random() * 10);
      TimeUnit.SECONDS.sleep(duration); // Simular el corte de cabello
    } catch (InterruptedException iex) {
      iex.printStackTrace();
    }
    System.out.println(
        "Completado el corte de cabello del cliente: "
            + customer.getName()
            + " en "
            + duration
            + " segundos.");
  }

  /**
   * Este método agrega un cliente a la cola de la peluquería. Primero verifica si hay una silla
   * disponible para el cliente. Si no hay sillas disponibles, el cliente se va de la tienda. Si hay
   * una silla disponible, el cliente se agrega a la cola. Si el cliente es el único en la cola,
   * notifica al peluquero.
   *
   * @param customer el cliente que se va a agregar a la cola
   */
  public void add(Customer customer) {
    System.out.println(
        "Cliente: " + customer.getName() + " entrando a la tienda a las " + customer.getInTime());
    // Sincronizar el bloqueo
    synchronized (listCustomer) {
      // Verificar si hay sillas disponibles
      if (listCustomer.size() == nchair) {
        System.out.println("No hay sillas disponibles para el cliente " + customer.getName());
        System.out.println("El cliente " + customer.getName() + " se va...");
        return;
      }

      ((LinkedList<Customer>) listCustomer).offer(customer); // Agregar el cliente a la cola
      System.out.println("Cliente: " + customer.getName() + " consiguió una silla.");

      // Notificar al peluquero si el cliente es el único en la cola
      if (listCustomer.size() == 1) listCustomer.notify();
    }
  }
}
