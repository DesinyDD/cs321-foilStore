package org.turkey.controllers.purchaseOrder.toWaitPay;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdatePurchaseOrderToWaitPayController {
    @FXML private JFXButton cancelBtn;

    @FXML private void updateStatus() throws IOException {

        // Confirm Box
        Stage updatePurchaseOrderToWaitPayPage = new Stage();
        updatePurchaseOrderToWaitPayPage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/purchaseOrder/toWaitPay/updatePurchaseOrderToWaitPayConfirm.fxml"));
        Scene scene = new Scene(loader.load());
        updatePurchaseOrderToWaitPayPage.setScene(scene);
        updatePurchaseOrderToWaitPayPage.setTitle("ยืนยันการเปลี่ยนสถานะ");
        updatePurchaseOrderToWaitPayPage.setResizable(false);
        updatePurchaseOrderToWaitPayPage.show();

        this.close();
    }

    @FXML private void close() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
}
