/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author LEMTIKOM
 * Muhamad Azis - 22523289
 * Andi Arya Tri Buana Agung - 22523299
 * Pugar Huda Mantoro - 22523045
 * Muhammad Haris Rusnanda - 22523282
 */
public class FoodCycle extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LandingPage.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**zz
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
