package colectivo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Empresa {
    private String nombre;
    private String cuit;
    private List<Tramo> tramos;
    private List<Viaje> viajes;

    public Empresa(String nombre, String cuit) {
        this.nombre = nombre;
        this.cuit = cuit;
        this.tramos = new ArrayList<>();
        this.viajes = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public List<Tramo> getTramos() {
        return tramos;
    }

    public List<Viaje> getViajes() {
        return viajes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Empresa empresa)) return false;
        return Objects.equals(cuit, empresa.cuit);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cuit);
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "nombre='" + nombre + '\'' +
                ", cuit='" + cuit + '\'' +
                ", tramos=" + tramos +
                ", viajes=" + viajes +
                '}';
    }

    public void agregarTramo(Localidad bas, Localidad azu, int i) {
        this.tramos.add(new Tramo(i, bas, azu));
    }

    public Viaje agregarViaje(Colectivo c1, Recorrido r1, LocalDate of) {
        Viaje viaje = new Viaje(of, r1, c1);
        this.viajes.add(viaje);
        return viaje;
    }

    /**
     * Tramo que contiene el precio del pasaje para las localidades ingresadas
     *
     * @param origen:  localidad donde sube el pasajero
     * @param destino: localidad donde baja el pasajero
     * @return tramo que contiene el precio del pasaje o null si la empresa no vende
     * pasajes para las localidades ingresadas
     */
    public Tramo tramoPasaje(Localidad origen, Localidad destino) {
        for (Tramo tramo : tramos) {
            if (tramo.getOrigen().equals(origen) && tramo.getDestino().equals(destino)) {
                return tramo;
            }
        }
        return null;
    }

    /**
     * Viajes que salen en la fecha dada y pasan por el tramo indicado
     *
     * @param fecha:   fecha en la que realiza el viaje
     * @param origen:  localidad donde sube el pasajero
     * @param destino: localidad donde baja el pasajero
     * @return Lista de todos los viajes que cumplen con la condición solicitada.
     * Retorna null si la empresa no vende pasajes para las localidades
     * ingresadas (utilizar el método "tramos" ya implementado para la
     * validación)
     */
    public List<Viaje> viaje(LocalDate fecha, Localidad origen, Localidad destino) {
        List<Viaje> viajes = new ArrayList<>();

        if (tramoPasaje(origen, destino) == null) {
            return null;
        }

        for (Viaje viaje : this.viajes) {
            if (viaje.getFecha().equals(fecha) && !viaje.getRecorrido().tramos(origen, destino).isEmpty()) {
                viajes.add(viaje);
            }
        }
        return viajes;
    }

}
