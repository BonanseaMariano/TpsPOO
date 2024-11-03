package models;

/**
 * A class representing a shared resource that can be read and written by multiple threads.
 */
public class SharedResource {
    private int value;

    /**
     * Gets the value of the shared resource.
     *
     * @return the value of the shared resource
     */
    public synchronized int getValue() {
        return value;
    }

    /**
     * Sets the value of the shared resource.
     *
     * @param value the new value of the shared resource
     */
    public synchronized void setValue(int value) {
        this.value = value;
    }
}