package com.example.ecommerceapp;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderdProduct {
    private StringProperty name;
    private IntegerProperty price;
    private IntegerProperty quantity;
    private StringProperty orderDate;
    private StringProperty deliveryDate;

    public OrderdProduct(String name, int price, int quantity, LocalDateTime orderDate, String deliveryDate) {
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleIntegerProperty(price);
        this.quantity = new SimpleIntegerProperty(quantity);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.orderDate = new SimpleStringProperty(orderDate.format(formatter));
        this.deliveryDate = new SimpleStringProperty(deliveryDate);
    }

    public static ObservableList<OrderdProduct> getAllOrderedProducts(String User) {
        String selectAllProduct = "SELECT item_name, item_price, quantity, order_date, delivery_day FROM orders where customer_name = '" + User + "'";
        return fetchProductDataFromDB(selectAllProduct);
    }

    public static ObservableList<OrderdProduct> fetchProductDataFromDB(String query) {
        ObservableList<OrderdProduct> data = FXCollections.observableArrayList();
        DbConnection dbConnection = new DbConnection();
        try {
            ResultSet rs = dbConnection.getQueryTable(query);
            while (rs.next()) {
                String itemName = rs.getString("item_name");
                int itemPrice = rs.getInt("item_price");
                int quantity = rs.getInt("quantity");
                Timestamp orderTimestamp = rs.getTimestamp("order_date");
                String deliveryDay = rs.getString("delivery_day");

                OrderdProduct orderdProduct = new OrderdProduct(itemName, itemPrice, quantity, orderTimestamp.toLocalDateTime(), deliveryDay);
                data.add(orderdProduct);
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public StringProperty nameProperty() {
        return name;
    }

    public IntegerProperty priceProperty() {
        return price;
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public StringProperty orderDateProperty() {
        return orderDate;
    }

    public StringProperty deliveryDateProperty() {
        return deliveryDate;
    }
    }


