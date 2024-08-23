package carlosfontela.cuentas;

import java.util.Objects;

public class Persona extends Cliente {

    private String nombre;
    private String apellido;
    private int dni;

    public Persona(String nombre, String apellido, int dni,
                   String calle, int numero, String entre1, String entre2,
                   String codigoPostal, String telefono, String email) {
        super(calle, numero, entre1, entre2, codigoPostal, telefono, email);
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDni() {
        return dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona persona)) return false;
        return dni == persona.dni;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dni);
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni=" + dni + super.toString() +
                '}';
    }
}
