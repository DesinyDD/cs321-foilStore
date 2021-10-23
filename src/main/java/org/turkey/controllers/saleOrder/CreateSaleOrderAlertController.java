package org.turkey.controllers.saleOrder;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CreateSaleOrderAlertController {
    @FXML private Label code;
    private String saleCode;

    public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                code.setText(saleCode);
            }
        });
    }

    @FXML private void close(ActionEvent event) {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        stage.close();
    }

    public void setSaleCode(String saleCode) {
        this.saleCode = saleCode;
    }
}
