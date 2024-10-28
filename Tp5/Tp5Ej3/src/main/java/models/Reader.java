package models;

public class Reader implements Runnable {
  private final SharedResource resource;
  private final ReadWriteLock lock;
  private String name;

  public Reader(SharedResource resource, ReadWriteLock lock, int i) {
    this.resource = resource;
    this.lock = lock;
    this.name = "Reader " + i;
  }

  @Override
  public void run() {
    try {
      while (true) {
        lock.lockRead();
        long time = (long) (3000 + Math.random() * 2000);
        System.out.println(name + " locks read for " + time + " ms");
        int value = resource.getValue();
        System.out.println(name + " Reads " + value);
        System.out.println(name + " unlocks read");
        lock.unlockRead();
        Thread.sleep(time);
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
