/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Makanan;

/**
 *
 * @author zain
 */
public class MemesanController implements Initializable {
    
    @FXML
    private ChoiceBox<String> CBpengambilan;

    @FXML
    private Label LjumlahPesanan;

    @FXML
    private Label Llokasi;

    @FXML
    private TableColumn<Makanan, String> TCJenis;

    @FXML
    private TableColumn<Makanan, Integer> TCJumlah;
    
    @FXML
    private TableColumn<Makanan, String> TCKadaluwarsa;
    
    @FXML
    private TableColumn<Makanan, String> TCLokasi;

    @FXML
    private TableColumn<Makanan, String> TCNama;

    @FXML
    private TableColumn<Makanan, String> TCTanggal;

    @FXML
    private TableView<Makanan> TVMemesan;
    
    @FXML
    private Button kembali;

    @FXML
    private Button jumlah;

    @FXML
    private Button kurang;
    
    @FXML
    private Button pesan;
    
    private ObservableList<Makanan> makananList = FXCollections.observableArrayList();
    
    private int jumlahPesanan = 0;

    @FXML
    private void handleButtonKembaliAction(ActionEvent event)  throws Exception{
        System.out.println("tes");
        FXMLLoader loader = new FXMLLoader(); 
        loader.setLocation(HomeKonsumenController.class.getResource("/view/HomeKonsumen.fxml"));
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
    private void handleButtonPilihAction(ActionEvent event) throws Exception {
        Makanan selectedMakanan = TVMemesan.getSelectionModel().getSelectedItem();
        String metodePengambilan = CBpengambilan.getValue();

        if (selectedMakanan != null) {
            if ((metodePengambilan == null || metodePengambilan.equals("Pilih Metode Pengambilan")) && jumlahPesanan == 0) {
                // Implementasi logika untuk pesanan
                showErrorAlert("Jumlah & Metode Pengambilan", "Harap tentukan jumlah pesanan dan pilih metode pengambilan terlebih dahulu.");
            } else if (metodePengambilan == null || metodePengambilan.equals("Pilih Metode Pengambilan")) {
                showErrorAlert("Metode Pengambilan", "Maaf, pilih metode pengambilan terlebih dahulu.");
            } else if (jumlahPesanan == 0) {
                showErrorAlert("Jumlah Pesanan", "Jumlah pesanan harus lebih dari nol.");
            } else {
                System.out.println("Memesan makanan: " + selectedMakanan.getNamaMakanan());
                // Lakukan aksi pengurangan jumlah pesanan dan update label jumlah pesanan
                jumlahPesanan--;
                LjumlahPesanan.setText(String.valueOf(jumlahPesanan));

                // Lakukan aksi pengurangan jumlah makanan di database
                int idMakanan = selectedMakanan.getIdMakanan();
                updateJumlahMakanan(idMakanan, selectedMakanan.getJumlahMakanan() - 1);
            }
        } else {
            if ((metodePengambilan == null || metodePengambilan.equals("Pilih Metode Pengambilan")) && jumlahPesanan == 0) {
                showErrorAlert("Lengkapi Data Pemesanan", "Harap pilih makanan, jumlah pesanan, dan metode pengambilan terlebih dahulu.");
            }else if (metodePengambilan == null || metodePengambilan.equals("Pilih Metode Pengambilan")) {
                showErrorAlert("Metode Pengambilan", "Maaf, pilih metode pengambilan terlebih dahulu.");
            } else if (jumlahPesanan == 0) {
                showErrorAlert("Jumlah Pesanan", "Jumlah pesanan harus lebih dari nol.");
            }
        }

    }

    @FXML
    private void handleButtonKurangAction(ActionEvent event) {
        if (jumlahPesanan > 0) {
            jumlahPesanan--;
            LjumlahPesanan.setText(String.valueOf(jumlahPesanan));
        } else {
            showErrorAlert("Jumlah Pesanan", "Jumlah pesanan tidak bisa kurang dari nol.");
        }
    }

    @FXML
    private void handleButtonTambahAction(ActionEvent event) {
        Makanan selectedMakanan = TVMemesan.getSelectionModel().getSelectedItem();
        if (selectedMakanan != null) {
            int stokMakanan = selectedMakanan.getJumlahMakanan();
            if (stokMakanan > 0) {
                jumlahPesanan++;
                LjumlahPesanan.setText(String.valueOf(jumlahPesanan));
                stokMakanan--;
                selectedMakanan.setJumlahMakanan(stokMakanan);
                updateJumlahMakanan(selectedMakanan.getIdMakanan(), stokMakanan);
            } else {
                showErrorAlert("Stok Makanan", "Stok makanan tidak mencukupi.");
            }
        } else {
            showErrorAlert("Pilih Makanan", "Harap pilih makanan terlebih dahulu.");
        }
    }

    private void updateJumlahMakanan(int idMakanan, int jumlahMakanan) {
        try {
            Connection connection = DBConnection.getConnection();
            String query = "UPDATE tbmakanan SET jumlahMakanan = ? WHERE idMakanan = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, jumlahMakanan);
            statement.setInt(2, idMakanan);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Tampilkan alert kesalahan
            showErrorAlert("Error", "Failed to update data in the database.");
        }
    }

    private void loadDataFromDatabase() {
        try {
            Connection connection = DBConnection.getConnection();
            String query = "SELECT * FROM tbmakanan";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idMakanan = resultSet.getInt("idMakanan");
                String tanggalPenawaran = resultSet.getString("tanggalPenawaran");
                String namaMakanan = resultSet.getString("namaMakanan");
                int jumlahMakanan = resultSet.getInt("jumlahMakanan");
                String lokasiPengambilan = resultSet.getString("lokasiPengambilan");
                String jenisMakanan = resultSet.getString("jenisMakanan");
                String tanggalKadaluwarsa = resultSet.getString("tanggalKadaluwarsa");

                Makanan makanan = new Makanan(idMakanan, tanggalPenawaran, namaMakanan, jumlahMakanan, lokasiPengambilan, jenisMakanan, tanggalKadaluwarsa);
                makananList.add(makanan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Tampilkan alert kesalahan
            showErrorAlert("Error", "Failed to load data from database.");
        }
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
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
        // TODO
        TCTanggal.setCellValueFactory(new PropertyValueFactory<>("tanggalPenawaran"));
        TCNama.setCellValueFactory(new PropertyValueFactory<>("namaMakanan"));
        TCJumlah.setCellValueFactory(new PropertyValueFactory<>("jumlahMakanan"));
        TCLokasi.setCellValueFactory(new PropertyValueFactory<>("lokasiPengambilan"));
        TCJenis.setCellValueFactory(new PropertyValueFactory<>("jenisMakanan"));
        TCKadaluwarsa.setCellValueFactory(new PropertyValueFactory<>("tanggalKadaluwarsa"));

        // Mengatur ObservableList sebagai data sumber TableView
        TVMemesan.setItems(makananList);

        // Panggil metode untuk mengambil data dari database dan menambahkannya ke makananList
        loadDataFromDatabase();

        ObservableList<String> options = FXCollections.observableArrayList(
            "Pilih Metode Pengambilan",
            "Makanan Diantar",
            "Ambil Langsung"
        );

        CBpengambilan.setItems(options);
        CBpengambilan.getSelectionModel().selectFirst();

        CBpengambilan.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("Pilih Metode Pengambilan")) {
                CBpengambilan.getSelectionModel().clearSelection();
        } else {
            }
        });

        LjumlahPesanan.setText(String.valueOf(jumlahPesanan));

        makananList.addListener((ListChangeListener<Makanan>) change -> {
            while (change.next()) {
                if (change.wasRemoved()) {
                    TVMemesan.getItems().clear();
                    TVMemesan.getItems().addAll(makananList);
                }
            }
        });
    }
}
