package colectivo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Viaje {
    private LocalDate fecha;
    private Recorrido recorrido;
    private Colectivo colectivo;
    private List<Pasaje> pasajes;

    public Viaje(LocalDate fecha, Recorrido recorrido, Colectivo colectivo) {
        this.fecha = fecha;
        this.recorrido = recorrido;
        this.colectivo = colectivo;
        this.pasajes = new ArrayList<>();
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Recorrido getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(Recorrido recorrido) {
        this.recorrido = recorrido;
    }

    public Colectivo getColectivo() {
        return colectivo;
    }

    public void setColectivo(Colectivo colectivo) {
        this.colectivo = colectivo;
    }

    public List<Pasaje> getPasajes() {
        return pasajes;
    }


    @Override
    public String toString() {
        return "Viaje{" +
                "fecha=" + fecha +
                ", recorrido=" + recorrido +
                ", colectivo=" + colectivo +
                ", pasajes=" + pasajes +
                '}';
    }

    private class Pasaje {
        private String nombre;
        private String dni;
        private int asiento;
        private Tramo tramo;

        public Pasaje(Tramo tramo, int asiento, String dni, String nombre) {
            this.tramo = tramo;
            this.asiento = asiento;
            this.dni = dni;
            this.nombre = nombre;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getDni() {
            return dni;
        }

        public void setDni(String dni) {
            this.dni = dni;
        }

        public int getAsiento() {
            return asiento;
        }

        public void setAsiento(int asiento) {
            this.asiento = asiento;
        }

        public Tramo getTramo() {
            return tramo;
        }

        public void setTramo(Tramo tramo) {
            this.tramo = tramo;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pasaje pasaje)) return false;
            return Objects.equals(dni, pasaje.dni);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(dni);
        }

        @Override
        public String toString() {
            return "Pasaje{" +
                    "nombre='" + nombre + '\'' +
                    ", dni='" + dni + '\'' +
                    ", asiento=" + asiento +
                    ", tramo=" + tramo +
                    '}';
        }
    }

    /**
     * Asientos libres en el colectivo
     *
     * @return retorna una lista con los números de asientos libre que hay en el
     * colectivo
     */
    public List<Integer> asientosLibres() {
        List<Integer> asientosLibres = new ArrayList<>();
        for (int i = 0; i < colectivo.getAsientos(); i++) {
            asientosLibres.add(i + 1);
        }
        for (Pasaje pasaje : pasajes) {
            asientosLibres.remove((Integer) pasaje.getAsiento());
        }
        return asientosLibres;
    }

    /**
     * Vende un pasaje del viaje a un pasajero para un tramo y asiento dado
     *
     * @throws RecorridoNoValidoException() El colectivo no pasa por el tramo
     *                                      solicitado o no para en las localidades
     *                                      del mismo (utilizar el método "tramos"
     *                                      ya implementado para la validación)
     * @throws AsientoOcupadoException()    El asiento ya está vendido
     */
    public void venderPasaje(String nombre, String dni, Tramo tramo, int asiento) throws RecorridoNoValidoException, AsientoOcupadoException {
        if (recorrido.tramos(tramo.getOrigen(), tramo.getDestino()).isEmpty()) {
            throw new RecorridoNoValidoException();
        }
        if (!asientosLibres().contains(asiento)) {
            throw new AsientoOcupadoException();
        }
        pasajes.add(new Pasaje(tramo, asiento, dni, nombre));
    }

}
