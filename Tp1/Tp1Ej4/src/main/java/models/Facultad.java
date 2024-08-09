package models;

import java.util.ArrayList;
import java.util.List;

public class Facultad {
    private List<Profesor> profesores;
    private List<Alumno> alumnos;
    private List<Materia> materias;

    public Facultad() {
        this.profesores = new ArrayList<>();
        this.alumnos = new ArrayList<>();
        this.materias = new ArrayList<>();
    }

    public List<Profesor> getProfesores() {
        return profesores;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public List<Materia> getMaterias() {
        return materias;
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

    public void addMateria(Materia materia) {
        this.materias.add(materia);
    }

    public void addAlumno(Alumno alumno) {
        this.alumnos.add(alumno);
    }

    public void addProfesor(Profesor profesor) {
        this.profesores.add(profesor);
    }

    public void addMateriaProfesor(Profesor profesor, Materia materia) {
        for (Profesor p : this.profesores) {
            if (p.equals(profesor)) {
                p.addMateria(materia);
                return;
            }
        }
    }

    public void addMateriaAlumno(Alumno alumno, Materia materia) {
        for (Alumno a : this.alumnos) {
            if (a.equals(alumno)) {
                a.addMateria(materia);
                return;
            }
        }
    }
}
