package sample.ems.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sample.ems.DatabaseConnection;
import sample.ems.model.EmployeesData;
import sample.ems.Main;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.function.Predicate;


public class EmployeesController implements Initializable {

    @FXML
    private TextField keywordTextField;

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
    private TableColumn<EmployeesData, Integer> idTableColumn;

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
    private TableColumn<EmployeesData, Integer> sapTableColumn;

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
        LoadEmployeesData();

        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<EmployeesData> filteredData = new FilteredList<>(employeesData, p -> true);
        keywordTextField.setOnKeyReleased(e -> {
            // 2. Set the filter Predicate whenever the filter changes.
            keywordTextField.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super EmployeesData>) employee -> {
                    // If filter text is empty, display all persons.
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    // Compare search value of every employee with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (employee.getID().toString().contains(newValue)) {
                        return true;
                    } else if (employee.getSAP_Personalnummer().toString().contains(newValue)) {
                        return true;
                    } else if (employee.getSpalte1().toLowerCase().contains(newValue)) {
                        return true;
                    } else if (employee.getVorname().toLowerCase().contains(newValue)) {
                        return true;
                    } else if (employee.getNachname().toLowerCase().contains(newValue)) {
                        return true;
                    } else if (employee.getRI().toLowerCase().contains(newValue)) {
                        return true;
                    } else if (employee.getVerfugbarkeit().contains(newValue)) {
                        return true;
                    } else if (employee.getBerufserfahrung().toLowerCase().contains(newValue)) {
                        return true;
                    } else if (employee.getANU().toLowerCase().contains(newValue)) {
                        return true;
                    } else if (employee.getMobilitat().toLowerCase().contains(newValue)) {
                        return true;
                    } else if (employee.getKompetenzen().toLowerCase().contains(newValue)) {
                        return true;
                    } else if (employee.getTools().toLowerCase().contains(newValue)) {
                        return true;
                    } else if (employee.getSprachen().toLowerCase().contains(newValue)) {
                        return true;
                    } else if (employee.getRT().toLowerCase().contains(newValue)) {
                        return true;
                    } else if (employee.getAktionen().toLowerCase().contains(newValue)) {
                        return true;
                    } else if (employee.getProjektwunsch().toLowerCase().contains(newValue)) {
                        return true;
                    } else if (employee.getSchwerpunkt().toLowerCase().contains(newValue)) {
                        return true;
                    } else if (employee.getDivision().toLowerCase().contains(newValue)) {
                        return true;
                    } else if (employee.getEinheit().toLowerCase().contains(newValue)) {
                        return true;
                    } else if (employee.getPosition_RI().toLowerCase().contains(newValue)) {
                        return true;
                    } else if (employee.getManager1().toLowerCase().contains(newValue)) {
                        return true;
                    } else if (employee.getManager2().toLowerCase().contains(newValue)) {
                        return true;
                    } else {
                        return false;
                    }
                });
            });
            SortedList<EmployeesData> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(employeeTableView.comparatorProperty());
            employeeTableView.setItems(sortedData);
        });
    }

    // onclick the data from the database is loaded or refreshed
    public void importButtonOnAction(ActionEvent event) {
        LoadEmployeesData();
    }

    // method to import data from database into javafx
    public void LoadEmployeesData() {
        try {
            Connection conn = DatabaseConnection.Connector();
            employeesData = FXCollections.observableArrayList();

            // PropertyValueFactory corresponds to the new EmployeeModel fields
            // the table column is the one you annotate above
            idTableColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
            sapTableColumn.setCellValueFactory(new PropertyValueFactory<>("SAP_Personalnummer"));
            spalte1TableColumn.setCellValueFactory(new PropertyValueFactory<>("Spalte1"));
            vornameTableColumn.setCellValueFactory(new PropertyValueFactory<>("Vorname"));
            nachnameTableColumn.setCellValueFactory(new PropertyValueFactory<>("Nachname"));
            riTableColumn.setCellValueFactory(new PropertyValueFactory<>("RI"));
            verfugbarkeitTableColumn.setCellValueFactory(new PropertyValueFactory<>("Verfugbarkeit"));
            berufserfahrungTableColumn.setCellValueFactory(new PropertyValueFactory<>("Berufserfahrung"));
            anuTableColumn.setCellValueFactory(new PropertyValueFactory<>("ANU"));
            mobilitatTableColumn.setCellValueFactory(new PropertyValueFactory<>("Mobilitat"));
            kompetenzenTableColumn.setCellValueFactory(new PropertyValueFactory<>("Kompetenzen"));
            toolsTableColumn.setCellValueFactory(new PropertyValueFactory<>("Tools"));
            sprachenTableColumn.setCellValueFactory(new PropertyValueFactory<>("Sprachen"));
            rtTableColumn.setCellValueFactory(new PropertyValueFactory<>("RT"));
            aktionenTableColumn.setCellValueFactory(new PropertyValueFactory<>("Aktionen"));
            projektwunschTableColumn.setCellValueFactory(new PropertyValueFactory<>("Projektwunsch"));
            schwerpunktTableColumn.setCellValueFactory(new PropertyValueFactory<>("Schwerpunkt"));
            divisionTableColumn.setCellValueFactory(new PropertyValueFactory<>("Division"));
            einheitTableColumn.setCellValueFactory(new PropertyValueFactory<>("Einheit"));
            position_RITableColumn.setCellValueFactory(new PropertyValueFactory<>("Position_RI"));
            manager1TableColumn.setCellValueFactory(new PropertyValueFactory<>("Manager1"));
            manager2TableColumn.setCellValueFactory(new PropertyValueFactory<>("Manager2"));

            // SQL query to get all data from the database
            String query = "SELECT * FROM employees_acc";
            assert conn != null;
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet queryOutput = pst.executeQuery();

            while (queryOutput.next()) {
                employeesData.add(new EmployeesData(queryOutput.getInt("ID"), queryOutput.getInt("SAP_Personalnummer"),
                        queryOutput.getString("Spalte1"), queryOutput.getString("Vorname"), queryOutput.getString("Nachname"),
                        queryOutput.getString("RI"), queryOutput.getString("Verfugbarkeit"), queryOutput.getString("Berufserfahrung"),
                        queryOutput.getString("ANU"), queryOutput.getString("Mobilitat"), queryOutput.getString("Kompetenzen"),
                        queryOutput.getString("Tools"), queryOutput.getString("Sprachen"), queryOutput.getString("RT"),
                        queryOutput.getString("Aktionen"), queryOutput.getString("Projektwunsch"), queryOutput.getString("Schwerpunkt"),
                        queryOutput.getString("Division"), queryOutput.getString("Einheit"), queryOutput.getString("Position_RI"),
                        queryOutput.getString("Manager1"), queryOutput.getString("Manager2")
                ));
                employeeTableView.setItems(this.employeesData);
            }
            queryOutput.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.getCause();
            e.printStackTrace();
        }
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
    public void editEmployeeButtonOnAction(ActionEvent event) {
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
        Integer value1 = this.idTableColumn.getCellData(ID);

        try {
            String deleteQuery = "DELETE FROM employees_acc WHERE ID =? ";
            assert conn != null;
            pst = conn.prepareStatement(deleteQuery);

            pst.setInt(1, value1);
            pst.executeUpdate();

            pst.close();


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }


    public void exportButtonOnAction(ActionEvent event) {
        Connection conn = DatabaseConnection.Connector();
        PreparedStatement pst;
        ResultSet rs;
        try {
            String exportQuery = "SELECT * FROM employees_acc";
            assert conn != null;
            pst = conn.prepareStatement(exportQuery);
            rs = pst.executeQuery();

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Employee Data");
            Row header = sheet.createRow(0);
//            header.createCell(0).setCellValue("ID");
            header.createCell(0).setCellValue("SAP_Personalnummer");
            header.createCell(1).setCellValue("Spalte1");
            header.createCell(2).setCellValue("Vorname");
            header.createCell(3).setCellValue("Nachname");
            header.createCell(4).setCellValue("RI");
            header.createCell(5).setCellValue("Verfugbarkeit");
            header.createCell(6).setCellValue("Berufserfahrung");
            header.createCell(7).setCellValue("ANU");
            header.createCell(8).setCellValue("Mobilitat");
            header.createCell(9).setCellValue("Kompetenzen");
            header.createCell(10).setCellValue("Tools");
            header.createCell(11).setCellValue("Sprachen");
            header.createCell(12).setCellValue("RT");
            header.createCell(13).setCellValue("Aktionen");
            header.createCell(14).setCellValue("Projektwunsch");
            header.createCell(15).setCellValue("Schwerpunkt");
            header.createCell(16).setCellValue("Division");
            header.createCell(17).setCellValue("Einheit");
            header.createCell(18).setCellValue("Position_RI");
            header.createCell(19).setCellValue("Manager1");
            header.createCell(20).setCellValue("Manager2");
            int index = 1;
            while (rs.next()) {

                Row row = sheet.createRow(index);
//                row.createCell(0).setCellValue(rs.getString("ID"));
                row.createCell(0).setCellValue(rs.getString("SAP_Personalnummer"));
                row.createCell(1).setCellValue(rs.getString("Spalte1"));
                row.createCell(2).setCellValue(rs.getString("Vorname"));
                row.createCell(3).setCellValue(rs.getString("Nachname"));
                row.createCell(4).setCellValue(rs.getString("RI"));
                row.createCell(5).setCellValue(rs.getString("Verfugbarkeit"));
                row.createCell(6).setCellValue(rs.getString("Berufserfahrung"));
                row.createCell(7).setCellValue(rs.getString("ANU"));
                row.createCell(8).setCellValue(rs.getString("Mobilitat"));
                row.createCell(9).setCellValue(rs.getString("Kompetenzen"));
                row.createCell(10).setCellValue(rs.getString("Tools"));
                row.createCell(11).setCellValue(rs.getString("Sprachen"));
                row.createCell(12).setCellValue(rs.getString("RT"));
                row.createCell(13).setCellValue(rs.getString("Aktionen"));
                row.createCell(14).setCellValue(rs.getString("Projektwunsch"));
                row.createCell(15).setCellValue(rs.getString("Schwerpunkt"));
                row.createCell(16).setCellValue(rs.getString("Division"));
                row.createCell(17).setCellValue(rs.getString("Einheit"));
                row.createCell(18).setCellValue(rs.getString("Position_RI"));
                row.createCell(19).setCellValue(rs.getString("Manager1"));
                row.createCell(20).setCellValue(rs.getString("Manager2"));
                index++;
            }
            FileOutputStream fileOut = new FileOutputStream("/Users/haykay14/Desktop/EmployeeData.xlsx");

            System.out.println("File Exported");
            workbook.write(fileOut);
            fileOut.close();

            pst.close();
            rs.close();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void backEmployeesButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) backEmployeesButton.getScene().getWindow();
        stage.close();
    }
}
