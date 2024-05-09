package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.InputException;
import model.Team;
import model.Teams;

public class AddTeamController extends Controller<Teams> {
    @FXML
    private TextField nameTextField;

    @FXML
    private Button addButton;

    @FXML
    private void initialize() {
        this.addButton.disableProperty().bind(nameTextField.textProperty().isEmpty());
    }

    @FXML
    private void addTeam() {
        // will never be empty due to logic above!
        String teamName = nameTextField.getText();
        if (this.model.hasTeam(teamName)) {
            try {
                Stage stage = new Stage();
                stage.setX(ViewLoader.X + 601);
                stage.setY(ViewLoader.Y);
                stage.getIcons().add(new Image("/view/nba.png"));
                ViewLoader.showStage(
                    new InputException(teamName + " team already exists"),
                    "/view/error.fxml",
                    "Error!",
                    stage
                );
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }
            return;
        }
        
        this.model.addTeam(new Team(teamName));
    }
}
