package com.example.ecommerceapp;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class ProductList {
    private TableView<Product> productTable;
    private TableView<OrderdProduct> ordersProductTable;

    public VBox createTable(ObservableList<Product> data) {
        //Columns
        TableColumn id = new TableColumn("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name = new TableColumn("NAME");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price = new TableColumn("PRICE");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn description = new TableColumn("Product Description");
        description.setCellValueFactory(new PropertyValueFactory<>("description"));

        productTable = new TableView<>();
        productTable.getColumns().addAll(id, name, price,description);
        productTable.setItems(data);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); //Extra column remover

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.getChildren().addAll(productTable);

        return vBox;

    }

        public VBox getAllProducts(){
            ObservableList<Product> data = Product.getAllProducts();
            return createTable(data);
        }


        public VBox getSearchedProduct(String searchedProduct){
            ObservableList<Product> data = Product.getSearchedProduct(searchedProduct);
            return createTable(data);
        }



    public VBox createOrdersTable(ObservableList<OrderdProduct> data) {
        //Columns
        TableColumn name = new TableColumn("Product Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn quantity = new TableColumn("Quantity");
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn orderdate = new TableColumn("Order Date");
        orderdate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));

        TableColumn deliveryDate = new TableColumn("Delivery Day");
        deliveryDate.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));



        ordersProductTable = new TableView<>();
        ordersProductTable.getColumns().addAll(name, price,quantity,orderdate,deliveryDate);
        ordersProductTable.setItems(data);
        ordersProductTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); //Extra column remover

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.getChildren().addAll(ordersProductTable);

        return vBox;

    }

    public VBox getAllOrderdProducts(String User){

        ObservableList<OrderdProduct> data = OrderdProduct.getAllOrderedProducts(User);
        return createOrdersTable(data);
    }


        public Product getSelectedProduct(){

        return productTable.getSelectionModel().getSelectedItem();
        }

        public VBox getProductsInCart(ObservableList<Product> data){

        return createTable(data);
        }
}
