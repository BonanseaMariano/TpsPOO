package test;

import org.junit.Before;
import org.junit.Test;

import models.Alumno;
import exceptions.AlumnoRepetidoException;
import models.Facultad;
import models.Materia;
import exceptions.MateriaRepetidaException;
import models.Profesor;
import exceptions.ProfesorRepetidoException;

//Test ejercicio 14 con las modificaciones del ejercicio 15  

public class TestFacultadEje45 {

	private Facultad mifacultad = new Facultad("Ingenieria", "Puerto Madryn");

	private Alumno alumno1, alumno2, alumno3;
	private Materia materia1, materia2, materia3, materia4;
	private Profesor profesor1, profesor2, profesor3;

	@Before
	public void antesDelTest() {

		alumno1 = mifacultad.agregarAlumno(1,"Juan", "Perez","Roca 538", "Puerto Madryn","15486329", "elmail@hotmail.com");
		alumno2 = mifacultad.agregarAlumno(2, "Miguel","Sanchez","Villarino 238", "Puerto Madryn","15649829", "sumail@hotmail.com");
		alumno3 = mifacultad.agregarAlumno(3, "Nicolas","Fernandez","San Martin 228", "Trelew","15134872", "trelewalumno@gmail.com");	
			
		materia1 = mifacultad.agregarMateria(1, "Algebra");
		materia2 = mifacultad.agregarMateria(2, "Algebra 2");
		materia3 = mifacultad.agregarMateria(3, "Programacion");
		materia4 = mifacultad.agregarMateria(4, "Logica");

		profesor1 =  mifacultad.agregarProfesor(1,"Alvaro", "Dominguez",materia1,"Gales 538", "Puerto Madryn","15648978", "pmail@hotmail.com");
		profesor2 =  mifacultad.agregarProfesor(2,"Esteban", "Alosa",materia3,"Belgrano 18", "Trelew","15364988", "lalala@hotmail.com");
		profesor3 =  mifacultad.agregarProfesor(3,"Matias", "Gimenez",materia4,"Derbes 123", "Puerto Madryn","15326493", "profemail@gmail.com.ar");
	
		profesor1.agregarMateria(materia2);

		alumno1.agregarMateria(materia1);

		alumno2.agregarMateria(materia1);
		alumno2.agregarMateria(materia4);

		alumno3.agregarMateria(materia2);
		alumno3.agregarMateria(materia3);

	}

	@Test(expected = MateriaRepetidaException.class)
	public void agregarMateriaRepetidaAAlumno() {
		alumno1.agregarMateria(materia1);
	}

	@Test(expected = MateriaRepetidaException.class)
	public void agrgearMateriaRepetidaAProfesor() {
		profesor1.agregarMateria(materia2);
	}

	@Test(expected = MateriaRepetidaException.class)
	public void agregarMateriaRepetidaAFacultad() {
		mifacultad.agregarMateria(1, "Algebra");
	}

	@Test(expected = AlumnoRepetidoException.class)
	public void agregarAlumnoRepetidoAFacultad() {
		mifacultad.agregarAlumno(3, "Nicolas", "Fernandez", null, null, null, null);
	}

	@Test(expected = ProfesorRepetidoException.class)
	public void agregarProfesorRepetidoAFacultad() {
		mifacultad.agregarProfesor(3, "Matias", "Gimenez", materia4,"Derbes 123", "Puerto Madryn","15326493", "profemail@gmail.com.ar" );
	}

}
