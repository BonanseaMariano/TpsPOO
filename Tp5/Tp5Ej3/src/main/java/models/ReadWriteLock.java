package models;

/**
 * A ReadWriteLock allows multiple threads to read a shared resource concurrently, but only one
 * thread to write to the resource at a time. It also ensures that no thread can read while another
 * thread is writing.
 */
public class ReadWriteLock {

  private int readers = 0; // Number of active readers
  private int writers = 0; // Number of active writers
  private int writeRequests = 0; // Number of write requests

  /**
   * Acquires the read lock. If there are active writers or pending write requests, the calling
   * thread will be blocked until it is safe to read.
   *
   * @throws InterruptedException if the current thread is interrupted
   */
  public synchronized void lockRead() throws InterruptedException {
    while (writers > 0 || writeRequests > 0) {
      wait();
    }
    readers++;
  }

  /** Releases the read lock and notifies all waiting threads. */
  public synchronized void unlockRead() {
    readers--;
    notifyAll();
  }

  /**
   * Acquires the write lock. If there are active readers or writers, the calling thread will be
   * blocked until it is safe to write.
   *
   * @throws InterruptedException if the current thread is interrupted
   */
  public synchronized void lockWrite() throws InterruptedException {
    writeRequests++;
    while (readers > 0 || writers > 0) {
      wait();
    }
    writeRequests--;
    writers++;
  }

  /**
   * Releases the write lock and notifies all waiting threads.
   *
   * @throws InterruptedException if the current thread is interrupted
   */
  public synchronized void unlockWrite() throws InterruptedException {
    writers--;
    notifyAll();
  }
}
