package managers;

import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Empleado;

public class StageManager {
  public static Stage stage;
  private static List<Empleado> empleados;

  public StageManager() {
    empleados = new ArrayList<>();
    stage = new Stage();
    stage.setTitle("Empleados");
    initializeStage();
  }

  public static void updateEmpleado(Empleado empleado) {
    empleados.set(empleados.indexOf(empleado), empleado);
  }

  private void initializeStage() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MainPanel.fxml"));
      Parent root = loader.load();
      Scene scene = new Scene(root, 600, 400);
      stage.setScene(scene);
      stage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void agregarEmpleado(Empleado empleado) {
    empleados.add(empleado);
  }

  public static List<Empleado> getEmpleados() {
    return empleados;
  }
}
