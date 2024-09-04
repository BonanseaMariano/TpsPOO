package club;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Socio {
    private static double cuotaSocial;
    private List<Barco> barcos;
    private int numero;
    private String nombre;

    public Socio(int numero, String nombre) {
        this.numero = numero;
        this.nombre = nombre;
        this.barcos = new ArrayList<>();
    }

    public static double getCuotaSocial() {
        return cuotaSocial;
    }

    public static void setCuotaSocial(double cuota) {
        cuotaSocial = cuota;
    }

    public List<Barco> getBarcos() {
        return barcos;
    }

    public void setBarcos(List<Barco> barcos) {
        this.barcos = barcos;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Socio socio)) return false;
        return numero == socio.numero;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numero);
    }

    @Override
    public String toString() {
        return "Socio{" +
                "cuotaSocial=" + cuotaSocial +
                ", barcos=" + barcos +
                ", numero=" + numero +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    public double calcularCuota() {
        double total = this.cuotaSocial;
        for (Barco barco : this.barcos) {
            total += barco.calcularTarifa();
        }
        return total;
    }

    public void agregarBarco(Barco barco) {
        this.barcos.add(barco);
    }
}
