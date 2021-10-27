package org.turkey.controllers.saleOrder.toComplete;

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
import org.turkey.models.SaleOrder;
import org.turkey.models.Status;
import org.turkey.services.HTTPRequest.DBConnector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UpdateSaleOrderToCompleteConfirmController {
    @FXML private JFXButton cancelBtn;
    @FXML private TableView<SaleOrder> table;
    @FXML private TableColumn<SaleOrder,String> code,customer;
    @FXML private TableColumn<SaleOrder, Float> price;
    private ObservableList list;
    private SaleOrder saleOrder;

    @FXML private void confirm() throws IOException {
        // edit status api


        // fetch so for set table
        List<SaleOrder> saleOrderList = new DBConnector().getSaleOrder();
        ArrayList<SaleOrder> saleOrders = new ArrayList<>();
        for (SaleOrder saleOrder1 : saleOrderList){
            if(saleOrder1.getStatus().equals(Status.WaitPay)){
                saleOrders.add(saleOrder1);
            }
        }
        setSOTable(saleOrders);
        // Alert Box
        Stage updateSaleOrderToCompleteAlertPage = new Stage();
        updateSaleOrderToCompleteAlertPage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/saleOrder/toComplete/updateSaleOrderToCompleteAlert.fxml"));
        Scene scene = new Scene(loader.load());
        updateSaleOrderToCompleteAlertPage.setScene(scene);
        updateSaleOrderToCompleteAlertPage.setTitle("สำเร็จ");
        updateSaleOrderToCompleteAlertPage.setResizable(false);
        updateSaleOrderToCompleteAlertPage.show();
        UpdateSaleOrderToCompleteAlertController ua = loader.getController();
        ua.setSoCode(saleOrder.getCode());
        this.close();
    }

    @FXML private void close() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
    public void setSOTable(ArrayList<SaleOrder> arrayList){
        table.getItems().clear();
        list = FXCollections.observableArrayList(arrayList);
        table.setItems(list);
        code.setCellValueFactory(new PropertyValueFactory<>("code"));
        price.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        customer.setCellValueFactory(new PropertyValueFactory<>("customerName"));
    }

    public void setSaleOrder(SaleOrder saleOrder) {
        this.saleOrder = saleOrder;
    }

    public void setTable(TableView<SaleOrder> table) {
        this.table = table;
    }

    public void setCode(TableColumn<SaleOrder, String> code) {
        this.code = code;
    }

    public void setPrice(TableColumn<SaleOrder, Float> price) {
        this.price = price;
    }

    public void setCustomer(TableColumn<SaleOrder, String> customer) {
        this.customer = customer;
    }
}
