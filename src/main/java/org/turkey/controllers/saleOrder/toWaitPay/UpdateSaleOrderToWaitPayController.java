package org.turkey.controllers.saleOrder.toWaitPay;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdateSaleOrderToWaitPayController {
    @FXML private JFXButton cancelBtn;

    @FXML private void updateStatus() throws IOException {

        // Confirm Box
        Stage updateSaleOrderToWaitPayPage = new Stage();
        updateSaleOrderToWaitPayPage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/saleOrder/toWaitPay/updateSaleOrderToWaitPayConfirm.fxml"));
        Scene scene = new Scene(loader.load());
        updateSaleOrderToWaitPayPage.setScene(scene);
        updateSaleOrderToWaitPayPage.setTitle("ยืนยันการเปลี่ยนสถานะ");
        updateSaleOrderToWaitPayPage.setResizable(false);
        updateSaleOrderToWaitPayPage.show();

        this.close();
    }

    @FXML private void close() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
}
