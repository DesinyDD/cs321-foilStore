package org.turkey.controllers.customer;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
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
import org.turkey.services.HTTPRequest.DBConnector;
import org.turkey.services.NavBarService;

import java.io.IOException;
import java.util.List;

public class CustomerController {
    @FXML private JFXButton addCustomerBtn;
    @FXML private TableView<Customer> table;
    @FXML private TableColumn<Customer, String> name, phone, address;
    private ObservableList list;
    private List<Customer> customers = new DBConnector().getCustomer();

    @FXML public void initialize(){
        table.setRowFactory( tv -> {
            TableRow<Customer> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty()) ) {
                    Customer rowData = row.getItem();
                    try {
                        editCustomer(rowData);
                    } catch (IOException e) {
                        // do nothing . . .
                    }
                }
            });
            return row;
        });
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                setCustomerTable();
            }
        });
    }

    @FXML private void addCustomer() throws IOException {
        Stage createCustomerPage = new Stage();
        createCustomerPage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/customer/createCustomer.fxml"));
        Scene scene = new Scene(loader.load());
        createCustomerPage.setScene(scene);
        CreateCustomerController cc = loader.getController();
        cc.setTable(table);
        cc.setName(name);
        cc.setPhone(phone);
        cc.setAddress(address);
        createCustomerPage.show();
    }

    @FXML private void editCustomer(Customer customer) throws IOException {
        Stage editCustomerPage = new Stage();
        editCustomerPage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/turkey/customer/editCustomer.fxml"));
        Scene scene = new Scene(loader.load());
        editCustomerPage.setScene(scene);
        editCustomerPage.setTitle("แก้ไขข้อมูลลูกค้า");
        editCustomerPage.setResizable(false);
        EditCustomerController ec = loader.getController();
        ec.setThisCustomer(customer);
        ec.setTable(table);
        ec.setAddressCol(this.address);
        ec.setNameCol(this.name);
        ec.setPhoneCol(this.phone);
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
