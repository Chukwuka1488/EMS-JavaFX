package sample.ems.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import sample.ems.DatabaseConnection;
import sample.ems.model.EmployeesData;
import sample.ems.Main;


import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class EmployeesController implements Initializable {


    @FXML
    private Button addEmployeeButton;

    @FXML
    private Button deleteEmployeeButton;

    @FXML
    private Button backEmployeesButton;

    @FXML
    private Button exportButton;

    @FXML
    private Button importButton;

    @FXML
    private TableView<EmployeesData> employeeTableView;

    @FXML
    private TableColumn<EmployeesData, String> idTableColumn;

    @FXML
    private TableColumn<EmployeesData, String> aktionenTableColumn;

    @FXML
    private TableColumn<EmployeesData, String> anuTableColumn;

    @FXML
    private TableColumn<EmployeesData, String> berufserfahrungTableColumn;

    @FXML
    private TableColumn<EmployeesData, String> divisionTableColumn;

    @FXML
    private TableColumn<EmployeesData, String> einheitTableColumn;

    @FXML
    private TableColumn<EmployeesData, String> kompetenzenTableColumn;

    @FXML
    private TableColumn<EmployeesData, String> manager1TableColumn;

    @FXML
    private TableColumn<EmployeesData, String> manager2TableColumn;

    @FXML
    private TableColumn<EmployeesData, String> mobilitatTableColumn;

    @FXML
    private TableColumn<EmployeesData, String> nachnameTableColumn;

    @FXML
    private TableColumn<EmployeesData, String> position_RITableColumn;

    @FXML
    private TableColumn<EmployeesData, String> projektwunschTableColumn;

    @FXML
    private TableColumn<EmployeesData, String> riTableColumn;

    @FXML
    private TableColumn<EmployeesData, String> rtTableColumn;

    @FXML
    private TableColumn<EmployeesData, String> sapTableColumn;

    @FXML
    private TableColumn<EmployeesData, String> schwerpunktTableColumn;

    @FXML
    private TableColumn<EmployeesData, String> spalte1TableColumn;

    @FXML
    private TableColumn<EmployeesData, String> sprachenTableColumn;

    @FXML
    private TableColumn<EmployeesData, String> toolsTableColumn;

    @FXML
    private TableColumn<EmployeesData, Date> verfugbarkeitTableColumn;

    @FXML
    private TableColumn<EmployeesData, String> vornameTableColumn;


    private ObservableList<EmployeesData> employeesData;

//    EmployeesData employee = null;

    int ID = -1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void importButtonOnAction(ActionEvent event) throws SQLException {
        LoadEmployeesData();
    }

    public void LoadEmployeesData() {
        try {
            Connection conn = DatabaseConnection.Connector();
            this.employeesData = FXCollections.observableArrayList();

            // SQL query to get all data from the database
            assert conn != null;
            ResultSet queryOutput = conn.createStatement().executeQuery("SELECT * FROM employees_acc");

            while (queryOutput.next()) {
                this.employeesData.add(new EmployeesData(queryOutput.getString(1), queryOutput.getString(2),
                        queryOutput.getString(3), queryOutput.getString(4), queryOutput.getString(5),
                        queryOutput.getString(6), queryOutput.getString(7), queryOutput.getString(8),
                        queryOutput.getString(9), queryOutput.getString(10), queryOutput.getString(11),
                        queryOutput.getString(12), queryOutput.getString(13), queryOutput.getString(14),
                        queryOutput.getString(15), queryOutput.getString(16), queryOutput.getString(17),
                        queryOutput.getString(18), queryOutput.getString(19), queryOutput.getString(20),
                        queryOutput.getString(21), queryOutput.getString(22)));
            }


        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }

        // PropertyValueFactory corresponds to the new EmployeeModel fields
        // the table column is the one you annotate above
        this.idTableColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        this.sapTableColumn.setCellValueFactory(new PropertyValueFactory<>("SAP_Personalnummer"));
        this.spalte1TableColumn.setCellValueFactory(new PropertyValueFactory<>("Spalte1"));
        this.vornameTableColumn.setCellValueFactory(new PropertyValueFactory<>("Vorname"));
        this.nachnameTableColumn.setCellValueFactory(new PropertyValueFactory<>("Nachname"));
        this.riTableColumn.setCellValueFactory(new PropertyValueFactory<>("RI"));
        this.verfugbarkeitTableColumn.setCellValueFactory(new PropertyValueFactory<>("Verfugbarkeit"));
        this.berufserfahrungTableColumn.setCellValueFactory(new PropertyValueFactory<>("Berufserfahrung"));
        this.anuTableColumn.setCellValueFactory(new PropertyValueFactory<>("ANU"));
        this.mobilitatTableColumn.setCellValueFactory(new PropertyValueFactory<>("Mobilitat"));
        this.kompetenzenTableColumn.setCellValueFactory(new PropertyValueFactory<>("Kompetenzen"));
        this.toolsTableColumn.setCellValueFactory(new PropertyValueFactory<>("Tools"));
        this.sprachenTableColumn.setCellValueFactory(new PropertyValueFactory<>("Sprachen"));
        this.rtTableColumn.setCellValueFactory(new PropertyValueFactory<>("RT"));
        this.aktionenTableColumn.setCellValueFactory(new PropertyValueFactory<>("Aktionen"));
        this.projektwunschTableColumn.setCellValueFactory(new PropertyValueFactory<>("Projektwunsch"));
        this.schwerpunktTableColumn.setCellValueFactory(new PropertyValueFactory<>("Schwerpunkt"));
        this.divisionTableColumn.setCellValueFactory(new PropertyValueFactory<>("Division"));
        this.einheitTableColumn.setCellValueFactory(new PropertyValueFactory<>("Einheit"));
        this.position_RITableColumn.setCellValueFactory(new PropertyValueFactory<>("Position_RI"));
        this.manager1TableColumn.setCellValueFactory(new PropertyValueFactory<>("Manager1"));
        this.manager2TableColumn.setCellValueFactory(new PropertyValueFactory<>("Manager2"));

        this.employeeTableView.setItems(null);
        this.employeeTableView.setItems(this.employeesData);

        // Update table to allow editable
        employeeTableView.setEditable(true);
        sapTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    // add employee form stage
    public void addEmployeeButtonOnAction(ActionEvent event) {
        createEmployeeFormStage();
    }

    public void createEmployeeFormStage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("addEmployee.fxml"));
            Stage addEmpStage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 800, 700);
            addEmpStage.setTitle("Employee Management System");
            addEmpStage.setScene(scene);
            addEmpStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    // edit employee form stage
    public void editEmployeeButtonOnAction(ActionEvent event) throws SQLException {
        editEmployeeFormStage();
    }


    public void editEmployeeFormStage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("editEmployee.fxml"));
            Stage editEmpStage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 800, 700);
            EditEmployeeController pass = fxmlLoader.getController();
            pass.passEmployees(employeeTableView.getSelectionModel().getSelectedItem());
            editEmpStage.setTitle("Employee Management System");
            editEmpStage.setScene(scene);
            editEmpStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    // delete employee
    public void deleteEmployeeButtonOnAction(ActionEvent event) {
        deleteEmployee();
    }

    public void deleteEmployee() {
        Connection conn = DatabaseConnection.Connector();
        PreparedStatement pst;
        ID = employeeTableView.getSelectionModel().getSelectedIndex();
        if (ID <= -1) {
            System.out.println("no index found");
        }
        // id number
        String value1 = this.idTableColumn.getCellData(ID);

        try {
            String deleteQuery = "DELETE FROM employees_acc WHERE ID =? ";
            assert conn != null;
            pst = conn.prepareStatement(deleteQuery);

            pst.setString(1, value1);
            pst.executeUpdate();

            pst.close();


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }


    public void exportButtonOnAction(ActionEvent event) {
    }

    public void backEmployeesButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) backEmployeesButton.getScene().getWindow();
        stage.close();
    }


}
