package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Player;
import model.Teams;

public class ViewPlayersController extends Controller<Teams>  {
    @FXML
    private TableView<Player> playersTableView;

    @FXML
    private TextField levelTextField;

    @FXML
    private TextField nameTextField;


    @FXML
    private TextField ageFromTextField;

    @FXML
    private TextField ageToTextField;

    @FXML
    private void initialize() {
        playersTableView.setItems(this.model.allPlayersList());
    }

    @FXML
    private void filterTable() {
        playersTableView.setItems(
            this.model.allPlayersList().filtered((p) -> {
                if (
                    !levelTextField.getText().isEmpty()
                    && !p.getLevel().contains(levelTextField.getText())
                ) {
                    return false;
                }

                return true;
            })
        );
    }

    @FXML
    private void close() {
        this.stage.close();
    }
}

