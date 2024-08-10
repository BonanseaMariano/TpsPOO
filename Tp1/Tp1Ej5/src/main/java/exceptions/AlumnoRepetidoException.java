package exceptions;

public class AlumnoRepetidoException extends RuntimeException {

    public AlumnoRepetidoException() {
        super("Alumno ya existente");
    }
}
