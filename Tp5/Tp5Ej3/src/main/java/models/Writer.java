package models;

public class Writer implements Runnable {
    private final SharedResource resource;
    private final ReadWriteLock lock;
    private String name;

    public Writer(SharedResource resource, ReadWriteLock lock, int i) {
        this.resource = resource;
        this.lock = lock;
        this.name = "Writer " + i;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int value = (int) (Math.random() * 100);

                lock.lockWrite();
                long time = (long) (3000 + Math.random() * 2000);
                System.out.println(name + " locks write for " + time + " ms");
                resource.setValue(value);
                System.out.println(name + " Writes " + value);
                System.out.println(name + " unlocks write");
                lock.unlockWrite();
                Thread.sleep(time);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}