package org.turkey.controllers.saleOrder.toComplete;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdateSaleOrderToCompleteController {
    @FXML private JFXButton cancelBtn;

    @FXML private void updateStatus() throws IOException {

        // Confirm Box
        Stage updateSaleOrderToCompleteConfirmPage = new Stage();
        updateSaleOrderToCompleteConfirmPage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/saleOrder/toComplete/updateSaleOrderToCompleteConfirm.fxml"));
        Scene scene = new Scene(loader.load());
        updateSaleOrderToCompleteConfirmPage.setScene(scene);
        updateSaleOrderToCompleteConfirmPage.setTitle("ยืนยันการเปลี่ยนสถานะ");
        updateSaleOrderToCompleteConfirmPage.setResizable(false);
        updateSaleOrderToCompleteConfirmPage.show();

        this.close();
    }

    @FXML private void close() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
}
