package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Game;
import model.Season;

public class CurrentRoundTeamsController extends Controller<Season>  {
    @FXML
    private TableView<Game> gamesTableView;

    public String getRoundString() {
        return "Round: " + (this.model.round() + 1);
    }

    @FXML
    private void initialize() {
        this.gamesTableView.setItems(this.model.getCurrentSchedule());

        TableColumn<Game, String> team1Column = new TableColumn<Game, String>();
        team1Column.setText("Team-1");
        team1Column.setCellValueFactory((data) -> data.getValue().team1());
        this.gamesTableView.getColumns().add(team1Column);

        TableColumn<Game, String> vsColumn = new TableColumn<Game, String>();
        vsColumn.setCellValueFactory((data) -> new SimpleStringProperty("VS"));
        this.gamesTableView.getColumns().add(vsColumn);

        TableColumn<Game, String> team2Column = new TableColumn<Game, String>();
        team2Column.setText("Team-2");
        team2Column.setCellValueFactory((data) -> data.getValue().team2());
        this.gamesTableView.getColumns().add(team2Column);
    }
  
    @FXML
    private void close() {
        this.stage.close();
    }
}







