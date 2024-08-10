package models;

import exceptions.MateriaRepetidaException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Alumno extends DatosPersonal {
    private int legajo;
    private String nombre;
    private String apellido;
    private List<Materia> materias;

    public Alumno(int legajo, String nombre, String apellido, String calle, String ciudad, String telefono, String email) {
        super(calle, ciudad, telefono, email);
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.materias = new ArrayList<>();
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alumno alumno)) return false;
        return legajo == alumno.legajo;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(legajo);
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

    public void agregarMateria(Materia materia) throws MateriaRepetidaException {
        if (materias.contains(materia)) {
            throw new MateriaRepetidaException();
        }
        this.materias.add(materia);
    }
}
