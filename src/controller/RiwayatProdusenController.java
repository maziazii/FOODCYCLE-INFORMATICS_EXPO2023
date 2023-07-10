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
import model.Penawaran;
import model.Session;

public class RiwayatProdusenController implements Initializable {
    @FXML
    private TableColumn<Penawaran, Integer> TCidMakanan;

    @FXML
    private TableColumn<Penawaran, String> TCjenis;

    @FXML
    private TableColumn<Penawaran, Integer> TCjumlahMakanan;

    @FXML
    private TableColumn<Penawaran, String> TCkadaluwarsa;

    @FXML
    private TableColumn<Penawaran, String> TClokasi;

    @FXML
    private TableColumn<Penawaran, String> TCnamaMakanan;

    @FXML
    private TableColumn<Penawaran, String> TCtanggalPenawaran;

    @FXML
    private TableView<Penawaran> TVriwayatProdusen;

    @FXML
    private Button kembali;

    @FXML
    private void handleButtonKembaliAction(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(RiwayatProdusenController.class.getResource("/view/HomeProdusen.fxml"));
        Stage dialogStage = new Stage();
        dialogStage.setTitle("testing");
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(loader.load());
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
        Stage currentStage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        currentStage.close();
    }

    private ObservableList<Penawaran> getDataFromDatabase(String username) {
        ObservableList<Penawaran> penawaranList = FXCollections.observableArrayList();
        try {
            Connection connection = DBConnection.getConnection();
            String query = "SELECT * FROM tbpenawaran WHERE idPengguna = (SELECT idPengguna FROM tbregistrasi WHERE username = ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idMakanan = resultSet.getInt("idMakanan");
                String tanggalPenawaran = resultSet.getString("tanggalPenawaran");
                String namaMakanan = resultSet.getString("namaMakanan");
                int jumlahMakanan = resultSet.getInt("jumlahMakanan");
                String jenis = resultSet.getString("jenisMakanan");
                String lokasi = resultSet.getString("lokasiPengambilan");
                String kadaluwarsa = resultSet.getString("tanggalKadaluwarsa");

                Penawaran penawaran = new Penawaran(idMakanan, tanggalPenawaran, namaMakanan, jumlahMakanan, lokasi, jenis, kadaluwarsa);
                penawaranList.add(penawaran);
            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
            showErrorAlert("Error", "Failed to retrieve data from the database.");
        }

        return penawaranList;
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
        String username = Session.getLoggedInUsername();

        ObservableList<Penawaran> penawaranList = getDataFromDatabase(username);

        TVriwayatProdusen.setItems(penawaranList);

        TCidMakanan.setCellValueFactory(new PropertyValueFactory<>("idMakanan"));
        TCtanggalPenawaran.setCellValueFactory(new PropertyValueFactory<>("tanggalPenawaran"));
        TCnamaMakanan.setCellValueFactory(new PropertyValueFactory<>("namaMakanan"));
        TCjumlahMakanan.setCellValueFactory(new PropertyValueFactory<>("jumlahMakanan"));
        TCjenis.setCellValueFactory(new PropertyValueFactory<>("jenis"));
        TClokasi.setCellValueFactory(new PropertyValueFactory<>("lokasi"));
        TCkadaluwarsa.setCellValueFactory(new PropertyValueFactory<>("kadaluwarsa"));
    }
}
