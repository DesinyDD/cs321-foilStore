package org.turkey.controllers.customer;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.turkey.controllers.FailAlertController;
import org.turkey.models.Customer;
import org.turkey.services.HTTPRequest.DBConnector;

import java.io.IOException;
import java.util.List;

public class EditCustomerController {
    @FXML
    private JFXButton editBtn;
    @FXML
    private TextField nameF, phoneF;
    @FXML
    private TextArea addressF;
    @FXML
    private TableView<Customer> table;
    @FXML
    private TableColumn<Customer, String> nameCol, phoneCol, addressCol;
    private Customer thisCustomer;
    private ObservableList list;
    @FXML private Label nameAlert, addressAlert, phoneAlert;

    public void initialize() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                clearAlert();
                nameF.setText(thisCustomer.getName());
                addressF.setText(thisCustomer.getAddress());
                phoneF.setText(thisCustomer.getPhoneNo());
            }
        });
    }

    @FXML
    private void editCustomer() throws IOException {
        if (!nameF.getText().trim().equals("") && !phoneF.getText().trim().equals("") && !addressF.getText().trim().equals("")) {
            // Alert Box
            Stage editCustomerAlertPage = new Stage();
            editCustomerAlertPage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/customer/editCustomerAlert.fxml"));
            Scene scene = new Scene(loader.load());
            editCustomerAlertPage.setScene(scene);
            editCustomerAlertPage.setTitle("สำเร็จ");
            editCustomerAlertPage.setResizable(false);
            EditCustomerAlertController ea = loader.getController();
            ea.setCustomerName(nameF.getText());
            editCustomerAlertPage.show();
            thisCustomer.setName(nameF.getText().trim());
            thisCustomer.setAddress(addressF.getText().trim());
            thisCustomer.setPhoneNo(phoneF.getText().trim());
            // edit ที่ database
            new DBConnector().updateCustomer(thisCustomer);

            // ดึงข้อมูลมาใหม่
            List<Customer> customers = new DBConnector().getCustomer();

            setCustomerTable(customers);

            Stage stage = (Stage) editBtn.getScene().getWindow();
            stage.close();
        } else {
            failToEditCustomer();
            if (nameF.getText().trim().equals("")) {
                nameAlert.setText("กรุณากรอกชื่อ");
            }
            if (addressF.getText().trim().equals("")) {
                addressAlert.setText("กรุณากรอกที่อยู่");
            }
            if (phoneF.getText().trim().equals("")) {
                phoneAlert.setText("กรุณากรอกเบอร์ติดต่อ");
            }
        }
    }

    public void setCustomerTable(List<Customer> customers) {
        list = FXCollections.observableArrayList(customers);
        table.setItems(list);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
    }

    @FXML
    public void failToEditCustomer() throws IOException {
        Stage stage1 = new Stage();
        stage1.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/failAlert.fxml"));
        stage1.setScene(new Scene(loader.load()));
        stage1.setTitle("แจ้งเตือน");
        stage1.setResizable(false);
        FailAlertController fa = loader.getController();
        fa.setFrom("แก้ไขข้อมูลลูกค้าไม่สำเร็จไม่สำเร็จ");
        stage1.show();

    }
    @FXML public void clearAlert(){
        nameAlert.setText("");
        addressAlert.setText("");
        phoneAlert.setText("");
    }

    public void setThisCustomer(Customer thisCustomer) {
        this.thisCustomer = thisCustomer;
    }

    public void setTable(TableView<Customer> table) {
        this.table = table;
    }

    public void setNameCol(TableColumn<Customer, String> nameCol) {
        this.nameCol = nameCol;
    }

    public void setAddressCol(TableColumn<Customer, String> addressCol) {
        this.addressCol = addressCol;
    }

    public void setPhoneCol(TableColumn<Customer, String> phoneCol) {
        this.phoneCol = phoneCol;
    }
}
