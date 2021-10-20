package org.turkey.controllers.customer;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class EditCustomerController {
    @FXML private JFXButton editBtn;
    @FXML private TextField nameF, phoneF;
    @FXML private TextArea addressF;
    private String name, address, phone;

    public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                nameF.setText(name);
                addressF.setText(address);
                phoneF.setText(phone);
            }
        });
    }

    @FXML private void editCustomer() throws IOException {
        if(!nameF.getText().trim().equals("") && !phoneF.getText().trim().equals("") && !addressF.getText().trim().equals("")){
            // Alert Box
            Stage editCustomerAlertPage = new Stage();
            editCustomerAlertPage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/customer/editCustomerAlert.fxml"));
            Scene scene = new Scene(loader.load());
            editCustomerAlertPage.setScene(scene);
            editCustomerAlertPage.setTitle("สำเร็จ");
            editCustomerAlertPage.setResizable(false);
            EditCustomerAlertController ea = loader.getController();
            ea.setCustomerName(nameF.getText());
            editCustomerAlertPage.show();

            Stage stage = (Stage) editBtn.getScene().getWindow();
            stage.close();
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
