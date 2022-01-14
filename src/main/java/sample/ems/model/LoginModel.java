package sample.ems.model;

import sample.ems.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
    Connection connection;

    public LoginModel() {
        connection = DatabaseConnection.Connector();
        if (connection == null) System.exit(1);
    }

    public boolean isDbConnected() {
        try {
            return !connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
            return false;
        }
    }

    public boolean validateLogin(String user, String pass) throws SQLException {
        PreparedStatement preparedstatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM user_account WHERE username = ? and password = ?";

        try {

            preparedstatement = connection.prepareStatement(query);
            preparedstatement.setString(1, user);
            preparedstatement.setString(2, pass);

            resultSet = preparedstatement.executeQuery();

            if (resultSet.next()) {

                return true;

            } else {

                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
            return false;
        } finally {
            assert preparedstatement != null;
            preparedstatement.close();
            assert resultSet != null;
            resultSet.close();
        }
    }
}
