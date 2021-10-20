package org.turkey.controllers.stock;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigInteger;

public class EditItemController {
    @FXML private JFXButton cancelBtn;
    @FXML private TextField codeF, priceF, minF;
    private String colorCode;
    private float price;
    private BigInteger minAmount;

    public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                codeF.setText(colorCode);
                priceF.setText(price+"");
                minF.setText(minAmount+"");
            }
        });
    }

    @FXML private void editItem() throws IOException {
        Stage editItemConfirmPage = new Stage();
        editItemConfirmPage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/stock/editItemAlert.fxml"));
        Scene scene = new Scene(loader.load());
        editItemConfirmPage.setScene(scene);
        editItemConfirmPage.setTitle("สำเร็จ");
        editItemConfirmPage.setResizable(false);
        editItemConfirmPage.show();
        EditItemAlertController ea = loader.getController();
        ea.setColorCode(codeF.getText());
        this.close();
    }

    @FXML private void close() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public void setMinAmount(BigInteger minAmount) {
        this.minAmount = minAmount;
    }
}
