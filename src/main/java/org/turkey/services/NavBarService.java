package org.turkey.services;

import javafx.fxml.FXML;
import org.turkey.Main;

import java.io.IOException;

public class NavBarService {
    @FXML public static void switchToHome() throws IOException { Main.setRoot("home/home"); }
    @FXML public static void switchToCustomer() throws IOException { Main.setRoot("customer/customer"); }
    @FXML public static void switchToSaleOrder() throws IOException { Main.setRoot("saleOrder/saleOrder"); }
    @FXML public static void switchToStock() throws IOException { Main.setRoot("stock/stock"); }
    @FXML public static void switchToReport() throws IOException { Main.setRoot("report/report"); }
    @FXML public static void switchToPurchaseOrder() throws IOException { Main.setRoot("purchaseOrder/purchaseOrder"); }
}
