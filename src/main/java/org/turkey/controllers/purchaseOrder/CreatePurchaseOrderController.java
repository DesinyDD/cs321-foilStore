package org.turkey.controllers.purchaseOrder;

import com.jfoenix.controls.JFXButton;
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

public class CreatePurchaseOrderController {
    @FXML private Label phone, supplierAlert, codeAlert, item1Alert, item2Alert, item3Alert;
    @FXML private ComboBox code1, code2, code3, supplier;
    @FXML private TextField quantityField_1, quantityField_2, quantityField_3, codeF;
    @FXML private TextField priceField_1, priceField_2, priceField_3;
    @FXML private JFXButton cancel;
    @FXML private TableView<Po> table;
    @FXML private TableColumn<Po, String> code;
    @FXML private TableColumn<Po, String> totalPriceCol;
    @FXML private TableColumn<Po, String> supplierCol;
    private List<Item> stock = new DBConnector().getItem();
    private ObservableList list;
    private List<Supplier> supplierList = new DBConnector().getSupplier();
    private List<String> supplierName = new ArrayList<>(), color = new ArrayList<>();
    private List<Po> poList = new DBConnector().getPO();
    private BigInteger supplier_id;
    private PoLine poLine;
    private Supplier supp;
    @FXML public void initialize() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                clearAlert();
            }
        });
        for (Item item : stock){
            color.add(item.getCode());
        }
        for (Supplier s : supplierList){
            supplierName.add(s.getName());
        }
        phone.setText("");
        supplier.getItems().addAll(supplierName);
        code1.getItems().addAll(color);
        code2.getItems().addAll(color);
        code3.getItems().addAll(color);
        cancel.setOnAction(event->{
            Stage stage = (Stage) cancel.getScene().getWindow();
            stage.close();
        });
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
        priceField_1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,2})?")) {
                    priceField_1.setText(oldValue);
                }
            }
        });
        priceField_2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,2})?")) {
                    priceField_2.setText(oldValue);
                }
            }
        });
        priceField_3.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,2})?")) {
                    priceField_3.setText(oldValue);
                }
            }
        });
    }

    @FXML public void createPO(ActionEvent event) throws IOException {
        clearAlert();
        if(!codeF.getText().trim().equals("")){
            // มี code
            if ((code1.getValue() != null && !quantityField_1.getText().trim().equals("") && !priceField_1.getText().trim().equals(""))||
                    (code2.getValue() != null && !quantityField_2.getText().trim().equals("") && !priceField_2.getText().trim().equals(""))||
                    (code3.getValue() != null && !quantityField_3.getText().trim().equals("") && !priceField_3.getText().trim().equals(""))){
                // มีสั่งสินค้า
                Boolean sup = false, notComp = false, row1 = false, row2 = false, row3 = false;
                if(supplier.getValue()==null){
                    sup = true;
                }
                if((code1.getValue() != null && quantityField_1.getText().trim().equals("") && priceField_1.getText().trim().equals(""))||
                        (code1.getValue() != null && !quantityField_1.getText().trim().equals("") && priceField_1.getText().trim().equals(""))||
                        (code1.getValue() != null && quantityField_1.getText().trim().equals("") && !priceField_1.getText().trim().equals(""))||
                        (code1.getValue() == null && !quantityField_1.getText().trim().equals("") && priceField_1.getText().trim().equals(""))||
                        (code1.getValue() == null && !quantityField_1.getText().trim().equals("") && !priceField_1.getText().trim().equals(""))||
                        (code1.getValue() == null && quantityField_1.getText().trim().equals("") && !priceField_1.getText().trim().equals(""))){
                    // รายการแรกไม่สมบูรณ์
                    row1 = true;
                    notComp = true;
                }
                if((code2.getValue() != null && quantityField_2.getText().trim().equals("") && priceField_2.getText().trim().equals(""))||
                        (code2.getValue() != null && !quantityField_2.getText().trim().equals("") && priceField_2.getText().trim().equals(""))||
                        (code2.getValue() != null && quantityField_2.getText().trim().equals("") && !priceField_2.getText().trim().equals(""))||
                        (code2.getValue() == null && !quantityField_2.getText().trim().equals("") && priceField_2.getText().trim().equals(""))||
                        (code2.getValue() == null && !quantityField_2.getText().trim().equals("") && !priceField_2.getText().trim().equals(""))||
                        (code2.getValue() == null && quantityField_2.getText().trim().equals("") && !priceField_2.getText().trim().equals(""))){
                    // รายการสองไม่สมบูรณ์
                    row2 = true;
                    notComp = true;
                }
                if((code3.getValue() != null && quantityField_3.getText().trim().equals("") && priceField_3.getText().trim().equals(""))||
                        (code3.getValue() != null && !quantityField_3.getText().trim().equals("") && priceField_3.getText().trim().equals(""))||
                        (code3.getValue() != null && quantityField_3.getText().trim().equals("") && !priceField_3.getText().trim().equals(""))||
                        (code3.getValue() == null && !quantityField_3.getText().trim().equals("") && priceField_3.getText().trim().equals(""))||
                        (code3.getValue() == null && !quantityField_3.getText().trim().equals("") && !priceField_3.getText().trim().equals(""))||
                        (code3.getValue() == null && quantityField_3.getText().trim().equals("") && !priceField_3.getText().trim().equals(""))){
                    // รายการสามไม่สมบูรณ์
                    row3 = true;
                    notComp = true;
                }
                if(sup || notComp){
                    if (sup){
                        supplierAlert.setText("กรุณาเลือกบริษัทที่ผลิต");
                    }
                    if (notComp){
                        // มีบางรายการกรอกข้อมูลสินค้าไม่ครบ
                        if (row1){
                            item1Alert.setText("กรุณากรอกข้อมูลการสั่งสินค้าแถวที่ 1 ให้สมบูรณ์");
                        }
                        if (row2){
                            item2Alert.setText("กรุณากรอกข้อมูลการสั่งสินค้าแถวที่ 2 ให้สมบูรณ์");
                        }
                        if (row3){
                            item3Alert.setText("กรุณากรอกข้อมูลการสั่งสินค้าแถวที่ 3 ให้สมบูรณ์");
                        }
                    }
                    failToCreatePO();
                }else{
                    // ครบหมดถ้าสดชื่น
                    Boolean newErr = false;
                    float price;
                    Po po = new Po(codeF.getText(), supplier_id, 0, supp);
                    if(code1.getValue() != null && !quantityField_1.getText().trim().equals("") && !priceField_1.getText().trim().equals("")){
                        //แถว 1 ครบ
                        if (Integer.parseInt(quantityField_1.getText())<1 && Integer.parseInt(priceField_1.getText())<1){
                            // จำนวนน้อบกว่า 1 และ ราคาน้อยกว่า 1
                            newErr = true;
                            item1Alert.setText("จำนวนสินค้าและราคาต่อม้วนต้องเป็น 1 ขึ้นไป");
                        }else if(Integer.parseInt(quantityField_1.getText())<1){
                            // จำนวนน้อยกว่า 1 อย่างเดียว
                            newErr = true;
                            item1Alert.setText("จำนวนสินค้าต้องเป็น 1 ขึ้นไป");
                        }else if(Integer.parseInt(priceField_1.getText())<1){
                            // ราคาน้อยกว่า 1 อย่างเดียว
                            newErr = true;
                            item1Alert.setText("ราคาต่อม้วนต้องเป็น 1 ขึ้นไป");
                        }else{
                            price = Integer.parseInt(quantityField_1.getText())* Float.parseFloat(priceField_1.getText());
                            poLine = new PoLine(codeF.getText(), code1.getValue().toString(), new BigInteger(quantityField_1.getText()),
                                    Float.parseFloat(priceField_1.getText()));
                            po.addPoLine(poLine);
                            po.addTotalPrice(price);
                        }
                    }
                    if(code2.getValue() != null && !quantityField_2.getText().trim().equals("") && !priceField_2.getText().trim().equals("")){
                        //แถว 2 ครบ
                        if (Integer.parseInt(quantityField_2.getText())<1 && Integer.parseInt(priceField_2.getText())<1){
                            // จำนวนน้อบกว่า 1 และ ราคาน้อยกว่า 1
                            newErr = true;
                            item2Alert.setText("จำนวนสินค้าและราคาต่อม้วนต้องเป็น 1 ขึ้นไป");
                        }else if(Integer.parseInt(quantityField_2.getText())<1){
                            // จำนวนน้อยกว่า 1 อย่างเดียว
                            newErr = true;
                            item2Alert.setText("จำนวนสินค้าต้องเป็น 1 ขึ้นไป");
                        }else if(Integer.parseInt(priceField_2.getText())<1){
                            // ราคาน้อยกว่า 1 อย่างเดียว
                            newErr = true;
                            item2Alert.setText("ราคาต่อม้วนต้องเป็น 1 ขึ้นไป");
                        }else {
                            price = Integer.parseInt(quantityField_2.getText()) * Float.parseFloat(priceField_2.getText());
                            poLine = new PoLine(codeF.getText(), code2.getValue().toString(), new BigInteger(quantityField_2.getText()),
                                    Float.parseFloat(priceField_2.getText()));
                            po.addPoLine(poLine);
                            po.addTotalPrice(price);
                        }
                    }
                    if(code3.getValue() != null && !quantityField_3.getText().trim().equals("") && !priceField_3.getText().trim().equals("")){
                        //แถว 3 ครบ
                        if (Integer.parseInt(quantityField_3.getText())<1 && Integer.parseInt(priceField_3.getText())<1){
                            // จำนวนน้อบกว่า 1 และ ราคาน้อยกว่า 1
                            newErr = true;
                            item3Alert.setText("จำนวนสินค้าและราคาต่อม้วนต้องเป็น 1 ขึ้นไป");
                        }else if(Integer.parseInt(quantityField_3.getText())<1){
                            // จำนวนน้อยกว่า 1 อย่างเดียว
                            newErr = true;
                            item3Alert.setText("จำนวนสินค้าต้องเป็น 1 ขึ้นไป");
                        }else if(Integer.parseInt(priceField_3.getText())<1){
                            // ราคาน้อยกว่า 1 อย่างเดียว
                            newErr = true;
                            item3Alert.setText("ราคาต่อม้วนต้องเป็น 1 ขึ้นไป");
                        }else {
                            price = Integer.parseInt(quantityField_3.getText()) * Float.parseFloat(priceField_3.getText());
                            poLine = new PoLine(codeF.getText(), code3.getValue().toString(), new BigInteger(quantityField_3.getText()),
                                    Float.parseFloat(priceField_3.getText()));
                            po.addPoLine(poLine);
                            po.addTotalPrice(price);
                        }
                    }
                    if (newErr){
                        failToCreatePO();
                    }else {
//                    CALL POST API
                        ResponseMessage res = new DBConnector().createPO(po);

                        if (res.isSuccess()) {
                            Button b = (Button) event.getSource();
                            Stage stage = (Stage) b.getScene().getWindow();

                            // Alert Box
                            Stage purchaseOrderAlertPage = new Stage();
                            purchaseOrderAlertPage.initModality(Modality.APPLICATION_MODAL);
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/purchaseOrder/createPurchaseOrderAlert.fxml"));
                            Scene scene = new Scene(loader.load());
                            purchaseOrderAlertPage.setScene(scene);
                            purchaseOrderAlertPage.setTitle("สำเร็จ");
                            purchaseOrderAlertPage.setResizable(false);
                            CreatePurchaseOrderAlertController pa = loader.getController();
                            pa.setPoCode(codeF.getText());
                            purchaseOrderAlertPage.show();

                            ArrayList<Po> arrayList = new ArrayList<>();
                            for (Po po1 : poList) {
                                if (po1.getStatus().equals(PurchaseStatus.Wait)) {
                                    arrayList.add(po1);
                                }
                            }
                            arrayList.add(po);
                            setPOTable(arrayList);
                            stage.close();
                        } else {
                            failToCreatePO();
                            if (res.getError().getCode() != null) {
                                codeAlert.setText(res.getError().getCode().get(0));
                            }
                        }
                    }
                }
            }else{
                // ไมมีสินค้าที่สั่ง
                failToCreatePO();
                item1Alert.setText("กรุณาสั่งซื้อสินค้าให้สมบูรณ์อย่างน้อย 1 รายการ");
                item2Alert.setText("กรุณาสั่งซื้อสินค้าให้สมบูรณ์อย่างน้อย 1 รายการ");
                item3Alert.setText("กรุณาสั่งซื้อสินค้าให้สมบูรณ์อย่างน้อย 1 รายการ");
            }
        }else{
            failToCreatePO();
            codeAlert.setText("กรุณากรอกเลขกำกับใบสั่งซื้อ");
        }
    }

    public void setPOTable(ArrayList<Po> arrayList){
        table.getItems().clear();
        list = FXCollections.observableArrayList(arrayList);
        table.setItems(list);
        code.setCellValueFactory(new PropertyValueFactory<>("code"));
        totalPriceCol.setCellValueFactory(new PropertyValueFactory<>("totalPriceWithComma"));
        supplierCol.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
    }

    @FXML public void showPhone(){
        for(Supplier supplier1 : supplierList){
            if (supplier.getValue() == null){
                phone.setText("");
                break;
            }else{
                if (supplier.getValue().toString().equals(supplier1.getName())){
                    phone.setText("เบอร์โทรศัพท์ : "+supplier1.getPhoneNo());
                    supplier_id = supplier1.getId();
                    supp = supplier1;
                    break;
                }
            }
        }
    }

    @FXML public void failToCreatePO() throws IOException {
        Stage stage1 = new Stage();
        stage1.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/failAlert.fxml"));
        stage1.setScene(new Scene(loader.load()));
        stage1.setTitle("แจ้งเตือน");
        stage1.setResizable(false);
        FailAlertController fa = loader.getController();
        fa.setFrom("สร้างใบสั่งซื้อไม่สำเร็จ");
        stage1.show();

    }

    @FXML public void clearAlert(){
        codeAlert.setText("");
        item1Alert.setText("");
        item2Alert.setText("");
        item3Alert.setText("");
        supplierAlert.setText("");
    }

    @FXML public void clear1(ActionEvent event){
        code1.getSelectionModel().clearSelection();
        quantityField_1.setText("");
        priceField_1.setText("");
    }

    @FXML public void clear2(ActionEvent event){
        code2.getSelectionModel().clearSelection();
        quantityField_2.setText("");
        priceField_2.setText("");
    }

    @FXML public void clear3(ActionEvent event){
        code3.getSelectionModel().clearSelection();
        quantityField_3.setText("");
        priceField_3.setText("");
    }

    public void setTable(TableView<Po> table) {
        this.table = table;
    }

    public void setCode(TableColumn<Po, String> code) {
        this.code = code;
    }

    public void setTotalPriceCol(TableColumn<Po, String> totalPriceCol) {
        this.totalPriceCol = totalPriceCol;
    }

    public void setSupplierCol(TableColumn<Po, String> supplierCol) {
        this.supplierCol = supplierCol;
    }
}
