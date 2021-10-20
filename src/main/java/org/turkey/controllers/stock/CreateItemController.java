package org.turkey.controllers.stock;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateItemController {
    @FXML private JFXButton cancelBtn;
    @FXML private TextField code, price, min;

    @FXML private void createItem() throws IOException {
        Stage createItemAlertPage = new Stage();
        createItemAlertPage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/stock/createItemAlert.fxml"));
        Scene scene = new Scene(loader.load());
        createItemAlertPage.setScene(scene);
        createItemAlertPage.setTitle("สำเร็จ");
        createItemAlertPage.setResizable(false);
        createItemAlertPage.show();
        CreateItemAlertController ca = loader.getController();
        ca.setColorCode(code.getText());
        this.close();
    }

    @FXML private void close() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
}
