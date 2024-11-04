package heladeria;

import java.util.Random;

// Hilo productor que genera clientes y los agrega a la cola de la caja
public class GeneradorCliente implements Runnable {
  private static final Random generator = new Random();
  private final Buffer colaCaja;
  private final int clientes;

  public GeneradorCliente(Buffer colaCaja, int clientes) {
    this.colaCaja = colaCaja;
    this.clientes = clientes;
  }

  @Override
  public void run() {
    // Crear un nuevo cliente y agregarlo a la cola por cada cliente a generar
    Cliente cliente;
    for (int count = 1; count <= clientes; count++) {
      try {
        // Esperar por un tiempo aleatorio entre 1 y 3 segundos
        Thread.sleep(generator.nextInt(2000) + 1000);
        // Crear un nuevo cliente con el conteo actual
        cliente = new Cliente(count);
        // Establecer el tiempo de ingreso del cliente al tiempo actual del sistema
        cliente.setIngreso(System.currentTimeMillis());
        // Agregar el cliente a la cola
        colaCaja.set(cliente);
      } catch (InterruptedException exception) {
        // Imprimir la traza de la pila si ocurre una interrupción
        exception.printStackTrace();
      }
    }
    // Imprimir un mensaje indicando el fin de la generación de clientes
    System.out.println("Fin Ingreso Clientes");
  }
}
