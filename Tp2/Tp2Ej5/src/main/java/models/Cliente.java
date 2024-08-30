package models;

import exceptions.ClienteMaxCuentasException;
import exceptions.SaldoInsuficienteException;

public abstract class Cliente {

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

    public void setDireccion(Domicilio valor) {
        direccion = valor;
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

    public void agregarCuenta(CuentaBancaria cuenta) throws ClienteMaxCuentasException {
        if (cantidadCuentas >= maximoCuentas) throw new ClienteMaxCuentasException();
        cuentas[cantidadCuentas] = cuenta;
        cantidadCuentas++;
    }

    public int getCantidadCuentas() {
        return cantidadCuentas;
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
        //El importe supera el saldo disponible
        if (saldoDisponibleTotal() < importe) throw new SaldoInsuficienteException("Saldo: " + saldoDisponibleTotal());

        double importeRestante = importe;

        // Primero se debita de las cajas de ahorro
        for (int i = 0; importeRestante > 0 && i < getCantidadCuentas(); i++) {
            if (getCuentas()[i] instanceof CajaAhorro) {
                CajaAhorro caja = (CajaAhorro) getCuentas()[i];
                if (importeRestante >= caja.saldoDisponible()) { // El dinero de la cuenta no alcanza
                    caja.extraer(caja.saldoDisponible());
                    importeRestante -= caja.saldoDisponible();
                } else { // El dinero de la cuenta alcanza
                    caja.extraer(importeRestante);
                    importeRestante = 0;
                }
            }
        }

        // Luego se debita de las cuentas corrientes
        for (int i = 0; importeRestante > 0 && i < getCantidadCuentas(); i++) {
            if (getCuentas()[i] instanceof CuentaCorriente) {
                CuentaCorriente cuenta = (CuentaCorriente) getCuentas()[i];
                if (importeRestante >= cuenta.saldoDisponible()) { // El dinero de la cuenta no alcanza
                    cuenta.extraer(cuenta.getSaldo());
                    importeRestante -= cuenta.getSaldo();
                } else { // El dinero de la cuenta alcanza
                    cuenta.extraer(importeRestante);
                    importeRestante = 0;
                }
            }
        }
    }

    private class Domicilio {
        String calle;
        int numero;
        String entre1;
        String entre2;
        String codigoPostal;
        String telefono;

        private Domicilio(String calle, int numero, String entre1, String entre2,
                          String codigoPostal, String telefono) {
            this.calle = calle;
            this.numero = numero;
            this.entre1 = entre1;
            this.entre2 = entre2;
            this.codigoPostal = codigoPostal;
            this.telefono = telefono;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String valor) {
            telefono = valor;
        }

        public String getCalle() {
            return calle;
        }

        public String getCodigoPostal() {
            return codigoPostal;
        }

        public String[] getEntreCalles() {
            String[] entre = new String[2];
            entre[0] = entre1;
            entre[1] = entre2;
            return entre;
        }

        public int getNumero() {
            return numero;
        }
    }
}
