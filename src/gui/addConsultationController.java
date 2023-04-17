package gui;

import entities.Consultation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import services.ConsultationService;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import static sun.plugin.ClassLoaderInfo.reset;

public class addConsultationController implements Initializable {

    @FXML
    private javafx.scene.control.TextField patient_id;
    @FXML
    private javafx.scene.control.TextField typec;

    @FXML
    private DatePicker datec;


    @FXML
    private javafx.scene.control.TextField timec;




    public void ajouterC(ActionEvent actionEvent) {
        Consultation c = new Consultation();
        try {
            if (datec.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de prise de consultation !");
                alert.setHeaderText("Veuillez ajouter une date de consultation valide !");
                alert.showAndWait();
                return;
            }
            c.setDatec(Date.valueOf(datec.getValue()));


            // Vérification du patient_id (int)
            int patientId = Integer.parseInt(patient_id.getText());

            if (patientId <= 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de prise de consultation !");
                alert.setHeaderText("Veuillez ajouter un patient_id valide (> 0) !");
                alert.showAndWait();
                return;
            }
            if (patient_id.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de prise de consultation !");
                alert.setHeaderText("Veuillez sélectionner un patient !");
                alert.showAndWait();
                return;
            }
            c.setPatient_id(patientId);

            if (typec.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de prise de consultation !");
                alert.setHeaderText("Veuillez sélectionner un Sujet de consultation !");
                alert.showAndWait();
                return;
            }
            c.setTypec(typec.getText());
            if (timec.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de prise de consultation !");
                alert.setHeaderText("Veuillez sélectionner l'heure consultation !");
                alert.showAndWait();
                return;
            }
            if (!timec.getText().matches("^1[0-2]:[0-5][0-9]$")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de prise de consultation !");
                alert.setHeaderText("Veuillez saisir une heure valide  !");
                alert.showAndWait();
                return;
            }
            c.setTimec(timec.getText());


            // Appel du service pour ajouter le rendez-vous
            ConsultationService cns = new ConsultationService();
            cns.ajouter(c);

            // Réinitialisation des champs
            reset();


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    @FXML
    void afficherC(ActionEvent event) throws IOException {

            Parent root = FXMLLoader.load(getClass().getResource("Liste.fxml"));


        Scene scene = new Scene(root);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();



    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void main(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));


        Scene scene = new Scene(root);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }
}
