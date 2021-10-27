package org.turkey.controllers.purchaseOrder;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CreatePurchaseOrderAlertController {
    @FXML private Label code;
    private String poCode;

    public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                code.setText(poCode);
            }
        });
    }

    @FXML private void close(ActionEvent event) {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        stage.close();
    }

    public void setPoCode(String poCode) {
        this.poCode = poCode;
    }
}
