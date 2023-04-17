package gui;


import entities.Consultation;
import entities.Ordonnance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.OrdonnanceService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;

import static java.lang.String.valueOf;

public class ListeOrodnnanceController implements Initializable {
    @FXML
    private ListView<Ordonnance> cnsLv;
    OrdonnanceService rvs = new OrdonnanceService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {

            ObservableList<Ordonnance> observableUserList = FXCollections.observableList(rvs.recuperer2());
            cnsLv.setItems(observableUserList);
            cnsLv.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            cnsLv.setCellFactory(new Callback<ListView<Ordonnance>, ListCell<Ordonnance>>() {

                @Override
                public ListCell<Ordonnance> call(ListView<Ordonnance> listView) {
                    return new ListCell<Ordonnance>() {
                        @Override
                        protected void updateItem(Ordonnance cn, boolean empty) {
                            super.updateItem(cn, empty);
                            if (cn != null) {

                                setText(cn.getId() + " -- " + cn.getdateo() + " -- " + cn.getMedicament() + " -- " + cn.getDescription());
                            } else {
                                setText(null);
                            }
                        }

                    };
                }


            });


        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    @FXML
    public void detail(ActionEvent event) throws IOException {
// Get the selected consultation from the ListView
        Ordonnance selectedOrdonnance = cnsLv.getSelectionModel().getSelectedItem();

        if (selectedOrdonnance != null) {
            // If a consultation is selected, create a new window to display its details
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailOrdonnance.fxml"));
            Parent root = loader.load();
            DetailOrdonnanceController controller = loader.getController();
            System.out.println(selectedOrdonnance.getId());
            controller.setOrdonnanceId(selectedOrdonnance.getId());

            // Show the new window
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    public void backtoadd(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("addordonnance.fxml"));


        Scene scene = new Scene(root);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

}
