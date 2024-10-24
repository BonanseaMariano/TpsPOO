package models;

public class Empacadora implements Runnable {
    private final CintaEmpaque cintaEmpaque;
    private final int numEquipos; // Número de equipos productores
    private int equiposFinalizados = 0;

    public Empacadora(CintaEmpaque cintaEmpaque, int numEquipos) {
        this.cintaEmpaque = cintaEmpaque;
        this.numEquipos = numEquipos;
    }

    @Override
    public void run() {
        try {
            while (equiposFinalizados < numEquipos) { // Empacadora sigue trabajando hasta que todos los equipos terminen
                Producto producto = cintaEmpaque.retirarProducto(); // Usar método de la clase CintaEmpaque

                if (producto.getNombre().equals("FIN")) {
                    equiposFinalizados++; // Un equipo ha terminado
                    System.out.println("Un equipo ha terminado. Equipos finalizados: " + equiposFinalizados);
                } else {
                    // Empacar el producto
                    System.out.println("Empacadora empacó " + producto);
                    Thread.sleep(1000); // Empacar un producto cada segundo
                }
            }

            System.out.println("Todos los equipos han finalizado. Empacadora se detiene.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

