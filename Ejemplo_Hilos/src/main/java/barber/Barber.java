package barber;

public class Barber implements Runnable {
  private Bshop shop;

  public Barber(Bshop shop) {
    this.shop = shop;
  }

  public void run() {
    try {
      Thread.sleep(10000);
    } catch (InterruptedException iex) {
      iex.printStackTrace();
    }
    System.out.println("Barber started..");
    while (true) {
      shop.cutHair();
    }
  }
}
