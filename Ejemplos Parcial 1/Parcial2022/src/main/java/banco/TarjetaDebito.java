package banco;

import java.time.LocalDate;

public class TarjetaDebito extends Tarjeta {

    public TarjetaDebito(CajaAhorro cajaAhorro, int numeroTarjeta, Titular titular, LocalDate validaDesde, LocalDate validaHasta) {
        super(cajaAhorro, numeroTarjeta, titular, validaDesde, validaHasta);
    }

    @Override
    public void realizarPago(LocalDate fecha, String detalle, double importe) throws SaldoInsuficienteException {
        verificarTarjeta();
        if (importe > getCajaAhorro().getSaldo()) {
            throw new SaldoInsuficienteException();
        }
        getCajaAhorro().setSaldo(getCajaAhorro().getSaldo() - importe);
    }

    @Override
    public double obtenerSaldo() {
        return getCajaAhorro().getSaldo();
    }
}
