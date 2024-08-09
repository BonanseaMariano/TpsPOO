package sistemaFacultad;

import models.Alumno;
import models.Facultad;
import models.Materia;
import models.Profesor;

import java.util.Map;

public class Sistema {
    private static Alumno a1 = new Alumno("Roca 123", "Rosario", 123456, "r@r.com", 1, "Juan", "Perez");
    private static Alumno a2 = new Alumno("Calle 123", "Rosario", 123456, "r@r.com", 2, "Pedro", "Gonzalez");
    private static Alumno a3 = new Alumno("Calle 234", "Rosario", 123456, "r@r.com", 3, "Maria", "Lopez");

    private static Materia m1 = new Materia(1, "Matematicas");
    private static Materia m2 = new Materia(2, "Fisica");
    private static Materia m3 = new Materia(3, "Quimica");

    private static Profesor p1 = new Profesor("Calle 123", "Rosario", 123456, "r@r.com", 1, "Ricardo", "Perez");
    private static Profesor p2 = new Profesor("Calle 234", "Rosario", 123456, "r@r.com", 2, "Lola", "Gonzalez");

    public static void main(String[] args) {
        Facultad facultad = new Facultad();
        cargarDatosFacultad(facultad);
        System.out.println(facultad);
        cPorM(facultad);
        mDP(facultad, p1);
        cDeM(facultad, m3);
    }

    private static void cargarDatosFacultad(Facultad facultad) {
        //Materias
        facultad.addMateria(m1);
        facultad.addMateria(m2);
        facultad.addMateria(m3);
        facultad.AddCorrelativa(m2, m1);
        facultad.AddCorrelativa(m3, m1);
        //Alumnos
        facultad.addAlumno(a1);
        facultad.addMateriaAlumno(a1, m2);
        facultad.addMateriaAlumno(a1, m3);
        facultad.addAlumno(a2);
        facultad.addMateriaAlumno(a2, m1);
        facultad.addAlumno(a3);
        //Profesores
        facultad.addProfesor(p1);
        facultad.addMateriaProfesor(p1, m2);
        facultad.addProfesor(p2);
    }

    private static void cPorM(Facultad facultad) {
        System.out.println("\t---- Cantidad de alumnos por materia ----");
        for (Map.Entry<Materia, Integer> e : facultad.cantidadAlumnosPorMateria().entrySet()) {
            System.out.println(e.getKey() + ": " + e.getValue() + " alumnos");
        }
    }

    private static void mDP(Facultad facultad, Profesor p) {
        System.out.println("\t---- Materias del profesor " + p.getNombre() + " ----");
        for (Materia m : facultad.materiasDeProfesor(p)) {
            System.out.println(m);
        }
    }

    private static void cDeM(Facultad facultad, Materia m) {
        System.out.println("\t---- Cantidad de alumnos de la materia " + m.getNombre() + " ----");
        System.out.println(facultad.cantidadAlumnosMateria(m));
    }
}
