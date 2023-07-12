package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Node;

/**
 * @author LEMTIKOM
 * Muhamad Azis - 22523289
 * Andi Arya Tri Buana Agung - 22523299
 * Pugar Huda Mantoro - 22523045
 * Muhammad Haris Rusnanda - 22523282
 */
public class LandingPageController implements Initializable {

    @FXML
    private Button login;

    @FXML
    private Button register;
       
    @FXML
    private void handleButtonLoginAction(ActionEvent event)  throws Exception{
        System.out.println("tes");
        FXMLLoader loader = new FXMLLoader(); 
        loader.setLocation(LoginController.class.getResource("/view/Login.fxml"));
        Stage dialogStage = new Stage(); 
        dialogStage.setTitle("testing");
        dialogStage.initModality(Modality.APPLICATION_MODAL); 
        Scene scene = new Scene(loader.load()); 
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
        Stage currentStage = (Stage)((Node) (event.getSource())).getScene().getWindow();
        currentStage.close();
    }

    @FXML
    private void handleButtonRegisterAction(ActionEvent event)  throws Exception{
        System.out.println("tes");
        FXMLLoader loader = new FXMLLoader(); 
        loader.setLocation(RegisterController.class.getResource("/view/Register.fxml"));
        Stage dialogStage = new Stage(); 
        dialogStage.setTitle("testing");
        dialogStage.initModality(Modality.APPLICATION_MODAL); 
        Scene scene = new Scene(loader.load()); 
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
        Stage currentStage = (Stage)((Node) (event.getSource())).getScene().getWindow();
        currentStage.close();
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}