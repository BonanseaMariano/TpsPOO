package models;

import java.util.Objects;

public class Cliente {
    private int codigo;
    private String nombre;
    private String CUIT;
    private String telefono;
    private String direccion;

    public Cliente(int codigo, String nombre, String CUIT, String telefono, String direccion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.CUIT = CUIT;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCUIT() {
        return CUIT;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente cliente)) return false;
        return codigo == cliente.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "codigo=" + codigo +
                ", CUIT=" + CUIT +
                ", telefono=" + telefono +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
