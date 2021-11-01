package org.turkey.controllers.purchaseOrder;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.turkey.controllers.purchaseOrder.toComplete.UpdatePurchaseOrderToCompleteController;
import org.turkey.controllers.purchaseOrder.toWaitPay.UpdatePurchaseOrderToWaitPayController;
import org.turkey.models.*;
import org.turkey.services.HTTPRequest.DBConnector;
import org.turkey.services.NavBarService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderController {
    @FXML private JFXButton waitDeliveryBtn, waitPayBtn, doneBtn;
    @FXML private TableView<Po> table;
    @FXML private TableColumn<Po, String> code,supplier;
    @FXML private TableColumn<Po, String> price;
    private ObservableList list;
    private Po order;
    private PoLine orderLine;
    private List<Po> orders = new DBConnector().getPO();
    private Boolean check = false;

    @FXML public void initialize() {
        table.setRowFactory( tv -> {
            TableRow<Po> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty()) ) {
                    Po rowData = row.getItem();
                    try {
                        if (rowData.getStatus().equals(PurchaseStatus.Wait)){
                            this.updatePurchaseOrderToWaitPay(rowData);
                        }else if(rowData.getStatus().equals(PurchaseStatus.WaitPay)){
                            this.updateOrderToComplete(rowData);
                        }else if(rowData.getStatus().equals(PurchaseStatus.Complete)){
                            this.showOrderComplete(rowData);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                showWaitDelivery();
            }
        });
    }

    @FXML private void createPurchaseOrder() throws IOException {
        check = true;
        Stage createPurchaseOrderPage = new Stage();
        createPurchaseOrderPage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/purchaseOrder/createPurchaseOrder.fxml"));
        Scene scene = new Scene(loader.load());
        createPurchaseOrderPage.setScene(scene);
        CreatePurchaseOrderController poc = loader.getController();
        poc.setTable(table);
        poc.setTotalPriceCol(price);
        poc.setCode(code);
        poc.setSupplierCol(supplier);
        createPurchaseOrderPage.show();
    }

    // เปลี่ยนสถานะ Order เป็น 'WaitPay'
    @FXML private void updatePurchaseOrderToWaitPay(Po po) throws IOException {
        check = true;
        Stage updatePurchaseOrderToWaitPayPage = new Stage();
        updatePurchaseOrderToWaitPayPage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/purchaseOrder/toWaitPay/updatePurchaseOrderToWaitPay.fxml"));
        Scene scene = new Scene(loader.load());
        updatePurchaseOrderToWaitPayPage.setScene(scene);
        updatePurchaseOrderToWaitPayPage.setTitle("เปลี่ยนสถานะใบสั่งซื้อ");
        updatePurchaseOrderToWaitPayPage.setResizable(false);
        UpdatePurchaseOrderToWaitPayController ua = loader.getController();
        ua.setPo(po);
        ua.setTable(table);
        ua.setSupplier(supplier);
        ua.setCode(code);
        ua.setPrice(price);
        updatePurchaseOrderToWaitPayPage.show();
    }

    // เปลี่ยนสถานะ Order เป็น 'Complete'
    @FXML private void updateOrderToComplete(Po po) throws IOException {
        check = true;
        Stage updatePurchaseOrderToCompletePage = new Stage();
        updatePurchaseOrderToCompletePage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/purchaseOrder/toComplete/updatePurchaseOrderToComplete.fxml"));
        Scene scene = new Scene(loader.load());
        updatePurchaseOrderToCompletePage.setScene(scene);
        updatePurchaseOrderToCompletePage.setTitle("เปลี่ยนสถานะใบสั่งซื้อ");
        updatePurchaseOrderToCompletePage.setResizable(false);
        UpdatePurchaseOrderToCompleteController ua = loader.getController();
        ua.setPo(po);
        ua.setTable(table);
        ua.setSupplier(supplier);
        ua.setCode(code);
        ua.setPrice(price);
        updatePurchaseOrderToCompletePage.show();
    }

    // แสดง Order (สำหรับ Order ที่มีสถานะ 'Complete')
    @FXML private void showOrderComplete(Po po) throws IOException {
        Stage showPurchaseOrderCompletePage = new Stage();
        showPurchaseOrderCompletePage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/purchaseOrder/showPurchaseOrderComplete.fxml"));
        Scene scene = new Scene(loader.load());
        showPurchaseOrderCompletePage.setScene(scene);
        showPurchaseOrderCompletePage.setTitle("ใบสั่งขาย");
        showPurchaseOrderCompletePage.setResizable(false);
        ShowPurchaseOrderCompleteController soc = loader.getController();
        soc.setPo(po);
        showPurchaseOrderCompletePage.show();
    }

    @FXML private void showWaitDelivery() {
        if (check){
            orders = new DBConnector().getPO();
            check = false;
        }
        clearBtnStyle();
        this.waitDeliveryBtn.setStyle("-fx-background-color: #525564; -fx-background-radius: 50; -fx-text-fill: #fef6eb");
        ArrayList<Po> arrayList = new ArrayList<>();
        for(Po order: orders){
            if(order.getStatus().equals(PurchaseStatus.Wait)){
                arrayList.add(order);
            }
        }
        setPOTable(arrayList);
    }

    @FXML private void showWaitPay() {
        if (check){
            orders = new DBConnector().getPO();
            check = false;
        }
        clearBtnStyle();
        this.waitPayBtn.setStyle("-fx-background-color: #525564; -fx-background-radius: 50; -fx-text-fill: #fef6eb");
        ArrayList<Po> arrayList = new ArrayList<>();
        for(Po order: orders){
            if(order.getStatus().equals(PurchaseStatus.WaitPay)){
                arrayList.add(order);
            }
        }
        setPOTable(arrayList);
    }

    @FXML private void showDone() {
        if (check){
            orders = new DBConnector().getPO();
            check = false;
        }
        clearBtnStyle();
        this.doneBtn.setStyle("-fx-background-color: #525564; -fx-background-radius: 50; -fx-text-fill: #fef6eb");
        ArrayList<Po> arrayList = new ArrayList<>();
        for(Po order: orders){
            if(order.getStatus().equals(PurchaseStatus.Complete)){
                arrayList.add(order);
            }
        }
        setPOTable(arrayList);
    }

    @FXML private void clearBtnStyle() {
        this.waitDeliveryBtn.setStyle("-fx-background-color: transparent; -fx-border-color: #000000; -fx-border-radius: 50");
        this.waitPayBtn.setStyle("-fx-background-color: transparent; -fx-border-color: #000000; -fx-border-radius: 50");
        this.doneBtn.setStyle("-fx-background-color: transparent; -fx-border-color: #000000; -fx-border-radius: 50");
    }
    public void setPOTable(ArrayList<Po> arrayList){
        table.getItems().clear();
        list = FXCollections.observableArrayList(arrayList);
        table.setItems(list);
        code.setCellValueFactory(new PropertyValueFactory<>("code"));
        price.setCellValueFactory(new PropertyValueFactory<>("totalPriceWithComma"));
        supplier.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
    }

    // Page Switcher
    @FXML private void toHome() throws IOException { NavBarService.switchToHome(); }
    @FXML private void toCustomer() throws IOException { NavBarService.switchToCustomer(); }
    @FXML private void toSaleOrder() throws IOException { NavBarService.switchToSaleOrder(); }
    @FXML private void toStock() throws IOException { NavBarService.switchToStock(); }
    @FXML private void toReport() throws IOException { NavBarService.switchToReport(); }
}
