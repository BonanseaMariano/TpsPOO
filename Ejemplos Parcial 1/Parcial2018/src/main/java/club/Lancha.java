package club;

public class Lancha extends Barco {
    private static double porcGuarderiaCubierta = 0.5;
    private boolean guarderiaCubierta;

    public Lancha(String nombre, String matricula, Socio socio, double manga, double eslora, boolean guarderiaCubierta) {
        super(nombre, matricula, socio, manga, eslora);
        this.guarderiaCubierta = guarderiaCubierta;
    }

    public static double getPorcGuarderiaCubierta() {
        return porcGuarderiaCubierta;
    }

    public static void setPorcGuarderiaCubierta(double porcGuarderiaCubierta) {
        Lancha.porcGuarderiaCubierta = porcGuarderiaCubierta;
    }

    public boolean isGuarderiaCubierta() {
        return guarderiaCubierta;
    }

    public void setGuarderiaCubierta(boolean guarderiaCubierta) {
        this.guarderiaCubierta = guarderiaCubierta;
    }

    @Override
    public String toString() {
        return "Lancha{" +
                "guarderiaCubierta=" + guarderiaCubierta +
                '}';
    }

    @Override
    public double calcularAlquilerGuarderia() {
        double total = (getManga() * getEslora()) * getValorAlquilerM2();
        if (guarderiaCubierta) {
            total += total * porcGuarderiaCubierta;
        }
        return 0;
    }
}
