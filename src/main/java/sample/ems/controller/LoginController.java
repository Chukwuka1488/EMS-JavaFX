package sample.ems.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.ems.model.LoginModel;
import sample.ems.Main;


import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    public sample.ems.model.LoginModel LoginModel = new LoginModel();

    @FXML
    private Button registerButton;

    @FXML
    private Button loginButton;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private ImageView managementImageView;

    @FXML
    private ImageView lockImageView;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField enterPasswordField;

    /*
     * For the images and Icons on the page
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File managementImageFile = new File("images/management2.jpeg");
        Image managementImage = new Image(managementImageFile.toURI().toString());
        managementImageView.setImage(managementImage);

        File lockImageFile = new File("images/lock.png");
        Image lockImage = new Image(lockImageFile.toURI().toString());
        lockImageView.setImage(lockImage);

        if (LoginModel.isDbConnected()) {
            loginMessageLabel.setText("Database Connected!");
        } else {
            loginMessageLabel.setText("Database Not Connected!");
        }

    }

    public void loginButtonOnAction(ActionEvent event) throws SQLException {
        try {
            if (LoginModel.validateLogin(usernameTextField.getText(), enterPasswordField.getText())) {
                loginMessageLabel.setText("Login Successful!");
                Stage stage = (Stage) usernameTextField.getScene().getWindow();
                stage.close();
                homeFormStage();
            } else {
                loginMessageLabel.setText("Invalid Username or Password.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            loginMessageLabel.setText("Invalid Username or Password.");
        }

    }


    public void registerButtonOnAction(ActionEvent event) {
        createAccountFormStage();
    }


    public void createAccountFormStage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("register.fxml"));
            Stage registerStage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            registerStage.setTitle("Register");
            registerStage.setScene(scene);
            registerStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void homeFormStage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home.fxml"));
            Stage homeStage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 800, 580);
            homeStage.setTitle("Employee Management System");
            homeStage.setScene(scene);
            homeStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

}

