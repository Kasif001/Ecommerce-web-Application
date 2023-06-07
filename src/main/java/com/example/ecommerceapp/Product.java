package com.example.ecommerceapp;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class Product {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleIntegerProperty price;
    private SimpleStringProperty description;


    public Product(int id, String name, int price,String description) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleIntegerProperty(price);
        this.description = new SimpleStringProperty(description);
    }

    public static ObservableList<Product> getAllProducts(){
        String selectAllProduct = "SELECT id, name, price,description FROM product";
        return fetchProductDataFromDB(selectAllProduct);
    }
    public static ObservableList<Product> fetchProductDataFromDB(String query){
            ObservableList<Product> data = FXCollections.observableArrayList();
            DbConnection dbConnection = new DbConnection();
            try{
                ResultSet rs = dbConnection.getQueryTable(query);
                while (rs.next()){
                    Product product = new Product(rs.getInt("id"), rs.getString("name"), rs.getInt("price"),rs.getString("description"));
                    data.add(product);
                }
                return data;
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
    }

    public static ObservableList<Product> getSearchedProduct(String productName){
        String selectAllProduct = "SELECT id, name, price,description FROM product where name = '"+productName+"' OR price = '"+productName+"'";
        return fetchProductDataFromDB(selectAllProduct);
    }


    public int getId() {
        return id.get();
    }
    public String getName() {
        return name.get();
    }

    public double getPrice() {
        return price.get();
    }

    public String getDescription() {
        return description.get();
    }
}
