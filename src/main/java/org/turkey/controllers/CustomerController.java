package org.turkey.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.turkey.services.NavBarService;

import java.io.IOException;

public class CustomerController {
    @FXML private JFXButton addCustomerBtn;

    @FXML private void addCustomer() throws IOException {
        Stage createCustomerPage = new Stage();
        createCustomerPage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/createCustomer.fxml"));
        Scene scene = new Scene(loader.load());
        createCustomerPage.setScene(scene);
        createCustomerPage.show();
    }

    // Page Switcher
    @FXML private void toHome() throws IOException { NavBarService.switchToHome(); }
    @FXML private void toCustomer() throws IOException { NavBarService.switchToCustomer(); }
    @FXML private void toSaleOrder() throws IOException { NavBarService.switchToSaleOrder(); }
    @FXML private void toStock() throws IOException { NavBarService.switchToStock(); }
    @FXML private void toReport() throws IOException { NavBarService.switchToReport(); }
}
