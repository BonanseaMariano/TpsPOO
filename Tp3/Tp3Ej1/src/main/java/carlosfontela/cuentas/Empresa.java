package carlosfontela.cuentas;

import java.util.Objects;

public class Empresa extends Cliente {

    private String razonSocial;
    private String cuit;


    public Empresa(String razonSocial, String cuit, String calle, int numero,
                   String entre1, String entre2, String codigoPostal, String telefono, String email) {
        super(calle, numero, entre1, entre2, codigoPostal, telefono, email);
        this.razonSocial = razonSocial;
        this.cuit = cuit;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public String getCuit() {
        return cuit;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Empresa empresa)) return false;
        return Objects.equals(cuit, empresa.cuit);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cuit);
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "razonSocial='" + razonSocial + '\'' +
                ", cuit='" + cuit + '\'' +
                '}';
    }
}
