package sample.ems;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
//    public static Connection databaseLink;

    public static Connection Connector() {
//        String databaseName = "Employee";
//        String databaseUser = "root";
//        String databasePassword = " Onyema_07";
//        String url = "jdbc:mysql://localhost/" + databaseName;

        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:Employees.sqlite");
            System.out.println("Connected to database successfully");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
            return null;
        }
//        return databaseLink;
    }
}
