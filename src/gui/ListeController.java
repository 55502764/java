package gui;


import entities.Consultation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.ConsultationService;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import static java.lang.String.valueOf;

public class ListeController implements Initializable {
    @FXML
    private DatePicker datec;
    @FXML
    private javafx.scene.control.TextField patient_id;
    @FXML
    private javafx.scene.control.TextField typec;

    @FXML
    private javafx.scene.control.TextField timec;
    @FXML javafx.scene.control.TextField id;

    @FXML
    private ListView<Consultation> cnsLv;
    ConsultationService rvs = new ConsultationService();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {

            ObservableList<Consultation> observableUserList = FXCollections.observableList(rvs.recuperer());
            cnsLv.setItems(observableUserList);
            cnsLv.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            cnsLv.setCellFactory(new Callback<ListView<Consultation>, ListCell<Consultation>>() {
                @Override
                public ListCell<Consultation> call(ListView<Consultation> listView) {
                    return new ListCell<Consultation>() {
                        @Override
                        protected void updateItem(Consultation cn, boolean empty) {
                            super.updateItem(cn, empty);
                            if (cn != null) {
                                System.out.println(cn.getDatec());
                                setText(cn.getId() + " -- " + cn.getDatec() + " -- " + cn.getTypec() + " -- " + cn.getTimec() + " -- " + cn.getPatient_id() );
                            } else {
                                setText(null);
                            }
                        }
                    };
                }
            });

            cnsLv.setOnMouseClicked(event -> {
                Consultation comSelectionne = cnsLv.getSelectionModel().getSelectedItem();
                if (comSelectionne != null) {

                    datec.setValue(comSelectionne.getDatec().toLocalDate());
                    typec.setText(comSelectionne.getTypec());
                    timec.setText(comSelectionne.getTimec());
                    patient_id.setText(Integer.toString(comSelectionne.getPatient_id()));



                }
            });
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    @FXML
    public void supprimerC(javafx.event.ActionEvent actionEvent) throws SQLException {
        Consultation r= cnsLv.getSelectionModel().getSelectedItem();
        rvs.supprimer(r.getId());
        cnsLv.refresh();
        // Mise à jour de la liste des utilisateurs après suppression
        ObservableList<Consultation> observableRvList = FXCollections.observableList(rvs.recuperer());
        cnsLv.setItems(observableRvList);
        // Réinitialisation de la sélection de la ListView
        cnsLv.getSelectionModel().clearSelection();
        // Réinitialisation des champs de texte et de la sélection de la ChoiceBox


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("La consultation a été supprimé avec succès !");
        alert.showAndWait();

    }
    public void modifierC(ActionEvent actionEvent) throws SQLException {


        try {
            Consultation r = cnsLv.getSelectionModel().getSelectedItem();
            if (r == null) {
                // Display an error message if no consultation is selected
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Aucune consultation sélectionnée");
                alert.setContentText("Veuillez sélectionner une consultation à modifier.");
                alert.showAndWait();
            } else {
                // Update the selected consultation with the new values
                if (datec.getValue() == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur de prise de consultation !");
                    alert.setHeaderText("Veuillez ajouter une date de consultation valide !");
                    alert.showAndWait();
                    return;
                }
                r.setDatec(Date.valueOf(datec.getValue()));
                if (typec.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur de prise de consultation !");
                    alert.setHeaderText("Veuillez sélectionner un Sujet de consultation !");
                    alert.showAndWait();
                    return;
                }
                r.setTypec(typec.getText());
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
                r.setTimec(timec.getText());
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
                r.setPatient_id(Integer.parseInt(patient_id.getText()));

                // Call the modifier method in the ConsultationService class to update the consultation in the database
                rvs.modifier(r);

                // Refresh the ListView to display the updated consultation
                cnsLv.refresh();

                // Display a success message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setHeaderText(null);
                alert.setContentText("La consultation a été modifiée avec succès !");
                alert.showAndWait();

                // Clear the text fields and the selection

                cnsLv.getSelectionModel().clearSelection();
            }
        } catch (SQLException ex) {
            // Display an error message if there is an SQL exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur lors de la modification de la consultation");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }

    public void backtoaddC(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("addConsultation.fxml"));


        Scene scene = new Scene(root);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }





}
