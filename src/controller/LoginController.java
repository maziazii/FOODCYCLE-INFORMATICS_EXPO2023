package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * @author LEMTIKOM
 * Muhamad Azis - 22523289
 * Andi Arya Tri Buana Agung - 22523299
 * Pugar Huda Mantoro - 22523045
 * Muhammad Haris Rusnanda - 22523282
 */
public class LoginController implements Initializable {

    @FXML
    private ChoiceBox<String> CBPilihPeran;

    
    @FXML
    private PasswordField PFpassword;

    @FXML
    private TextField TFisiUsername;

    @FXML
    private Button simpanLogin;

    @FXML
    private Button register;

    private Connection connection;

    @FXML
    private void handleButtonRegisterAction(ActionEvent event) throws IOException {
        System.out.println("tes");
        FXMLLoader loader = new FXMLLoader(); 
        loader.setLocation(RegisterController.class.getResource("/view/Register.fxml"));
        Stage dialogStage = new Stage(); 
        dialogStage.setTitle("testing");
        dialogStage.initModality(Modality.APPLICATION_MODAL); 
        Scene scene = new Scene(loader.load()); 
        dialogStage.setScene(scene);
        // Show the dialog and wait until the user closes it dialogStage.showAndWait();
        dialogStage.showAndWait();
        Stage currentStage = (Stage)((Node) (event.getSource())).getScene().getWindow();
        currentStage.close();
    }

    @FXML
    private void handleButtonHomeAction(ActionEvent event) {
        String username = TFisiUsername.getText();
        String password = PFpassword.getText();
        String peran = CBPilihPeran.getValue();

        if (username.isEmpty() || password.isEmpty() || peran == null || peran.equals("Pilih Masuk Sebagai")) {
            showErrorAlert("Harap isi semua data yang diperlukan!");
            return;
        }

        boolean loginSuccess = login(username, password);

        if (loginSuccess) {
            openHomeWindow(peran);
        } else {
            showErrorAlert("Username, password, atau peran tidak valid!");
        }
    }

    private void openHomeWindow(String peran) {
        try {
            String resourcePath = "/view/Home" + peran + ".fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resourcePath));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Home " + peran);

            Window loginWindow = simpanLogin.getScene().getWindow();
            loginWindow.hide();

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean login(String username, String password) {
        try {
            String query = "SELECT COUNT(*) FROM tbregistrasi WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
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
    
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> options = FXCollections.observableArrayList(
            "Pilih Masuk Sebagai",
            "Konsumen",
            "Produsen"
        );

        CBPilihPeran.setItems(options);
        CBPilihPeran.getSelectionModel().selectFirst();

        CBPilihPeran.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("Pilih Masuk Sebagai")) {
                CBPilihPeran.getSelectionModel().clearSelection();
            } else {
                // Lakukan tindakan lain sesuai pilihan pengguna
            }
        });

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/foodcycle", "root", ""); // Sesuaikan dengan informasi koneksi Anda
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void finalize() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

