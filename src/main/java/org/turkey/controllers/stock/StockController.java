package org.turkey.controllers.stock;

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
import org.turkey.models.Item;
import org.turkey.models.StatusInApp;
import org.turkey.services.HTTPRequest.DBConnector;
import org.turkey.services.NavBarService;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

public class StockController {

    @FXML private TableView<Item> table;
    @FXML private TableColumn<Item, String> code;
    @FXML private TableColumn<Item, BigInteger> amount;
    @FXML private TableColumn<Item, Enum<StatusInApp>> status;
    private List<Item> stock = new DBConnector().getItem();
    private ObservableList list;

    @FXML public void initialize() {
        table.setRowFactory( tv -> {
            TableRow<Item> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty()) ) {
                    Item rowData = row.getItem();
                    try {
                        editItem(rowData);
                    } catch (IOException e) {
//                         do nothing . . .
                    }
                    System.out.println(rowData);
                }
            });
            return row;
        });

//        stock = new ArrayList<>();
//        MockUpData.mockUpStock(stock);
        setItemTable();
    }

    @FXML private void createItem() throws IOException {
        Stage createItemPage = new Stage();
        createItemPage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/stock/createItem.fxml"));
        Scene scene = new Scene(loader.load());
        createItemPage.setScene(scene);
        createItemPage.setTitle("เพิ่มประเภทสินค้า");
        createItemPage.setResizable(false);
        CreateItemController ci = loader.getController();
        ci.setTable(table);
        ci.setCodeF(code);
        ci.setStatus(status);
        ci.setAmountF(amount);
        createItemPage.show();
    }

    @FXML private void editItem(Item item) throws IOException {
        Stage editItemPage = new Stage();
        editItemPage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/stock/editItem.fxml"));
        Scene scene = new Scene(loader.load());
        editItemPage.setScene(scene);
        editItemPage.setTitle("แก้ไขข้อมูลสินค้า");
        editItemPage.setResizable(false);
        EditItemController ec = loader.getController();
        ec.setAmount(amount);
        ec.setCode(code);
        ec.setTable(table);
        ec.setStatus(status);
//        ec.setColorCode(colorCode);
//        ec.setMinAmount(min);
//        ec.setPrice(price);
        ec.setThisItem(item);
        editItemPage.show();
    }

//    @FXML private void createPurchaseOrder() throws IOException {
//        Stage createPurchaseOrderPage = new Stage();
//        createPurchaseOrderPage.initModality(Modality.APPLICATION_MODAL);
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/purchaseOrder/createPurchaseOrder.fxml"));
//        Scene scene = new Scene(loader.load());
//        CreatePurchaseOrderController con = loader.getController();
//        createPurchaseOrderPage.setScene(scene);
//        createPurchaseOrderPage.show();
//    }

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
        code.setCellValueFactory(new PropertyValueFactory<>("code"));
//        status.setCellValueFactory(new PropertyValueFactory<>("status"));
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
