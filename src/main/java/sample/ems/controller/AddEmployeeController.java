package sample.ems.controller;

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

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AddEmployeeController implements Initializable {
    @FXML
    private ImageView addEmpImageView;

    @FXML
    private Label addEmpMessageLabel;

    @FXML
    private TextField aktionenTextField;

    @FXML
    private TextField anuTextField;

    @FXML
    private TextField berufserfahrungTextField;

    @FXML
    private Button clearButton;

    @FXML
    private TextField divisionTextField;

    @FXML
    private TextField einheitTextField;

    @FXML
    private TextField kompetenzenTextField;

    @FXML
    private TextField manager1TextField;

    @FXML
    private TextField manager2TextField;

    @FXML
    private TextField mobilitatTextField;

    @FXML
    private TextField nachnameTextField;

    @FXML
    private TextField positionriTextField;

    @FXML
    private TextField projektwunschTextField;

    @FXML
    private Button quitButtonAddEmpPage;

    @FXML
    private TextField riTextField;

    @FXML
    private TextField rtTextField;

    @FXML
    private TextField sapTextField;

    @FXML
    private TextField schwerpunktTextField;

    @FXML
    private TextField spalteTextField;

    @FXML
    private TextField sprachenTextField;

    @FXML
    private Button submitButton;

    @FXML
    private TextField toolsTextField;

    @FXML
    private DatePicker verfugbarkeitTextField;

    @FXML
    private TextField vornameTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File addEmpImageFile = new File("images/lock.png");
        Image addEmpImage = new Image(addEmpImageFile.toURI().toString());
        addEmpImageView.setImage(addEmpImage);
    }

    @FXML
    void clearButtonOnAction(ActionEvent event) {
        clearData();
    }


    @FXML
    void quitButtonAddEmpPageOnAction(ActionEvent event) {
        Stage stage = (Stage) quitButtonAddEmpPage.getScene().getWindow();
        stage.close();
    }


    @FXML
    void submitButtonOnAction(ActionEvent event) {
        createEmployee();
    }

    public void createEmployee() {
        try {
            DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            Connection conn = DatabaseConnection.Connector();
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement("INSERT INTO employees_acc(SAP_Personalnummer, Spalte1, " +
                    "Vorname, Nachname, RI, Verfugbarkeit, Berufserfahrung, ANU, Mobilitat, Kompetenzen, Tools, " +
                    "Sprachen, RT, Aktionen, Projektwunsch, Schwerpunkt, Division, Einheit, Position_RI, Manager1, " +
                    "Manager2) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, this.sapTextField.getText());
            ps.setString(2, this.spalteTextField.getText());
            ps.setString(3, this.vornameTextField.getText());
            ps.setString(4, this.nachnameTextField.getText());
            ps.setString(5, this.riTextField.getText());
            ps.setString(6, this.verfugbarkeitTextField.getValue().format(formattedDate));
            ps.setString(7, this.berufserfahrungTextField.getText());
            ps.setString(8, this.anuTextField.getText());
            ps.setString(9, this.mobilitatTextField.getText());
            ps.setString(10, this.kompetenzenTextField.getText());
            ps.setString(11, this.toolsTextField.getText());
            ps.setString(12, this.sprachenTextField.getText());
            ps.setString(13, this.rtTextField.getText());
            ps.setString(14, this.aktionenTextField.getText());
            ps.setString(15, this.projektwunschTextField.getText());
            ps.setString(16, this.schwerpunktTextField.getText());
            ps.setString(17, this.divisionTextField.getText());
            ps.setString(18, this.einheitTextField.getText());
            ps.setString(19, this.positionriTextField.getText());
            ps.setString(20, this.manager1TextField.getText());
            ps.setString(21, this.manager2TextField.getText());

            ps.execute();
            addEmpMessageLabel.setText("Employee has been added successfully!");
            conn.close();
            clearData();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void clearData() {
        this.sapTextField.setText("");
        this.spalteTextField.setText("");
        this.vornameTextField.setText("");
        this.nachnameTextField.setText("");
        this.riTextField.setText("");
        this.verfugbarkeitTextField.setValue(null);
        this.berufserfahrungTextField.setText("");
        this.anuTextField.setText("");
        this.mobilitatTextField.setText("");
        this.kompetenzenTextField.setText("");
        this.toolsTextField.setText("");
        this.sprachenTextField.setText("");
        this.rtTextField.setText("");
        this.aktionenTextField.setText("");
        this.projektwunschTextField.setText("");
        this.schwerpunktTextField.setText("");
        this.divisionTextField.setText("");
        this.einheitTextField.setText("");
        this.positionriTextField.setText("");
        this.manager1TextField.setText("");
        this.manager2TextField.setText("");
    }

    public void quitButtonHomePageOnAction(MouseEvent mouseEvent) {
    }
}
