package models;

public class Cliente {
  private final int id;
  private final long tiempoLlegada;

  public Cliente(int id) {
    this.id = id;
    this.tiempoLlegada = System.currentTimeMillis();
  }

  public int getId() {
    return id;
  }

  public long getTiempoLlegada() {
    return tiempoLlegada;
  }
}
