package models;

import java.util.ArrayList;
import java.util.List;

public class Alumno extends DatosPersonal {
    private int legajo;
    private String nombre;
    private String apellido;
    private List<Materia> materias;

    public Alumno(String calle, String ciudad, int telefono, String email, int legajo, String nombre, String apellido) {
        super(calle, ciudad, telefono, email);
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.materias = new ArrayList<>();
    }

    public int getLegajo() {
        return legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    @Override
    public String toString() {
        String mensaje = "Alumno{" +
                "legajo=" + legajo +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'';
        for (Materia m : this.materias) {
            mensaje += ", materia=" + m.getCodigo();
        }
        return mensaje + ", " + super.toString() + "}";
    }

    protected void addMateria(Materia materia) {
        this.materias.add(materia);
    }
}
