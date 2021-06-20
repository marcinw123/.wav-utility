package sample;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class HelpWindowLayoutController {

    @FXML
    private GridPane gridPaneFromHelpWindowId;

    public void closeThisWindowButtonHandler() {
        Stage stage = (Stage) gridPaneFromHelpWindowId.getScene().getWindow();
        stage.close();
    }

}
