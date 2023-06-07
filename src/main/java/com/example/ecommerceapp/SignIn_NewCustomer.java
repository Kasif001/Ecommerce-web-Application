package com.example.ecommerceapp;

public class SignIn_NewCustomer {
        public boolean customerSignIn(String name,String email,String moblie,String password3,String Address){
            String signInQuery = "insert into customer(name, email, mobile, password,address) values('"+name+"','"+email+"','"+moblie+"','"+password3+"','"+Address+"')";
            DbConnection conn = new DbConnection();
            int isDataInserted = conn.updateDatabase(signInQuery);
            if(isDataInserted > 0) return true;
            return false;
        }
}
