package org.turkey.controllers.saleOrder.toComplete;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdateSaleOrderToCompleteConfirmController {
    @FXML private JFXButton cancelBtn;

    @FXML private void confirm() throws IOException {

        // Alert Box
        Stage updateSaleOrderToCompleteAlertPage = new Stage();
        updateSaleOrderToCompleteAlertPage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/saleOrder/toComplete/updateSaleOrderToCompleteAlert.fxml"));
        Scene scene = new Scene(loader.load());
        updateSaleOrderToCompleteAlertPage.setScene(scene);
        updateSaleOrderToCompleteAlertPage.setTitle("สำเร็จ");
        updateSaleOrderToCompleteAlertPage.setResizable(false);
        updateSaleOrderToCompleteAlertPage.show();

        this.close();
    }

    @FXML private void close() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
}
