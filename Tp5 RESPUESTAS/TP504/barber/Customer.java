package barber;

import java.util.Date;

// Hilo que representa a un cliente
public class Customer implements Runnable {
  private String name;
  private Date inTime;

  private Bshop shop;

  public Customer(Bshop shop) {
    this.shop = shop;
  }

  public String getName() {
    return name;
  }

  public Date getInTime() {
    return inTime;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setInTime(Date inTime) {
    this.inTime = inTime;
  }

  public void run() {
    goForHairCut();
  }

  /**
   * Este método agrega al cliente actual a la cola de la peluquería para un corte de cabello. Está
   * sincronizado para garantizar la seguridad de los hilos cuando se agregan múltiples clientes de
   * manera concurrente.
   */
  private synchronized void goForHairCut() {
    shop.add(this);
  }
}
