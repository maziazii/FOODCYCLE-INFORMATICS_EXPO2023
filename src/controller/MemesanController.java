package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Makanan;
import model.Session;
import model.Pemesanan;
import java.time.LocalDate;

/**
 * @author LEMTIKOM
 * Muhamad Azis - 22523289
 * Andi Arya Tri Buana Agung - 22523299
 * Pugar Huda Mantoro - 22523045
 * Muhammad Haris Rusnanda - 22523282
 */
public class MemesanController implements Initializable {
    
    @FXML
    private ChoiceBox<String> CBpengambilan;

    @FXML
    private Label LjumlahPesanan;

    @FXML
    private Label Llokasi;

    @FXML
    private DatePicker DPtanggalPemesanan;

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

    private boolean isDatePickerSelected() {
        LocalDate selectedDate = DPtanggalPemesanan.getValue();
        return selectedDate != null;
    }

    private Makanan getSelectedMakanan() {
        return null;
    }

    private boolean validateQuantity(int jumlahMakanan) {
        return jumlahMakanan <= getSelectedMakanan().getJumlahMakanan();
    }

    private String getAlamatPenggunaFromDatabase(String username) {
        String alamatPengguna = "";
        try {
            Connection connection = DBConnection.getConnection();
            String query = "SELECT alamat FROM tbregistrasi WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                alamatPengguna = resultSet.getString(1);
            } else {
                showErrorAlert("Pencarian Username", "Username tidak ditemukan!");
            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
            showErrorAlert("Pencarian Alamat Pengguna", "Terjadi kesalahan dalam mendapatkan alamat pengguna!");
        }
        return alamatPengguna;
    }

    @FXML
    private void handleButtonKembaliAction(ActionEvent event)  throws Exception{
        System.out.println("tes");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomeKonsumen.fxml"));
        Stage dialogStage = new Stage(); 
        dialogStage.setTitle("testing");
        dialogStage.initModality(Modality.APPLICATION_MODAL); 
        Scene scene = new Scene(loader.load()); 
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
        Stage currentStage = (Stage)((Node)(event.getSource())).getScene().getWindow();
        currentStage.close();
    }
    
    @FXML
    private void handleButtonPilihAction(ActionEvent event) throws Exception {
        Makanan selectedMakanan = TVMemesan.getSelectionModel().getSelectedItem();
        String metodePengambilan = CBpengambilan.getValue();

        if (selectedMakanan != null) {
            // Mendapatkan jumlah makanan dari objek Makanan terpilih
            if ((metodePengambilan == null || metodePengambilan.equals("Pilih Metode Pengambilan")) && jumlahPesanan == 0) {
                showErrorAlert("Jumlah & Metode Pengambilan", "Harap tentukan jumlah pesanan dan pilih metode pengambilan terlebih dahulu.");
            } else if (metodePengambilan == null || metodePengambilan.equals("Pilih Metode Pengambilan")) {
                showErrorAlert("Metode Pengambilan", "Maaf, pilih metode pengambilan terlebih dahulu.");
            } else if (jumlahPesanan == 0) {
                showErrorAlert("Jumlah Pesanan", "Jumlah pesanan harus lebih dari nol.");
            } else {
                if (!isDatePickerSelected()) {
                    showErrorAlert("Tanggal Pemesanan", "Harap pilih tanggal pemesanan terlebih dahulu.");
                } else {
                    LocalDate selectedDate = DPtanggalPemesanan.getValue();
                    LocalDate currentDate = LocalDate.now();

                    if (selectedDate.isAfter(currentDate)) {
                        Pemesanan pemesanan = new Pemesanan(0, selectedMakanan.getIdMakanan(), 0, DPtanggalPemesanan.getValue().toString(), selectedMakanan.getNamaMakanan(), jumlahPesanan, metodePengambilan, Llokasi.getText());

                        saveDataToDatabase(pemesanan);
                        showSuccessAlert();
                    } else {
                        showErrorAlert("Tanggal Kadaluwarsa", "Makanan sudah kadaluwarsa. Pilih makanan yang belum memasuki tanggal kadaluwarsa.");
                    }
                }
            }
        } else {
            if ((metodePengambilan == null || metodePengambilan.equals("Pilih Metode Pengambilan")) && jumlahPesanan == 0) {
                showErrorAlert("Lengkapi Data Pemesanan", "Harap pilih makanan, jumlah pesanan, dan metode pengambilan terlebih dahulu.");
            } else if (metodePengambilan == null || metodePengambilan.equals("Pilih Metode Pengambilan")) {
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
                if (jumlahPesanan == 0) {
                jumlahPesanan = 1;
            } else {
                jumlahPesanan++;
            }
            LjumlahPesanan.setText (String.valueOf(jumlahPesanan));
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

    private void showSuccessAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Pesanan Berhasil Terkirim");
        alert.setHeaderText(null);
        alert.setContentText("Data pemesanan berhasil dikirim. Apakah anda akan melakukan pemesanan baru?");
        
        ButtonType okButton = new ButtonType("TIDAK");
        ButtonType penawaranBaruButton = new ButtonType("Pemesanan Baru");

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
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomeKonsumen.fxml"));
                    Stage homeKonsumenStage = new Stage();
                    homeKonsumenStage.setTitle("Home Konsumen");
                    homeKonsumenStage.setScene(new Scene(loader.load()));
                    homeKonsumenStage.initModality(Modality.APPLICATION_MODAL);
                    homeKonsumenStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (result.get() == penawaranBaruButton) {
                resetForm();
            }
        }
    }

    private void deleteMakananFromDatabase(int idMakanan) {
        try {
            Connection connection = DBConnection.getConnection();
            String query = "DELETE FROM tbmakanan WHERE idMakanan = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idMakanan);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Tampilkan alert kesalahan
            showErrorAlert("Error", "Failed to delete data from the database.");
        }
    }

    private void resetForm() {
        DPtanggalPemesanan.setValue(null);
        LjumlahPesanan.setText("0");
        Llokasi.setText("");
        CBpengambilan.getSelectionModel().clearSelection();
    }

    private int getIdPengguna(String username) {
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
                showErrorAlert("Pencarian Username", "Username tidak ditemukan!");
            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
            showErrorAlert("Pencarian id Pengguna", "Terjadi kesalahan dalam mendapatkan idPengguna!");
        }
        return idPengguna;
    }

    private void saveDataToDatabase(Pemesanan pemesanan) {
        try {
            Connection connection = DBConnection.getConnection();
            String querry = "INSERT INTO tbpemesanan (idPengguna, idMakanan, tanggalPemesanan, namaMakanan, jumlahPemesanan, metodePengambilan, lokasiMetode) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(querry);

            String username = Session.getLoggedInUsername();
            int idPengguna = getIdPengguna(username);
    
            statement.setInt(1, idPengguna);
            statement.setInt(2, pemesanan.getIdMakanan());
            statement.setString(3, pemesanan.getTanggalPemesanan());
            statement.setString(4, pemesanan.getNamaMakanan());
            statement.setInt(5, pemesanan.getJumlahPemesanan());
            statement.setString(6, pemesanan.getMetodePengambilan());
            statement.setString(7, pemesanan.getLokasiMetode());
            statement.executeUpdate();
    } catch (SQLException e) {
            e.printStackTrace();
            showErrorAlert("Error", "Failed to save data to the database.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Object connection = DBConnection.getConnection();
        if (connection == null) {
            showErrorAlert("Connection", "Gagal terhubbung ke database!");
        }

        TCTanggal.setCellValueFactory(new PropertyValueFactory<>("tanggalPenawaran"));
        TCNama.setCellValueFactory(new PropertyValueFactory<>("namaMakanan"));
        TCJumlah.setCellValueFactory(new PropertyValueFactory<>("jumlahMakanan"));
        TCLokasi.setCellValueFactory(new PropertyValueFactory<>("lokasiPengambilan"));
        TCJenis.setCellValueFactory(new PropertyValueFactory<>("jenisMakanan"));
        TCKadaluwarsa.setCellValueFactory(new PropertyValueFactory<>("tanggalKadaluwarsa"));

        // Mengatur ObservableList sebagai data sumber TableView
        TVMemesan.setItems(makananList);

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
                Llokasi.setText("");
            } else if (newValue.equals("Makanan Diantar")) {
                String username = Session.getLoggedInUsername();
                String alamatPengguna = getAlamatPenggunaFromDatabase(username);
                Llokasi.setText(alamatPengguna);
            } else if (newValue.equals("Ambil Langsung")) {
                Makanan selectedMakanan = TVMemesan.getSelectionModel().getSelectedItem();
                if (selectedMakanan != null) {
                    String lokasiPengambilan = selectedMakanan.getLokasiPengambilan();
                    Llokasi.setText(lokasiPengambilan);
                } else {
                    Llokasi.setText("");
                }
            } else {
                Llokasi.setText("");
            }
        });

        LjumlahPesanan.setText(String.valueOf(jumlahPesanan));

        makananList.addListener((ListChangeListener<Makanan>) change -> {
            while (change.next()) {
                if (change.wasRemoved()) {
                    for (Makanan removedMakanan : change.getRemoved()) {
                        if (removedMakanan.getJumlahMakanan() <= 0) {
                            deleteMakananFromDatabase(removedMakanan.getIdMakanan());
                        }
                    }
                }
            }
        });
    }
}
