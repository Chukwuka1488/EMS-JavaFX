package sample.ems;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {
    private final Desktop desktop = Desktop.getDesktop();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Employee Management System");


        stage.setScene(scene);
        stage.show();


//        openFile()
    }

    public static void main(String[] args) {
        launch();
    }

    private void openFile(File file) {
        try {

            desktop.open(file);
        } catch (IOException e) {
//            Logger.getLogger(FileChooserSample.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }
}