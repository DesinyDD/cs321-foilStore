package org.turkey.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.turkey.models.*;
import org.turkey.services.NavBarService;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

public class SaleOrderController {
    @FXML private JFXButton waitCreateBillBtn, waitPayBtn, doneBtn;
    @FXML private TableView<Order> table;
    @FXML private TableColumn<Order,String> code,customer;
    @FXML private TableColumn<Order, Float> price;
    private ArrayList<Order> orders;
    private Order order;
    private OrderLine orderLine;
    private ObservableList list;

    @FXML public void initialize(){
        orders = new ArrayList<>();
        order = new SaleOrder("F62190158900",new Customer(new BigInteger("6"),"Test","09xxxxxxxx","?"),8000, SaleStatus.WaitCreateBill);
        orderLine = new SaleOrderLine(new BigInteger("1"),"F62190158900","OSP 730", new BigInteger("35"));
        order.addOrderLine(orderLine);
        orderLine = new SaleOrderLine(new BigInteger("2"),"F62190158900","OSP 900", new BigInteger("12"));
        order.addOrderLine(orderLine);
        orderLine = new SaleOrderLine(new BigInteger("3"),"F62190158900","OSP 830", new BigInteger("5"));
        order.addOrderLine(orderLine);
        orders.add(order);
        order = new SaleOrder("F62190158901",new Customer(new BigInteger("6"),"Test","09xxxxxxxx","?"),13500, SaleStatus.WaitPay);
        orderLine = new SaleOrderLine(new BigInteger("4"),"F62190158901","OSP 420", new BigInteger("15"));
        order.addOrderLine(orderLine);
        orderLine = new SaleOrderLine(new BigInteger("5"),"F62190158901","OSP 630", new BigInteger("5"));
        order.addOrderLine(orderLine);
        orderLine = new SaleOrderLine(new BigInteger("6"),"F62190158901","OSP 213", new BigInteger("75"));
        order.addOrderLine(orderLine);
        orders.add(order);
        order = new SaleOrder("F62190158899",new Customer(new BigInteger("6"),"Test","09xxxxxxxx","?"),8000, SaleStatus.Complete);
        orderLine = new SaleOrderLine(new BigInteger("7"),"F62190158900","OSP 730", new BigInteger("35"));
        order.addOrderLine(orderLine);
        orderLine = new SaleOrderLine(new BigInteger("8"),"F62190158900","OSP 900", new BigInteger("12"));
        order.addOrderLine(orderLine);
        orderLine = new SaleOrderLine(new BigInteger("9"),"F62190158900","OSP 830", new BigInteger("5"));
        order.addOrderLine(orderLine);
        orders.add(order);
        showWaitCreateBill();
    }

    @FXML private void createSaleOrder() throws IOException {
        Stage createSaleOrderPage = new Stage();
        createSaleOrderPage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/createSaleOrder.fxml"));
        Scene scene = new Scene(loader.load());
        createSaleOrderPage.setScene(scene);
        CreateSaleOrderController csc = loader.getController();
        csc.setTable(table);
        csc.setCodeCol(code);
        csc.setPrice(price);
        csc.setCustomerCol(customer);
        ArrayList<Order> arrayList = new ArrayList<>();
        for(Order order: orders){
            if(((SaleOrder)order).getStatus().equals(SaleStatus.WaitCreateBill)){
                arrayList.add(order);
            }
        }
        csc.setWaitCrateBillSO(arrayList);
        createSaleOrderPage.show();
    }

    @FXML private void showWaitCreateBill() {
        clearBtnStyle();
        this.waitCreateBillBtn.setStyle("-fx-background-color: #525564; -fx-background-radius: 50; -fx-text-fill: #fef6eb");
        ArrayList<Order> arrayList = new ArrayList<>();
        for(Order order: orders){
            if(((SaleOrder)order).getStatus().equals(SaleStatus.WaitCreateBill)){
                arrayList.add(order);
            }
        }
        setSOTable(arrayList);
    }

    @FXML private void showWaitPay() {
        clearBtnStyle();
        this.waitPayBtn.setStyle("-fx-background-color: #525564; -fx-background-radius: 50; -fx-text-fill: #fef6eb");
        ArrayList<Order> arrayList = new ArrayList<>();
        for(Order order: orders){
            if(((SaleOrder)order).getStatus().equals(SaleStatus.WaitPay)){
                arrayList.add(order);
            }
        }
        setSOTable(arrayList);
    }

    @FXML private void showDone() {
        clearBtnStyle();
        this.doneBtn.setStyle("-fx-background-color: #525564; -fx-background-radius: 50; -fx-text-fill: #fef6eb");
        ArrayList<Order> arrayList = new ArrayList<>();
        for(Order order: orders){
            if(((SaleOrder)order).getStatus().equals(SaleStatus.Complete)){
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

    public void setSOTable(ArrayList<Order> arrayList){
        table.getItems().clear();
        list = FXCollections.observableArrayList(arrayList);
        table.setItems(list);
        code.setCellValueFactory(new PropertyValueFactory<>("code"));
        price.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        customer.setCellValueFactory(new PropertyValueFactory<>("partner"));
    }
    // Page Switcher
    @FXML private void toHome() throws IOException { NavBarService.switchToHome(); }
    @FXML private void toCustomer() throws IOException { NavBarService.switchToCustomer(); }
    @FXML private void toSaleOrder() throws IOException { NavBarService.switchToSaleOrder(); }
    @FXML private void toStock() throws IOException { NavBarService.switchToStock(); }
    @FXML private void toReport() throws IOException { NavBarService.switchToReport(); }
}
