package sample.ems.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.ems.DatabaseConnection;
import sample.ems.model.EmployeesData;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ResourceBundle;


public class EditEmployeeController implements Initializable {

    @FXML
    private ImageView addEmpImageView;

    @FXML
    private Label addEmpMessageLabel;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField sapTextField;

    @FXML
    private TextField spalteTextField;

    @FXML
    private TextField vornameTextField;

    @FXML
    private TextField nachnameTextField;

    @FXML
    private TextField riTextField;

    @FXML
    private DatePicker verfugbarkeitTextField;

    @FXML
    private TextField berufserfahrungTextField;

    @FXML
    private TextField anuTextField;

    @FXML
    private TextField mobilitatTextField;

    @FXML
    private TextField kompetenzenTextField;

    @FXML
    private TextField toolsTextField;

    @FXML
    private TextField sprachenTextField;

    @FXML
    private TextField rtTextField;

    @FXML
    private TextField aktionenTextField;

    @FXML
    private TextField projektwunschTextField;

    @FXML
    private TextField schwerpunktTextField;

    @FXML
    private TextField divisionTextField;

    @FXML
    private TextField einheitTextField;

    @FXML
    private TextField positionriTextField;

    @FXML
    private TextField manager1TextField;

    @FXML
    private TextField manager2TextField;

    @FXML
    private Button quitButtonAddEmpPage;

    @FXML
    private Button submitButton;

    @FXML
    private Button clearButton;


    private ObservableList<EmployeesData> employeesData;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File addEmpImageFile = new File("images/lock.png");
        Image addEmpImage = new Image(addEmpImageFile.toURI().toString());
        addEmpImageView.setImage(addEmpImage);
    }

    public void passEmployees(EmployeesData selectedEmployee) {

        // Local static employee object will be used in our pass employee method
        idTextField.setText(String.valueOf(selectedEmployee.getID()));
        sapTextField.setText(String.valueOf(selectedEmployee.getSAP_Personalnummer()));
        spalteTextField.setText(selectedEmployee.getSpalte1());
        vornameTextField.setText(selectedEmployee.getVorname());
        nachnameTextField.setText(selectedEmployee.getNachname());
        riTextField.setText(selectedEmployee.getRI());
        // parsing the string to convert it into date
        String JE_date = selectedEmployee.getVerfugbarkeit();
        DateTimeFormatter JEFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate local_date = LocalDate.parse(JE_date, JEFormatter);
        System.out.println(local_date);
        verfugbarkeitTextField.setValue(local_date);
        berufserfahrungTextField.setText(selectedEmployee.getBerufserfahrung());
        anuTextField.setText(selectedEmployee.getANU());
        mobilitatTextField.setText(selectedEmployee.getMobilitat());
        kompetenzenTextField.setText(selectedEmployee.getKompetenzen());
        toolsTextField.setText(selectedEmployee.getTools());
        sprachenTextField.setText(selectedEmployee.getSprachen());
        rtTextField.setText(selectedEmployee.getRT());
        aktionenTextField.setText(selectedEmployee.getAktionen());
        projektwunschTextField.setText(selectedEmployee.getProjektwunsch());
        schwerpunktTextField.setText(selectedEmployee.getSchwerpunkt());
        divisionTextField.setText(selectedEmployee.getDivision());
        einheitTextField.setText(selectedEmployee.getEinheit());
        positionriTextField.setText(selectedEmployee.getPosition_RI());
        manager1TextField.setText(selectedEmployee.getManager1());
        manager2TextField.setText(selectedEmployee.getManager2());
    }

    @FXML
    void submitButton(ActionEvent event) {

    }


    @FXML
    void submitButtonOnAction(ActionEvent event) {
        updateEmployee();
    }

    public void updateEmployee() {
        Connection conn = DatabaseConnection.Connector();
        PreparedStatement pst;
        try {
            DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            String value1 = idTextField.getText();
            String value2 = sapTextField.getText();
            String value3 = spalteTextField.getText();
            String value4 = vornameTextField.getText();
            String value5 = nachnameTextField.getText();
            String value6 = riTextField.getText();
            String value7 = verfugbarkeitTextField.getValue().format(formattedDate);
            String value8 = berufserfahrungTextField.getText();
            String value9 = anuTextField.getText();
            String value10 = mobilitatTextField.getText();
            String value11 = kompetenzenTextField.getText();
            String value12 = toolsTextField.getText();
            String value13 = sprachenTextField.getText();
            String value14 = rtTextField.getText();
            String value15 = aktionenTextField.getText();
            String value16 = projektwunschTextField.getText();
            String value17 = schwerpunktTextField.getText();
            String value18 = divisionTextField.getText();
            String value19 = einheitTextField.getText();
            String value20 = positionriTextField.getText();
            String value21 = manager1TextField.getText();
            String value22 = manager2TextField.getText();

            String sql = "UPDATE employees_acc SET " +
                    "ID = '" + value1 + "', " +
                    "SAP_Personalnummer = '" + value2 + "', " +
                    "Spalte1 = '" + value3 + "', " +
                    "Vorname = '" + value4 + "', " +
                    "Nachname = '" + value5 + "', " +
                    "RI = '" + value6 + "', " +
                    "Verfugbarkeit = '" + value7 + "', " +
                    "Berufserfahrung = '" + value8 + "', " +
                    "ANU = '" + value9 + "', " +
                    "Mobilitat = '" + value10 + "', " +
                    "Kompetenzen = '" + value11 + "', " +
                    "Tools = '" + value12 + "', " +
                    "Sprachen = '" + value13 + "', " +
                    "RT = '" + value14 + "', " +
                    "Aktionen = '" + value15 + "', " +
                    "Projektwunsch = '" + value16 + "', " +
                    "Schwerpunkt = '" + value17 + "', " +
                    "Division = '" + value18 + "', " +
                    "Einheit = '" + value19 + "', " +
                    "Position_RI = '" + value20 + "', " +
                    "Manager1 = '" + value21 + "', " +
                    "Manager2 = '" + value22 + "' WHERE ID = '" + value1 + "' ";

            assert conn != null;
            pst = conn.prepareStatement(sql);
            pst.executeUpdate();
            addEmpMessageLabel.setText("Employee has been added successfully!");


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }


    @FXML
    void quitButtonHomePageOnAction(MouseEvent event) {

    }


    @FXML
    void clearButtonOnAction(ActionEvent event) {
        clearData();
    }

    private void clearData() {
        sapTextField.setText("");
        spalteTextField.setText("");
        vornameTextField.setText("");
        nachnameTextField.setText("");
        riTextField.setText("");
        verfugbarkeitTextField.setValue(null);
        berufserfahrungTextField.setText("");
        anuTextField.setText("");
        mobilitatTextField.setText("");
        kompetenzenTextField.setText("");
        toolsTextField.setText("");
        sprachenTextField.setText("");
        rtTextField.setText("");
        aktionenTextField.setText("");
        projektwunschTextField.setText("");
        schwerpunktTextField.setText("");
        divisionTextField.setText("");
        einheitTextField.setText("");
        positionriTextField.setText("");
        manager1TextField.setText("");
        manager2TextField.setText("");
    }


    @FXML
    void quitButtonAddEmpPageOnAction(ActionEvent event) {
        Stage stage = (Stage) quitButtonAddEmpPage.getScene().getWindow();
        stage.close();
    }

}
