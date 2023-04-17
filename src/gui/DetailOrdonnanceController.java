package gui;

import entities.Ordonnance;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import services.OrdonnanceService;

import java.io.IOException;

public class DetailOrdonnanceController {
    @FXML
    private Label medicament;

    @FXML
    private Label dateo;

    @FXML
    private Label description;



    private int ordonnanceId;

    public void setOrdonnanceId(int id) {
        ordonnanceId = id;
        System.out.println("thi"+ordonnanceId);
        displayConsultationDetails();
    }

    private void displayConsultationDetails() {
        // Retrieve the details of the selected consultation from the database using its ID
        OrdonnanceService rs = new OrdonnanceService();
        System.out.println("tsecs"+ordonnanceId);
        Ordonnance ordonnance = rs.getById(ordonnanceId);
        System.out.println("thi"+ordonnance);
        // Display the details in the labels
        if (ordonnance.getdateo() != null) {
            dateo.setText(ordonnance.getdateo().toString());
        } else {
            dateo.setText("");
        }
        description.setText(ordonnance.getDescription());
        medicament.setText(ordonnance.getMedicament());

    }
    public void backtoliste(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("ListeOrdonnance.fxml"));


        Scene scene = new Scene(root);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }
}
