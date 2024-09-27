package models;

public class Producto {
    private final String equipo;
    private final int id;

    public Producto(String equipo, int id) {
        this.equipo = equipo;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "equipo='" + equipo + '\'' +
                ", id=" + id +
                '}';
    }
}