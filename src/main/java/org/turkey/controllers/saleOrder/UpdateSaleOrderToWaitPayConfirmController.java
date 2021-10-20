package org.turkey.controllers.saleOrder;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdateSaleOrderToWaitPayConfirmController {
    @FXML private JFXButton cancelBtn;

    @FXML private void confirm() throws IOException {

        // Alert Box
        Stage updateSaleOrderToWaitPayAlertPage = new Stage();
        updateSaleOrderToWaitPayAlertPage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/saleOrder/updateSaleOrderToWaitPayAlert.fxml"));
        Scene scene = new Scene(loader.load());
        updateSaleOrderToWaitPayAlertPage.setScene(scene);
        updateSaleOrderToWaitPayAlertPage.setTitle("สำเร็จ");
        updateSaleOrderToWaitPayAlertPage.setResizable(false);
        updateSaleOrderToWaitPayAlertPage.show();

        this.close();
    }

    @FXML private void close() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
}
