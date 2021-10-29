package org.turkey.controllers.stock;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.turkey.controllers.FailAlertController;
import org.turkey.models.Item;
import org.turkey.models.StatusInApp;
import org.turkey.services.HTTPRequest.DBConnector;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

public class CreateItemController {
    @FXML private JFXButton cancelBtn;
    @FXML private TextField code, price, min;
    @FXML private TableView<Item> table;
    @FXML private TableColumn<Item, String> codeF;
    @FXML private TableColumn<Item, String> amountF;
    @FXML private TableColumn<Item, Enum<StatusInApp>> status;
    @FXML private Label codeAlert, priceAlert, minAlert;
    private List<Item> stock = new DBConnector().getItem();
    private ObservableList list;

    public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                clearAlert();
            }
        });
        price.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,2})?")) {
                    price.setText(oldValue);
                }
            }
        });
        min.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}?")) {
                    min.setText(oldValue);
                }
            }
        });
    }

    @FXML private void createItem() throws IOException {
        clearAlert();
        if(code.getText().trim().equals("") || price.getText().trim().equals("") || min.getText().trim().equals("")){
            System.out.println("create fail");
            failToCreateItem();
            if (code.getText().trim().equals("")){
                codeAlert.setText("กรุณากรอกชื่อรหัสสี");
            }
            if (price.getText().trim().equals("")){
                priceAlert.setText("กรุณาใส่ราคารขายต่อม้วน");
            }
            if (min.getText().trim().equals("")){
                minAlert.setText("กรุณาใส่จำนวนคงเหลือขั้นต่ำ");
            }
        }else{
            Item item = new Item(code.getText().trim(), Float.parseFloat(price.getText().trim()), new BigInteger(min.getText()));
            stock.add(item);
            System.out.println(item);
            new DBConnector().createItem(item);
            setItemTable();
            // alert
            Stage createItemAlertPage = new Stage();
            createItemAlertPage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/stock/createItemAlert.fxml"));
            Scene scene = new Scene(loader.load());
            createItemAlertPage.setScene(scene);
            createItemAlertPage.setTitle("สำเร็จ");
            createItemAlertPage.setResizable(false);
            createItemAlertPage.show();
            CreateItemAlertController ca = loader.getController();
            ca.setColorCode(code.getText());
            this.close();
        }
    }

    @FXML private void close() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    public void setItemTable(){
        list = FXCollections.observableArrayList(stock);
        table.setItems(list);
        codeF.setCellValueFactory(new PropertyValueFactory<>("code"));
//        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        amountF.setCellValueFactory(new PropertyValueFactory<>("amountWithComma"));
    }
    @FXML public void failToCreateItem() throws IOException {
        Stage stage1 = new Stage();
        stage1.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/failAlert.fxml"));
        stage1.setScene(new Scene(loader.load()));
        stage1.setTitle("แจ้งเตือน");
        stage1.setResizable(false);
        FailAlertController fa = loader.getController();
        fa.setFrom("สร้างประเภทสินค้าใหม่ไม่สำเร็จ");
        stage1.show();

    }
    @FXML public void clearAlert(){
        codeAlert.setText("");
        priceAlert.setText("");
        minAlert.setText("");
    }

    public void setTable(TableView<Item> table) {
        this.table = table;
    }

    public void setAmountF(TableColumn<Item, String> amountF) {
        this.amountF = amountF;
    }

    public void setStatus(TableColumn<Item, Enum<StatusInApp>> status) {
        this.status = status;
    }

    public void setCodeF(TableColumn<Item, String> codeF) {
        this.codeF = codeF;
    }
}
