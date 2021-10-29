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
import org.turkey.models.ResponseMessage;
import org.turkey.models.StatusInApp;
import org.turkey.services.HTTPRequest.DBConnector;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

public class EditItemController {
    @FXML private JFXButton cancelBtn;
    @FXML private TextField codeF, priceF, minF;
    @FXML private TableView<Item> table;
    @FXML private TableColumn<Item, String> code;
    @FXML private TableColumn<Item, String> amount;
    @FXML private TableColumn<Item, Enum<StatusInApp>> status;
    @FXML private Label codeAlert, priceAlert, minAlert;
    private Item thisItem, beforeEdit;
    private ObservableList list;

    public void initialize() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                clearAlert();
                codeF.setText(thisItem.getCode());
                priceF.setText(thisItem.getPrice()+"");
                minF.setText(thisItem.getMinAmount()+"");
                beforeEdit = new Item(thisItem.getCode(), thisItem.getPrice(), thisItem.getAmount(), thisItem.getMinAmount());
            }
        });
        priceF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,2})?")) {
                    priceF.setText(oldValue);
                }
            }
        });
        minF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}?")) {
                    minF.setText(oldValue);
                }
            }
        });
    }

    @FXML
    private void editItem() throws IOException {
        clearAlert();
        if (!codeF.getText().trim().equals("") && !priceF.getText().trim().equals("") && !minF.getText().trim().equals("")) {
            thisItem.setCode(codeF.getText().trim());
            thisItem.setPrice(Float.parseFloat(priceF.getText().trim()));
            thisItem.setMinAmount(new BigInteger(minF.getText().trim()));
            System.out.println(thisItem);
            System.out.println(beforeEdit);
            // edit item on database
            ResponseMessage res =  new DBConnector().updateItem(thisItem, beforeEdit);

            // ดึงข้อมูล item หลังแก้จาก database
            List<Item> stock = new DBConnector().getItem();
            setItemTable(stock);

            Stage editItemConfirmPage = new Stage();
            editItemConfirmPage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/stock/editItemAlert.fxml"));
            Scene scene = new Scene(loader.load());
            editItemConfirmPage.setScene(scene);
            editItemConfirmPage.setTitle("สำเร็จ");
            editItemConfirmPage.setResizable(false);
            editItemConfirmPage.show();
            EditItemAlertController ea = loader.getController();
            ea.setColorCode(codeF.getText());
            this.close();
        } else {
            failToEditItem();
            if (codeF.getText().trim().equals("")) {
                codeAlert.setText("กรุณากรอกชื่อรหัสสี");
            }
            if (priceF.getText().trim().equals("")) {
                priceAlert.setText("กรุณาใส่ราคารขายต่อม้วน");
            }
            if (minF.getText().trim().equals("")) {
                minAlert.setText("กรุณาใส่จำนวนคงเหลือขั้นต่ำ");
            }
        }
    }

    @FXML
    public void failToEditItem() throws IOException {
        Stage stage1 = new Stage();
        stage1.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/failAlert.fxml"));
        stage1.setScene(new Scene(loader.load()));
        stage1.setTitle("แจ้งเตือน");
        stage1.setResizable(false);
        FailAlertController fa = loader.getController();
        fa.setFrom("แก้ไขประเภทสินค้าใหม่ไม่สำเร็จ");
        stage1.show();

    }

    @FXML
    private void close() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    public void setItemTable(List<Item> stock) {
        list = FXCollections.observableArrayList(stock);
        table.setItems(list);
        code.setCellValueFactory(new PropertyValueFactory<>("code"));
//        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amountWithComma"));
    }
    @FXML public void clearAlert(){
        codeAlert.setText("");
        priceAlert.setText("");
        minAlert.setText("");
    }

    public void setThisItem(Item thisItem) {
        this.thisItem = thisItem;
    }

    public void setTable(TableView<Item> table) {
        this.table = table;
    }

    public void setCode(TableColumn<Item, String> code) {
        this.code = code;
    }

    public void setStatus(TableColumn<Item, Enum<StatusInApp>> status) {
        this.status = status;
    }

    public void setAmount(TableColumn<Item, String> amount) {
        this.amount = amount;
    }
}
