package org.turkey.controllers.customer;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class EditCustomerController {
    @FXML private JFXButton editBtn;

    @FXML private void editCustomer() throws IOException {

        // Alert Box
        Stage editCustomerAlertPage = new Stage();
        editCustomerAlertPage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/customer/editCustomerAlert.fxml"));
        Scene scene = new Scene(loader.load());
        editCustomerAlertPage.setScene(scene);
        editCustomerAlertPage.setTitle("สำเร็จ");
        editCustomerAlertPage.setResizable(false);
        editCustomerAlertPage.show();

        Stage stage = (Stage) editBtn.getScene().getWindow();
        stage.close();
    }
}
