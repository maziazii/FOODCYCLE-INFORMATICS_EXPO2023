package controller;

import java.net.URL;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Pemesanan;
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
        // Show the dialog and wait until the user closes it dialogStage.showAndWait();
        dialogStage.showAndWait();
        Stage currentStage = (Stage)((Node) (event.getSource())).getScene().getWindow();
        currentStage.close();
    }

    @FXML
    private void handleButtonAction(ActionEvent event)throws Exception{
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
            if (newValue.equals("Pilih Status Pengambilan")) {
                CBstatus.getSelectionModel().clearSelection();
        } else {
                // Lakukan tindakan lain sesuai pilihan pengguna
            }
        });
    }

}
