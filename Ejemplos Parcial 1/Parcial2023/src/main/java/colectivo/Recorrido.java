package colectivo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Recorrido {
    private int id;
    private List<Localidad> localidades;

    public Recorrido(int id, Localidad l1, Localidad l2) {
        this.id = id;
        this.localidades = new ArrayList<>();
        this.localidades.add(l1);
        this.localidades.add(l2);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Localidad> getLocalidades() {
        return localidades;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recorrido recorrido)) return false;
        return id == recorrido.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Recorrido{" +
                "id=" + id +
                ", localidades=" + localidades +
                '}';
    }

    public void agregarLocalidad(Localidad bbl) {
        this.localidades.add(bbl);
    }

    /**
     * Listado de todas las localidades por las que pasa el colectivo entre dos
     * localidades dadas
     *
     * @param origen:  localidad inicial del tramo
     * @param destino: localidad final del tramo
     * @return retorna una lista con todas las localidades de un tramo dado. En la
     * lista retornada incluye la localidad de origen y no incluye la
     * localidad de destino
     */
    public List<Localidad> tramos(Localidad origen, Localidad destino) {
        List<Localidad> resultado = new ArrayList<>();
        int inicio = localidades.indexOf(origen);
        int fin = localidades.indexOf(destino);


        if (inicio != -1 && fin != -1 && inicio < fin) {
            resultado = localidades.subList(inicio, fin);
        }

        return resultado;
    }

}
