package club;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Club {
    private String nombre;
    private List<Socio> socios;
    private List<Barco> barcos;

    public Club(String nombre) {
        this.nombre = nombre;
        this.barcos = new ArrayList<>();
        this.socios = new ArrayList<>();
    }

    public Socio agregarSocio(int numero, String nombre) {
        Socio socio = new Socio(numero, nombre);
        if (!socios.contains(socio)) {
            socios.add(socio);
        }
        return socio;
    }

    public Velero agregarVelero(String nombre, String matricula, Socio socio, double manga, double eslora) {
        Velero velero = new Velero(nombre, matricula, socio, manga, eslora);
        if (!barcos.contains(velero)) {
            barcos.add(velero);
        }
        return velero;
    }

    public Lancha agregarLancha(String nombre, String matricula, Socio socio, double manga, double eslora, boolean guarderiaCubierta) {
        Lancha lancha = new Lancha(nombre, matricula, socio, manga, eslora, guarderiaCubierta);
        if (!barcos.contains(lancha)) {
            barcos.add(lancha);
        }
        return lancha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Socio> getSocios() {
        return socios;
    }

    public void setSocios(List<Socio> socios) {
        this.socios = socios;
    }

    public List<Barco> getBarcos() {
        return barcos;
    }

    public void setBarcos(List<Barco> barcos) {
        this.barcos = barcos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Club club)) return false;
        return Objects.equals(nombre, club.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    @Override
    public String toString() {
        return "Club{" +
                "nombre='" + nombre + '\'' +
                ", socios=" + socios +
                ", barcos=" + barcos +
                '}';
    }
}
