package org.turkey.controllers.stock;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class EditItemController {
    @FXML private JFXButton cancelBtn;

    @FXML private void editItem() throws IOException {
        Stage editItemConfirmPage = new Stage();
        editItemConfirmPage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/stock/editItemAlert.fxml"));
        Scene scene = new Scene(loader.load());
        editItemConfirmPage.setScene(scene);
        editItemConfirmPage.setTitle("สำเร็จ");
        editItemConfirmPage.setResizable(false);
        editItemConfirmPage.show();
        this.close();
    }

    @FXML private void close() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
}
