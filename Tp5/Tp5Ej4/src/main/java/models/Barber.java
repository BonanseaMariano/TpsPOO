package models;

/**
 * Represents a barber in the barber shop simulation. The barber continuously attempts to cut hair
 * of waiting customers.
 */
public class Barber implements Runnable {
  private final BarberShop shop;

  /**
   * Constructs a new Barber with a reference to the barber shop.
   *
   * @param shop the barber shop where the barber works
   */
  public Barber(BarberShop shop) {
    this.shop = shop;
  }

  /**
   * The run method for the barber thread. The barber continuously cuts hair of waiting customers.
   */
  @Override
  public void run() {
    try {
      while (true) {
        shop.cutHair();
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
