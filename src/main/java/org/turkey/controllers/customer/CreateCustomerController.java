package org.turkey.controllers.customer;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

public class CreateCustomerController {
    private List<Customer> customers = new DBConnector().getCustomer();
    private ObservableList list;
    @FXML
    private TableView<Customer> table;
    @FXML
    private TableColumn<Customer, String> name, phone, address;
    @FXML
    private TextField nameF, phoneF;
    @FXML
    private TextArea addressF;
    @FXML private Label nameAlert, addressAlert, phoneAlert;

    public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                clearAlert();
            }
        });
    }

    @FXML
    public void addCustomer(ActionEvent event) throws IOException {
        clearAlert();
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        if (!nameF.getText().trim().equals("") && !phoneF.getText().trim().equals("") && !addressF.getText().trim().equals("")) {
            Customer customer = new Customer(nameF.getText(), addressF.getText(), phoneF.getText());
//            create customer API
            new DBConnector().createCustomer(customer);

            customers.add(customer);
            setCustomerTable();
            // Alert Box
            Stage createCustomerAlertPage = new Stage();
            createCustomerAlertPage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/customer/createCustomerAlert.fxml"));
            Scene scene = new Scene(loader.load());
            createCustomerAlertPage.setScene(scene);
            createCustomerAlertPage.setTitle("สำเร็จ");
            createCustomerAlertPage.setResizable(false);
            CreateCustomerAlertController ca = loader.getController();
            ca.setCustomerName(nameF.getText());
            createCustomerAlertPage.show();

            stage.close();
        } else {
            failToCreateCustomer();
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

    public void setCustomerTable() {
        list = FXCollections.observableArrayList(customers);
        table.setItems(list);
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
    }

    @FXML
    public void failToCreateCustomer() throws IOException {
        Stage stage1 = new Stage();
        stage1.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/failAlert.fxml"));
        stage1.setScene(new Scene(loader.load()));
        stage1.setTitle("แจ้งเตือน");
        stage1.setResizable(false);
        FailAlertController fa = loader.getController();
        fa.setFrom("เพิ่มข้อมูลลูกค้าไม่สำเร็จ");
        stage1.show();

    }

    @FXML public void clearAlert(){
        nameAlert.setText("");
        addressAlert.setText("");
        phoneAlert.setText("");
    }

    public void setAddress(TableColumn<Customer, String> address) {
        this.address = address;
    }

    public void setName(TableColumn<Customer, String> name) {
        this.name = name;
    }

    public void setTable(TableView<Customer> table) {
        this.table = table;
    }

    public void setPhone(TableColumn<Customer, String> phone) {
        this.phone = phone;
    }
}
