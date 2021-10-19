package org.turkey.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.turkey.models.Item;
import org.turkey.models.Status;

import java.math.BigInteger;
import java.util.ArrayList;

public class CreatePurchaseOrderController {
    @FXML private TextField quantityField_1, quantityField_2, quantityField_3;
    @FXML private TextField priceField_1, priceField_2, priceField_3;
    @FXML private Button cancel;
    @FXML private TableView<Item> table;
    @FXML private TableColumn<Item, String> code;
    @FXML private TableColumn<Item, BigInteger> amount;
    @FXML private TableColumn<Item, Enum<Status>> status;
    private ArrayList<Item> stock;
    private ObservableList list;
    @FXML public void initialize() {
        cancel.setOnAction(event->{
            Stage stage =(Stage)cancel.getScene().getWindow();
            stage.close();
        });
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

    @FXML public void createPO(ActionEvent event){
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        Item item = new Item("Test",new BigInteger("30"),200, new BigInteger("10"));
        stock.add(item);
        setItemTable();
        stage.close();
    }

    public void setItemTable(){
        list = FXCollections.observableArrayList(stock);
        table.setItems(list);
        code.setCellValueFactory(new PropertyValueFactory<>("colorCode"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }

    public void setStock(ArrayList<Item> stock){this.stock = stock;}
    public void setTable(TableView<Item> table){this.table = table;}
    public void setAmount(TableColumn<Item, BigInteger> amount) {this.amount = amount;}
    public void setCode(TableColumn<Item, String> code) {this.code = code;}
    public void setStatus(TableColumn<Item, Enum<Status>> status) {this.status = status;}
}
