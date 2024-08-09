package models;

import java.util.ArrayList;
import java.util.List;

public class Alumno {
    private int legajo;
    private String nombre;
    private String apellido;
    private List<Materia> materias;

    public Alumno(int legajo, String nombre, String apellido) {
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
        return mensaje + "}";
    }

    protected void addMateria(Materia materia) {
        this.materias.add(materia);
    }
}
