package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NewFXMain1 extends Application {

    @Override
    public void start(Stage primaryStage)  {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Home.fxml"));


            Parent root = loader.load();
            Scene sc = new Scene(root);
            primaryStage.setTitle("Consultation & Ordonnance");
            primaryStage.setScene(sc);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println("Error" + ex.getMessage());
        }


    }
    public static void main(String[] args) {

        launch(args);
    }


}
