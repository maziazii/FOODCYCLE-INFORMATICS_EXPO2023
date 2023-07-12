package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Pemesanan;
import model.Session;

public class RiwayatKonsumenController implements Initializable {
    @FXML
    private TableColumn<Pemesanan, String> TCTanggalPemesanan;

    @FXML
    private TableColumn<Pemesanan, Integer> TCidMakanan;

    @FXML
    private TableColumn<Pemesanan, Integer> TCidPemesanan;

    @FXML
    private TableColumn<Pemesanan, Integer> TCjumlahMakanan;

    @FXML
    private TableColumn<Pemesanan, String> TClokasiMetode;

    @FXML
    private TableColumn<Pemesanan, String> TCmetodePengambilan;

    @FXML
    private TableColumn<Pemesanan, String> TCnamaMakananan;

    @FXML
    private TableView<Pemesanan> TVriwayatKonsumen;

    @FXML
    private Button kembali;

    @FXML
    private void handleButtonKembaliAction(ActionEvent event) throws Exception {
        System.out.println("tes");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(RiwayatKonsumenController.class.getResource("/view/HomeKonsumen.fxml"));
        Stage dialogStage = new Stage();
        dialogStage.setTitle("testing");
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(loader.load());
        dialogStage.setScene(scene);
        // Show the dialog and wait until the user closes it dialogStage.showAndWait();
        dialogStage.showAndWait();
        Stage currentStage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        currentStage.close();
    }

    private ObservableList<Pemesanan> getDiterimaPemesananFromDatabase(String username) {
         ObservableList<Pemesanan> pemesananList = FXCollections.observableArrayList();
        
        try {
            Connection connection = DBConnection.getConnection();
            String query = "SELECT * FROM tbpemesanan WHERE idPengguna = (SELECT idPengguna FROM tbregistrasi WHERE username = ?) AND status = 'Diterima'";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
        
            while (resultSet.next()) {
                int idPengguna = resultSet.getInt("idPengguna");
                int idPemesanan = resultSet.getInt("idPemesanan");
                int idMakanan = resultSet.getInt("idMakanan");
                String tanggalPemesanan = resultSet.getString("tanggalPemesanan");
                String namaMakanan = resultSet.getString("namaMakanan");
                int jumlahPemesanan = resultSet.getInt("jumlahPemesanan");
                String metodePengambilan = resultSet.getString("metodePengambilan");
                String lokasiMetode = resultSet.getString("lokasiMetode");
                String status = resultSet.getString("status");
        
                Pemesanan pemesanan = new Pemesanan(idPengguna, idMakanan, idPemesanan, tanggalPemesanan, namaMakanan, jumlahPemesanan, metodePengambilan, lokasiMetode, status);
                pemesananList.add(pemesanan);
            }
        
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
            showErrorAlert("Error", "Failed to retrieve data from the database.");
        }
        
        return pemesananList;
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(getClass().getResource("/media/attention.png").toString()));

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/css/CSSFoodCycle.css").toExternalForm());
        dialogPane.getStyleClass().add("alert-error");

        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Mendapatkan username pengguna yang sedang login
        String username = Session.getLoggedInUsername();

        // Mengambil data pemesanan dari database berdasarkan username pengguna
        ObservableList<Pemesanan> pemesananList = getDiterimaPemesananFromDatabase(username);

        // Mengatur ObservableList sebagai data sumber TableView
        TVriwayatKonsumen.setItems(pemesananList);

        // Mengatur kolom-kolom untuk menampilkan data pemesanan
        TCidPemesanan.setCellValueFactory(new PropertyValueFactory<>("idPemesanan"));
        TCidMakanan.setCellValueFactory(new PropertyValueFactory<>("idMakanan"));
        TCnamaMakananan.setCellValueFactory(new PropertyValueFactory<>("namaMakanan"));
        TCjumlahMakanan.setCellValueFactory(new PropertyValueFactory<>("jumlahPemesanan"));
        TCmetodePengambilan.setCellValueFactory(new PropertyValueFactory<>("metodePengambilan"));
        TClokasiMetode.setCellValueFactory(new PropertyValueFactory<>("lokasiMetode"));
        TCTanggalPemesanan.setCellValueFactory(new PropertyValueFactory<>("tanggalPemesanan"));
    }
}