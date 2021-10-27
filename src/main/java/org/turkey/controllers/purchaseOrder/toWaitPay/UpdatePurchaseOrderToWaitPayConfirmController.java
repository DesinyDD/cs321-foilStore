package org.turkey.controllers.purchaseOrder.toWaitPay;

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
import org.turkey.models.Po;
import org.turkey.models.PurchaseStatus;
import org.turkey.services.HTTPRequest.DBConnector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UpdatePurchaseOrderToWaitPayConfirmController {
    @FXML private JFXButton cancelBtn;
    @FXML private TableView<Po> table;
    @FXML private TableColumn<Po, String> code,supplier;
    @FXML private TableColumn<Po, Float> price;
    private Po po;
    private ObservableList list;

    @FXML private void confirm() throws IOException {
        // edit PO status on api


        //fetch data from db
        List<Po> poList = new DBConnector().getPO();
        ArrayList<Po> arrayList = new ArrayList<>();
        for (Po po1: poList){
            if(po1.getStatus().equals(PurchaseStatus.Wait)){
                arrayList.add(po1);
            }
        }
        setPOTable(arrayList);

        // Alert Box
        Stage updatePurchaseOrderToWaitPayAlertPage = new Stage();
        updatePurchaseOrderToWaitPayAlertPage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/purchaseOrder/toWaitPay/updatePurchaseOrderToWaitPayAlert.fxml"));
        Scene scene = new Scene(loader.load());
        updatePurchaseOrderToWaitPayAlertPage.setScene(scene);
        updatePurchaseOrderToWaitPayAlertPage.setTitle("สำเร็จ");
        updatePurchaseOrderToWaitPayAlertPage.setResizable(false);
        updatePurchaseOrderToWaitPayAlertPage.show();
        UpdatePurchaseOrderToWaitPayAlertController ua = loader.getController();
        ua.setCode(po.getCode());
        this.close();
    }

    @FXML private void close() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
    public void setPOTable(ArrayList<Po> arrayList){
        table.getItems().clear();
        list = FXCollections.observableArrayList(arrayList);
        table.setItems(list);
        code.setCellValueFactory(new PropertyValueFactory<>("code"));
        price.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        supplier.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
    }

    public void setPo(Po po) {
        this.po = po;
    }

    public void setTable(TableView<Po> table) {
        this.table = table;
    }

    public void setCode(TableColumn<Po, String> code) {
        this.code = code;
    }

    public void setPrice(TableColumn<Po, Float> price) {
        this.price = price;
    }

    public void setSupplier(TableColumn<Po, String> supplier) {
        this.supplier = supplier;
    }
}
