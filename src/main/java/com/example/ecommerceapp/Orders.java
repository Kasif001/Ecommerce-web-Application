package com.example.ecommerceapp;

import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class Orders {
    public static int placeMultipleOrder(Customer customer, ObservableList<Product> productList){
        String groupOrderId = "select max(group_order_id) +1 id from orders";
        DbConnection dbConnection = new DbConnection();
        try{
            ResultSet rs = dbConnection.getQueryTable(groupOrderId);
            int count = 0;
            if(rs.next()){
                for(Product product: productList ){
                String placeOrder = "INSERT INTO orders(group_order_id, customer_id, product_id, item_name,customer_name,item_price) VALUES ("+rs.getInt("id")+","+customer.getId()+", "+product.getId()+",'"+product.getName()+"','"+customer.getName()+"',"+product.getPrice()+")";
                count+=dbConnection.updateDatabase(placeOrder);
                }
                return  count;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }


    public static boolean placeSingleOrder(Customer customer, Product product){
        DbConnection conn = new DbConnection();
        String groupOrderId = "select max(group_order_id) +1 id from orders";

        try{
            ResultSet rs = conn.getQueryTable(groupOrderId);
            if(rs.next()){
                String placeOrder = "INSERT INTO orders(group_order_id,customer_id, product_id, item_name,customer_name,item_price) VALUES ("+rs.getInt("id")+","+customer.getId()+", "+product.getId()+",'"+product.getName()+"','"+customer.getName()+"',"+product.getPrice()+")";
//                int isDataInserted = conn.updateDatabase(placeOrder);
//                if(isDataInserted > 0) return true;
                return conn.updateDatabase(placeOrder)!=0;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
