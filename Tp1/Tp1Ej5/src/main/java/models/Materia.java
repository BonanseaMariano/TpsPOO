package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Materia {
    private int codigo;
    private String nombre;
    private List<Materia> correlativas;

    public Materia(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.correlativas = new ArrayList<>();
    }

    public List<Materia> getCorrelativas() {
        return correlativas;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Materia materia)) return false;
        return codigo == materia.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }

    @Override
    public String toString() {
        return "Materia{" +
                "codigo=" + codigo +
                ", nombre=" + nombre +
                '}';
    }

    protected void addCorrelativa(Materia correlativa) {
        this.correlativas.add(correlativa);
    }
}
