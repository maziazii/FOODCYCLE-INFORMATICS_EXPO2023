package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Session;

/**
 * @author LEMTIKOM
 * Muhamad Azis - 22523289
 * Andi Arya Tri Buana Agung - 22523299
 * Pugar Huda Mantoro - 22523045
 * Muhammad Haris Rusnanda - 22523282
 */
public class DBConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/foodcycle";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

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

    // public static String getAlamatPengguna() {
    //     String alamatPengguna = null;

    //     try {
    //         Connection connection = DBConnection.getConnection();
    //         String query = "SELECT alamat FROM tbregistrasi WHERE idPengguna = ?";
    //         PreparedStatement statement = connection.prepareStatement(query);
    //         statement.setInt(1, username); // Gantikan dengan metode yang sesuai untuk mendapatkan ID pengguna saat ini
    //         ResultSet resultSet = statement.executeQuery();
    //         if (resultSet.next()) {
    //             alamatPengguna = resultSet.getString(1);
    //         } else {
    //             showErrorAlert("Lokasi", "Alamat pengguna tidak ditemukan!");
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     return alamatPengguna;
    // }

    public static String getLokasiPengambilan(int idMakanan) {
        String lokasiPengambilan = null;

        try (Connection connection = getConnection()) {
            String query = "SELECT lokasiPengambilan FROM tbmakanan WHERE idMakanan = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idMakanan);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                lokasiPengambilan = resultSet.getString("lokasiPengambilan");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lokasiPengambilan;
    }
}
