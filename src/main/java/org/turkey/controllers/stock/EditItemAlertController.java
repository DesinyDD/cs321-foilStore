package org.turkey.controllers.stock;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class EditItemAlertController {
    @FXML private Label code;
    private String colorCode;

    public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                code.setText(colorCode);
            }
        });
    }

    @FXML private void close(ActionEvent event) {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        stage.close();
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }
}
