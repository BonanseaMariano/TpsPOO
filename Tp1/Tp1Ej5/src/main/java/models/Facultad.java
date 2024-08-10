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

    public Alumno agregarAlumno(int legajo, String nombre, String apellido, String calle, String ciudad, String telefono, String email) throws AlumnoRepetidoException {
        Alumno alumno = new Alumno(legajo, nombre, apellido, calle, ciudad, telefono, email);
        if (alumnos.contains(alumno)) {
            throw new AlumnoRepetidoException();
        }
        this.alumnos.add(alumno);
        return alumno;
    }

    public Profesor agregarProfesor(int legajo, String nombre, String apellido, Materia materia, String calle, String ciudad, String telefono, String email) throws ProfesorRepetidoException {
        Profesor profesor = new Profesor(legajo, nombre, apellido, materia, calle, ciudad, telefono, email);
        if (profesores.contains(profesor)) {
            throw new ProfesorRepetidoException();
        }
        this.profesores.add(profesor);
        return profesor;
    }

    /**
     * Devuelve una lista de materias asociadas al profesor dado.
     *
     * @param profesor el profesor para el cual se recuperan las materias
     * @return una lista de materias asociadas al profesor, o null si el profesor no se encuentra
     */
    public List<Materia> materiasPorProfesor(Profesor profesor) {
        for (Profesor p : this.profesores) {
            if (p.equals(profesor)) {
                return p.getMaterias();
            }
        }
        return null;
    }

    /**
     * Devuelve una lista de alumnos matriculados en una materia específica.
     *
     * @param materia La materia de la que se recuperan los alumnos
     * @return Una lista de alumnos matriculados en la materia especificada
     */
    public List<Alumno> alumnosPorMateria(Materia materia) {
        List<Alumno> alumnos = new ArrayList<>();
        for (Alumno a : this.alumnos) {
            if (a.getMaterias().contains(materia)) {
                alumnos.add(a);
            }
        }
        return alumnos;
    }

    /**
     * Devuelve la cantidad total de alumnos matriculados en una materia determinada.
     *
     * @param materia la materia para la cual se cuenta la cantidad de alumnos
     * @return la cantidad total de alumnos matriculados en la materia
     */
    public int cantidadAlumnosPorMateria(Materia materia) {
        return alumnosPorMateria(materia).size();
    }
}
