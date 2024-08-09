package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void AddCorrelativa(Materia materia, Materia correlativa) {
        for (Materia m : this.materias) {
            if (m.equals(materia)) {
                m.addCorrelativa(correlativa);
                return;
            }
        }
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

    public List<Materia> materiasDeProfesor(Profesor profesor) {

        for (Profesor p : this.profesores) {
            if (p.equals(profesor)) {
                return p.getMaterias();
            }
        }
        return null;
    }

    public int cantidadAlumnosMateria(Materia materia) {
        int cant = 0;
        for (Alumno a : this.alumnos) {
            if (a.getMaterias().contains(materia)) {
                cant++;
            }
        }
        return cant;
    }

    public Map<Materia, Integer> cantidadAlumnosPorMateria() {
        Map<Materia, Integer> map = new HashMap<>();
        for (Materia m : this.materias) {
            map.put(m, this.cantidadAlumnosMateria(m));
        }
        return map;
    }

}
