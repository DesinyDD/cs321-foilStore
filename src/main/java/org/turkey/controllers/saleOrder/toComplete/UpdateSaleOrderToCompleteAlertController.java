package org.turkey.controllers.saleOrder.toComplete;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class UpdateSaleOrderToCompleteAlertController {
    @FXML private Label code;
    private String soCode;

    public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                code.setText(soCode);
            }
        });
    }

    @FXML private void close(ActionEvent event) {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        stage.close();
    }

    public void setSoCode(String soCode) {
        this.soCode = soCode;
    }
}
