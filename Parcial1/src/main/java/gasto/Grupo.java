package gasto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Grupo {
    private String nombre;
    private List<Persona> personas;
    private List<Gasto> gastos;
    private List<Pago> pagos;

    public Grupo(String nombre) {
        this.nombre = nombre;
        this.gastos = new ArrayList<>();
        this.pagos = new ArrayList<>();
        this.personas = new ArrayList<>();
    }

    public Persona agregarIntegrante(String nombre, String email) {
        Persona persona = new Persona(nombre, email);
        if (personas.contains(persona)) {
            throw new PersonaRepetidaException("La persona" + persona + " ya existe en el grupo");
        }
        personas.add(persona);
        return persona;
    }

    public Gasto agregarGasto(LocalDate fecha, String descripcion, Persona pagador) {
        Gasto gasto = new Gasto(fecha, descripcion, pagador);
        gastos.add(gasto);
        return gasto;
    }

    public Pago agregarPago(LocalDate fecha, Persona transfiere, Persona recibe, double importe) {
        Pago pago = new Pago(fecha, importe, transfiere, recibe);
        pagos.add(pago);
        return pago;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public List<Gasto> getGastos() {
        return gastos;
    }

    public List<Pago> getPagos() {
        return pagos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Grupo grupo)) return false;
        return Objects.equals(nombre, grupo.nombre) && Objects.equals(personas, grupo.personas) && Objects.equals(gastos, grupo.gastos) && Objects.equals(pagos, grupo.pagos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, personas, gastos, pagos);
    }

    @Override
    public String toString() {
        return "Grupo{" +
                "nombre='" + nombre + '\'' +
                ", personas=" + personas +
                ", gastos=" + gastos +
                ", pagos=" + pagos +
                '}';
    }

    /**
     * Suma todos los importes que pagó una persona
     *
     * @param persona: persona que realizó los pagos
     * @return importe total de todos los gastos que pagó una persona
     */
    public double totalPagado(Persona persona) {
        double total = 0;
        for (Gasto gasto : gastos) {
            if (gasto.getPagador().equals(persona)) {
                total += gasto.importePagado();
            }
        }
        return total;
    }

    /**
     * Suma todos los importes que gastó una persona
     *
     * @param persona: persona que realizó los gastos
     * @return importe total de todos los gastos que realizó una persona
     */
    public double totalGastado(Persona persona) {
        double total = 0;
        for (Gasto gasto : gastos) {
            total += gasto.importeGastado(persona);
        }
        return total;
    }

    /**
     * Retorna cuando le debe una persona a otra. Calcula todos los gastos
     * que le pago la persona1 a la persona2, los compensa con los gastos que pago la
     * persona2 a la persona1 y tiene en cuenta las transferencias que se hicieron
     * entre ambos
     *
     * @param persona1
     * @param persona2
     * @return saldo que tiene la persona1 con la persona2. El saldo puede ser:
     * <p>
     * acreedor: saldo positivo, la persona2 le debe a la persona1
     * <p>
     * deudor: saldo negativo, la persona1 le debe a la persona2
     * <p>
     * nulo: saldo cero, no hay deudas entre la persona1 y la persona2
     */
    public double saldo(Persona persona1, Persona persona2) {
        double total = 0;

        //Gastos
        for (Gasto gasto : gastos) {
            if (gasto.getPagador().equals(persona1)) {
                total += gasto.importeGastado(persona2);
            }
            if (gasto.getPagador().equals(persona2)) {
                total -= gasto.importeGastado(persona1);
            }
        }

        //Pagos
        for (Pago pago : pagos) {
            if (pago.getTransfiere().equals(persona1) && pago.getRecibe().equals(persona2)) {
                total += pago.getImporte();
            }
            if (pago.getTransfiere().equals(persona2) && pago.getRecibe().equals(persona1)) {
                total -= pago.getImporte();
            }
        }

        return total;
    }

}
