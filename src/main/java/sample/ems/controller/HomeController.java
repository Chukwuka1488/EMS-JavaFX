package sample.ems.controller;

//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.io.FileInputStream;
import java.io.IOException;

import javafx.scene.control.Alert;

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
import java.util.ArrayList;
import java.util.Iterator;
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
        PreparedStatement pst = null;

        FileChooser fileChooser = new FileChooser();

        // Set Extension filter

        FileChooser.ExtensionFilter extFilterXlsx = new FileChooser.ExtensionFilter("Excel Files (*.xlsx)", excelFile);

        fileChooser.getExtensionFilters().addAll(extFilterXlsx);
        File file = fileChooser.showOpenDialog(null);
        System.out.println(file.toString());
        String excelFilePath = file.toString();
        System.out.println(excelFilePath);
        if (excelFilePath.isEmpty()) {
            System.out.println("No File Found");
        } else {
            singleFileText.setText("File successfully Uploaded");
        }
        System.out.print("Almost before...");

        // SQL statement for creating a new table
        String createSql = "CREATE TABLE IF NOT EXISTS employee_acc_one (" +
                "ID INTEGER NOT NULL UNIQUE PRIMARY KEY AUTOINCREMENT, SAP_Personalnummer INTEGER NOT NULL, " +
                "Spalte1 TEXT,Vorname TEXT NOT NULL,Nachname TEXT NOT NULL,RI TEXT, Verfugbarkeit TEXT, " +
                "Berufserfahrung TEXT, ANU TEXT, Mobilitat TEXT,Kompetenzen TEXT, Tools TEXT, Sprachen TEXT, " +
                "RT TEXT, Aktionen TEXT, Projektwunsch TEXT,Schwerpunkt TEXT, Division TEXT, Einheit TEXT, " +
                "Position_RI TEXT, Manager1 TEXT, Manager2 TEXT)";

        try {
            Statement stmt = conn.createStatement();
            // create a new table
            stmt.execute(createSql);
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Database Created");
    }

    @FXML
    public void databaseUploadOnAction(ActionEvent event) {
        Connection conn = DatabaseConnection.Connector();
        String excelFilePath = "data/Employee_Mock-Data.xlsx";
        int batchSize = 20;
//        uploadFileButton.setOnAction(e -> {
        System.out.print("Almost set...");
        try {
            FileInputStream fileInput = new FileInputStream(new File(excelFilePath));

            Workbook workbook = new XSSFWorkbook(fileInput);

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            assert conn != null;
            conn.setAutoCommit(false);



//            String createSQL = "CREATE TABLE employee_acc (
//            ID INTEGER NOT NULL UNIQUE,
//                    SAP_Personalnummer INTEGER NOT NULL UNIQUE,
//            Spalte1 TEXT,
//            Vorname TEXT NOT NULL,
//            Nachname TEXT NOT NULL,
//            RI TEXT,
//            Verfugbarkeit TEXT,
//            Berufserfahrung TEXT,
//            ANU TEXT,
//            Mobilitat TEXT,
//            Kompetenzen TEXT,
//            Tools TEXT,
//            Sprachen TEXT,
//            RT TEXT,
//            Aktionen TEXT,
//            Projektwunsch TEXT,
//            Schwerpunkt TEXT,
//            Division TEXT,
//            Einheit TEXT,
//            Position_RI TEXT,
//            Manager1 TEXT,
//            Manager2 TEXT,
//            PRIMARY KEY (ID AUTOINCREMENT)
//            )"
//            PreparedStatement pst = conn.prepareStatement(createSQL);

            String loadFile = "INSERT INTO employee_acc_one(SAP_Personalnummer, Spalte1, " +
                    "Vorname, Nachname, RI, Verfugbarkeit, Berufserfahrung, ANU, Mobilitat, Kompetenzen, Tools, " +
                    "Sprachen, RT, Aktionen, Projektwunsch, Schwerpunkt, Division, Einheit, Position_RI, Manager1, " +
                    "Manager2) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(loadFile);

            int count = 0;

            rowIterator.next();

            while (rowIterator.hasNext()) {
                Row nextRow = rowIterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell nextCell = cellIterator.next();

                    int columnIndex = nextCell.getColumnIndex();

                    switch (columnIndex) {
                        case 0:
                            int SAP_Personalnummer = (int) nextCell.getNumericCellValue();
                            pst.setInt(1, SAP_Personalnummer);
                            break;
                        case 1:
                            String Spalte1 = nextCell.getStringCellValue();
                            pst.setString(2, Spalte1);
                            break;
                        case 2:
                            String Vorname = nextCell.getStringCellValue();
                            pst.setString(3, Vorname);
                            break;
                        case 3:
                            String Nachname = nextCell.getStringCellValue();
                            pst.setString(4, Nachname);
                            break;
                        case 4:
                            String RI = nextCell.getStringCellValue();
                            pst.setString(5, RI);
                            break;
                        case 5:
                            String Verfugbarkeit = nextCell.getStringCellValue();
                            pst.setString(6, Verfugbarkeit);
                            break;
                        case 6:
                            String Berufserfahrung = nextCell.getStringCellValue();
                            pst.setString(7, Berufserfahrung);
                            break;
                        case 7:
                            String ANU = nextCell.getStringCellValue();
                            pst.setString(8, ANU);
                            break;
                        case 8:
                            String Mobilitat = nextCell.getStringCellValue();
                            pst.setString(9, Mobilitat);
                            break;
                        case 9:
                            String Kompetenzen = nextCell.getStringCellValue();
                            pst.setString(10, Kompetenzen);
                            break;
                        case 10:
                            String Tools = nextCell.getStringCellValue();
                            pst.setString(11, Tools);
                            break;
                        case 11:
                            String Sprachen = nextCell.getStringCellValue();
                            pst.setString(12, Sprachen);
                            break;
                        case 12:
                            String RT = nextCell.getStringCellValue();
                            pst.setString(13, RT);
                            break;
                        case 13:
                            String Aktionen = nextCell.getStringCellValue();
                            pst.setString(14, Aktionen);
                            break;
                        case 14:
                            String Projektwunsch = nextCell.getStringCellValue();
                            pst.setString(15, Projektwunsch);
                            break;
                        case 15:
                            String Schwerpunkt = nextCell.getStringCellValue();
                            pst.setString(16, Schwerpunkt);
                            break;
                        case 16:
                            String Division = nextCell.getStringCellValue();
                            pst.setString(17, Division);
                            break;
                        case 17:
                            String Einheit = nextCell.getStringCellValue();
                            pst.setString(18, Einheit);
                            break;
                        case 18:
                            String Position_RI = nextCell.getStringCellValue();
                            pst.setString(19, Position_RI);
                            break;
                        case 19:
                            String Manager1 = nextCell.getStringCellValue();
                            pst.setString(20, Manager1);
                            break;
                        case 20:
                            String Manager2 = nextCell.getStringCellValue();
                            pst.setString(21, Manager2);
                            break;
                    }
                }
                pst.addBatch();

                pst.execute();
            }

            workbook.close();

            // execute remaining queries
            pst.executeBatch();

            conn.commit();
            conn.close();


//            System.out.print("Almost workbook...");
//
//            Row row;
//
//            // read rows from Excel sheet and add it to database
//            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
//                row = sheet.getRow(i);
//                System.out.print("Almost...");
//                assert false;
//                pst.setInt(1, (int) row.getCell(0).getNumericCellValue());
//                pst.setString(2, row.getCell(1).getStringCellValue());
//                pst.setString(3, row.getCell(2).getStringCellValue());
//                pst.setString(4, row.getCell(3).getStringCellValue());
//                pst.setString(5, row.getCell(4).getStringCellValue());
//                pst.setString(6, row.getCell(5).getStringCellValue());
//                pst.setString(7, row.getCell(6).getStringCellValue());
//                pst.setString(8, row.getCell(7).getStringCellValue());
//                pst.setString(9, row.getCell(8).getStringCellValue());
//                pst.setString(10, row.getCell(9).getStringCellValue());
//                System.out.print("Almost...");
//                pst.setString(11, row.getCell(10).getStringCellValue());
//                pst.setString(12, row.getCell(11).getStringCellValue());
//                pst.setString(13, row.getCell(12).getStringCellValue());
//                pst.setString(14, row.getCell(13).getStringCellValue());
//                pst.setString(15, row.getCell(14).getStringCellValue());
//                pst.setString(16, row.getCell(15).getStringCellValue());
//                pst.setString(17, row.getCell(16).getStringCellValue());
//                pst.setString(18, row.getCell(17).getStringCellValue());
//                pst.setString(19, row.getCell(18).getStringCellValue());
//                pst.setString(20, row.getCell(19).getStringCellValue());
//                pst.setString(21, row.getCell(20).getStringCellValue());
//                System.out.print("Almost exec...");
//                pst.execute();
//                System.out.print("Almost execute...");
//            }
//
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Information Dialog");
//            alert.setHeaderText(null);
//            System.out.print("Almost alert...");
//            alert.setContentText("Employee Details Imported from Excel Sheet to Database.");
//            alert.showAndWait();
//            System.out.print("Almost alerted...");
//
//            workbook.close();
//            fileInput.close();
//            assert false;
//            pst.close();


        } catch (IOException ex1) {
            System.out.println("Error reading file");
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex1);
            ex1.printStackTrace();
        } catch (SQLException ex2) {
            System.out.println("Database Error");
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex2);
            ex2.printStackTrace();
        }
//
//        }
//        );
        System.out.println("Finally");
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

    public void fileFormStage() {
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
