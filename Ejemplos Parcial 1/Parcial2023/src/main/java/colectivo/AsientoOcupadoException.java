package colectivo;

public class AsientoOcupadoException extends RuntimeException {
    public AsientoOcupadoException() {
        super("Asiento ocupado");
    }
}
