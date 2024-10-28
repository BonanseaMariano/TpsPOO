package models;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Represents a Barber Shop where customers wait to get their hair cut.
 */
class BarberShop {
    // The number of chairs available for waiting customers in the barber shop.
    private final int numberOfChairs;
    // A blocking queue to hold the customers waiting for a haircut.
    // The size of the queue is limited by the number of chairs.
    private final BlockingQueue<Customer> waitingCustomers;
    // A lock to ensure thread-safe operations on the shared resources.
    private final Lock lock;
    // A condition to signal the barber when a customer is available.
    private final Condition barberAvailable;

    /**
     * Constructs a BarberShop with a specified number of chairs.
     *
     * @param numberOfChairs the number of chairs available for waiting customers
     */
    public BarberShop(int numberOfChairs) {
        this.numberOfChairs = numberOfChairs;
        this.waitingCustomers = new ArrayBlockingQueue<>(numberOfChairs);
        this.lock = new ReentrantLock();
        this.barberAvailable = lock.newCondition();
    }

    /**
     * Allows a customer to enter the shop and wait for a haircut.
     * If no chairs are available, the customer leaves.
     *
     * @param customer the customer entering the shop
     * @throws InterruptedException if the thread is interrupted while waiting
     */
    public void enterShop(Customer customer) throws InterruptedException {
        lock.lock();
        try {
            if (!waitingCustomers.offer(customer)) {
                System.out.println("Customer " + customer.getId() + " leaves as no chair is available.");
                return;
            }
            System.out.println("Customer " + customer.getId() + " is waiting, available chairs: "
                    + (numberOfChairs - waitingCustomers.size()));
            barberAvailable.signal(); // Signal the barber that a customer is available
        } finally {
            lock.unlock();
        }
    }

    /**
     * The barber cuts the hair of the next waiting customer.
     * If no customers are waiting, the barber waits.
     *
     * @throws InterruptedException if the thread is interrupted while waiting
     */
    public void cutHair() throws InterruptedException {
        lock.lock();
        try {
            while (waitingCustomers.isEmpty()) {
                System.out.println("Barber is sleeping as no customers are waiting.");
                barberAvailable.await(); // Wait until a customer is available
            }
            Customer customer = waitingCustomers.take();
            System.out.println("Barber is cutting hair of customer " + customer.getId());
            Thread.sleep(5000); // Simulate time taken to cut hair
            System.out.println("Barber finished cutting hair of customer " + customer.getId());
        } finally {
            lock.unlock();
        }
    }
}