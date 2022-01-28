package sample.ems.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.ems.model.EmployeesData;

import java.io.File;
import java.net.URL;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


public class ViewEmployeeController implements Initializable {

    @FXML
    private ImageView addEmpImageView;

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
    private TextArea kompetenzenTextField;

    @FXML
    private TextArea toolsTextField;

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


    private ObservableList<EmployeesData> employeesData;


    public void passEmployees(EmployeesData selectedEmployee) {

        // Local static employee object will be used in our pass employee method
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
    void quitButtonHomePageOnAction(MouseEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File addEmpImageFile = new File("images/lock.png");
        Image viewEmpImage = new Image(addEmpImageFile.toURI().toString());
        addEmpImageView.setImage(viewEmpImage);
    }


    @FXML
    void quitButtonAddEmpPageOnAction(ActionEvent event) {
        Stage stage = (Stage) quitButtonAddEmpPage.getScene().getWindow();
        stage.close();
    }

}
