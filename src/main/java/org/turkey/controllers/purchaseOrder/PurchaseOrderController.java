package org.turkey.controllers.purchaseOrder;

import com.jfoenix.controls.JFXButton;
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
import org.turkey.models.*;
import org.turkey.services.MockUpData;
import org.turkey.services.NavBarService;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

public class PurchaseOrderController {
    @FXML private JFXButton waitDeliveryBtn, waitPayBtn, doneBtn;
    @FXML private TableView<Order> table;
    @FXML private TableColumn<Order, String> code,supplier;
    @FXML private TableColumn<Order, Float> price;
    private ObservableList list;
    private Order order;
    private OrderLine orderLine;
    private ArrayList<Order> orders;

    @FXML public void initialize() {
        table.setRowFactory( tv -> {
            TableRow<Order> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty()) ) {
                    Order rowData = row.getItem();
                    try {
                        this.updatePurchaseOrderToWaitPay();
                        // this.updateOrderToComplete();
                        // this.showOrderComplete();
                    } catch (IOException e) {
                        System.err.println(e);
                    }
                    System.out.println(rowData);
                }
            });
            return row;
        });

        orders = new ArrayList<>();
        MockUpData.mockUpPO(orders);
        showWaitDelivery();
    }

    @FXML private void createPurchaseOrder() throws IOException {
        Stage createPurchaseOrderPage = new Stage();
        createPurchaseOrderPage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/purchaseOrder/createPurchaseOrder.fxml"));
        Scene scene = new Scene(loader.load());
        createPurchaseOrderPage.setScene(scene);
        createPurchaseOrderPage.show();
    }

    // เปลี่ยนสถานะ Order เป็น 'WaitPay'
    @FXML private void updatePurchaseOrderToWaitPay() throws IOException {
        Stage updatePurchaseOrderToWaitPayPage = new Stage();
        updatePurchaseOrderToWaitPayPage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/purchaseOrder/toWaitPay/updatePurchaseOrderToWaitPay.fxml"));
        Scene scene = new Scene(loader.load());
        updatePurchaseOrderToWaitPayPage.setScene(scene);
        updatePurchaseOrderToWaitPayPage.setTitle("เปลี่ยนสถานะใบสั่งซื้อ");
        updatePurchaseOrderToWaitPayPage.setResizable(false);
        updatePurchaseOrderToWaitPayPage.show();
    }

    // เปลี่ยนสถานะ Order เป็น 'Complete'
    @FXML private void updateOrderToComplete() throws IOException {
        Stage updatePurchaseOrderToCompletePage = new Stage();
        updatePurchaseOrderToCompletePage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/purchaseOrder/toComplete/updatePurchaseOrderToComplete.fxml"));
        Scene scene = new Scene(loader.load());
        updatePurchaseOrderToCompletePage.setScene(scene);
        updatePurchaseOrderToCompletePage.setTitle("เปลี่ยนสถานะใบสั่งขาย");
        updatePurchaseOrderToCompletePage.setResizable(false);
        updatePurchaseOrderToCompletePage.show();
    }

    // แสดง Order (สำหรับ Order ที่มีสถานะ 'Complete')
    @FXML private void showOrderComplete() throws IOException {
        Stage showPurchaseOrderCompletePage = new Stage();
        showPurchaseOrderCompletePage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/purchaseOrder/showPurchaseOrderComplete.fxml"));
        Scene scene = new Scene(loader.load());
        showPurchaseOrderCompletePage.setScene(scene);
        showPurchaseOrderCompletePage.setTitle("ใบสั่งขาย");
        showPurchaseOrderCompletePage.setResizable(false);
        showPurchaseOrderCompletePage.show();
    }

    @FXML private void showWaitDelivery() {
        clearBtnStyle();
        this.waitDeliveryBtn.setStyle("-fx-background-color: #525564; -fx-background-radius: 50; -fx-text-fill: #fef6eb");
        ArrayList<Order> arrayList = new ArrayList<>();
        for(Order order: orders){
            if(((PurchaseOrder)order).getStatus().equals(PurchaseStatus.Wait)){
                arrayList.add(order);
            }
        }
        setPOTable(arrayList);
    }

    @FXML private void showWaitPay() {
        clearBtnStyle();
        this.waitPayBtn.setStyle("-fx-background-color: #525564; -fx-background-radius: 50; -fx-text-fill: #fef6eb");
        ArrayList<Order> arrayList = new ArrayList<>();
        for(Order order: orders){
            if(((PurchaseOrder)order).getStatus().equals(PurchaseStatus.WaitPay)){
                arrayList.add(order);
            }
        }
        setPOTable(arrayList);
    }

    @FXML private void showDone() {
        clearBtnStyle();
        this.doneBtn.setStyle("-fx-background-color: #525564; -fx-background-radius: 50; -fx-text-fill: #fef6eb");
        ArrayList<Order> arrayList = new ArrayList<>();
        for(Order order: orders){
            if(((PurchaseOrder)order).getStatus().equals(PurchaseStatus.Complete)){
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
    public void setPOTable(ArrayList<Order> arrayList){
        table.getItems().clear();
        list = FXCollections.observableArrayList(arrayList);
        table.setItems(list);
        code.setCellValueFactory(new PropertyValueFactory<>("code"));
        price.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        supplier.setCellValueFactory(new PropertyValueFactory<>("partner"));
    }

    // Page Switcher
    @FXML private void toHome() throws IOException { NavBarService.switchToHome(); }
    @FXML private void toCustomer() throws IOException { NavBarService.switchToCustomer(); }
    @FXML private void toSaleOrder() throws IOException { NavBarService.switchToSaleOrder(); }
    @FXML private void toStock() throws IOException { NavBarService.switchToStock(); }
    @FXML private void toReport() throws IOException { NavBarService.switchToReport(); }
}
