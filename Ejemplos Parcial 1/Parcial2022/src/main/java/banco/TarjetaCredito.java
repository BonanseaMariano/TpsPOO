package banco;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TarjetaCredito extends Tarjeta {
    private double limite;
    private List<Movimiento> movimientos;

    public TarjetaCredito(CajaAhorro cajaAhorro, int numeroTarjeta, Titular titular, LocalDate validaDesde, LocalDate validaHasta, double limite) {
        super(cajaAhorro, numeroTarjeta, titular, validaDesde, validaHasta);
        this.limite = limite;
        this.movimientos = new ArrayList<>();
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    @Override
    public String toString() {
        return super.toString() +
                "{ limite=" + limite +
                ", movimientos=" + movimientos +
                '}';
    }

    @Override
    public void realizarPago(LocalDate fecha, String detalle, double importe) throws SaldoInsuficienteException {
        verificarTarjeta();
        // La suma de todas las compras realizadas más el importe a pagar no supera el límite de la tarjeta
        if (importe > obtenerSaldo()) {
            throw new SaldoInsuficienteException();
        }

        getCajaAhorro().setSaldo(getCajaAhorro().getSaldo() - importe);
        movimientos.add(new Movimiento(fecha, detalle, importe));
    }

    @Override
    public double obtenerSaldo() {
        double total = 0;
        for (Movimiento movimiento : movimientos) {
            total += movimiento.getImporte();
        }
        return limite - total;
    }
}
