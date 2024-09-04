package club;

public class Velero extends Barco {

    public Velero(String nombre, String matricula, Socio socio, double manga, double eslora) {
        super(nombre, matricula, socio, manga, eslora);
    }


    @Override
    public double calcularAlquilerGuarderia() {
        return (getManga() * getEslora()) * getValorAlquilerM2();
    }
}
