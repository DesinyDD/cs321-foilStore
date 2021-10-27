package org.turkey.controllers.purchaseOrder.toComplete;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class UpdatePurchaseOrderToCompleteAlertController {
    @FXML private Label label;
    private String code;

    public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                label.setText(code);
            }
        });
    }

    @FXML private void close(ActionEvent event) {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        stage.close();
    }

    public void setCode(String code) {
        this.code = code;
    }
}
