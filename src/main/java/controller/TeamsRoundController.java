package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import model.Season;
import model.Team;

public class TeamsRoundController extends Controller<Season>  {
    @FXML
    private ListView<Team> teamsListView;

    public String getRoundString() {
        return "Round: " + this.model.round();
    }

    @FXML
    private void initialize() {
        teamsListView.setItems(this.model.getCurrentTeams());
        teamsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}



