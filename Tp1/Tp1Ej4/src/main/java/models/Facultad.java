package models;

import exceptions.AlumnoRepetidoException;
import exceptions.MateriaRepetidaException;
import exceptions.ProfesorRepetidoException;

import java.util.ArrayList;
import java.util.List;

public class Facultad {
    private String nombre;
    private String ubicacion;
    private List<Profesor> profesores;
    private List<Alumno> alumnos;
    private List<Materia> materias;

    public Facultad(String nombre, String ubicacion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.profesores = new ArrayList<>();
        this.alumnos = new ArrayList<>();
        this.materias = new ArrayList<>();
    }

    @Override
    public String toString() {
        String mensaje = "\t---- Materias ----\n";
        for (Materia m : this.materias) {
            mensaje += m + "\n";
        }
        mensaje += "\t---- Profesores ----\n";
        for (Profesor p : this.profesores) {
            mensaje += p + "\n";
        }
        mensaje += "\t---- Alumnos ----\n";
        for (Alumno a : this.alumnos) {
            mensaje += a + "\n";
        }
        return mensaje;
    }

    public Materia agregarMateria(int codigo, String nombre) throws MateriaRepetidaException {
        Materia materia = new Materia(codigo, nombre);
        if (materias.contains(materia)) {
            throw new MateriaRepetidaException();
        }
        this.materias.add(materia);
        return materia;
    }

    public Alumno agregarAlumno(int legajo, String nombre, String apellido) throws AlumnoRepetidoException {
        Alumno alumno = new Alumno(legajo, nombre, apellido);
        if (alumnos.contains(alumno)) {
            throw new AlumnoRepetidoException();
        }
        this.alumnos.add(alumno);
        return alumno;
    }

    public Profesor agregarProfesor(int legajo, String nombre, String apellido, Materia materia) throws ProfesorRepetidoException {
        Profesor profesor = new Profesor(legajo, nombre, apellido, materia);
        if (profesores.contains(profesor)) {
            throw new ProfesorRepetidoException();
        }
        this.profesores.add(profesor);
        return profesor;
    }

}
