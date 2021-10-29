package org.turkey.controllers.saleOrder;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.turkey.controllers.FailAlertController;
import org.turkey.models.*;
import org.turkey.services.HTTPRequest.DBConnector;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class CreateSaleOrderController {
    @FXML private TextField quantityField_1, quantityField_2, quantityField_3, code;
    @FXML private ComboBox code1, code2, code3, customerBox, payment;
    @FXML private TableView<SaleOrder> table;
    @FXML private TableColumn<SaleOrder, String> codeCol,customerCol;
    @FXML private TableColumn<SaleOrder, String> price;
    @FXML private Label amount1, amount2, amount3, codeAlert, item1Alert, item2Alert, item3Alert, paymentAlert, customerAlert;
    private List<Item> stock = new DBConnector().getItem();
    private List<Customer> customers = new DBConnector().getCustomer();
    private List<SaleOrder> orders = new DBConnector().getSaleOrder(), waitPay;
    private Item item;
    private Customer customer;
    private SaleOrder order;
    private SaleOrderLine orderLine;
    private BigInteger customerID;
    private ArrayList<String> itemCode = new ArrayList<>(), customerName = new ArrayList<>(), orderCode = new ArrayList<>();
    private ObservableList list;

    @FXML public void initialize() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                clearAlert();
                payment.getItems().addAll("จ่ายเงินสด","จ่ายด้วยเช็ค");
            }
        });
//        MockUpData.mockUpSO(orders);
//        MockUpData.mockUpStock(stock);
//        MockUpData.mockUpCustomer(customers);
        for(SaleOrder order: orders){
            orderCode.add(order.getCode());
        }
        for(Item item: stock){
            itemCode.add(item.getCode());
        }
        for(Customer customer : customers ){
            customerName.add(customer.getName());
        }
        customerBox.getItems().addAll(customerName);
        code1.getItems().addAll(itemCode);
        code2.getItems().addAll(itemCode);
        code3.getItems().addAll(itemCode);
        quantityField_1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,4}?")) {
                    quantityField_1.setText(oldValue);
                }
            }
        });
        quantityField_2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,4}?")) {
                    quantityField_2.setText(oldValue);
                }
            }
        });
        quantityField_3.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,4}?")) {
                    quantityField_3.setText(oldValue);
                }
            }
        });
    }

    @FXML public void clear1(ActionEvent event){
        code1.getSelectionModel().clearSelection();
        amount1.setText("");
    }

    @FXML public void clear2(ActionEvent event){
        code2.getSelectionModel().clearSelection();
        amount2.setText("");
    }

    @FXML public void clear3(ActionEvent event){
        code3.getSelectionModel().clearSelection();
        amount3.setText("");
    }

    @FXML public void select1(ActionEvent event){
        boolean clear = false;
        BigInteger amount = new BigInteger(0+"");
        for (Item item1 : stock){
            if (code1.getValue()==null){
                clear = true;
                break;
            }
            if(code1.getValue().toString().equals(item1.getCode())){
                amount = item1.getAmount();
            }
        }
        if(!clear) {
            amount1.setText("/" + amount);
        }
    }
    @FXML public void select2(ActionEvent event){
        boolean clear = false;
        BigInteger amount = new BigInteger(0+"");
        for (Item item1 : stock){
            if (code2.getValue()==null){
                clear = true;
                break;
            }
            if(code2.getValue().toString().equals(item1.getCode())){
                amount = item1.getAmount();
            }
        }
        if(!clear) {
            amount2.setText("/" + amount);
        }
    }
    @FXML public void select3(ActionEvent event){
        boolean clear = false;
        BigInteger amount = new BigInteger(0+"");
        for (Item item1 : stock){
            if (code3.getValue()==null){
                clear = true;
                break;
            }
            if(code3.getValue().toString().equals(item1.getCode())){
                amount = item1.getAmount();
            }
        }
        if(!clear) {
            amount3.setText("/" + amount);
        }
    }

    @FXML public void CreateSO(ActionEvent event) throws IOException {
        clearAlert();
        Button b = (Button) event.getSource();
        Stage stage =(Stage) b.getScene().getWindow();
        if(!code.getText().trim().equals("")){
            if((code1.getValue() != null && !quantityField_1.getText().trim().equals("")) ||
                    (code2.getValue() != null && !quantityField_2.getText().trim().equals(""))||
                    (code3.getValue() != null && !quantityField_3.getText().trim().equals(""))){
                System.out.println("have order line");
                boolean err = false, payMethod = false, custBox = false, row1 = false, row2 = false, row3 = false;
                // 3 เงื่อนไขข้างล่างให้เช็คว่าเเมื่อช่องใดเป็นว่างแต่ถ้า 2 ช่องไม่ต้องแสดง
                //ช่องแรกกรอกข้อมูลไม่ครบ(ไม่นับไม่กรอก)
                if((code1.getValue() == null && !quantityField_1.getText().trim().equals("")) ||
                        (code1.getValue() != null && quantityField_1.getText().trim().equals(""))){
                    System.out.println(1);
                    row1 = true;
                    err = true;
                }
                //ช่องสองกรอกข้อมูลไม่ครบ(ไม่นับไม่กรอก)
                if((code2.getValue() == null && !quantityField_2.getText().trim().equals("")) ||
                        (code2.getValue() != null && quantityField_2.getText().trim().equals(""))){
                    System.out.println(2);
                    row2 = true;
                    err = true;
                }
                //ช่องสามกรอกข้อมูลไม่ครบ(ไม่นับไม่กรอก)
                if((code3.getValue() == null && !quantityField_3.getText().trim().equals("")) ||
                        (code3.getValue() != null && quantityField_3.getText().trim().equals(""))){
                    err = true;
                    System.out.println(3);
                    row3 = true;
                }
                if(payment.getValue()==null){
                    payMethod = true;
                    System.out.println("no method selected");
                }
                //ไม่เลือกลูกค้า
                if(customerBox.getValue() == null){
                    custBox = true;
                }
                if(err==true || payMethod==true || custBox==true){
                    // เลือกลูกค้าแต่ช่องสินค้ายังกรอกไม่่ดี
                    if(err){
                        System.out.println("order not complete");
                        if (row1){
                            item1Alert.setText("กรุณากรอกสินค้ารายการที่ 1 ให้สมบูรณ์");
                        }
                        if (row2) {
                            item2Alert.setText("กรุณากรอกสินค้ารายการที่ 2 ให้สมบูรณ์");
                        }
                        if (row3) {
                            item3Alert.setText("กรุณากรอกสินค้ารายการที่ 3 ให้สมบูรณ์");
                        }
                    }
                    if(payMethod){
                        paymentAlert.setText("กรุณาเลือกรูปแบบการชำระเงิน");
                        System.out.println("No payment");
                    }
                    if(custBox){
                        customerAlert.setText("กรุณาเลือกลูกค้าที่สั่งซื้อ");
                        System.out.println("No customer");
                    }
                    failToCreateSO();
                }else {
                    // เสดหมด
                    System.out.println("success");
                    for(Customer customer1: customers){
                        if(customer1.getName().equals(customerBox.getValue().toString())){
                            customer = customer1;
                            customerID = customer1.getCustomerId();
                        }
                    }
                    order = new SaleOrder(code.getText(), customerID, customer, payment.getValue().toString());
                    System.out.println(order);
//                    //ยังไม่ได้ทำฟังก์ชันลดสินค้า
                    if(code1.getValue() != null && !quantityField_1.getText().trim().equals("")){
                        float price = 0;
                        for(Item item1: stock){
                            if(item1.getCode().equals(code1.getValue().toString())){
                                item = item1;
                                price = item1.getPrice();
                                break;
                            }
                        }
                        orderLine = new SaleOrderLine(code.getText(), code1.getValue().toString(), new BigInteger(quantityField_1.getText()), item);
                        System.out.println(orderLine.toString());
                        order.addSaleOrderLine(orderLine);
                        order.addToTotal(Float.parseFloat(quantityField_1.getText())*price);
                    }
                    if(code2.getValue() != null && !quantityField_2.getText().trim().equals("")){
                        float price = 0;
                        for(Item item1: stock){
                            if(item1.getCode().equals(code2.getValue().toString())){
                                item = item1;
                                price = item1.getPrice();
                                break;
                            }
                        }
                        orderLine = new SaleOrderLine(code.getText(), code2.getValue().toString(), new BigInteger(quantityField_2.getText()), item);
                        System.out.println(orderLine.toString());
                        order.addSaleOrderLine(orderLine);
                        order.addToTotal(Float.parseFloat(quantityField_2.getText())*price);
                    }
                    if(code3.getValue() != null && !quantityField_3.getText().trim().equals("")){
                        float price = 0;
                        for(Item item1: stock){
                            if(item1.getCode().equals(code3.getValue().toString())){
                                item = item1;
                                price = item1.getPrice();
                                break;
                            }
                        }
                        orderLine = new SaleOrderLine(code.getText(), code3.getValue().toString(), new BigInteger(quantityField_3.getText()), item);
                        System.out.println(orderLine);
                        order.addSaleOrderLine(orderLine);
                        order.addToTotal(Float.parseFloat(quantityField_3.getText())*price);
                    }
                    waitPay.add(order);
                    setSOTable(waitPay);
                    System.out.println(order);

                    new DBConnector().createSaleOrder(order);

                    // Alert Box
                    Stage createItemPage = new Stage();
                    createItemPage.initModality(Modality.APPLICATION_MODAL);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/saleOrder/createSaleOrderAlert.fxml"));
                    Scene scene = new Scene(loader.load());
                    createItemPage.setScene(scene);
                    createItemPage.setTitle("สำเร็จ");
                    createItemPage.setResizable(false);
                    CreateSaleOrderAlertController ca = loader.getController();
                    ca.setSaleCode(code.getText());
                    createItemPage.show();

                    stage.close();
                }
            }else{
                failToCreateSO();
                System.out.println("no order");
                item1Alert.setText("กรุณาสั่งขายสินค้าให้สมบูรณ์อย่างน้อย 1 รายการ");
                item2Alert.setText("กรุณาสั่งขายสินค้าให้สมบูรณ์อย่างน้อย 1 รายการ");
                item3Alert.setText("กรุณาสั่งขายสินค้าให้สมบูรณ์อย่างน้อย 1 รายการ");
            }
        }else{
            failToCreateSO();
            System.out.println("no code");
            codeAlert.setText("กรุณากรอกเลขกำกับใบสั่งขาย");
        }
    }

    public void setSOTable(List<SaleOrder> arrayList){
        table.getItems().clear();
        list = FXCollections.observableArrayList(arrayList);
        table.setItems(list);
        codeCol.setCellValueFactory(new PropertyValueFactory<>("code"));
        price.setCellValueFactory(new PropertyValueFactory<>("totalPriceWithComma"));
        customerCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
    }

    @FXML public void failToCreateSO() throws IOException {
        Stage stage1 = new Stage();
        stage1.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/failAlert.fxml"));
        stage1.setScene(new Scene(loader.load()));
        stage1.setTitle("แจ้งเตือน");
        stage1.setResizable(false);
        FailAlertController fa = loader.getController();
        fa.setFrom("สร้างใบสั่งขายไม่สำเร็จ");
        stage1.show();

    }
    @FXML public void clearAlert(){
        codeAlert.setText("");
        item1Alert.setText("");
        item2Alert.setText("");
        item3Alert.setText("");
        paymentAlert.setText("");
        customerAlert.setText("");
    }

    public void setTable(TableView<SaleOrder> table) {
        this.table = table;
    }

    public void setCodeCol(TableColumn<SaleOrder, String> codeCol) {
        this.codeCol = codeCol;
    }

    public void setCustomerCol(TableColumn<SaleOrder, String> customerCol) {
        this.customerCol = customerCol;
    }

    public void setPrice(TableColumn<SaleOrder, String> price) {
        this.price = price;
    }

    public void setWaitPay(List<SaleOrder> waitPay) {
        this.waitPay = waitPay;
    }
}
