package carlosfontela.cuentas;

import java.util.Arrays;
import java.util.Objects;

public abstract class Cliente implements OperacionBanco {

    private Domicilio direccion;
    private String email;
    private static int maximoCuentas = 10;
    private CuentaBancaria[] cuentas;

    private int cantidadCuentas;

    public Cliente(String calle, int numero, String entre1, String entre2,
                   String codigoPostal, String telefono, String email) {
        this.direccion = new Domicilio(calle, numero, entre1, entre2, codigoPostal, telefono);
        this.email = email;
        this.cuentas = new CuentaBancaria[maximoCuentas];
        this.cantidadCuentas = 0;
    }


    public Domicilio getDireccion() {
        return direccion;
    }

    public static int getMaximoCuentas() {
        return maximoCuentas;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String valor) {
        email = valor;
    }

    public CuentaBancaria[] getCuentas() {
        return cuentas;
    }

    public int getCantidadCuentas() {
        return cantidadCuentas;
    }

    public void agregarCuenta(CuentaBancaria cuenta) throws ClienteMaxCuentasException {
        if (cantidadCuentas >= maximoCuentas) throw new ClienteMaxCuentasException();
        cuentas[cantidadCuentas] = cuenta;
        cantidadCuentas++;
    }

    public static void setMaximoCuentas(int maximoCuentas) {
        Cliente.maximoCuentas = maximoCuentas;
    }

    public double saldoTotal() {
        double total = 0;
        for (int i = 0; i < getCantidadCuentas(); i++) {
            total += getCuentas()[i].getSaldo();
        }
        return total;
    }

    public double saldoDisponibleTotal() {
        double total = 0;
        for (int i = 0; i < getCantidadCuentas(); i++) {
            total += getCuentas()[i].saldoDisponible();
        }
        return total;
    }

    public void pagarTarjetaCredito(double importe) {
        if (saldoDisponibleTotal() < importe) throw new SaldoInsuficienteException("Saldo: " + saldoDisponibleTotal());

        double importeRestante = importe;

        //Primero se debita de las cajas de ahorro
        for (int i = 0; i < getCantidadCuentas(); i++) {
            if (getCuentas()[i] instanceof CajaAhorro) {
                CajaAhorro caja = (CajaAhorro) getCuentas()[i];
                if (importeRestante >= caja.saldoDisponible()) { //El dinero de la cuenta no alcanza
                    caja.extraer(caja.saldoDisponible());
                    importeRestante -= caja.saldoDisponible();
                } else { //El dinero de la cuenta alcanzo
                    caja.extraer(importeRestante);
                    return;
                }
            }
        }

        //Luego se debita de las cuentas corrientes
        for (int i = 0; i < getCantidadCuentas(); i++) {
            if (getCuentas()[i] instanceof CuentaCorriente) {
                CuentaCorriente caja = (CuentaCorriente) getCuentas()[i];
                if (importeRestante >= caja.saldoDisponible()) { //El dinero de la cuenta no alcanza
                    caja.extraer(caja.getSaldo());
                    importeRestante -= caja.getSaldo();
                } else { //El dinero de la cuenta alcanzo
                    caja.extraer(importeRestante);
                    return;
                }
            }
        }

    }

    //Inner Class
    private class Domicilio {
        private String calle;
        private int numero;
        private String entre1;
        private String entre2;
        private String codigoPostal;
        private String telefono;

        private Domicilio(String calle, int numero, String entre1, String entre2,
                          String codigoPostal, String telefono) {
            this.calle = calle;
            this.numero = numero;
            this.entre1 = entre1;
            this.entre2 = entre2;
            this.codigoPostal = codigoPostal;
            this.telefono = telefono;
        }

        public String getCalle() {
            return calle;
        }

        public void setCalle(String calle) {
            this.calle = calle;
        }

        public int getNumero() {
            return numero;
        }

        public void setNumero(int numero) {
            this.numero = numero;
        }

        public String getEntre1() {
            return entre1;
        }

        public void setEntre1(String entre1) {
            this.entre1 = entre1;
        }

        public String getEntre2() {
            return entre2;
        }

        public void setEntre2(String entre2) {
            this.entre2 = entre2;
        }

        public String getCodigoPostal() {
            return codigoPostal;
        }

        public void setCodigoPostal(String codigoPostal) {
            this.codigoPostal = codigoPostal;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Domicilio domicilio)) return false;
            return numero == domicilio.numero && Objects.equals(calle, domicilio.calle) && Objects.equals(codigoPostal, domicilio.codigoPostal);
        }

        @Override
        public int hashCode() {
            return Objects.hash(calle, numero, codigoPostal);
        }

        @Override
        public String toString() {
            return "calle='" + calle + '\'' +
                    ", numero=" + numero +
                    ", entre1='" + entre1 + '\'' +
                    ", entre2='" + entre2 + '\'' +
                    ", codigoPostal='" + codigoPostal + '\'' +
                    ", telefono='" + telefono;
        }
    }

    @Override
    public String toString() {
        String mensaje = direccion +
                ", email='" + email + '\'' +
                ", cuentas=";
        for (int i = 0; i < getCantidadCuentas(); i++) {
            mensaje += " '" + cuentas[i] + "',";
        }
        mensaje += ", cantidadCuentas=" + cantidadCuentas;
        return mensaje;
    }

    @Override
    public double obtenerSaldoDisponible() {
        return saldoDisponibleTotal();
    }

    @Override
    public double obtenerComision() {
        double total = 0;
        for (int i = 0; i < getCantidadCuentas(); i++) {
            total += cuentas[i].obtenerComision();
        }
        return total;
    }
}
