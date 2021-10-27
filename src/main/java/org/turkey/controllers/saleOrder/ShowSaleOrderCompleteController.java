package org.turkey.controllers.saleOrder;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.turkey.models.SaleOrder;

public class ShowSaleOrderCompleteController {
    private SaleOrder saleOrder;
    @FXML private Label label;
    public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                label.setText(saleOrder.showLabel());
            }
        });
    }
    @FXML private void close(ActionEvent event) {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        stage.close();
    }

    public void setSaleOrder(SaleOrder saleOrder) {
        this.saleOrder = saleOrder;
    }
}
