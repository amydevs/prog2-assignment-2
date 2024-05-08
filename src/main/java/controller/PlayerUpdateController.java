package controller;

import java.io.IOException;
import java.util.List;
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
import model.Player;
import model.Team;

public class PlayerUpdateController extends Controller<Player>  {

    private boolean isExistingPlayer;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField creditTextField;

    @FXML
    private TextField ageTextField;

    @FXML
    private TextField noTextField;

    @FXML
    private Button updateButton;

    @FXML
    private Button addButton;

    @FXML 
    private void initialize() {
        Player player = this.model;
        Team team = this.model.getTeam();

        this.isExistingPlayer = team
            .getPlayers()
            .getPlayersList()
            .contains(player);

        if (isExistingPlayer) {
            this.addButton.setDisable(true);
        }
        else {
            this.updateButton.setDisable(true);
        }
        
        this.nameTextField.setText(player.getName());
        this.creditTextField.setText(player.getCredit().toString());
        this.ageTextField.setText(player.getAge().toString());
        this.noTextField.setText(player.getNo().toString());
    }

    @FXML
    private void updateAddPlayer() {
        Validator validator = new Validator();
        validator.generateErrors(
            this.nameTextField.getText(), 
            this.creditTextField.getText(), 
            this.ageTextField.getText(), 
            this.noTextField.getText()
        );
        List<String> errors = validator.errors();
        if (!errors.isEmpty()) {
            try {
                Stage stage = new Stage();
                stage.setX(ViewLoader.X + 601);
                stage.setY(ViewLoader.Y);
                stage.getIcons().add(new Image("/view/nba.png"));
                ViewLoader.showStage(
                    new InputException(String.join("\n", validator.errors())),
                    "/view/error.fxml",
                    "Input Errors",
                    stage
                );
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }
            return;
        }
        this.model.setName(this.nameTextField.getText());
        this.model.setCredit(Double.parseDouble(this.creditTextField.getText()));
        this.model.setAge(Integer.parseInt(this.ageTextField.getText()));
        this.model.setNo(Integer.parseInt(this.noTextField.getText()));
        if (this.isExistingPlayer) {
            int index = this.model.getTeam().getCurrentPlayers().indexOf(this.model);
            this.model.getTeam().getCurrentPlayers().set(index, this.model);
        }
        else {
            this.model.getTeam().getPlayers().addPlayer(this.model);
        }
        this.stage.close();
    }

    @FXML
    private void close() {
        this.stage.close();
    }

}
