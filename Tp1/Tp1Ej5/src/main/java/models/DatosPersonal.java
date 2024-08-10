package models;

public abstract class DatosPersonal {
    private String calle;
    private String ciudad;
    private String telefono;
    private String email;

    public DatosPersonal(String calle, String ciudad, String telefono, String email) {
        this.calle = calle;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.email = email;
    }

    @Override
    public String toString() {
        return "calle='" + calle + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", telefono=" + telefono +
                ", email='" + email;
    }
}
