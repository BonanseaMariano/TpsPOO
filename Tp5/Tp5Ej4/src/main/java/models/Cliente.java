package models;

public class Cliente implements Runnable {
    private final Peluqueria peluqueria;

    public Cliente(Peluqueria peluqueria) {
        this.peluqueria = peluqueria;
    }

    @Override
    public void run() {
        try {
            peluqueria.cortarCabello();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
