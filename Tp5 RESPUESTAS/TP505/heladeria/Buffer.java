package heladeria;
public interface Buffer {
	public void set(Cliente value) throws InterruptedException;

	public Cliente get() throws InterruptedException;
}
