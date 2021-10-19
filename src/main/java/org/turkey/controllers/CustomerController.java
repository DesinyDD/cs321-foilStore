package org.turkey.controllers;

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
import org.turkey.models.Customer;
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
        customers = new ArrayList<>();
        Customer customer = new Customer(new BigInteger("1"),"Ford", "08xxxxxxxx","Bodin");
        customers.add(customer);
        customer = new Customer(new BigInteger("2"),"MIX", "08xxxxxxxx","KU");
        customers.add(customer);
        customer = new Customer(new BigInteger("3"),"Q", "08xxxxxxxx","Barn");
        customers.add(customer);
        setCustomerTable();
    }

    @FXML private void addCustomer() throws IOException {
        Stage createCustomerPage = new Stage();
        createCustomerPage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/createCustomer.fxml"));
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
