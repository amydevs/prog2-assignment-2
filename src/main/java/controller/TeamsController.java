package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.beans.binding.BooleanBinding;
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
        BooleanBinding disableBinding = this.teamsTableView
            .getSelectionModel()
            .selectedItemProperty()
            .isNull();
        manageButton.disableProperty().bind(disableBinding);
        deleteButton.disableProperty().bind(disableBinding);
        teamsTableView.setItems(this.model.currentTeams);
    }

    @FXML
    private void manageTeam() {
        // never null due to disabled logic
        Team team = teamsTableView.getSelectionModel().getSelectedItem();
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/nba.png"));
            
            ViewLoader.showStage(
                team,
                "/view/ManageTeamView.fxml",
                "Managing Team: " + team.getName(),
                stage
            );
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void deleteTeam() {
        // never null due to disabled logic
        Team team = teamsTableView.getSelectionModel().getSelectedItem();
        this.model.remove(team);
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
