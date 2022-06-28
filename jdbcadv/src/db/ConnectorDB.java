package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectorDB {
    public static Connection getConnection() throws SQLException{
        String dbURl = "jdbc:mysql://localhost:3306/bank";
        String username = "root";
        String password = "";
        Connection connection = DriverManager.getConnection(dbURl,username,password);
        return connection;
    }

    public static void close(ResultSet resultSet) throws  SQLException{
        if (resultSet != null) {
            resultSet.close();
        }
    }

    public static void close(Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws SQLException{
        if (getConnection() != null) {
            System.out.println("Connect thành công");
        }
    }
}
