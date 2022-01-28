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
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.ems.model.LoginModel;
import sample.ems.Main;


import java.io.File;
import java.net.URL;
import java.sql.SQLException;

import java.util.ResourceBundle;


public class LoginController implements Initializable {


    public sample.ems.model.LoginModel  LoginModel = new LoginModel();

    @FXML
    private PasswordField enterPasswordField;

    @FXML
    private ImageView lockImageView;

    @FXML
    private Button loginButton;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private ImageView managementImageView;

    @FXML
    private Button registerButton;

    @FXML
    private TextField usernameTextField;

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
        System.out.println("Yes");

        if (LoginModel.isDbConnected()) {
            loginMessageLabel.setText("Database Connected!");
        } else {
            loginMessageLabel.setText("Database Not Connected!");
        }

    }


    public void loginButtonOnAction(ActionEvent event) {
//        homeFormStage();
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
            Text text = new Text();
            //Setting font to the text
            text.setFont(Font.font("tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 13));
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
            Text text = new Text();
            //Setting font to the text
            text.setFont(Font.font("tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 13));
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

