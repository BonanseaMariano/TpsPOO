package barber;

// Hilo que representa al peluquero
public class Barber implements Runnable {
    private Bshop shop;

    public Barber(Bshop shop) {
        this.shop = shop;
    }

    /**
     * Este método es el punto de entrada para el hilo del peluquero.
     * Inicialmente duerme durante 10 segundos para simular que el peluquero se prepara para comenzar a trabajar.
     * Después de despertarse, entra en un bucle infinito donde llama continuamente al método `cutHair` en el objeto `shop`.
     * Este bucle asegura que el peluquero siempre esté listo para cortar el cabello mientras la peluquería esté abierta.
     */
    @Override
    public void run() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException iex) {
            iex.printStackTrace();
        }
        System.out.println("El peluquero ha comenzado..");
        while (true) {
            shop.cutHair();
        }
    }
}