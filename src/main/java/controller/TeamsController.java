package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Team;
import model.Teams;

public class TeamsController extends Controller<Teams> {

    @FXML
    private TableView<Team> teamsTableView;

    @FXML
    private Button manageButton;

    @FXML
    private Button deleteButton;

    @FXML
    private void initialize() {
        manageButton.setDisable(true);
        deleteButton.setDisable(true);
        teamsTableView
                .getSelectionModel()
                .selectedItemProperty()
                .addListener((_observable, _oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        manageButton.setDisable(false);
                        deleteButton.setDisable(false);
                        return;
                    }
                    manageButton.setDisable(true);
                    deleteButton.setDisable(true);
                });
        teamsTableView.setItems(this.model.currentTeams);
    }

    @FXML
    private void addTeam() {
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/nba.png"));
            ViewLoader.showStage(this.model, "/view/AddTeam.fxml", "Adding New Team", stage);
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void close() {
        this.stage.close();
    }
}
