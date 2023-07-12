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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
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
public class InboxProdusenController implements Initializable {    
    @FXML
    private ChoiceBox<String> CBstatus;

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
    private TableView<Pemesanan> TVinbox;

    @FXML
    private Button kembali;

    @FXML
    private void handleButtonKembaliAction(ActionEvent event)  throws Exception{
        System.out.println("tes");
        FXMLLoader loader = new FXMLLoader(); 
        loader.setLocation(HomeKonsumenController.class.getResource("/view/HomeProdusen.fxml"));
        Stage dialogStage = new Stage(); 
        dialogStage.setTitle("testing");
        dialogStage.initModality(Modality.APPLICATION_MODAL); 
        Scene scene = new Scene(loader.load()); 
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
        Stage currentStage = (Stage)((Node) (event.getSource())).getScene().getWindow();
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
    
    private int getIdMakananByUsername(String username) {
        int idMakanan = 0;
    
        try {
            Connection connection = DBConnection.getConnection();
            String query = "SELECT idMakanan FROM tbpenawaran WHERE idPengguna = (SELECT idPengguna FROM tbregistrasi WHERE username = ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
    
            if (resultSet.next()) {
                idMakanan = resultSet.getInt("idMakanan");
            }
    
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
            showErrorAlert("Error", "Failed to retrieve data from the database.");
        }
    
        return idMakanan;
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

    private void updateStatusInDatabase(Pemesanan pemesanan) {
        try {
            Connection connection = DBConnection.getConnection();
            String query = "UPDATE tbpemesanan SET status = ? WHERE idPemesanan = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, pemesanan.getStatus());
            statement.setInt(2, pemesanan.getIdPemesanan());
            statement.executeUpdate();
            statement.close();

            refreshTableData();
        } catch (SQLException e) {
            e.printStackTrace();
            showErrorAlert("Error", "Failed to update status in the database.");
        }
    }   
    
    private void updateStatusInTableView(Pemesanan pemesanan, String newStatus) {
        pemesanan.setStatus(newStatus);
        TVinbox.refresh();
    }  
    
    private void refreshTableData() {
        String username = Session.getLoggedInUsername();
        String selectedStatus = CBstatus.getValue(); 
    
        ObservableList<Pemesanan> pemesananList = getDataFromDatabase(username).filtered(pemesanan -> pemesanan.getStatus().equals(selectedStatus)); 
    
        TVinbox.setItems(pemesananList);
    
        CBstatus.getSelectionModel().clearSelection();
        CBstatus.getSelectionModel().selectFirst();
    }
    
    private void showSuccessAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(getClass().getResource("/media/checklist.png").toString()));
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/css/CSSFoodCycle.css").toExternalForm());
        dialogPane.getStyleClass().add("alert-success");

        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String username = Session.getLoggedInUsername();
        int idMakanan = getIdMakananByUsername(username);

        ObservableList<Pemesanan> pemesananList = getDataFromDatabase(username).filtered(pemesanan -> pemesanan.getIdMakanan() == idMakanan);

        TVinbox.setItems(pemesananList);

        TCidPemesanan.setCellValueFactory(new PropertyValueFactory<>("idPemesanan"));
        TCidMakanan.setCellValueFactory(new PropertyValueFactory<>("idMakanan"));
        TCnama.setCellValueFactory(new PropertyValueFactory<>("namaMakanan"));
        TCjumlah.setCellValueFactory(new PropertyValueFactory<>("jumlahPemesanan"));
        TCpengambilan.setCellValueFactory(new PropertyValueFactory<>("metodePengambilan"));
        TClokasi.setCellValueFactory(new PropertyValueFactory<>("lokasiMetode"));
        TCstatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        TVinbox.setRowFactory(tv -> {
            TableRow<Pemesanan> row = new TableRow<>();
            row.itemProperty().addListener((observable, oldItem, newItem) -> {
                if (newItem != null && newItem.getStatus().equals("Diterima")) {
                    row.getStyleClass().add("row-diterima");
                } else {
                    row.getStyleClass().remove("row-diterima");
                }
            });
            return row;
        });        

        ObservableList<String> options = FXCollections.observableArrayList(
            "Pilih Status Pengambilan",
            "Masuk",
            "Tunggu Diambil",
            "Sedang Diantar",
            "Diterima"
        );

        CBstatus.setItems(options);
        CBstatus.getSelectionModel().selectFirst();

        CBstatus.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equals("Pilih Status Pengambilan")) {
                Pemesanan selectedPemesanan = TVinbox.getSelectionModel().getSelectedItem();
                if (selectedPemesanan == null) {
                    showErrorAlert("Pilih Data Pemesanan", "Mohon pilih data pemesanan terlebih dahulu.");
                    CBstatus.getSelectionModel().clearSelection();
                } else {
                    updateStatusInTableView(selectedPemesanan, newValue);
                    updateStatusInDatabase(selectedPemesanan);
                    if (newValue.equals("Diterima")) {
                        showSuccessAlert("Pemesanan Diterima", "Pemesanan dengan ID " + selectedPemesanan.getIdPemesanan() + " sudah diterima oleh konsumen.");
                    }

                    String selectedStatus = selectedPemesanan.getStatus();
                    ObservableList<Pemesanan> pemesanan1List = TVinbox.getItems().filtered(pemesanan -> pemesanan.getStatus().equals(selectedStatus));
                    TVinbox.setItems(pemesanan1List);
                }
            }
        });               
    }
}
