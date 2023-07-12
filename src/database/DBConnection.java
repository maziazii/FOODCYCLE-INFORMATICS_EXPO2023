package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * @author LEMTIKOM
 * Muhamad Azis - 22523289
 * Andi Arya Tri Buana Agung - 22523299
 * Pugar Huda Mantoro - 22523045
 * Muhammad Haris Rusnanda - 22523282
 */
public class DBConnection {
    private static final String DB_URL = "jdbc:mysql://aws.connect.psdb.cloud/foodcycle?sslMode=VERIFY_IDENTITY";
    private static final String USERNAME = "hr68d6ypo2sfe8p8uv5b";
    private static final String PASSWORD = "pscale_pw_j140KSL2BACgELqQ8yHVVpRei2FL186hBMAunUbLg8A";

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
