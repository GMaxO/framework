package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseProvider {
    public static Connection connectToDb(String url, String username, String password) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void disconnectFromDb(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet executeSQLRequest(Connection connection, String sqlRequest) {
        ResultSet resultSet = null;
        try {
            resultSet = connection.createStatement().executeQuery(sqlRequest);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static void execSQLReq(Connection connection, String sqlRequest) {
        try {
            connection.createStatement().execute(sqlRequest);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
