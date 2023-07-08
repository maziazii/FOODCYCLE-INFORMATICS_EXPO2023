/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package controller;

import model.Makanan;
import model.Session;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import database.DBConnection;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * @author LEMTIKOM
 * Muhamad Azis - 22523289
 * Andi Arya Tri Buana Agung - 22523299
 * Pugar Huda Mantoro - 22523045
 * Muhammad Haris Rusnanda - 22523282
 */
public class MenawarkanController implements Initializable {
    
    @FXML
    private ChoiceBox<String> CBJenis;

    @FXML
    private DatePicker DPKadaluwarsa;

    @FXML
    private DatePicker DPPenawaran;

    @FXML
    private TextField TFJumlahMakanan;

    @FXML
    private TextField TFLokasiPengambilan;

    @FXML
    private TextField TFNamaMakanan;

    @FXML
    private Button kembali;

    @FXML
    private Rectangle myChoiceBox;

    @FXML
    private Button tawarkan;

    private boolean isPenawaranDilakukan = false;
    
    @FXML
    private void handleButtonKembaliAction(ActionEvent event)  throws Exception{
        System.out.println("tes");
        FXMLLoader loader = new FXMLLoader(); 
        loader.setLocation(HomeProdusenController.class.getResource("/view/HomeProdusen.fxml"));
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
    private void handleButtonTawarkanAction(ActionEvent event) {
        if (isFormValid()) {
            String tanggalPenawaran = DPPenawaran.getValue().toString();
            String namaMakanan = TFNamaMakanan.getText();
            int jumlahMakanan;
            try {
                jumlahMakanan = Integer.parseInt(TFJumlahMakanan.getText());
            } catch (NumberFormatException e) {
                showErrorAlert("Jumlah makanan harus berupa angka!");
                return;
            }
            String lokasiPengambilan = TFLokasiPengambilan.getText();
            String jenisMakanan = CBJenis.getValue();
            String tanggalKadaluwarsa = DPKadaluwarsa.getValue().toString();

            if (jenisMakanan.equals("Pilih Jenis Makanan")) {
                showErrorAlert("Harap pilih jenis makanan!");
            } else {
                Makanan makanan = new Makanan(0, tanggalPenawaran, namaMakanan, jumlahMakanan, lokasiPengambilan, jenisMakanan, tanggalKadaluwarsa);
                saveMakananToDatabase(makanan);
                showSuccessAlert();
                isPenawaranDilakukan = true;
            }
        } else {
            showErrorAlert("Harap isi semua data yang diperlukan!");
        }
    }

    private boolean isFormValid() {
        if (DPPenawaran.getValue() == null || TFNamaMakanan.getText().isEmpty() || TFJumlahMakanan.getText().isEmpty() ||
                TFLokasiPengambilan.getText().isEmpty() || CBJenis.getValue() == null || DPKadaluwarsa.getValue() == null) {
            return false;
        }
        return true;
    }

    private void saveMakananToDatabase(Makanan makanan) {
        try {
            Connection connection = DBConnection.getConnection();
            String query = "INSERT INTO tbmakanan (idPengguna, tanggalPenawaran, namaMakanan, jumlahMakanan, lokasiPengambilan, jenisMakanan, tanggalKadaluwarsa) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
    
            String username = Session.getLoggedInUsername(); // Mendapatkan username dari Session
            int idPengguna = getIdPenggunaFromDatabase(username);
    
            statement.setInt(1, idPengguna);
            statement.setString(2, makanan.getTanggalPenawaran());
            statement.setString(3, makanan.getNamaMakanan());
            statement.setInt(4, makanan.getJumlahMakanan());
            statement.setString(5, makanan.getLokasiPengambilan());
            statement.setString(6, makanan.getJenisMakanan());
            statement.setString(7, makanan.getTanggalKadaluwarsa());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            showErrorAlert("Terjadi kesalahan dalam menyimpan data makanan!");
        }
    }

    private int getIdPenggunaFromDatabase(String username) {
        int idPengguna = 0;
        try {
            Connection connection = DBConnection.getConnection();
            String query = "SELECT idPengguna FROM tbregistrasi WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                idPengguna = resultSet.getInt(1);
            } else {
                showErrorAlert("Username tidak ditemukan!");
            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
            showErrorAlert("Terjadi kesalahan dalam mendapatkan idPengguna!");
        }
        return idPengguna;
    }
    private void showSuccessAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Penawaran Berhasil");
        alert.setHeaderText(null);
        alert.setContentText("Data makanan berhasil ditawarkan. Apakah anda akan melakukan penawaran baru?");
        
        ButtonType okButton = new ButtonType("TIDAK");
        ButtonType penawaranBaruButton = new ButtonType("Penawaran Baru");

        alert.getButtonTypes().setAll(okButton, penawaranBaruButton);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(getClass().getResource("/media/checklist.png").toString()));
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/css/CSSFoodCycle.css").toExternalForm());
        dialogPane.getStyleClass().add("alert-success");
        
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent()) {
            if (result.get() == okButton) {
                // Lakukan tindakan jika OK dipilih (misalnya menutup dialog)
                alert.close();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomeProdusen.fxml"));
                    Stage homeProdusenStage = new Stage();
                    homeProdusenStage.setTitle("Home Produsen");
                    homeProdusenStage.setScene(new Scene(loader.load()));
                    homeProdusenStage.initModality(Modality.APPLICATION_MODAL);
                    homeProdusenStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (result.get() == penawaranBaruButton) {
                // Reset data input dan status penawaran
                resetForm();
                isPenawaranDilakukan = false;
            }
        }
    }

    private void showErrorAlert(String massage) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(massage);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(getClass().getResource("/media/attention.png").toString()));

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/css/CSSFoodCycle.css").toExternalForm());
        dialogPane.getStyleClass().add("alert-error");

        alert.showAndWait();
    }

    private void resetForm() {
        DPPenawaran.setValue(null);
        TFNamaMakanan.clear();
        TFJumlahMakanan.clear();
        TFLokasiPengambilan.clear();
        CBJenis.getSelectionModel().clearSelection();
        DPKadaluwarsa.setValue(null);
        isPenawaranDilakukan = false;
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> options = FXCollections.observableArrayList(
            "Pilih Jenis Makanan",
            "Basah",
            "Kering"
        );

        CBJenis.setItems(options);
        CBJenis.getSelectionModel().selectFirst();

        CBJenis.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("Pilih Jenis Makanan")) {
                CBJenis.getSelectionModel().clearSelection();
            } else {
                // Lakukan tindakan lain sesuai pilihan pengguna
            }
        });
    }       
}
