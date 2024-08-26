// Conjunto.java
package carlosfontela.algebra;

public interface Conjunto {
    int getBase();    // los elementos del conjunto pueden valer entre 0 y getBase( )-1

    Conjunto union(Conjunto b);

    Conjunto interseccion(Conjunto b);

    Conjunto diferencia(Conjunto b);

    boolean pertenece(int x);

    boolean incluido(Conjunto b);

    boolean vacio();

    void vaciar();

    void agregarElemento(int x);

    void sacarElemento(int x);

    int cardinalidad();

    boolean igual(Conjunto b);

    Conjunto diferenciaSimetrica(Conjunto b);
}
