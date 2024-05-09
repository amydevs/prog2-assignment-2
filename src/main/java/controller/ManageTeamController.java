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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
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
        BooleanBinding disableBinding = this.playersTableView
            .getSelectionModel()
            .selectedItemProperty()
            .isNull();
        this.updateButton.disableProperty().bind(disableBinding);
        this.deleteButton.disableProperty().bind(disableBinding);
        playersTableView.setItems(this.model.getCurrentPlayers());
    }

    @FXML
    private void addPlayer() {
        Player player = new Player("", 0.0, 0, 0);
        player.setTeam(this.model);
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/nba.png"));
            
            ViewLoader.showStage(
                player,
                "/view/PlayerUpdateView.fxml",
                "Adding New Player",
                stage
            );
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void updatePlayer() {
        Player player = this.playersTableView.getSelectionModel().getSelectedItem();
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/nba.png"));
            
            ViewLoader.showStage(
                player,
                "/view/PlayerUpdateView.fxml",
                "Updating Player: " + player.getName(),
                stage
            );
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
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
