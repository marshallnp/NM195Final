package com.finalnm.controller;

import com.finalnm.model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DashboardController {

    @FXML
    private TableView<Customer> CustomerTable;
    @FXML
    private TableColumn<Customer, String> CustomerTableName;
    @FXML
    private TableColumn<Customer, String> CustomerTablePostal;

    public void initialize() {
        loadCustomerData();
    }

    private void loadCustomerData() {
        ObservableList<Customer> customers = FXCollections.observableArrayList();

        try (Connection conn = helper.JDBC.openConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT Customer_Name, Postal_Code FROM customers")) {

            while (resultSet.next()) {
                String name = resultSet.getString("Customer_Name");
                String postalCode = resultSet.getString("Postal_Code");
                customers.add(new Customer(name, postalCode));
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exception
        }

        CustomerTableName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        CustomerTablePostal.setCellValueFactory(cellData -> cellData.getValue().postalCodeProperty());

        CustomerTable.setItems(customers);
    }

    public void OnExitClick(ActionEvent actionEvent) {
        System.out.println("Exiting the application");
        System.exit(0);
    }
}
