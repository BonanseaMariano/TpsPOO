package barber;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Bshop {
	private int nchair;
	private List<Customer> listCustomer;

	public Bshop() {
		nchair = 3;
		listCustomer = new LinkedList<Customer>();
	}

	public void cutHair() {
		Customer customer;
		System.out.println("Barber waiting for lock.");
		synchronized (listCustomer) {

			while (listCustomer.size() == 0) {
				System.out.println("Barber is waiting for customer.");
				try {
					listCustomer.wait();
				} catch (InterruptedException iex) {
					iex.printStackTrace();
				}
			}
			System.out.println("Barber found a customer in the queue.");
			customer = (Customer) ((LinkedList<?>) listCustomer).poll();
		}
		long duration = 0;
		try {
			System.out.println("Cuting hair of Customer : " + customer.getName());
			duration = (long) (Math.random() * 10);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException iex) {
			iex.printStackTrace();
		}
		System.out
				.println("Completed Cuting hair of Customer : " + customer.getName() + " in " + duration + " seconds.");
	}

	public void add(Customer customer) {
		System.out.println("Customer : " + customer.getName() + " entering the shop at " + customer.getInTime());

		synchronized (listCustomer) {
			if (listCustomer.size() == nchair) {
				System.out.println("No chair available for customer " + customer.getName());
				System.out.println("Customer " + customer.getName() + "Exists...");
				return;
			}


			
			((LinkedList<Customer>) listCustomer) .offer(customer);
			System.out.println("Customer : " + customer.getName() + " got the chair.");

			if (listCustomer.size() == 1)
				listCustomer.notify();
		}
	}
}