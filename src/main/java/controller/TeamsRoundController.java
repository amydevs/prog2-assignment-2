package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import model.Season;
import model.Team;

public class TeamsRoundController extends Controller<Season>  {
    @FXML
    private ListView<Team> teamsListView;

    @FXML
    private Button pushButton;

    public String getRoundString() {
        return "Round: " + this.model.round();
    }

    @FXML
    private void initialize() {
        this.teamsListView.setItems(this.model.getCurrentTeams());
        this.teamsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.pushButton
            .disableProperty()
            .bind(
                Bindings.size(
                    this.teamsListView
                        .getSelectionModel()
                        .getSelectedItems()
                ).isNotEqualTo(2)
            );
    }
}



