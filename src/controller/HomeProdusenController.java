package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author LEMTIKOM
 * Muhamad Azis - 22523289
 * Andi Arya Tri Buana Agung - 22523299
 * Pugar Huda Mantoro - 22523045
 * Muhammad Haris Rusnanda - 22523282
 */
public class HomeProdusenController implements Initializable {
    @FXML
    private Button inbox;

    @FXML
    private Button keluar;

    @FXML
    private Button menawarkan;

    @FXML
    private Button pindah;

    @FXML
    private Button riwayat;
        
    @FXML
    private void handleButtonKeluar(ActionEvent event) throws IOException {
        System.out.println("tes");
        FXMLLoader loader = new FXMLLoader(); 
        loader.setLocation(HomeProdusenController.class.getResource("/view/LandingPage.fxml"));
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
    private void handleButtonPindah(ActionEvent event) throws IOException {
        System.out.println("tes");
        FXMLLoader loader = new FXMLLoader(); 
        loader.setLocation(HomeProdusenController.class.getResource("/view/HomeKonsumen.fxml"));
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
    private void handleButtonMenawarkanAction(ActionEvent event)  throws Exception{
        System.out.println("tes");
        FXMLLoader loader = new FXMLLoader(); 
        loader.setLocation(HomeProdusenController.class.getResource("/view/Menawarkan.fxml"));
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
    private void handleButtonInboxAction(ActionEvent event)  throws Exception{
        System.out.println("tes");
        FXMLLoader loader = new FXMLLoader(); 
        loader.setLocation(HomeProdusenController.class.getResource("/view/InboxProdusen.fxml"));
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
    private void handleButtonRiwayatAction(ActionEvent event)  throws Exception{
        System.out.println("tes");
        FXMLLoader loader = new FXMLLoader(); 
        loader.setLocation(HomeProdusenController.class.getResource("/view/RiwayatProdusen.fxml"));
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
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}