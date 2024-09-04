package club;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Barco {
    private static double valorAlquilerM2;
    private String nombre;
    private String matricula;
    private List<Socio> socios;
    private double manga;
    private double eslora;

    public Barco(String nombre, String matricula, Socio socio, double manga, double eslora) {
        this.nombre = nombre;
        this.matricula = matricula;
        this.socios = new ArrayList<>();
        agregarSocio(socio);
        this.manga = manga;
        this.eslora = eslora;
    }

    public double calcularTarifa() {
        return calcularAlquilerGuarderia() / socios.size();
    }

    public void agregarSocio(Socio socio) {
        if (!this.socios.contains(socio)) {
            socio.agregarBarco(this);
            this.socios.add(socio);
        }
    }

    public static double getValorAlquilerM2() {
        return valorAlquilerM2;
    }

    public static void setValorAlquilerM2(double valorAlquilerM2) {
        Barco.valorAlquilerM2 = valorAlquilerM2;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public List<Socio> getSocios() {
        return socios;
    }

    public void setSocios(List<Socio> socios) {
        this.socios = socios;
    }

    public double getManga() {
        return manga;
    }

    public void setManga(double manga) {
        this.manga = manga;
    }

    public double getEslora() {
        return eslora;
    }

    public void setEslora(double eslora) {
        this.eslora = eslora;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Barco barco)) return false;
        return Objects.equals(matricula, barco.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(matricula);
    }

    @Override
    public String toString() {
        return "Barco{" +
                "nombre='" + nombre + '\'' +
                ", matricula='" + matricula + '\'' +
                ", socios=" + socios +
                ", manga=" + manga +
                ", eslora=" + eslora +
                '}';
    }

    public abstract double calcularAlquilerGuarderia();
}
