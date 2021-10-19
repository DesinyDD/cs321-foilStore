package org.turkey.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.turkey.models.Customer;

import java.math.BigInteger;
import java.util.ArrayList;

public class CreateCustomerController {
    private ArrayList<Customer> customers;
    private ObservableList list;
    @FXML private TableView<Customer> table;
    @FXML private TableColumn<Customer, String> name, phone, address;
    @FXML private TextField nameF, phoneF;
    @FXML private TextArea addressF;

    @FXML public void addCustomer(ActionEvent event){
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        if(!nameF.getText().trim().equals("") && !phoneF.getText().trim().equals("") && !addressF.getText().trim().equals("")){
            Customer customer = new Customer(new BigInteger("4"),nameF.getText(),phoneF.getText(),addressF.getText());
            customers.add(customer);
            setCustomerTable();
            stage.close();
        }else{
            System.out.println(1);
        }
    }

    public void setCustomerTable(){
        list = FXCollections.observableArrayList(customers);
        table.setItems(list);
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
    }

    public void setAddress(TableColumn<Customer, String> address) {this.address = address;}
    public void setCustomers(ArrayList<Customer> customers) {this.customers = customers;}
    public void setName(TableColumn<Customer, String> name) {this.name = name;}
    public void setTable(TableView<Customer> table) {this.table = table;}
    public void setPhone(TableColumn<Customer, String> phone) {this.phone = phone;}
}
