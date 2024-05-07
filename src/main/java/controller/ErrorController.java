package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import model.InputException;

public class ErrorController extends Controller<InputException>  {
    @FXML
    private Text errorMessageText;

    @FXML
    private void initialize() {
        this.errorMessageText.setText(this.model.getMessage());
    }

    @FXML
    private void close() {
        this.stage.close();
    }
}
