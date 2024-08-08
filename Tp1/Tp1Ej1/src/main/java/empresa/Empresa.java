package empresa;

import models.Departamento;
import models.Empleado;

public class Empresa {
    private static Departamento[] departamentos;
    private static Empleado[] empleados;

    public static void main(String[] args) {
        departamentos = new Departamento[3];
        empleados = new Empleado[8];

        departamentos[0] = new Departamento(1, "Gerencia");
        departamentos[1] = new Departamento(2, "Produccion");
        departamentos[2] = new Departamento(3, "Ventas");

        empleados[0] = new Empleado(10, "Pepito", null, departamentos[0]);
        empleados[1] = new Empleado(20, "Pepita", null, departamentos[0]);
        empleados[2] = new Empleado(30, "Carlos", null, departamentos[1]);
        empleados[3] = new Empleado(40, "Carla", null, departamentos[1]);
        empleados[4] = new Empleado(50, "Roberto", null, departamentos[2]);
        empleados[5] = new Empleado(60, "Roberta", null, departamentos[2]);
    }
}
