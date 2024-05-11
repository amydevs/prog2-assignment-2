package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import model.Season;
import model.Record;

public class RecordController extends Controller<Season> {
    @FXML
    private TableView<Record> recordsTableView;


    @FXML
    private void initialize() {
        this.recordsTableView.setItems(this.model.record());
    }
    
    @FXML
    private void close() {
        this.stage.close();
    }
}







