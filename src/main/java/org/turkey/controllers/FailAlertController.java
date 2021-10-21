package org.turkey.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class FailAlertController {
    @FXML private Label alertText;
    @FXML private JFXButton agreeBtn;

    @FXML public void setAlert(String word) {
        alertText.setText(word);
    }

    @FXML private void close() {
        Stage stage = (Stage) agreeBtn.getScene().getWindow();
        stage.close();
    }
}
