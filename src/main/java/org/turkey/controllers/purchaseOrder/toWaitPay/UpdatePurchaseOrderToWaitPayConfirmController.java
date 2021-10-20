package org.turkey.controllers.purchaseOrder.toWaitPay;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdatePurchaseOrderToWaitPayConfirmController {
    @FXML private JFXButton cancelBtn;

    @FXML private void confirm() throws IOException {

        // Alert Box
        Stage updatePurchaseOrderToWaitPayAlertPage = new Stage();
        updatePurchaseOrderToWaitPayAlertPage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/purchaseOrder/toWaitPay/updatePurchaseOrderToWaitPayAlert.fxml"));
        Scene scene = new Scene(loader.load());
        updatePurchaseOrderToWaitPayAlertPage.setScene(scene);
        updatePurchaseOrderToWaitPayAlertPage.setTitle("สำเร็จ");
        updatePurchaseOrderToWaitPayAlertPage.setResizable(false);
        updatePurchaseOrderToWaitPayAlertPage.show();

        this.close();
    }

    @FXML private void close() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
}
