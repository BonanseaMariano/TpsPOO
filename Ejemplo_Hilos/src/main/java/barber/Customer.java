package barber;

import java.util.Date;

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

  private synchronized void goForHairCut() {
    shop.add(this);
  }
}
