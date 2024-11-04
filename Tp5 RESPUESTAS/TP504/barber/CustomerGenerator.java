package barber;

import java.util.Date;
import java.util.concurrent.TimeUnit;

// Hilo que genera clientes
public class CustomerGenerator implements Runnable {
  private Bshop shop;

  public CustomerGenerator(Bshop shop) {
    this.shop = shop;
  }

  /**
   * El método run genera continuamente nuevos clientes y comienza un nuevo hilo para cada cliente.
   * A cada cliente se le asigna un nombre único basado en el ID del hilo y se introduce un tiempo
   * de espera aleatorio entre la generación de cada cliente para simular llegadas de clientes en
   * tiempo real.
   */
  @Override
  public void run() {
    while (true) {
      // Crear un nuevo cliente y establecer la hora actual como la hora de entrada
      Customer customer = new Customer(shop);
      customer.setInTime(new Date());

      // Crear un nuevo hilo para el cliente y establecer un nombre único para el hilo
      Thread thcustomer = new Thread(customer);
      customer.setName("Hilo Cliente " + thcustomer.getId());

      // Iniciar el hilo del cliente
      thcustomer.start();

      try {
        // Dormir por un tiempo aleatorio entre 0 a 10 segundos para simular el tiempo de llegada
        // del cliente
        TimeUnit.SECONDS.sleep((long) (Math.random() * 10));
      } catch (InterruptedException iex) {
        // Imprimir la traza de la pila si ocurre una InterruptedException
        iex.printStackTrace();
      }
    }
  }
}
