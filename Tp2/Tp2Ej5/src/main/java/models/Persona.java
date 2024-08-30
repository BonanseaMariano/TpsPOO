package models;

public class Persona extends Cliente {

	private String nombre;
	private String apellido;
	private int dni;

	public Persona (String nombre, String apellido, int dni, 
				String calle, int numero, String entre1, String entre2,
					String codigoPostal, String telefono, String email) {
		super (calle, numero, entre1, entre2, codigoPostal, telefono, email);
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
	}

	public String getApellido ( ) {
			return apellido;
	}

	public String getNombre ( ) {
			return nombre;
	}
	
	public int getDni ( ) {
			return dni;
	}
}
