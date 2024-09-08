package carlosfontela.algebra;

import java.util.HashSet;
import java.util.Set;

public class ConjuntoColeccion extends BaseConjunto {

    private Set<Integer> implementacion;
    private int base;

    public ConjuntoColeccion(int base) {
        if (base > 0 && base < 65)
            this.base = base;
        else
            this.base = 64;
        implementacion = new HashSet<>();
    }

    public int getBase() {
        return base;
    }

    public boolean pertenece(int x) {
        return implementacion.contains(x);
    }

    public boolean vacio() {
        return implementacion.isEmpty();
    }

    public void vaciar() {
        implementacion.clear();
    }

    public void agregarElemento(int x) {
        if (x >= 0 && x < this.getBase())
            implementacion.add(x);
    }

    public void sacarElemento(int x) {
        implementacion.remove(x);
    }

    public int cardinalidad() {
        return implementacion.size();
    }

}