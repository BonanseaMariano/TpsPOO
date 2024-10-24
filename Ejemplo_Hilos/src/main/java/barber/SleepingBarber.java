package barber;

public class SleepingBarber {
	 
    public static void main(String a[])
    {
        Bshop shop = new Bshop();
 
        Barber barber = new Barber(shop);
        CustomerGenerator cg = new CustomerGenerator(shop);
 
        Thread thbarber = new Thread(barber);
        Thread thcg = new Thread(cg);
        thcg.start();
        thbarber.start();
    }
}
