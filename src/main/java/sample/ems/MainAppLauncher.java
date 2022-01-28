package sample.ems;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainAppLauncher {
    public static void main(String[] args) {
        Main.main(args);
//        checkDrivers();
    }

//    private static boolean checkDrivers() {
//        try{
//            Class.forName("org.sqlite.JDBC");
//            DriverManager.registerDriver(new org.sqlite.JDBC());
//            return true;
//        } catch (ClassNotFoundException | SQLException e) {
//            Logger.getAnonymousLogger()
//                    .log(Level.SEVERE, LocalDateTime.now() + ": Could not start SQL Drivers");
//            return false;
//        }
//    }
}
