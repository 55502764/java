package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Home {
    public void GererC(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("addConsultation.fxml"));


        Scene scene = new Scene(root);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }
    public void GererO(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("addordonnance.fxml"));


        Scene scene = new Scene(root);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }
}
