package org.turkey.controllers.saleOrder.toComplete;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class UpdateSaleOrderToCompleteAlertController {
    @FXML private void close(ActionEvent event) {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        stage.close();
    }
}
