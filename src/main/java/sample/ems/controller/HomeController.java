package sample.ems.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.ems.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private Button quitButtonHomePage;

    @FXML
    private Button employeesButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void quitButtonHomePageOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) quitButtonHomePage.getScene().getWindow();
        stage.close();
    }

    public void employeesButtonOnAction(ActionEvent actionEvent) {
        employeesFormStage();
    }

    public void employeesFormStage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("employees.fxml"));
            Stage employeeStage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 1500, 800);
            employeeStage.setTitle("Employees");
            employeeStage.setScene(scene);
            employeeStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
