package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Game;
import model.Season;
import model.Team;

public class TeamsRoundController extends Controller<Season>  {
    @FXML
    private ListView<Team> teamsListView;

    @FXML
    private TableView<Game> gamesTableView;

    @FXML
    private Button pushButton;

    public String getRoundString() {
        return "Round: " + (this.model.round() + 1);
    }

    @FXML
    private void initialize() {
        this.teamsListView.setItems(this.model.getCurrentTeams());
        this.teamsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        this.gamesTableView.setItems(this.model.getCurrentSchedule());

        TableColumn<Game, Integer> termColumn = new TableColumn<Game, Integer>();
        termColumn.setText("Game");
        termColumn.setCellValueFactory((data) -> data.getValue().term.asObject());
        this.gamesTableView.getColumns().add(termColumn);

        TableColumn<Game, String> team1Column = new TableColumn<Game, String>();
        team1Column.setText("Team-1");
        team1Column.setCellValueFactory((data) -> data.getValue().team1());
        this.gamesTableView.getColumns().add(team1Column);

        TableColumn<Game, String> team2Column = new TableColumn<Game, String>();
        team2Column.setText("Team-2");
        team2Column.setCellValueFactory((data) -> data.getValue().team2());
        this.gamesTableView.getColumns().add(team2Column);

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

    @FXML
    private void addTeamsToRound() {
        this.model.addTeams(
            this.teamsListView
                .getSelectionModel()
                .getSelectedItems()
        );
    }

    @FXML private void arrangeSeason() {
        this.stage.close();
    }
}



