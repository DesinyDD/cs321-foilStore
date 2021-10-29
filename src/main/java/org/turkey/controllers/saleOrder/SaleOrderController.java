package org.turkey.controllers.saleOrder;

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
import org.turkey.controllers.saleOrder.toComplete.UpdateSaleOrderToCompleteController;
import org.turkey.models.*;
import org.turkey.services.HTTPRequest.DBConnector;
import org.turkey.services.NavBarService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaleOrderController {
    @FXML private JFXButton waitCreateBillBtn, waitPayBtn, doneBtn;
    @FXML private TableView<SaleOrder> table;
    @FXML private TableColumn<SaleOrder,String> code,customer;
    @FXML private TableColumn<SaleOrder, String> price;
    private List<SaleOrder> orders = new DBConnector().getSaleOrder();
//    private List<SaleOrder> saleOrder = new HttpManage().getSaleOrder();
    private SaleOrder order;
    private SaleOrderLine orderLine;
    private ObservableList list;
    private Boolean check = false;

    @FXML public void initialize() {
        table.setRowFactory( tv -> {
            TableRow<SaleOrder> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty()) ) {
                    SaleOrder rowData = row.getItem();
                    try {
                        // this.updateOrderToWaitPay();
                        // this.updateOrderToComplete();
                        if (rowData.getStatus().equals(Status.WaitPay)){
                            updateOrderToComplete(rowData);
                        }else if(rowData.getStatus().equals(Status.Complete)){
                            this.showOrderComplete(rowData);
                        }
                    } catch (IOException e) {
                        // do nothing . . .
                    }
                }
            });
            return row;
        });
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                showWaitPay();
            }
        });
//        MockUpData.mockUpSO(orders);
    }

    @FXML private void createSaleOrder() throws IOException {
        check = true;
        Stage createSaleOrderPage = new Stage();
        createSaleOrderPage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/saleOrder/createSaleOrder.fxml"));
        Scene scene = new Scene(loader.load());
        createSaleOrderPage.setScene(scene);
        CreateSaleOrderController csc = loader.getController();
        csc.setTable(table);
        csc.setCodeCol(code);
        csc.setPrice(price);
        csc.setCustomerCol(customer);
        ArrayList<SaleOrder> arrayList = new ArrayList<>();
        for(SaleOrder order: orders){
            if(order.getStatus().equals(Status.WaitPay)){
                arrayList.add(order);
            }
        }
        csc.setWaitPay(arrayList);
        createSaleOrderPage.show();
    }

    // เปลี่ยนสถานะ Order เป็น 'WaitPay'
    @FXML private void updateOrderToWaitPay() throws IOException {
        Stage updateSaleOrderToWaitPayPage = new Stage();
        updateSaleOrderToWaitPayPage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/saleOrder/toWaitPay/updateSaleOrderToWaitPay.fxml"));
        Scene scene = new Scene(loader.load());
        updateSaleOrderToWaitPayPage.setScene(scene);
        updateSaleOrderToWaitPayPage.setTitle("เปลี่ยนสถานะใบสั่งขาย");
        updateSaleOrderToWaitPayPage.setResizable(false);
        updateSaleOrderToWaitPayPage.show();
    }

    // เปลี่ยนสถานะ Order เป็น 'Complete'
    @FXML private void updateOrderToComplete(SaleOrder saleOrder) throws IOException {
        check = true;
        Stage updateSaleOrderToCompletePage = new Stage();
        updateSaleOrderToCompletePage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/saleOrder/toComplete/updateSaleOrderToComplete.fxml"));
        Scene scene = new Scene(loader.load());
        updateSaleOrderToCompletePage.setScene(scene);
        updateSaleOrderToCompletePage.setTitle("เปลี่ยนสถานะใบสั่งขาย");
        updateSaleOrderToCompletePage.setResizable(false);
        UpdateSaleOrderToCompleteController uso = loader.getController();
        uso.setSaleOrder(saleOrder);
        uso.setTable(table);
        uso.setCode(code);
        uso.setCustomer(customer);
        uso.setPrice(price);
        updateSaleOrderToCompletePage.show();
    }

    // แสดง Order (สำหรับ Order ที่มีสถานะ 'Complete')
    @FXML private void showOrderComplete(SaleOrder saleOrder) throws IOException {
        Stage showSaleOrderCompletePage = new Stage();
        showSaleOrderCompletePage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/saleOrder/showSaleOrderComplete.fxml"));
        Scene scene = new Scene(loader.load());
        showSaleOrderCompletePage.setScene(scene);
        showSaleOrderCompletePage.setTitle("ใบสั่งขาย");
        showSaleOrderCompletePage.setResizable(false);
        ShowSaleOrderCompleteController so = loader.getController();
        so.setSaleOrder(saleOrder);
        showSaleOrderCompletePage.show();
    }

//    @FXML private void showWaitCreateBill() {
////        System.out.println(orders);
//        clearBtnStyle();
//        this.waitCreateBillBtn.setStyle("-fx-background-color: #525564; -fx-background-radius: 50; -fx-text-fill: #fef6eb");
//        ArrayList<SaleOrder> arrayList = new ArrayList<>();
//        for(SaleOrder order: orders){
//            if(order.getStatus().equals(Status.WaitCreateBill)){
//                arrayList.add(order);
//            }
//        }
//        setSOTable(arrayList);
//    }

    @FXML private void showWaitPay() {
        if (check){
            orders = new DBConnector().getSaleOrder();
            check = false;
        }
        clearBtnStyle();
        this.waitPayBtn.setStyle("-fx-background-color: #525564; -fx-background-radius: 50; -fx-text-fill: #fef6eb");
        ArrayList<SaleOrder> arrayList = new ArrayList<>();
        for(SaleOrder order: orders){
            if(order.getStatus().equals(Status.WaitPay)){
                arrayList.add(order);
            }
        }
        setSOTable(arrayList);
    }

    @FXML private void showDone() {
        if (check){
            orders = new DBConnector().getSaleOrder();
            check = false;
        }
        clearBtnStyle();
        this.doneBtn.setStyle("-fx-background-color: #525564; -fx-background-radius: 50; -fx-text-fill: #fef6eb");
        ArrayList<SaleOrder> arrayList = new ArrayList<>();
        for(SaleOrder order: orders){
            if(order.getStatus().equals(Status.Complete)){
                arrayList.add(order);
            }
        }
        setSOTable(arrayList);
    }

    @FXML private void clearBtnStyle() {
        this.waitCreateBillBtn.setStyle("-fx-background-color: transparent; -fx-border-color: #000000; -fx-border-radius: 50");
        this.waitPayBtn.setStyle("-fx-background-color: transparent; -fx-border-color: #000000; -fx-border-radius: 50");
        this.doneBtn.setStyle("-fx-background-color: transparent; -fx-border-color: #000000; -fx-border-radius: 50");
    }

    public void setSOTable(ArrayList<SaleOrder> arrayList){
        table.getItems().clear();
        list = FXCollections.observableArrayList(arrayList);
        table.setItems(list);
        code.setCellValueFactory(new PropertyValueFactory<>("code"));
        price.setCellValueFactory(new PropertyValueFactory<>("totalPriceWithComma"));
        customer.setCellValueFactory(new PropertyValueFactory<>("customerName"));
    }
    // Page Switcher
    @FXML private void toHome() throws IOException { NavBarService.switchToHome(); }
    @FXML private void toCustomer() throws IOException { NavBarService.switchToCustomer(); }
    @FXML private void toSaleOrder() throws IOException { NavBarService.switchToSaleOrder(); }
    @FXML private void toStock() throws IOException { NavBarService.switchToStock(); }
    @FXML private void toReport() throws IOException { NavBarService.switchToReport(); }
}
