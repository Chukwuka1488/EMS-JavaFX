package sample.ems.controller;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.io.FileInputStream;
import java.io.IOException;

import sample.ems.DatabaseConnection;
import sample.ems.Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomeController implements Initializable {
    List<String> excelFile;

    @FXML
    public Label singleFileText;

    @FXML
    private Button quitButtonHomePage;

    @FXML
    private Button employeesButton;


    @FXML
    private Button databaseUpload;

    @FXML
    private Button uploadFileButton;

    private FileChooser fileChooser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        excelFile = new ArrayList<>();
        excelFile.add("*.xlsx");


    }

    public void quitButtonHomePageOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) quitButtonHomePage.getScene().getWindow();
        stage.close();
    }

    public void employeesButtonOnAction(ActionEvent actionEvent) {
        employeesFormStage();
    }

    @FXML
    public void uploadFileButtonOnAction(ActionEvent actionEvent) throws SQLException {

        Connection conn = DatabaseConnection.Connector();
        assert conn != null;
        PreparedStatement pst;
        FileChooser fileChooser = new FileChooser();
        // Set Extension filter
        FileChooser.ExtensionFilter extFilterXlsx = new FileChooser.ExtensionFilter("Excel Files (*.xlsx)", excelFile);
        fileChooser.getExtensionFilters().addAll(extFilterXlsx);
        File file = fileChooser.showOpenDialog(null);
        String excelFilePath = file.getAbsolutePath();
        System.out.println(excelFilePath);
        if (excelFilePath.isEmpty()) {
            System.out.println("No File Found");
        } else {
            singleFileText.setText("File successfully Uploaded");
        }
        System.out.print("Almost before...");


        // SQL statement for creating a new table
        String createSql = "CREATE TABLE IF NOT EXISTS employees_acc (" +
                "ID INTEGER NOT NULL UNIQUE PRIMARY KEY AUTOINCREMENT, SAP_Personalnummer INTEGER NOT NULL, " +
                "Spalte1 TEXT,Vorname TEXT NOT NULL,Nachname TEXT NOT NULL,RI TEXT, Verfugbarkeit DATE, " +
                "Berufserfahrung TEXT, ANU TEXT, Mobilitat TEXT,Kompetenzen TEXT, Tools TEXT, Sprachen TEXT, " +
                "RT TEXT, Aktionen TEXT, Projektwunsch TEXT,Schwerpunkt TEXT, Division TEXT, Einheit TEXT, " +
                "Position_RI TEXT, Manager1 TEXT, Manager2 TEXT)";

        try {
            Statement stmtCreate = conn.createStatement();
            // create a new table
            stmtCreate.execute(createSql);

            System.out.print("Almost set...");
            try {
                String loadFile = "INSERT INTO employees_acc (SAP_Personalnummer, Spalte1, " +
                        "Vorname, Nachname, RI, Verfugbarkeit, Berufserfahrung, ANU, Mobilitat, Kompetenzen, Tools, " +
                        "Sprachen, RT, Aktionen, Projektwunsch, Schwerpunkt, Division, Einheit, Position_RI, Manager1, " +
                        "Manager2) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                pst = conn.prepareStatement(loadFile);
                FileInputStream fileInput = new FileInputStream(excelFilePath);

                Workbook workbook = new XSSFWorkbook(fileInput);

                Sheet sheet = workbook.getSheetAt(0);
                Row row;
                for (int i = 1; i < sheet.getLastRowNum(); i++) {
                    row = sheet.getRow(i);

                    DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                    DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
                    pst.setString(1, row.getCell(0).getStringCellValue());
                    pst.setString(2, formatter.formatCellValue(row.getCell(1)));
                    pst.setString(3, row.getCell(2).getStringCellValue());
                    pst.setString(4, row.getCell(3).getStringCellValue());
                    pst.setString(5, row.getCell(4).getStringCellValue());
                    pst.setString(6, row.getCell(5).getLocalDateTimeCellValue().format(formattedDate));
                    pst.setString(7, row.getCell(6).getStringCellValue());
                    pst.setString(8, row.getCell(7).getStringCellValue());
                    pst.setString(9, row.getCell(8).getStringCellValue());
                    pst.setString(10, row.getCell(9).getStringCellValue());
                    pst.setString(11, row.getCell(10).getStringCellValue());
                    pst.setString(12, row.getCell(11).getStringCellValue());
                    pst.setString(13, row.getCell(12).getStringCellValue());
                    pst.setString(14, row.getCell(13).getStringCellValue());
                    pst.setString(15, row.getCell(14).getStringCellValue());
                    pst.setString(16, row.getCell(15).getStringCellValue());
                    pst.setString(17, row.getCell(16).getStringCellValue());
                    pst.setString(18, row.getCell(17).getStringCellValue());
                    pst.setString(19, row.getCell(18).getStringCellValue());
                    pst.setString(20, row.getCell(19).getStringCellValue());
                    pst.setString(21, row.getCell(20).getStringCellValue());
                    pst.execute();
                }
                singleFileText.setText("Data Uploaded");
                workbook.close();
                fileInput.close();
                pst.close();
            } catch (IOException ex1) {
                System.out.println("Error reading file");
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex1);
                ex1.printStackTrace();
            } catch (SQLException ex2) {
                System.out.println("Database Error");
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex2);
                ex2.printStackTrace();
            }
            System.out.println("Finally");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Database Created");
        conn.close();
    }

    @FXML
    public void databaseUploadOnAction(ActionEvent event) throws SQLException {
        Connection conn = DatabaseConnection.Connector();

        assert conn != null;
        conn.setAutoCommit(false);
        // SQL STATEMENT TO DROP TABLE
        Statement stmt = conn.createStatement();
        String sqlCommand = "DROP TABLE IF EXISTS 'employees_acc' ";
        System.out.println("output : " + stmt.executeUpdate(sqlCommand));
        singleFileText.setText("Data Cleared Up");
        stmt.close();
        // commit after execute sql command
        //COMMIT TRANSACTION makes all data modifications performed since
        //the start of the transaction a permanent part of the database,
        conn.commit();
        conn.close();

    }

    int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
    int screenHeight = (int) Screen.getPrimary().getBounds().getHeight();

    int initialX;
    int initialY;

    public void employeesFormStage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("employees.fxml"));

            Rectangle2D employeeRect = Screen.getPrimary().getVisualBounds();
            System.out.println(employeeRect);

            // Responsive Design
            int sceneWidth = 0;
            int sceneHeight = 0;

            if (screenWidth <= 800 && screenHeight <= 600) {
                sceneWidth = 600;
                sceneHeight = 350;
            } else if (screenWidth <= 1280 && screenHeight <= 720) {
                sceneWidth = 1200;
                sceneHeight = 600;
            } else if (screenWidth <= 1920 && screenHeight <= 1080) {
                sceneWidth = 1500;
                sceneHeight = 800;
            }

            Stage employeeStage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), sceneWidth, sceneHeight);
            employeeStage.setTitle("Employees");
            employeeStage.setScene(scene);
            employeeStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void autoEmployeesFormStage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("autoEmployee.fxml"));

            Rectangle2D employeeRect = Screen.getPrimary().getVisualBounds();
            System.out.println(employeeRect);

            // Responsive Design
            int sceneWidth = 0;
            int sceneHeight = 0;

            if (screenWidth <= 800 && screenHeight <= 600) {
                sceneWidth = 600;
                sceneHeight = 350;
            } else if (screenWidth <= 1280 && screenHeight <= 720) {
                sceneWidth = 1200;
                sceneHeight = 600;
            } else if (screenWidth <= 1920 && screenHeight <= 1080) {
                sceneWidth = 1500;
                sceneHeight = 800;
            }

            Stage autoEmployeeStage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), sceneWidth, sceneHeight);
            autoEmployeeStage.setTitle("Automotive Employees");
            autoEmployeeStage.setScene(scene);
            autoEmployeeStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void autoEmployeesButtonOnAction(ActionEvent actionEvent) {
        autoEmployeesFormStage();
    }

    public void aeroEmployeesFormStage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("aeroEmployee.fxml"));

            Rectangle2D employeeRect = Screen.getPrimary().getVisualBounds();
            System.out.println(employeeRect);

            // Responsive Design
            int sceneWidth = 0;
            int sceneHeight = 0;

            if (screenWidth <= 800 && screenHeight <= 600) {
                sceneWidth = 600;
                sceneHeight = 350;
            } else if (screenWidth <= 1280 && screenHeight <= 720) {
                sceneWidth = 1200;
                sceneHeight = 600;
            } else if (screenWidth <= 1920 && screenHeight <= 1080) {
                sceneWidth = 1500;
                sceneHeight = 800;
            }

            Stage aeroEmployeeStage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), sceneWidth, sceneHeight);
            aeroEmployeeStage.setTitle("Aerospace Employees");
            aeroEmployeeStage.setScene(scene);
            aeroEmployeeStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void aeroEmployeesButtonOnAction(ActionEvent actionEvent) {
        aeroEmployeesFormStage();
    }


}
