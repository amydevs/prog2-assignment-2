package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Player;
import model.Players;

public class ViewPlayersController extends Controller<Players>  {
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
        playersTableView.setItems(this.model.getFilteredPlayers());
        filterTable();

        this.levelTextField.textProperty().addListener(
            (_obs, _old, _new) -> filterTable()
        );
        this.nameTextField.textProperty().addListener(
            (_obs, _old, _new) -> filterTable()
        );
        this.ageFromTextField.textProperty().addListener(
            (_obs, _old, _new) -> filterTable()
        );
        this.ageToTextField.textProperty().addListener(
            (_obs, _old, _new) -> filterTable()
        );
    }

    private void filterTable() {
        int ageFrom;
        try {
            ageFrom = Integer.parseInt(this.ageFromTextField.getText());
        } catch (NumberFormatException e) {
            ageFrom = Integer.MIN_VALUE;
        }
        int ageTo;
        try {
            ageTo = Integer.parseInt(this.ageToTextField.getText());
        } catch (NumberFormatException e) {
            ageTo = Integer.MAX_VALUE;
        }
        
        this.model.filterList(
            this.nameTextField.getText(),
            this.levelTextField.getText(),
            ageFrom,
            ageTo
        );
    }

    @FXML
    private void close() {
        this.stage.close();
    }
}

