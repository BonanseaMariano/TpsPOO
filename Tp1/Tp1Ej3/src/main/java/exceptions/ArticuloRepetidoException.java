package exceptions;

public class ArticuloRepetidoException extends Exception {
    public ArticuloRepetidoException() {
        super("El articulo ya existe");
    }
}
