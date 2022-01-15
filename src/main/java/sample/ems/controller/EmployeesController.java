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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import sample.ems.DatabaseConnection;
import sample.ems.model.EmployeesData;
import sample.ems.Main;


import java.net.URL;
import java.sql.*;
import java.util.Arrays;
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
//    ObservableList<EmployeesData> dataList;

//    EmployeesData employee = null;

    int ID = -1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoadEmployeesData();

        Connection conn = DatabaseConnection.Connector();
        employeesData = FXCollections.observableArrayList();
        // 0. Initialize the columns.
        sapTableColumn.setCellValueFactory(cellData -> cellData.getValue().SAP_PersonalnummerProperty());
        spalte1TableColumn.setCellValueFactory(cellData -> cellData.getValue().Spalte1Property());
        vornameTableColumn.setCellValueFactory(cellData -> cellData.getValue().VornameProperty());
        nachnameTableColumn.setCellValueFactory(cellData -> cellData.getValue().NachnameProperty());
        riTableColumn.setCellValueFactory(cellData -> cellData.getValue().RIProperty());
//        verfugbarkeitTableColumn.setCellValueFactory(cellData -> cellData.getValue().VerfugbarkeitProperty());
        berufserfahrungTableColumn.setCellValueFactory(cellData -> cellData.getValue().BerufserfahrungProperty());
        anuTableColumn.setCellValueFactory(cellData -> cellData.getValue().ANUProperty());
        mobilitatTableColumn.setCellValueFactory(cellData -> cellData.getValue().MobilitatProperty());
        kompetenzenTableColumn.setCellValueFactory(cellData -> cellData.getValue().KompetenzenProperty());
        toolsTableColumn.setCellValueFactory(cellData -> cellData.getValue().ToolsProperty());
        sprachenTableColumn.setCellValueFactory(cellData -> cellData.getValue().SprachenProperty());
        rtTableColumn.setCellValueFactory(cellData -> cellData.getValue().RTProperty());
        aktionenTableColumn.setCellValueFactory(cellData -> cellData.getValue().AktionenProperty());
        projektwunschTableColumn.setCellValueFactory(cellData -> cellData.getValue().ProjektwunschProperty());
        schwerpunktTableColumn.setCellValueFactory(cellData -> cellData.getValue().SchwerpunktProperty());
        divisionTableColumn.setCellValueFactory(cellData -> cellData.getValue().DivisionProperty());
        einheitTableColumn.setCellValueFactory(cellData -> cellData.getValue().EinheitProperty());
        position_RITableColumn.setCellValueFactory(cellData -> cellData.getValue().Position_RIProperty());
        manager1TableColumn.setCellValueFactory(cellData -> cellData.getValue().Manager1Property());
        manager2TableColumn.setCellValueFactory(cellData -> cellData.getValue().Manager2Property());
//       schwerpunktTableColumn.setCellValueFactory(cellData -> cellData.getValue().SchwerpunktProperty());


        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<EmployeesData> filteredData = new FilteredList<>(employeesData, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        keywordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(employeesDataSearch -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String searchKeyWord = newValue.toLowerCase();

//                if (person.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
//                    return true; // Filter matches first name.
//                } else if (person.getLastName().toLowerCase().contains(lowerCaseFilter)) {
//                    return true; // Filter matches last name.
//                }
                if (employeesDataSearch.getSAP_Personalnummer().toLowerCase().contains(searchKeyWord)) {
                    return true;
                } else if (employeesDataSearch.getSpalte1().toLowerCase().contains(searchKeyWord)) {
                    return true;
                } else if (employeesDataSearch.getVorname().toLowerCase().contains(searchKeyWord)) {
                    return true;
                } else if (employeesDataSearch.getNachname().toLowerCase().contains(searchKeyWord)) {
                    return true;
                } else if (employeesDataSearch.getRI().toLowerCase().contains(searchKeyWord)) {
                    return true;
                } else if (employeesDataSearch.getVerfugbarkeit().toLowerCase().contains(searchKeyWord)) {
                    return true;
                } else if (employeesDataSearch.getBerufserfahrung().toLowerCase().contains(searchKeyWord)) {
                    return true;
                } else if (employeesDataSearch.getANU().toLowerCase().contains(searchKeyWord)) {
                    return true;
                } else if (employeesDataSearch.getMobilitat().toLowerCase().contains(searchKeyWord)) {
                    return true;
                } else if (employeesDataSearch.getKompetenzen().toLowerCase().contains(searchKeyWord)) {
                    return true;
                } else if (employeesDataSearch.getTools().toLowerCase().contains(searchKeyWord)) {
                    return true;
                } else if (employeesDataSearch.getSprachen().toLowerCase().contains(searchKeyWord)) {
                    return true;
                } else if (employeesDataSearch.getRT().toLowerCase().contains(searchKeyWord)) {
                    return true;
                } else if (employeesDataSearch.getAktionen().toLowerCase().contains(searchKeyWord)) {
                    return true;
                } else if (employeesDataSearch.getProjektwunsch().toLowerCase().contains(searchKeyWord)) {
                    return true;
                } else if (employeesDataSearch.getSchwerpunkt().toLowerCase().contains(searchKeyWord)) {
                    return true;
                } else if (employeesDataSearch.getDivision().toLowerCase().contains(searchKeyWord)) {
                    return true;
                } else if (employeesDataSearch.getEinheit().toLowerCase().contains(searchKeyWord)) {
                    return true;
                } else if (employeesDataSearch.getPosition_RI().toLowerCase().contains(searchKeyWord)) {
                    return true;
                } else if (employeesDataSearch.getManager1().toLowerCase().contains(searchKeyWord)) {
                    return true;
                } else return employeesDataSearch.getManager2().toLowerCase().contains(searchKeyWord);
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<EmployeesData> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(employeeTableView.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        employeeTableView.setItems(sortedData);
    }

    public void importButtonOnAction(ActionEvent event) throws SQLException {
        LoadEmployeesData();
    }

    public void LoadEmployeesData() {
        try {
            Connection conn = DatabaseConnection.Connector();
            employeesData = FXCollections.observableArrayList();

            // SQL query to get all data from the database
            assert conn != null;
            ResultSet queryOutput = conn.createStatement().executeQuery("SELECT * FROM employees_acc");

            while (queryOutput.next()) {
                employeesData.add(new EmployeesData(queryOutput.getString(1), queryOutput.getString(2),
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

//        employeeTableView.setItems(null);
        employeeTableView.setItems(this.employeesData);

        // Update table to allow editable
        // employeeTableView.setEditable(true);
        //Initial filtered list
//        FilteredList<EmployeesData> filteredData = new FilteredList<>(employeesData, b -> true);
//
//        keywordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
//            filteredData.setPredicate(employeesDataSearch -> {
//
//                // if no search value then display all records or whatever records it currently has
//                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
//                    return true;
//                }

//                String searchKeyWord = newValue.toLowerCase();

//                if (employeesDataSearch.getSAP_Personalnummer().toLowerCase().contains(searchKeyWord)) {
//                    return true;
//                } else if (employeesDataSearch.getSpalte1().toLowerCase().contains(searchKeyWord)) {
//                    return true;
//                } else if (employeesDataSearch.getVorname().toLowerCase().contains(searchKeyWord.toLowerCase())) {
//                    return true;
//                } else if (employeesDataSearch.getNachname().toLowerCase().contains(searchKeyWord.toLowerCase())) {
//                    return true;
//                } else if (employeesDataSearch.getRI().toLowerCase().contains(searchKeyWord.toLowerCase())) {
//                    return true;
//                } else if (employeesDataSearch.getVerfugbarkeit().toLowerCase().contains(searchKeyWord.toLowerCase())) {
//                    return true;
//                } else if (employeesDataSearch.getBerufserfahrung().toLowerCase().contains(searchKeyWord.toLowerCase())) {
//                    return true;
//                } else if (employeesDataSearch.getANU().toLowerCase().contains(searchKeyWord.toLowerCase())) {
//                    return true;
//                } else if (employeesDataSearch.getMobilitat().toLowerCase().contains(searchKeyWord.toLowerCase())) {
//                    return true;
//                } else if (employeesDataSearch.getKompetenzen().toLowerCase().contains(searchKeyWord.toLowerCase())) {
//                    return true;
//                } else if (employeesDataSearch.getTools().toLowerCase().contains(searchKeyWord.toLowerCase())) {
//                    return true;
//                } else if (employeesDataSearch.getSprachen().toLowerCase().contains(searchKeyWord.toLowerCase())) {
//                    return true;
//                } else if (employeesDataSearch.getRT().toLowerCase().contains(searchKeyWord.toLowerCase())) {
//                    return true;
//                } else if (employeesDataSearch.getAktionen().toLowerCase().contains(searchKeyWord.toLowerCase())) {
//                    return true;
//                } else if (employeesDataSearch.getProjektwunsch().toLowerCase().contains(searchKeyWord.toLowerCase())) {
//                    return true;
//                } else if (employeesDataSearch.getSchwerpunkt().toLowerCase().contains(searchKeyWord.toLowerCase())) {
//                    return true;
//                } else if (employeesDataSearch.getDivision().toLowerCase().contains(searchKeyWord.toLowerCase())) {
//                    return true;
//                } else if (employeesDataSearch.getEinheit().toLowerCase().contains(searchKeyWord.toLowerCase())) {
//                    return true;
//                } else if (employeesDataSearch.getPosition_RI().toLowerCase().contains(searchKeyWord.toLowerCase())) {
//                    return true;
//                } else if (employeesDataSearch.getManager1().toLowerCase().contains(searchKeyWord.toLowerCase())) {
//                    return true;
//                } else return employeesDataSearch.getManager2().toLowerCase().contains(searchKeyWord.toLowerCase());
//            });
//        });

//        employeeTableView = new TableView<>();
//        employeeTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
//        employeeTableView.getColumns().addAll(Arrays.asList(sapTableColumn, spalte1TableColumn, vornameTableColumn,
//                nachnameTableColumn, riTableColumn, verfugbarkeitTableColumn, berufserfahrungTableColumn, anuTableColumn,
//                mobilitatTableColumn, kompetenzenTableColumn, toolsTableColumn, sprachenTableColumn, rtTableColumn,
//                aktionenTableColumn, projektwunschTableColumn, schwerpunktTableColumn, divisionTableColumn, einheitTableColumn,
//                position_RITableColumn, manager1TableColumn, manager2TableColumn));
//
//        // 3. Wrap the FilteredList in a SortedList.
//        SortedList<EmployeesData> sortedData = new SortedList<>(filteredData);
//
//        // 4. Bind the SortedList comparator to the TableView comparator.
//        sortedData.comparatorProperty().bind(employeeTableView.comparatorProperty());
//
//        // Apply filtered and sorted data to the Table View
//        employeeTableView.setItems(sortedData);

        // 5. Add sorted (and filtered) data to the table.

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

    // search
//    @FXML
//    void search_employee() {
//
//        sapTableColumn.setCellFactory(new <TableColumn<EmployeesData, String>, TableCell<EmployeesData, String>>(SAP_Personalnummer));
//    }
//
//
//    private boolean searchFindsEmployees(EmployeesData employee, String searchText) {
//        return (employee.getSAP_Personalnummer().toLowerCase().contains(searchText.toLowerCase()))||
//                (employee.getSpalte1().toLowerCase().contains(searchText.toLowerCase()))||
//                (employee.getVorname().toLowerCase().contains(searchText.toLowerCase()))||
//                (employee.getNachname().toLowerCase().contains(searchText.toLowerCase()))||
//                (employee.getRI().toLowerCase().contains(searchText.toLowerCase()))||
//                (employee.getVerfugbarkeit().toLowerCase().contains(searchText.toLowerCase()))||
//                (employee.getBerufserfahrung().toLowerCase().contains(searchText.toLowerCase()))||
//                (employee.getANU().toLowerCase().contains(searchText.toLowerCase()))||
//                (employee.getMobilitat().toLowerCase().contains(searchText.toLowerCase()))||
//                (employee.getKompetenzen().toLowerCase().contains(searchText.toLowerCase()))||
//                (employee.getTools().toLowerCase().contains(searchText.toLowerCase()))||
//                (employee.getSprachen().toLowerCase().contains(searchText.toLowerCase()))||
//                (employee.getRT().toLowerCase().contains(searchText.toLowerCase()))||
//                (employee.getAktionen().toLowerCase().contains(searchText.toLowerCase()))||
//                (employee.getProjektwunsch().toLowerCase().contains(searchText.toLowerCase()))||
//                (employee.getSchwerpunkt().toLowerCase().contains(searchText.toLowerCase()))||
//                (employee.getDivision().toLowerCase().contains(searchText.toLowerCase()))||
//                (employee.getEinheit().toLowerCase().contains(searchText.toLowerCase()))||
//                (employee.getPosition_RI().toLowerCase().contains(searchText.toLowerCase()))||
//                (employee.getManager1().toLowerCase().contains(searchText.toLowerCase()))||
//                (employee.getManager2().toLowerCase().contains(searchText.toLowerCase()));
//    }
//    FilteredList<EmployeesData> filteredData;
//
//    {
//        assert false;
//        filteredData = new FilteredList<>(FXCollections.observableList(employeesData));
//        employeeTableView.setItems(filteredData);
//    }
//
//    private Predicate<EmployeesData> createPredicate(String searchText) {
//        return employee -> {
//            if (searchText == null || searchText.isEmpty()) {
//                return true;
//            }
//            return searchFindsEmployees(employee, searchText);
//        };
//    }

}
