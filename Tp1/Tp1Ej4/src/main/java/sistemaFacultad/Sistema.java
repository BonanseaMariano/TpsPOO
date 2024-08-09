package sistemaFacultad;

import models.Alumno;
import models.Facultad;
import models.Materia;
import models.Profesor;

public class Sistema {
    private static Alumno a1 = new Alumno(1, "Pepito", "Perez");
    private static Alumno a2 = new Alumno(2, "Pepita", "Perez");
    private static Alumno a3 = new Alumno(3, "Carlitos", "Perez");

    private static Materia m1 = new Materia(1, "Matematicas");
    private static Materia m2 = new Materia(2, "Fisica");
    private static Materia m3 = new Materia(3, "Quimica");

    private static Profesor p1 = new Profesor(1, "Juan", "Alvarez", m1);
    private static Profesor p2 = new Profesor(2, "Pedro", "Gonzalez", m3);

    public static void main(String[] args) {
        Facultad facultad = new Facultad();
        cargarDatosFacultad(facultad);
        System.out.println(facultad);
    }

    private static void cargarDatosFacultad(Facultad facultad) {
        //Materias
        facultad.addMateria(m1);
        facultad.addMateria(m2);
        facultad.addMateria(m3);
        //Alumnos
        facultad.addAlumno(a1);
        facultad.addMateriaAlumno(a1, m1);
        facultad.addMateriaAlumno(a1, m2);
        facultad.addMateriaAlumno(a1, m3);
        facultad.addAlumno(a2);
        facultad.addMateriaAlumno(a2, m1);
        facultad.addMateriaAlumno(a2, m2);
        facultad.addAlumno(a3);
        //Profesores
        facultad.addProfesor(p1);
        facultad.addMateriaProfesor(p1, m2);
        facultad.addProfesor(p2);

    }
}
