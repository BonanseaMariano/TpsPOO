package taxi;

public interface Buffer {
	public void set(Taxi taxi) throws InterruptedException;

	public Taxi get() throws InterruptedException;	
}
