package org.turkey.controllers.purchaseOrder.toComplete;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class UpdatePurchaseOrderToCompleteAlertController {
    @FXML private void close(ActionEvent event) {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        stage.close();
    }
}
