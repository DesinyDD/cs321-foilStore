package org.turkey.controllers.saleOrder.toComplete;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.turkey.models.SaleOrder;

import java.io.IOException;

public class UpdateSaleOrderToCompleteController {
    @FXML private JFXButton cancelBtn;
    @FXML private Label label;
    @FXML private TableView<SaleOrder> table;
    @FXML private TableColumn<SaleOrder,String> code,customer;
    @FXML private TableColumn<SaleOrder, Float> price;
    private SaleOrder saleOrder;

    public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                label.setText(saleOrder.showLabel());
            }
        });
    }

    @FXML private void updateStatus() throws IOException {
        // Confirm Box
        Stage updateSaleOrderToCompleteConfirmPage = new Stage();
        updateSaleOrderToCompleteConfirmPage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/saleOrder/toComplete/updateSaleOrderToCompleteConfirm.fxml"));
        Scene scene = new Scene(loader.load());
        updateSaleOrderToCompleteConfirmPage.setScene(scene);
        updateSaleOrderToCompleteConfirmPage.setTitle("ยืนยันการเปลี่ยนสถานะ");
        updateSaleOrderToCompleteConfirmPage.setResizable(false);
        updateSaleOrderToCompleteConfirmPage.show();

        UpdateSaleOrderToCompleteConfirmController con = loader.getController();
        con.setSaleOrder(saleOrder);
        con.setTable(table);
        con.setCode(code);
        con.setCustomer(customer);
        con.setPrice(price);
        this.close();
    }

    @FXML private void close() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
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
