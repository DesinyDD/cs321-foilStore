package org.turkey.controllers.report;

import javafx.application.Platform;
import javafx.fxml.FXML;
import org.turkey.models.SaleOrder;
import org.turkey.services.HTTPRequest.DBConnector;
import org.turkey.services.NavBarService;

import java.io.IOException;
import java.util.List;

public class ReportController {


    @FXML
    public void initialize() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                List<SaleOrder> saleOrderList = new DBConnector().getReport();
                System.out.println("INCONTROLLER");
                System.out.println(saleOrderList);
            }
        });
    }
    // Page Switcher
    @FXML private void toHome() throws IOException { NavBarService.switchToHome(); }
    @FXML private void toCustomer() throws IOException { NavBarService.switchToCustomer(); }
    @FXML private void toSaleOrder() throws IOException { NavBarService.switchToSaleOrder(); }
    @FXML private void toStock() throws IOException { NavBarService.switchToStock(); }
    @FXML private void toReport() throws IOException { NavBarService.switchToReport(); }
}
