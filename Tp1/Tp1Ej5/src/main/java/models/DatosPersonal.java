package models;

public abstract class DatosPersonal {
    private String calle;
    private String ciudad;
    private int telefono;
    private String email;

    public DatosPersonal(String calle, String ciudad, int telefono, String email) {
        this.calle = calle;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.email = email;
    }

    public String getCalle() {
        return calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "calle='" + calle + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", telefono=" + telefono +
                ", email='" + email;
    }
}
