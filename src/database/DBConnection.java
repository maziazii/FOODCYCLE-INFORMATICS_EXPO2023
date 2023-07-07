package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String DB_URL = "jdbc:mysql://aws.connect.psdb.cloud/dbfoodcycle?sslMode=VERIFY_IDENTITY";
    private static final String USERNAME = "dqm8fgh0fqjdpltcmlz0";
    private static final String PASSWORD = "pscale_pw_2fbpBlyeZQ2HQzP6lt1AfIE1T4cI1H4ZesKek2cm8PG";

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                System.out.println("Connected to the database.");
            } catch (SQLException e) {
                System.out.println("Failed to connect to the database.");
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Disconnected from the database.");
            } catch (SQLException e) {
                System.out.println("Failed to close the database connection.");
                e.printStackTrace();
            }
        }
    }
}
