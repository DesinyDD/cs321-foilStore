package org.turkey.controllers.purchaseOrder.toWaitPay;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class UpdatePurchaseOrderToWaitPayAlertController {
    @FXML private void close(ActionEvent event) {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        stage.close();
    }
}
