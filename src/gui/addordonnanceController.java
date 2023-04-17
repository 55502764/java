package gui;

import entities.Consultation;
import entities.Ordonnance;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import services.ConsultationService;
import services.OrdonnanceService;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import static sun.plugin.ClassLoaderInfo.reset;

public class addordonnanceController implements Initializable {
    @FXML
    private javafx.scene.control.TextField medicament;
    @FXML
    private TextArea description;

    @FXML
    private DatePicker dateo;


    public void ajouterO(ActionEvent actionEvent) {
        Ordonnance c = new Ordonnance();
        try {
            if (dateo.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de prise de Ordonnance !");
                alert.setHeaderText("Veuillez ajouter une date d'Ordonnance valide !");
                alert.showAndWait();
                return;
            }
            c.setdateo(Date.valueOf(dateo.getValue()));


            // Vérification du patient_id (int)

            // Vérification du type_rv (ChoiceBox)
            if (description.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de prise de Ordonnance !");
                alert.setHeaderText("Veuillez saisir la description de l'Ordonnance!");
                alert.showAndWait();
                return;
            }
            c.setDescription(description.getText());
            if (medicament.getText().isEmpty() ){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de prise de Ordonnance !");
                alert.setHeaderText("Veuillez saisir les medicaments !");
                alert.showAndWait();
                return;
            }


            c.setMedicament(medicament.getText());

            //Consultation selectedConsultation = ... // get the selected Consultation object
            //c.setConsultation(selectedConsultation);
            // Appel du service pour ajouter le rendez-vous
            OrdonnanceService cns = new OrdonnanceService();
            cns.ajouter(c);

            // Réinitialisation des champs
            reset();


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    @FXML
    void afficherO(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("ListeOrdonnance.fxml"));


        Scene scene = new Scene(root);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void main2(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));


        Scene scene = new Scene(root);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }
}
