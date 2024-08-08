package models;

import java.util.Objects;

public class Empleado {
    private int legajo;
    private String nombre;
    private Empleado supervisor;
    private Departamento departamento;

    public Empleado(int legajo, String nombre, Empleado supervisor, Departamento departamento) {
        this.legajo = legajo;
        this.nombre = nombre;
        this.supervisor = supervisor;
        this.departamento = departamento;
    }

    public int getLegajo() {
        return this.legajo;
    }

    public String getNom() {
        return this.nombre;
    }

    public Empleado getSupervisor() {
        return this.supervisor;
    }

    public Departamento getDepartamento() {
        return this.departamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Empleado empleado)) return false;
        return legajo == empleado.legajo;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(legajo);
    }
}
