package sample.ems.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.ems.DatabaseConnection;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    @FXML
    private Button registerButton;

    @FXML
    private Button closeButton;

    @FXML
    private Label registrationMessageLabel;

    @FXML
    private Label confirmPasswordLabel;

    @FXML
    private ImageView registerImageView;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField setPasswordField;

    @FXML
    private PasswordField confirmPasswordField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File registerImageFile = new File("images/lock.png");
        Image registerImage = new Image(registerImageFile.toURI().toString());
        registerImageView.setImage(registerImage);
    }


    public void registerButtonOnAction(ActionEvent event) {
        if (setPasswordField.getText().equals(confirmPasswordField.getText())) {
            registerUser();
            confirmPasswordLabel.setText("");
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        } else {
            confirmPasswordLabel.setText("Passwords Does Not Match!");
        }

    }

    public void closeButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
//        Platform.exit();
    }

    public void registerUser() {

        try {
            Connection conn = DatabaseConnection.Connector();
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement("INSERT INTO user_account(Firstname, Lastname, Username, " +
                    "Password) VALUES(?, ?, ?, ?)");
            ps.setString(1, this.firstNameTextField.getText());
            ps.setString(2, this.lastNameTextField.getText());
            ps.setString(3, this.usernameTextField.getText());
            ps.setString(4, this.setPasswordField.getText());

            ps.execute();
            registrationMessageLabel.setText("User successfully registered!");
            conn.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
