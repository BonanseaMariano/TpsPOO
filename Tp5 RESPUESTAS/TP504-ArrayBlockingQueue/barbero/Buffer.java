package barbero;
public interface Buffer {
	
	public void set(Cliente documento) throws InterruptedException;

	public Cliente get() throws InterruptedException;
	
	public int size();
}
