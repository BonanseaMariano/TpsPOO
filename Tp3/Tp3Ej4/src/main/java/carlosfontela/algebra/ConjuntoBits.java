// ConjuntoBits.java

package carlosfontela.algebra;

public class ConjuntoBits extends BaseConjunto {

    private long implementacion;
    private int base;

    public ConjuntoBits(int base) {
        if (base > 0 && base < 65)
            this.base = base;
        else base = 64;
        implementacion = 0;
    }

    public int getBase() {
        return base;
    }

    // potencia de 2^x
    private static long potencia2(int x) {
        if (x >= 0 && x <= 64) {
            long potencia = 1;
            for (int i = 1; i <= x; i++)
                potencia *= 2;
            return potencia;
        } else return -1;
    }

    public boolean pertenece(int x) {
        // debemos ver si hay un uno (1) en la posición x
        // número que representa un uno (1) en la posición x
        long numeroX = potencia2(x);
        // hacemos un AND bit a bit
        long and = numeroX & implementacion;
        // si el AND es igual al numeroX, entonces había un 1 en la posición x
        if (and == numeroX)
            return true;
        else return false;
    }

    public boolean vacio() {
        return (implementacion == 0);
    }

    public void vaciar() {
        implementacion = 0;
    }

    public void agregarElemento(int x) {
        if (x >= 0 && x < this.getBase()) {
            // debemos agregar un uno (1) en la posición x
            // número que representa un uno (1) en la posición x
            long numeroX = potencia2(x);
            // ahora hacemos el OR bit a bit:
            implementacion |= numeroX;
        }
    }

    public void sacarElemento(int x) {
        if (x >= 0 && x < this.getBase()) {
            // debemos agregar un cero (0) en la posición x
            // número que representa un uno (1) en la posición x
            long numeroX = potencia2(x);
            // número que representa un 0 en la posición x y 1 en las otras
            long numeroX0 = ~numeroX;
            // hacemos un AND bit a bit y ése es el resultado
            implementacion &= numeroX0;
        }
    }

    public int cardinalidad() {
        // debemos contar los unos (1) del número binario:
        int unos = 0;
        long temp = implementacion;
        while (temp > 0) {
            if (temp % 2 == 1)
                unos++;
            temp /= 2;
        }
        return unos;
    }

    @Override
    public Conjunto diferenciaSimetrica(Conjunto b) {
        return diferencia(b).union(b.diferencia(this));
    }

}
