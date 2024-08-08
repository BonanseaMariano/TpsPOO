package models;

import java.util.Objects;

public class Departamento {
    private int codigo;
    private String nombre;

    public Departamento(int cod, String nom) {
        this.codigo = cod;
        this.nombre = nom;
    }

    public int getCod() {
        return this.codigo;
    }

    public String getNom() {
        return this.nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Departamento that)) return false;
        return codigo == that.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }
}
