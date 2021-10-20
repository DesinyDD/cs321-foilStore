package org.turkey.controllers.purchaseOrder.toComplete;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdatePurchaseOrderToCompleteController {
    @FXML private JFXButton cancelBtn;

    @FXML private void updateStatus() throws IOException {

        // Confirm Box
        Stage updatePurchaseOrderToCompleteConfirmPage = new Stage();
        updatePurchaseOrderToCompleteConfirmPage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/purchaseOrder/toComplete/updatePurchaseOrderToCompleteConfirm.fxml"));
        Scene scene = new Scene(loader.load());
        updatePurchaseOrderToCompleteConfirmPage.setScene(scene);
        updatePurchaseOrderToCompleteConfirmPage.setTitle("ยืนยันการเปลี่ยนสถานะ");
        updatePurchaseOrderToCompleteConfirmPage.setResizable(false);
        updatePurchaseOrderToCompleteConfirmPage.show();

        this.close();
    }

    @FXML private void close() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
}
