package gasto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Gasto {
    private LocalDate fecha;
    private String descripcion;
    private Persona pagador;
    private List<Division> divisiones;

    public Gasto(LocalDate fecha, String descripcion, Persona pagador) {
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.pagador = pagador;
        this.divisiones = new ArrayList<>();
    }

    public void agregarDivision(Persona persona, double importe) {
        if (divisiones.contains(persona)) {
            throw new PersonaRepetidaException("La persona " + persona + " ya existe en el gasto");
        }
        divisiones.add(new Division(persona, importe));
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Persona getPagador() {
        return pagador;
    }

    public void setPagador(Persona pagador) {
        this.pagador = pagador;
    }

    public List<Division> getDivisiones() {
        return divisiones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gasto gasto)) return false;
        return Objects.equals(fecha, gasto.fecha) && Objects.equals(descripcion, gasto.descripcion) && Objects.equals(pagador, gasto.pagador) && Objects.equals(divisiones, gasto.divisiones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fecha, descripcion, pagador, divisiones);
    }

    @Override
    public String toString() {
        return "Gasto{" +
                "fecha=" + fecha +
                ", descripcion='" + descripcion + '\'' +
                ", divisiones=" + divisiones +
                '}';
    }

    // Inner class Division
    private class Division {
        private double importe;
        private Persona persona;

        public Division(Persona persona, double importe) {
            this.importe = importe;
            this.persona = persona;
        }

        public double getImporte() {
            return importe;
        }

        public void setImporte(double importe) {
            this.importe = importe;
        }

        public Persona getPersona() {
            return persona;
        }

        public void setPersona(Persona persona) {
            this.persona = persona;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Division division)) return false;
            return Double.compare(importe, division.importe) == 0 && Objects.equals(persona, division.persona);
        }

        @Override
        public int hashCode() {
            return Objects.hash(importe, persona);
        }

        @Override
        public String toString() {
            return "Division{" +
                    "importe=" + importe +
                    ", persona=" + persona +
                    '}';
        }
    }

    /**
     * Suma todos los importes que gasto cada integrante en un gasto en común
     *
     * @return importe total del gasto
     * <p>
     * Ejemplo: En el almuerzo Juan gasta $5000, Ana $3000 y Pedro $10000 el total
     * gastado es $18000
     */
    public double importePagado() {
        double total = 0;
        for (Division division : divisiones) {
            total += division.getImporte();
        }
        return total;
    }

    /**
     * Retorna cuanto gasto una persona dentro de un gasto en común
     *
     * @param persona
     * @return importe que gasto una persona dentro de un gasto en común. Si la
     * persona no participo en el gasto retorna cero
     * <p>
     * Ejemplo: En el almuerzo Juan gasta $5000, Ana $3000 y Pedro $10000 el total
     * gastado por Juan es $5000
     */
    public double importeGastado(Persona persona) {
        for (Division division : divisiones) {
            if (division.getPersona().equals(persona)) {
                return division.getImporte();
            }
        }
        return 0;
    }

}
