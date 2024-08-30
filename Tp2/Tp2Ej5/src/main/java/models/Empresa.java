package models;

public class Empresa extends Cliente {
	
	private String razonSocial;
	private String cuit;
	
	
	public Empresa (String razonSocial, String cuit, String calle, int numero,
			String entre1, String entre2, String codigoPostal, String telefono, String email) {
		super (calle, numero, entre1, entre2, codigoPostal, telefono, email);
		this.razonSocial = razonSocial;
		this.cuit = cuit;
	}
	
	public String getRazonSocial ( ) {
			return razonSocial;
	}

	public String getCuit ( ) {
			return cuit;
	}
}
