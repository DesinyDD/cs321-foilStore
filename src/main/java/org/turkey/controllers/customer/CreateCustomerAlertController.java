package org.turkey.controllers.customer;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CreateCustomerAlertController {
    @FXML private Label name;
    private String customer;

    public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                name.setText("คุณ "+customer);
            }
        });
    }

    @FXML private void close(ActionEvent event) {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        stage.close();
    }

    public void setCustomerName(String customer) {
        this.customer = customer;
    }
}
