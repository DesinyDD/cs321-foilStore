package org.turkey.controllers.purchaseOrder.toWaitPay;

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
import org.turkey.models.Po;

import java.io.IOException;

public class UpdatePurchaseOrderToWaitPayController {
    @FXML private Label label;
    @FXML private JFXButton cancelBtn;
    @FXML private TableView<Po> table;
    @FXML private TableColumn<Po, String> code,supplier;
    @FXML private TableColumn<Po, String> price;
    private Po po;

    public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                label.setText(po.showLabel());
            }
        });
    }

    @FXML private void updateStatus() throws IOException {
        // Confirm Box
        Stage updatePurchaseOrderToWaitPayPage = new Stage();
        updatePurchaseOrderToWaitPayPage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/purchaseOrder/toWaitPay/updatePurchaseOrderToWaitPayConfirm.fxml"));
        Scene scene = new Scene(loader.load());
        updatePurchaseOrderToWaitPayPage.setScene(scene);
        updatePurchaseOrderToWaitPayPage.setTitle("ยืนยันการเปลี่ยนสถานะ");
        updatePurchaseOrderToWaitPayPage.setResizable(false);
        updatePurchaseOrderToWaitPayPage.show();
        UpdatePurchaseOrderToWaitPayConfirmController ua = loader.getController();
        ua.setPo(po);
        ua.setTable(table);
        ua.setSupplier(supplier);
        ua.setCode(code);
        ua.setPrice(price);
        this.close();
    }

    @FXML private void close() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
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

    public void setPrice(TableColumn<Po, String> price) {
        this.price = price;
    }

    public void setSupplier(TableColumn<Po, String> supplier) {
        this.supplier = supplier;
    }
}
