package org.turkey.controllers.saleOrder;

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
        Stage createItemPage = new Stage();
        createItemPage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/saleOrder/updateSaleOrderToWaitPayConfirm.fxml"));
        Scene scene = new Scene(loader.load());
        createItemPage.setScene(scene);
        createItemPage.setTitle("ยืนยันการเปลี่ยนสถานะ");
        createItemPage.setResizable(false);
        createItemPage.show();

        this.close();
    }

    @FXML private void close() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
}
