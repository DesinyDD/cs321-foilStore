package org.turkey.controllers.customer;

import com.jfoenix.controls.JFXButton;
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
import org.turkey.models.Customer;
import org.turkey.models.Information;
import org.turkey.models.Item;
import org.turkey.services.MockUpData;
import org.turkey.services.NavBarService;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

public class CustomerController {
    @FXML private JFXButton addCustomerBtn;
    @FXML private TableView<Customer> table;
    @FXML private TableColumn<Customer, String> name, phone, address;
    private ObservableList list;
    private ArrayList<Customer> customers;

    @FXML public void initialize(){
        table.setRowFactory( tv -> {
            TableRow<Customer> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty()) ) {
                    Customer rowData = row.getItem();
                    try {
                        editCustomer(rowData.getName(), rowData.getAddress(), rowData.getPhoneNo());
                    } catch (IOException e) {
                        // do nothing . . .
                    }
                }
            });
            return row;
        });

        customers = new ArrayList<>();
        MockUpData.mockUpCustomer(customers);
        setCustomerTable();
    }

    @FXML private void addCustomer() throws IOException {
        Stage createCustomerPage = new Stage();
        createCustomerPage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/customer/createCustomer.fxml"));
        Scene scene = new Scene(loader.load());
        createCustomerPage.setScene(scene);
        CreateCustomerController cc = loader.getController();
        cc.setCustomers(customers);
        cc.setTable(table);
        cc.setName(name);
        cc.setPhone(phone);
        cc.setAddress(address);
        createCustomerPage.show();
    }

    @FXML private void editCustomer(String name, String address, String phone) throws IOException {
        Stage editCustomerPage = new Stage();
        editCustomerPage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/customer/editCustomer.fxml"));
        Scene scene = new Scene(loader.load());
        editCustomerPage.setScene(scene);
        editCustomerPage.setTitle("แก้ไขข้อมูลลูกค้า");
        editCustomerPage.setResizable(false);
        EditCustomerController ec = loader.getController();
        ec.setName(name);
        ec.setAddress(address);
        ec.setPhone(phone);
        editCustomerPage.show();
    }

    public void setCustomerTable(){
        list = FXCollections.observableArrayList(customers);
        table.setItems(list);
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
    }

    // Page Switcher
    @FXML private void toHome() throws IOException { NavBarService.switchToHome(); }
    @FXML private void toCustomer() throws IOException { NavBarService.switchToCustomer(); }
    @FXML private void toSaleOrder() throws IOException { NavBarService.switchToSaleOrder(); }
    @FXML private void toStock() throws IOException { NavBarService.switchToStock(); }
    @FXML private void toReport() throws IOException { NavBarService.switchToReport(); }
}
