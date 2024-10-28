package models;

/**
 * Represents a customer in the barber shop simulation.
 * Each customer has a unique ID and a reference to the barber shop.
 */
public class Customer implements Runnable {
    // A static counter to assign unique IDs to each customer.
    private static int idCounter = 0;
    // The unique ID of the customer.
    private final int id;
    // A reference to the barber shop the customer will visit.
    private final BarberShop shop;

    /**
     * Constructs a new Customer with a unique ID and a reference to the barber shop.
     *
     * @param shop the barber shop the customer will visit
     */
    public Customer(BarberShop shop) {
        this.id = ++idCounter;
        this.shop = shop;
    }

    /**
     * Returns the unique ID of the customer.
     *
     * @return the customer's ID
     */
    public int getId() {
        return id;
    }

    /**
     * The run method for the customer thread.
     * The customer attempts to enter the barber shop.
     */
    @Override
    public void run() {
        try {
            shop.enterShop(this);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}