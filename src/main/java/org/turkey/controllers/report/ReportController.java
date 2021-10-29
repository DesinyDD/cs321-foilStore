package org.turkey.controllers.report;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.turkey.models.SaleOrder;
import org.turkey.services.HTTPRequest.DBConnector;
import org.turkey.services.NavBarService;
import org.turkey.services.NumberWithComma;

import java.io.IOException;
import java.util.List;

public class ReportController {

    @FXML
    private Label totalLb;
    @FXML
    private VBox orderVBox, totalVBox;

    @FXML
    public void initialize() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                List<SaleOrder> saleOrderList = new DBConnector().getReport();
                System.out.println("INCONTROLLER");
                createLabel(saleOrderList);
                totalLb.setText("ยอดขายรวม " + getTotalPriceLastWeek(saleOrderList) + " บาท");
            }
        });
    }

    //    create report record
    private void createLabel(List<SaleOrder> saleOrderList) {
        for (SaleOrder saleOrder : saleOrderList) {
            Label codeLb = new Label(saleOrder.getCode());
            Label totalLb = new Label(saleOrder.getTotalPriceWithComma());
            orderVBox.getChildren().add(codeLb);
            totalVBox.getChildren().add(totalLb);
        }
    }

    private String getTotalPriceLastWeek(List<SaleOrder> saleOrderList) {
        float totalPrice = 0;
        for (SaleOrder saleOrder : saleOrderList) {
            totalPrice += saleOrder.getTotalPrice();
        }
        return NumberWithComma.addComma(totalPrice);
    }

    // Page Switcher
    @FXML
    private void toHome() throws IOException {
        NavBarService.switchToHome();
    }

    @FXML
    private void toCustomer() throws IOException {
        NavBarService.switchToCustomer();
    }

    @FXML
    private void toSaleOrder() throws IOException {
        NavBarService.switchToSaleOrder();
    }

    @FXML
    private void toStock() throws IOException {
        NavBarService.switchToStock();
    }

    @FXML
    private void toReport() throws IOException {
        NavBarService.switchToReport();
    }
}
