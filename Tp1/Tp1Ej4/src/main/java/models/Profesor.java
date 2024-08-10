package models;

import exceptions.MateriaRepetidaException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Profesor {
    private int legajo;
    private String nombre;
    private String apellido;
    private List<Materia> materias;

    public Profesor(int legajo, String nombre, String apellido, Materia materia) {
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.materias = new ArrayList<>();
        agregarMateria(materia);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profesor profesor)) return false;
        return legajo == profesor.legajo;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(legajo);
    }

    @Override
    public String toString() {
        String mensaje = "Profesor{" +
                "legajo=" + legajo +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'';
        for (Materia m : this.materias) {
            mensaje += ", materia=" + m.getCodigo();
        }
        return mensaje + "}";
    }

    public void agregarMateria(Materia materia) throws MateriaRepetidaException {
        if (materias.contains(materia)) {
            throw new MateriaRepetidaException();
        }
        this.materias.add(materia);
    }
}
