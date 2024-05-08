package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Player;
import model.Team;

public class ManageTeamController extends Controller<Team>  {
    @FXML
    private TextField teamNameTextField;

    @FXML
    private TableView<Player> playersTableView;
    
    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;
  
    @FXML
    private void initialize() {
        this.teamNameTextField.setText(this.model.getName());
        updateButton.setDisable(true);
        deleteButton.setDisable(true);
        playersTableView
                .getSelectionModel()
                .selectedItemProperty()
                .addListener((_observable, _oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        updateButton.setDisable(false);
                        deleteButton.setDisable(false);
                        return;
                    }
                    updateButton.setDisable(true);
                    deleteButton.setDisable(true);
                });
        playersTableView.setItems(this.model.getCurrentPlayers());
    }

    @FXML
    private void addPlayer() {
    }

    @FXML
    private void updatePlayer() {
        Player player = this.playersTableView.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void deletePlayer() {
        Player player = this.playersTableView.getSelectionModel().getSelectedItem();
        this.model.getPlayers().removePlayer(player);
    }

    @FXML
    private void saveAndClose() {
        this.model.setName(this.teamNameTextField.getText());
        this.stage.close();
    }
}
