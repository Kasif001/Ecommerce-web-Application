package com.example.ecommerceapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.regex.Pattern;

public class UserInterface {

    GridPane loginPage;
    GridPane newAccPage;
    HBox headerBar;
    HBox footerBar;
    VBox body;


    Label welcomeLable;
    Button signInButton;
    Customer loggedInCustomer;
    Button placeOrderButton;

    ProductList productList = new ProductList();
    ObservableList<Product> itemsInCart = FXCollections.observableArrayList();
    VBox productPage;
    VBox searchedProductPage;
    VBox orderdProductPage;
    public BorderPane createContent(){
        BorderPane root = new BorderPane();
        root.setPrefSize(1000,700);


        root.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        root.setTop(headerBar);
        body = new VBox();
        body.setPadding(new Insets(10));
        body.setAlignment(Pos.CENTER);
        root.setCenter(body);

        productPage = productList.getAllProducts();
        body.getChildren().add(productPage);



        root.setBottom(footerBar);

        return root;
    }
    UserInterface(){
        createLoginPage();
        createNewAccPage();
        createHeaderBar();
        createFooterBar();
    }

    private void createNewAccPage(){
        Text yourName = new Text("Your Name");

        TextField yourNameInput = new TextField();
        yourNameInput.setPromptText("First & Last Name");


        Text email = new Text("Email");
        Text password2 = new Text("Create Password");

        Text mobileNo = new Text("Mobile No.");
        TextField moblieNoInput = new TextField();
        moblieNoInput.setPromptText("At least 10 digit");

        TextField emailInput = new TextField();
        emailInput.setPromptText("Enter email");
        PasswordField passwordInput2 = new PasswordField();
        passwordInput2.setPromptText("At least 6 digit");

        Text addresstext = new Text("Address");
        TextField addressInput = new TextField();
        addressInput.setPromptText("House No, Area, City, ZIPCODE");

        Button continueButton = new Button("Continue");
        continueButton.setStyle("-fx-background-color:  yellow; ");


        CheckBox termConditions = new CheckBox();
        termConditions.setSelected(true);
        Text termConText = new Text("I agree to the Terms of Service & Privacy Policy");

        newAccPage = new GridPane();
        newAccPage.setAlignment(Pos.CENTER);
        newAccPage.setHgap(10);
        newAccPage.setVgap(10);
        newAccPage.add(yourName,0,1);
        newAccPage.add(yourNameInput,1,1);
        newAccPage.add(email,0,2);
        newAccPage.add(emailInput,1,2);
        newAccPage.add(mobileNo,0,3);
        newAccPage.add(moblieNoInput,1,3);
        newAccPage.add(password2,0,4);
        newAccPage.add(passwordInput2,1,4);
        newAccPage.add(addresstext,0,5);
        newAccPage.add(addressInput,1,5);
        newAccPage.add(termConditions,0,6);
        newAccPage.add(termConText,1,6);
        newAccPage.add(continueButton,1,8);

        continueButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
               String getInputName = yourNameInput.getText();
               String getInputEmail = emailInput.getText();
               String getInputMoblie = moblieNoInput.getText();
               String getInputPass = passwordInput2.getText();
               String getInputAddress = addressInput.getText();

               if(!getInputAddress.isEmpty() && !getInputName.trim().isEmpty() && !getInputEmail.trim().isEmpty() && !getInputMoblie.trim().isEmpty() && !getInputPass.trim().isEmpty() && getInputPass.length() > 6 && EMAIL_PATTERN.matcher(getInputEmail).matches()){
                   SignIn_NewCustomer signIn = new SignIn_NewCustomer();
                   if(signIn.customerSignIn(getInputName, getInputEmail, getInputMoblie, getInputPass,getInputAddress)){
                       body.getChildren().clear();
                       body.getChildren().add(loginPage);
                   }

               }else{
                   continueButton.setText("Fill all Details Correctly and Continue");
               }



            }
        });


    }

    public void createLoginPage(){
        Text userNameText = new Text("User Name");
        Text passwordText = new Text("Password");

        TextField userNameInput = new TextField("");

        userNameInput.setPromptText("Enter email");
        PasswordField passwordInput = new PasswordField();
        passwordInput.setText("");
        passwordInput.setPromptText("Enter Password");

        Button login = new Button("Login");
        login.setStyle("-fx-background-color:  white; ");

        Button createNewAcc = new Button("Create New Account");
        createNewAcc.setStyle("-fx-background-color: yellow; ");
        createNewAcc.setPrefSize(200,10);

        Label messageLable = new Label("Hi");
        Button AccCreatedSucc = new Button("Account Created Successfully");
        AccCreatedSucc.setPrefSize(200,20);
        AccCreatedSucc.setStyle("-fx-background-color: yellow; ");
        AccCreatedSucc.setPrefSize(200,10);
        AccCreatedSucc.setVisible(false);


        loginPage = new GridPane();


        loginPage.setAlignment(Pos.CENTER);
        loginPage.setHgap(10);
        loginPage.setVgap(10);
        loginPage.add(userNameText,0,0);
        loginPage.add(userNameInput,1,0);
        loginPage.add(passwordText,0,1);
        loginPage.add(passwordInput,1,1);
        loginPage.add(messageLable,0,2);

        loginPage.add(login,1,2);
        loginPage.add(createNewAcc,1,4);
        loginPage.add(AccCreatedSucc,1,4);

        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String name = userNameInput.getText();
                String pass = passwordInput.getText();
                Login login = new Login();
                loggedInCustomer = login.customerLogin(name, pass);

                createNewAcc.setVisible(true);
                AccCreatedSucc.setVisible(false);

                if(loggedInCustomer != null){
                    messageLable.setText("Welcome "+ loggedInCustomer.getName());
                    welcomeLable.setText( "Welcome " + loggedInCustomer.getName());

                    String UniversalName = loggedInCustomer.getName();

                    body.getChildren().clear();
                    body.getChildren().add(productPage);
                    footerBar.setVisible(true);


                }else {

                    messageLable.setText("Login Failed !! please give correct user name and password.");
                }
            }
        });

        createNewAcc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                body.getChildren().clear();
                body.getChildren().add(newAccPage);
                footerBar.setVisible(false);
                createNewAcc.setVisible(false);
                AccCreatedSucc.setVisible(true);



            }
        });


    }

    private void createHeaderBar(){

        Button homeButton = new Button();
        homeButton.setStyle("-fx-background-color: black; ");
        Image imageHome = new Image("C:\\Users\\kasif\\OneDrive\\Desktop\\javaFrame(Swing)\\E-CommerceApp\\src\\main\\java\\home5.jpg");
        ImageView imageViewHome = new ImageView();
        imageViewHome.setImage(imageHome);
        imageViewHome.setFitWidth(120);
        imageViewHome.setFitHeight(24);
        homeButton.setGraphic(imageViewHome);

        TextField search = new TextField();
        search.setPromptText("Search Here");
        search.setPrefWidth(500);
        search.setPrefHeight(30);

        Button searchButton = new Button();
        searchButton.setStyle("-fx-background-color: black ");
        Image image = new Image("C:\\Users\\kasif\\OneDrive\\Desktop\\javaFrame(Swing)\\E-CommerceApp\\src\\main\\java\\searchIcon5.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(35);
        imageView.setFitHeight(23);
        searchButton.setGraphic(imageView);

        Button cartButton = new Button();
        cartButton.setStyle("-fx-background-color: black; ");
        cartButton.setPrefSize(60,30);
        Image imageCart = new Image("C:\\Users\\kasif\\OneDrive\\Desktop\\javaFrame(Swing)\\E-CommerceApp\\src\\main\\java\\cartPhoto.png");
        ImageView imageViewCart = new ImageView();
        imageViewCart.setImage(imageCart);
        imageViewCart.setFitWidth(60);
        imageViewCart.setFitHeight(25);
        cartButton.setGraphic(imageViewCart);

        Button ordersButton = new Button("Orders");
        Font font2 = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 11);
        ordersButton.setFont(font2);
        ordersButton.setStyle("-fx-background-color: black; ");
        ordersButton.setTextFill(Color.WHITE);


        signInButton = new Button("Sign In");
        Font font = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 11);
        signInButton.setFont(font);
        signInButton.setStyle("-fx-background-color: black; ");
        signInButton.setTextFill(Color.WHITE);
        signInButton.setPrefSize(60,30);

        placeOrderButton = new Button("Place Order");
        placeOrderButton.setPrefSize(150,25);
        placeOrderButton.setStyle("-fx-background-color: Yellow; ");

        welcomeLable = new Label();
        Font font3 = Font.font("Verdana", FontWeight.BOLD,11);
        welcomeLable.setFont(font);
        welcomeLable.setTextFill(Color.WHITE);


        headerBar = new HBox();
        headerBar.setStyle("-fx-background-color: black; ");
        headerBar.setPadding(new Insets(10));
        headerBar.setAlignment(Pos.CENTER);
        headerBar.setSpacing(10);
        headerBar.getChildren().addAll(homeButton, search, searchButton,ordersButton,cartButton,welcomeLable,signInButton);

        signInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                body.getChildren().add(loginPage);
                headerBar.getChildren().remove(signInButton);
                footerBar.setVisible(false);
            }
        });

        cartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();

                VBox productPageforCart = productList.getProductsInCart(itemsInCart);
                productPageforCart.setSpacing(10);
                productPageforCart.getChildren().add(placeOrderButton);

                productPageforCart.setAlignment(Pos.CENTER);
                body.getChildren().add(productPageforCart);
                footerBar.setVisible(false);
            }
        });

        placeOrderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //need list of products and a customer
                if(itemsInCart == null){
                    //please select a product to place a order
                    showDialoge("Please add some products in the cart to place order");
                    return;
                }
                if(loggedInCustomer == null){
                    showDialoge("Please Login First To Place Order");
                    return;
                }

                int count = Orders.placeMultipleOrder(loggedInCustomer,itemsInCart);
                if(count != 0){
                    showDialoge("Order For "+count+ " Products Placed Successfull");
                }else {
                    showDialoge("Order Failed");
                }


            }
        });

        homeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                productPage = productList.getAllProducts();
                body.getChildren().add(productPage);
                footerBar.setVisible(true);
                search.clear();
                if(loggedInCustomer == null && headerBar.getChildren().indexOf(signInButton) == -1){
                    headerBar.getChildren().add(signInButton);
                }
            }
        });

        ordersButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(loggedInCustomer == null){
                    showDialoge("Please Login First To View Your Orders");
                    return;
                }
                String User = loggedInCustomer.getName();
                body.getChildren().clear();
                orderdProductPage = productList.getAllOrderdProducts(User);
                body.getChildren().add(orderdProductPage);
                footerBar.setVisible(false);
            }
        });

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String getProductName = search.getText();
                if(!getProductName.isEmpty()){
                    body.getChildren().clear();
                    searchedProductPage = productList.getSearchedProduct(getProductName);
                    body.getChildren().add(searchedProductPage);


                }
            }
        });
    }


    private void createFooterBar(){

        Button buyNowButton = new Button("Buy Now");
        buyNowButton.setPrefSize(200,25);
        buyNowButton.setStyle("-fx-background-color: yellow; ");


        Button addToCartButton = new Button("Add to Cart");
        addToCartButton.setPrefSize(200,25);
        addToCartButton.setStyle("-fx-background-color: yellow; ");


        footerBar = new HBox();
        footerBar.setPadding(new Insets(30));
        footerBar.setAlignment(Pos.CENTER);
        footerBar.setSpacing(20);
        footerBar.getChildren().addAll(addToCartButton,buyNowButton);

        buyNowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product selectedProduct = productList.getSelectedProduct();
                if(selectedProduct == null){
                    //please select a product to place a order
                    showDialoge("Please select a product before placeing an order");
                    return;
                }
                if(loggedInCustomer == null){
                    showDialoge("Please Login First To Buy An Product");
                    return;
                }

                if(!itemsInCart.isEmpty()){
                    showDialoge(" Order Failed!! You Have Items in Cart Please Continue From There.");
                    return;
                }



                boolean isPlaced = Orders.placeSingleOrder(loggedInCustomer,selectedProduct);
                if(isPlaced){
                    showDialoge("Your Order Placed Successfully");
                }


            }
        });

        addToCartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                Product product = productList.getSelectedProduct();
                if(product == null){
                    showDialoge("Please select a product before adding in Cart");
                    return;
                }
                itemsInCart.add(product);
                showDialoge("Selected Product Add Successfully");
            }

        });


    }
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$"
    );

    private void showDialoge(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


