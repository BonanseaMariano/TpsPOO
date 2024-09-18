package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import managers.StageManager;
import models.Empleado;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class TablaEmpleadosController implements Initializable {

    @FXML
    private TableView<Empleado> empleadosTable;

    @FXML
    private TableColumn<Empleado, String> nombreColumn;

    @FXML
    private TableColumn<Empleado, String> apellidoColumn;

    @FXML
    private TableColumn<Empleado, String> documentoColumn;

    @FXML
    private TableColumn<Empleado, Date> fechaNacimientoColumn;

    @FXML
    private TableColumn<Empleado, Double> salarioColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        apellidoColumn.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        documentoColumn.setCellValueFactory(new PropertyValueFactory<>("documento"));
        fechaNacimientoColumn.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        salarioColumn.setCellValueFactory(new PropertyValueFactory<>("salario"));

        // Hacer las columnas editables
        empleadosTable.setEditable(true);
        nombreColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        apellidoColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        fechaNacimientoColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));
        salarioColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        // Añadir eventos de edición
        nombreColumn.setOnEditCommit(this::modificacionNombre);
        apellidoColumn.setOnEditCommit(this::modificacionApellido);
        fechaNacimientoColumn.setOnEditCommit(this::modificacionFechaNac);
        salarioColumn.setOnEditCommit(this::modificacionSalario);

        empleadosTable.setItems(getEmpleadosList());

        TableColumn<Empleado, Void> colBtn = new TableColumn<>("Acciones");

        Callback<TableColumn<Empleado, Void>, TableCell<Empleado, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Empleado, Void> call(final TableColumn<Empleado, Void> param) {
                final TableCell<Empleado, Void> cell = new TableCell<>() {

                    private final Button btn = new Button("Eliminar");

                    {
                        btn.setOnAction(event -> {
                            Empleado empleado = getTableView().getItems().get(getIndex());
                            eliminarEmpleado(empleado);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                cell.setStyle("-fx-alignment: CENTER;");
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);
        empleadosTable.getColumns().add(colBtn);
    }

    private ObservableList<Empleado> getEmpleadosList() {
        return FXCollections.observableArrayList(StageManager.getEmpleados());
    }

    private void eliminarEmpleado(Empleado empleado) {
        StageManager.getEmpleados().remove(empleado);
        empleadosTable.setItems(getEmpleadosList());
    }

    @FXML
    public void modificacionNombre(TableColumn.CellEditEvent<Empleado, String> event) {
        Empleado empleado = event.getRowValue();
        String nuevoNombre = event.getNewValue();
        if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
            empleado.setNombre(nuevoNombre);
            StageManager.updateEmpleado(empleado);
        } else {
            event.consume(); // No aplicar cambios
        }
    }

    @FXML
    public void modificacionApellido(TableColumn.CellEditEvent<Empleado, String> event) {
        Empleado empleado = event.getRowValue();
        String nuevoApellido = event.getNewValue();
        if (nuevoApellido != null && !nuevoApellido.trim().isEmpty()) {
            empleado.setApellido(nuevoApellido);
            StageManager.updateEmpleado(empleado);
        } else {
            event.consume(); // No aplicar cambios
        }
    }

    @FXML
    public void modificacionFechaNac(TableColumn.CellEditEvent<Empleado, Date> event) {
        Empleado empleado = event.getRowValue();
        Date nuevaFechaNac = event.getNewValue();
        if (nuevaFechaNac != null) {
            empleado.setFechaNacimiento(nuevaFechaNac);
            StageManager.updateEmpleado(empleado);
        } else {
            event.consume(); // No aplicar cambios
        }
    }

    @FXML
    public void modificacionSalario(TableColumn.CellEditEvent<Empleado, Double> event) {
        Empleado empleado = event.getRowValue();
        Double nuevoSalario = event.getNewValue();
        if (nuevoSalario != null && nuevoSalario > 0) {
            empleado.setSalario(nuevoSalario);
            StageManager.updateEmpleado(empleado);
        } else {
            event.consume(); // No aplicar cambios
        }
    }

    public static class DateStringConverter extends StringConverter<Date> {
        private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        @Override
        public String toString(Date date) {
            return date != null ? dateFormat.format(date) : "";
        }

        @Override
        public Date fromString(String string) {
            try {
                return string != null && !string.isEmpty() ? dateFormat.parse(string) : null;
            } catch (ParseException e) {
                return null;
            }
        }
    }
}