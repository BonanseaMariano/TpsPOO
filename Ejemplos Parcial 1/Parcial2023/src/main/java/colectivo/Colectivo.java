package colectivo;

import java.util.Objects;

public class Colectivo {
    private String patente;
    private int asientos;

    public Colectivo(String patente, int asientos) {
        this.patente = patente;
        this.asientos = asientos;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public int getAsientos() {
        return asientos;
    }

    public void setAsientos(int asientos) {
        this.asientos = asientos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Colectivo colectivo)) return false;
        return Objects.equals(patente, colectivo.patente);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(patente);
    }

    @Override
    public String toString() {
        return "Colectivo{" +
                "patente='" + patente + '\'' +
                ", asientos=" + asientos +
                '}';
    }
}
