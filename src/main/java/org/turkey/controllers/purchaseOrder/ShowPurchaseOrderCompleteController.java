package org.turkey.controllers.purchaseOrder;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.turkey.models.Po;

public class ShowPurchaseOrderCompleteController {
    @FXML private Label label;
    private Po po;

    public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                label.setText(po.showLabel());
            }
        });
    }

    @FXML private void close(ActionEvent event) {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        stage.close();
    }

    public void setPo(Po po) {
        this.po = po;
    }
}
