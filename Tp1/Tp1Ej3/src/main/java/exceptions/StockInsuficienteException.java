package exceptions;

public class StockInsuficienteException extends Exception {
    public StockInsuficienteException() {
        super("No hay suficiente stock");
    }
}
