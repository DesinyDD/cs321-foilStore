package org.turkey.controllers;

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
import org.turkey.models.Item;
import org.turkey.models.Status;
import org.turkey.services.NavBarService;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

public class StockController {

    @FXML private TableView<Item> table;
    @FXML private TableColumn<Item, String> code;
    @FXML private TableColumn<Item, BigInteger> amount;
    @FXML private TableColumn<Item, Enum<Status>> status;
    private ArrayList<Item> stock;
    private ObservableList list;
    @FXML
    public void initialize(){
        stock = new ArrayList<>();
        Item item = new Item("OSP 900", new BigInteger(230+""), 1100, new BigInteger(50+""));
        stock.add(item);
        item = new Item("OSP 730", new BigInteger(180+""), 1000, new BigInteger(35+""));
        stock.add(item);
        setItemTable();
    }

    @FXML private void createPurchaseOrder() throws IOException {
        Stage createPurchaseOrderPage = new Stage();
        createPurchaseOrderPage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/createPurchaseOrder.fxml"));
        Scene scene = new Scene(loader.load());

        // test

        CreatePurchaseOrderController con = loader.getController();
        con.setStock(stock);
        con.setTable(table);
        con.setStatus(status);
        con.setCode(code);
        con.setAmount(amount);

        // test
        createPurchaseOrderPage.setScene(scene);
        createPurchaseOrderPage.show();
    }

//    public void setItemTable(){
//        code.setSortable(true);
//        table.getItems().clear();
//        for(Item item: stock){
//            table.getItems().add(item);
//            code.setCellValueFactory(new PropertyValueFactory<>("colorCode"));
//            status.setCellValueFactory(new PropertyValueFactory<>("status"));
//            amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
//        }
//        table.getSortOrder().add(code);
//        code.setSortable(false);
//        status.setSortable(false);
//        amount.setSortable(false);
//    }
    public void setItemTable(){
        list = FXCollections.observableArrayList(stock);
        table.setItems(list);
        code.setCellValueFactory(new PropertyValueFactory<>("colorCode"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }

    // Page Switcher
    @FXML private void toHome() throws IOException { NavBarService.switchToHome(); }
    @FXML private void toCustomer() throws IOException { NavBarService.switchToCustomer(); }
    @FXML private void toSaleOrder() throws IOException { NavBarService.switchToSaleOrder(); }
    @FXML private void toStock() throws IOException { NavBarService.switchToStock(); }
    @FXML private void toReport() throws IOException { NavBarService.switchToReport(); }
    @FXML private void toPurchaseOrder() throws IOException { NavBarService.switchToPurchaseOrder(); }
}
