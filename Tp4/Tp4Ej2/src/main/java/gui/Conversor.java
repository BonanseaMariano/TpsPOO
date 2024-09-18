package gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class Conversor extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    StageManager.setStage(primaryStage);
    StageManager.loadScene("/views/ConversorUnidades.fxml");
  }
}
