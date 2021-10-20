package org.turkey.controllers.purchaseOrder.toComplete;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdatePurchaseOrderToCompleteConfirmController {
    @FXML private JFXButton cancelBtn;

    @FXML private void confirm() throws IOException {

        // Alert Box
        Stage updatePurchaseOrderToCompleteAlertPage = new Stage();
        updatePurchaseOrderToCompleteAlertPage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/purchaseOrder/toComplete/updatePurchaseOrderToCompleteAlert.fxml"));
        Scene scene = new Scene(loader.load());
        updatePurchaseOrderToCompleteAlertPage.setScene(scene);
        updatePurchaseOrderToCompleteAlertPage.setTitle("สำเร็จ");
        updatePurchaseOrderToCompleteAlertPage.setResizable(false);
        updatePurchaseOrderToCompleteAlertPage.show();

        this.close();
    }

    @FXML private void close() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
}
