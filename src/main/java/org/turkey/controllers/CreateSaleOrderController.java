package org.turkey.controllers;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.turkey.models.*;

import java.math.BigInteger;
import java.util.ArrayList;

public class CreateSaleOrderController {
    @FXML private TextField quantityField_1, quantityField_2, quantityField_3, code;
    @FXML private ComboBox code1, code2, code3, customerBox;
    @FXML private TableView<Order> table;
    @FXML private TableColumn<Order, String> codeCol,customerCol;
    @FXML private TableColumn<Order, Float> price;
    private ArrayList<Item> stock = new ArrayList<>();
    private ArrayList<Information> customers = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>(), waitCrateBillSO;
    private Item item;
    private Information customer;
    private Order order;
    private OrderLine orderLine;
    private ArrayList<String> itemCode = new ArrayList<>(), customerName = new ArrayList<>(), orderCode = new ArrayList<>();
    private ObservableList list;

    @FXML public void initialize() {
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
        item = new Item("OSP 900", new BigInteger(230+""), 1000, new BigInteger(50+""));
        stock.add(item);
        item = new Item("OSP 730", new BigInteger(180+""), 1000, new BigInteger(35+""));
        stock.add(item);
        customer = new Customer(new BigInteger("1"),"Ford", "08xxxxxxxx","Bodin");
        customers.add(customer);
        customer = new Customer(new BigInteger("2"),"MIX", "08xxxxxxxx","KU");
        customers.add(customer);
        customer = new Customer(new BigInteger("3"),"Q", "08xxxxxxxx","Barn");
        customers.add(customer);
        for(Order order: orders){
            orderCode.add(order.getCode());
        }
        for(Item item: stock){
            itemCode.add(item.getColorCode());
        }
        for(Information customer : customers ){
            customerName.add(customer.getName());
        }
        customerBox.getItems().addAll(customerName);
        code1.getItems().addAll(itemCode);
        code2.getItems().addAll(itemCode);
        code3.getItems().addAll(itemCode);
        quantityField_1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,3}?")) {
                    quantityField_1.setText(oldValue);
                }
            }
        });
        quantityField_2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,3}?")) {
                    quantityField_2.setText(oldValue);
                }
            }
        });
        quantityField_3.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,3}?")) {
                    quantityField_3.setText(oldValue);
                }
            }
        });
    }

    public void CreateSO(ActionEvent event){
        Button b = (Button) event.getSource();
        Stage stage =(Stage) b.getScene().getWindow();
        if(!code.getText().trim().equals("")){
            if((code1.getValue() != null && !quantityField_1.getText().trim().equals("")) ||
                    (code2.getValue() != null && !quantityField_2.getText().trim().equals(""))||
                    (code3.getValue() != null && !quantityField_3.getText().trim().equals(""))){
                System.out.println("have order line");
                boolean err = false;
                // 3 เงื่อนไขข้างล่างให้เช็คว่าเเมื่อช่องใดเป็นว่างแต่ถ้า 2 ช่องไม่ต้องแสดง
                //ช่องแรกกรอกข้อมูลไม่ครบ(ไม่นับไม่กรอก)
                if((code1.getValue() == null && !quantityField_1.getText().trim().equals("")) ||
                        (code1.getValue() != null && quantityField_1.getText().trim().equals(""))){
                    System.out.println(1);
                    err = true;
                }
                //ช่องสองกรอกข้อมูลไม่ครบ(ไม่นับไม่กรอก)
                if((code2.getValue() == null && !quantityField_2.getText().trim().equals("")) ||
                        (code2.getValue() != null && quantityField_2.getText().trim().equals(""))){
                    System.out.println(2);
                    err = true;
                }
                //ช่องสามกรอกข้อมูลไม่ครบ(ไม่นับไม่กรอก)
                if((code3.getValue() == null && !quantityField_3.getText().trim().equals("")) ||
                        (code3.getValue() != null && quantityField_3.getText().trim().equals(""))){
                    err = true;
                    System.out.println(3);
                }
                //ไม่เลือกลูกค้า
                if(customerBox.getValue() == null){
                    System.out.println("no customer");
                }else if(err){
                    // เลือกลูกค้าแต่ช่องสินค้ายังกรอกไม่่ดี
                    System.out.println("not complete");
                }else{
                    // เสดหมด
                    System.out.println("success");
                    order = new SaleOrder(code.getText(), customer, 0, SaleStatus.WaitCreateBill);
                    //ยังไม่ได้ทำฟังก์ชันลดสินค้า
                    if(code3.getValue() != null && !quantityField_3.getText().trim().equals("")){
                        orderLine = new SaleOrderLine(new BigInteger("55"), code.getText(),
                                code3.getValue().toString(), new BigInteger(quantityField_3.getText()));
                        order.addOrderLine(orderLine);
                        order.addToTotal(Float.parseFloat(quantityField_3.getText())*1000);
                    }
                    if(code2.getValue() != null && !quantityField_2.getText().trim().equals("")){
                        orderLine = new SaleOrderLine(new BigInteger("55"), code.getText(),
                                code2.getValue().toString(), new BigInteger(quantityField_2.getText()));
                        order.addOrderLine(orderLine);
                        order.addToTotal(Float.parseFloat(quantityField_2.getText())*1000);
                    }
                    if(code1.getValue() != null && !quantityField_1.getText().trim().equals("")){
                        orderLine = new SaleOrderLine(new BigInteger("55"), code.getText(),
                                code1.getValue().toString(), new BigInteger(quantityField_1.getText()));
                        order.addOrderLine(orderLine);
                        order.addToTotal(Float.parseFloat(quantityField_1.getText())*1000);
                    }
                    waitCrateBillSO.add(order);
                    setSOTable(waitCrateBillSO);
                    stage.close();
                }
            }else{
                System.out.println("no order");
                //กรุณาใส่สินค้าอย่างน้อย 1 ชุด
            }
        }else{
            System.out.println("no code");
            // alert ต้องใส่เลขกำกับ
        }
    }

    public void setSOTable(ArrayList<Order> arrayList){
        table.getItems().clear();
        list = FXCollections.observableArrayList(arrayList);
        table.setItems(list);
        codeCol.setCellValueFactory(new PropertyValueFactory<>("code"));
        price.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        customerCol.setCellValueFactory(new PropertyValueFactory<>("partner"));
    }

    public void setTable(TableView<Order> table) {
        this.table = table;
    }

    public void setCodeCol(TableColumn<Order, String> codeCol) {
        this.codeCol = codeCol;
    }

    public void setCustomerCol(TableColumn<Order, String> customerCol) {
        this.customerCol = customerCol;
    }

    public void setPrice(TableColumn<Order, Float> price) {
        this.price = price;
    }

    public void setWaitCrateBillSO(ArrayList<Order> waitCrateBillSO) {
        this.waitCrateBillSO = waitCrateBillSO;
    }
}
