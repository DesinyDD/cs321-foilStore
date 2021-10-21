package org.turkey.controllers.saleOrder;

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
import org.turkey.models.*;
import org.turkey.services.MockUpData;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

public class CreateSaleOrderController {
    @FXML private TextField quantityField_1, quantityField_2, quantityField_3, code;
    @FXML private ComboBox code1, code2, code3, customerBox, payment;
    @FXML private TableView<SaleOrder> table;
    @FXML private TableColumn<SaleOrder, String> codeCol,customerCol;
    @FXML private TableColumn<SaleOrder, Float> price;
    private ArrayList<Item> stock = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<SaleOrder> orders = new ArrayList<>(), waitCrateBillSO;
    private Item item;
    private Customer customer;
    private SaleOrder order;
    private SaleOrderLine orderLine;
    private ArrayList<String> itemCode = new ArrayList<>(), customerName = new ArrayList<>(), orderCode = new ArrayList<>();
    private ObservableList list;

    @FXML public void initialize() {
//        MockUpData.mockUpSO(orders);
//        MockUpData.mockUpStock(stock);
//        MockUpData.mockUpCustomer(customers);
        for(SaleOrder order: orders){
//            orderCode.add(order.getCode());
        }
        for(Item item: stock){
//            itemCode.add(item.getColorCode());
        }
        for(Customer customer : customers ){
//            customerName.add(customer.getName());
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

    @FXML public void CreateSO(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage =(Stage) b.getScene().getWindow();
        try{
            order = new SaleOrder()
        }catch (Exception e){

        }
//        if(!code.getText().trim().equals("")){
//            if((code1.getValue() != null && !quantityField_1.getText().trim().equals("")) ||
//                    (code2.getValue() != null && !quantityField_2.getText().trim().equals(""))||
//                    (code3.getValue() != null && !quantityField_3.getText().trim().equals(""))){
//                System.out.println("have order line");
//                boolean err = false;
//                // 3 เงื่อนไขข้างล่างให้เช็คว่าเเมื่อช่องใดเป็นว่างแต่ถ้า 2 ช่องไม่ต้องแสดง
//                //ช่องแรกกรอกข้อมูลไม่ครบ(ไม่นับไม่กรอก)
//                if((code1.getValue() == null && !quantityField_1.getText().trim().equals("")) ||
//                        (code1.getValue() != null && quantityField_1.getText().trim().equals(""))){
//                    System.out.println(1);
//                    err = true;
//                }
//                //ช่องสองกรอกข้อมูลไม่ครบ(ไม่นับไม่กรอก)
//                if((code2.getValue() == null && !quantityField_2.getText().trim().equals("")) ||
//                        (code2.getValue() != null && quantityField_2.getText().trim().equals(""))){
//                    System.out.println(2);
//                    err = true;
//                }
//                //ช่องสามกรอกข้อมูลไม่ครบ(ไม่นับไม่กรอก)
//                if((code3.getValue() == null && !quantityField_3.getText().trim().equals("")) ||
//                        (code3.getValue() != null && quantityField_3.getText().trim().equals(""))){
//                    err = true;
//                    System.out.println(3);
//                }
//                //ไม่เลือกลูกค้า
//                if(customerBox.getValue() == null){
//                    System.out.println("no customer");
//                }else if(err){
//                    // เลือกลูกค้าแต่ช่องสินค้ายังกรอกไม่่ดี
//                    System.out.println("not complete");
//                }else{
//                    // เสดหมด
////                    System.out.println("success");
////                    order = new SaleOrder(code.getText(), customer, 0, Status.WaitCreateBill);
////                    //ยังไม่ได้ทำฟังก์ชันลดสินค้า
////                    if(code3.getValue() != null && !quantityField_3.getText().trim().equals("")){
////                        orderLine = new SaleOrderLine(new BigInteger("55"), code.getText(),
////                                code3.getValue().toString(), new BigInteger(quantityField_3.getText()));
////                        order.addOrderLine(orderLine);
////                        order.addToTotal(Float.parseFloat(quantityField_3.getText())*1000);
////                    }
////                    if(code2.getValue() != null && !quantityField_2.getText().trim().equals("")){
////                        orderLine = new SaleOrderLine(new BigInteger("55"), code.getText(),
////                                code2.getValue().toString(), new BigInteger(quantityField_2.getText()));
////                        order.addOrderLine(orderLine);
////                        order.addToTotal(Float.parseFloat(quantityField_2.getText())*1000);
////                    }
////                    if(code1.getValue() != null && !quantityField_1.getText().trim().equals("")){
////                        orderLine = new SaleOrderLine(new BigInteger("55"), code.getText(),
////                                code1.getValue().toString(), new BigInteger(quantityField_1.getText()));
////                        order.addOrderLine(orderLine);
////                        order.addToTotal(Float.parseFloat(quantityField_1.getText())*1000);
//                    }
//                    waitCrateBillSO.add(order);
//                    setSOTable(waitCrateBillSO);
//
//                    // Alert Box
//                    Stage createItemPage = new Stage();
//                    createItemPage.initModality(Modality.APPLICATION_MODAL);
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/saleOrder/createSaleOrderAlert.fxml"));
//                    Scene scene = new Scene(loader.load());
//                    createItemPage.setScene(scene);
//                    createItemPage.setTitle("สำเร็จ");
//                    createItemPage.setResizable(false);
//                    createItemPage.show();
//
//                    stage.close();
//                }
//            }else{
//                System.out.println("no order");
//                //กรุณาใส่สินค้าอย่างน้อย 1 ชุด
//            }
////        }else{
//            System.out.println("no code");
//            // alert ต้องใส่เลขกำกับ
        }
//    }

    public void setSOTable(ArrayList<SaleOrder> arrayList){
        table.getItems().clear();
        list = FXCollections.observableArrayList(arrayList);
        table.setItems(list);
        codeCol.setCellValueFactory(new PropertyValueFactory<>("code"));
        price.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        customerCol.setCellValueFactory(new PropertyValueFactory<>("partner"));
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

    public void setPrice(TableColumn<SaleOrder, Float> price) {
        this.price = price;
    }

    public void setWaitCrateBillSO(ArrayList<SaleOrder> waitCrateBillSO) {
        this.waitCrateBillSO = waitCrateBillSO;
    }
}
