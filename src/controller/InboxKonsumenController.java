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

/**
 * @author LEMTIKOM
 * Muhamad Azis - 22523289
 * Andi Arya Tri Buana Agung - 22523299
 * Pugar Huda Mantoro - 22523045
 * Muhammad Haris Rusnanda - 22523282
 */
public class InboxKonsumenController implements Initializable {
    
    @FXML
    private TableColumn<Pemesanan, Integer> TCidMakanan;

    @FXML
    private TableColumn<Pemesanan, Integer> TCidPemesanan;

    @FXML
    private TableColumn<Pemesanan, Integer> TCjumlah;

    @FXML
    private TableColumn<Pemesanan, String> TClokasi;

    @FXML
    private TableColumn<Pemesanan, String> TCnama;

    @FXML
    private TableColumn<Pemesanan, String> TCpengambilan;

    @FXML
    private TableColumn<Pemesanan, String> TCstatus;

    @FXML
    private TableView<Pemesanan> TVriwayat;

    @FXML
    private Button kemballi;

    @FXML
    private void handleButtonKembaliAction(ActionEvent event) throws Exception {
        System.out.println("tes");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HomeKonsumenController.class.getResource("/view/HomeKonsumen.fxml"));
        Stage dialogStage = new Stage();
        dialogStage.setTitle("testing");
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(loader.load());
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
        Stage currentStage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        currentStage.close();
    }

    private ObservableList<Pemesanan> getDataFromDatabase(String username) {
        ObservableList<Pemesanan> pemesananList = FXCollections.observableArrayList();
    
        try {
            Connection connection = DBConnection.getConnection();
            String query = "SELECT * FROM tbpemesanan WHERE idPengguna = (SELECT idPengguna FROM tbregistrasi WHERE username = ?)";
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
        //TODO
        String username = Session.getLoggedInUsername();

        ObservableList<Pemesanan> pemesananList = getDataFromDatabase(username);

        TVriwayat.setItems(pemesananList);

        TCidPemesanan.setCellValueFactory(new PropertyValueFactory<>("idPemesanan"));
        TCidMakanan.setCellValueFactory(new PropertyValueFactory<>("idMakanan"));
        TCnama.setCellValueFactory(new PropertyValueFactory<>("namaMakanan"));
        TCjumlah.setCellValueFactory(new PropertyValueFactory<>("jumlahPemesanan"));
        TCpengambilan.setCellValueFactory(new PropertyValueFactory<>("metodePengambilan"));
        TClokasi.setCellValueFactory(new PropertyValueFactory<>("lokasiMetode"));
        TCstatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }
}