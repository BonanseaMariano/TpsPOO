package barber;

/*
    Simular el funcionamiento de una peluquería.
    La peluquería tiene un peluquero, una silla de peluquero y n sillas para que se sienten los clientes en espera, si es que los hay.
    Si no hay clientes presentes, el peluquero se sienta en la silla de peluquero y se duerme.
    Cuando llega un cliente, éste debe despertarlo. Si llegan más clientes mientras el peluquero corta el cabello de un cliente, ellos se sientan
    (si hay sillas desocupadas).
 */

public class SleepingBarber {

    public static void main(String a[]) {
        // Shop
        Bshop shop = new Bshop();
        // Consumer
        Barber barber = new Barber(shop);
        // Producer
        CustomerGenerator cg = new CustomerGenerator(shop);
        // Initialize threads
        Thread thbarber = new Thread(barber);
        Thread thcg = new Thread(cg);
        thcg.start();
        thbarber.start();
    }
}
