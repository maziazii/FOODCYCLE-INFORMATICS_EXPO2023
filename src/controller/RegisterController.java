package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import database.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Registrasi;

/**
 * @author LEMTIKOM
 * Muhamad Azis - 22523289
 * Andi Arya Tri Buana Agung - 22523299
 * Pugar Huda Mantoro - 22523045
 * Muhammad Haris Rusnanda - 22523282
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField TFalamat;

    @FXML
    private TextField TFnama;

    @FXML
    private TextField TFpassword;

    @FXML
    private TextField TFtelepon;

    @FXML
    private TextField TFusername;

    @FXML
    private Button simpanRegister;

    private Connection connection;
    
    @FXML
    private void handleButtonLoginAction(ActionEvent event)  throws Exception{
        System.out.println("tes");
        FXMLLoader loader = new FXMLLoader(); 
        loader.setLocation(LoginController.class.getResource("/view/Login.fxml"));
        Stage dialogStage = new Stage(); 
        dialogStage.setTitle("testing");
        dialogStage.initModality(Modality.APPLICATION_MODAL); 
        Scene scene = new Scene(loader.load()); 
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
        Stage currentStage = (Stage)((Node) (event.getSource())).getScene().getWindow();
        currentStage.close();
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        String nama = TFnama.getText();
        String alamat = TFalamat.getText();
        String noTelepon = TFtelepon.getText();
        String username = TFusername.getText();
        String password = TFpassword.getText();

        if (nama.isEmpty() || alamat.isEmpty() || noTelepon.isEmpty() || username.isEmpty() || password.isEmpty()) {
            showErrorAlert("Semua bagian harus diisi!");
            return; 
        }
        if (!noTelepon.matches("\\d+")) {
            showErrorAlert("Telepon hanya boleh berisi angka!");
            return; 
        }
        if (usernameAlreadyExists(username)) {
            showErrorAlert("Username sudah digunakan!");
            return; 
        }

        Registrasi registrasi = new Registrasi(nama, alamat, username, "", noTelepon, password); // Perhatikan perubahan di sini
        saveRegistrasi(registrasi);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Login.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(loader.load()));
        stage.show();

        TFnama.clear();
        TFalamat.clear();
        TFtelepon.clear();
        TFusername.clear();
        TFpassword.clear();
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(getClass().getResource("/media/attention.png").toString()));

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/css/CSSFoodCycle.css").toExternalForm());
        dialogPane.getStyleClass().add("alert-error");

        alert.showAndWait();
    }

    private boolean usernameAlreadyExists(String username) {
        try {
            String query = "SELECT COUNT(*) FROM tbregistrasi WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);

            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private void saveRegistrasi(Registrasi registrasi) {
        try {
            String sql = "INSERT INTO tbregistrasi (nama, alamat, noTelepon, username, password, peran) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, registrasi.getNama());
            statement.setString(2, registrasi.getAlamat());
            statement.setString(3, registrasi.getnoTelepon());
            statement.setString(4, registrasi.getUsername());
            statement.setString(5, registrasi.getPassword());
            statement.setString(6, registrasi.getPeran()); // Perhatikan perubahan di sini

            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            showErrorAlert("Gagal menyimpan data ke database!");
        }
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        connection = DBConnection.getConnection();
        if (connection == null) {
            showErrorAlert("Gagal terhubbung ke database!");
        }
    }
}