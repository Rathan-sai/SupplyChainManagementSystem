package com.example.supplychainmanagementsystem;

import javafx.beans.Observable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

//product class is used to define what are the details of the every products which are seen in tableview.
public class product {

    //here we initialized an id, price, name for the product with in the product tableview.
    private SimpleIntegerProperty Id;
    private SimpleIntegerProperty price;
    private SimpleStringProperty name;

    //and placed the getter and the setter method for all the datatype we are initialized as private int it.
    public int getPrice() {
        return price.get();
    }

    public SimpleIntegerProperty priceProperty() {
        return price;
    }

    public int getId() {
        return Id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return Id;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    //created a constructor for the product with the details of id, name, price.
    public product(int Id, String name, int price)
    {
        this.Id = new SimpleIntegerProperty(Id);
        this.name = new  SimpleStringProperty(name);
        this.price = new SimpleIntegerProperty(price);
    }

    //A getallproducts method which is a return of observablelist of product and this function is connected to the database for access the products in it.
    //ObservableList is a list that if there are changes, it will be automatically updated. List is a collection of ordered objects in the java.util.List package.
    // The objects in the list are called elements.
    public static ObservableList<product> getAllProducts()
    {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        ObservableList<product> productlist = FXCollections.observableArrayList();
        String selectProducts = "select * from productsd";
        try {
            ResultSet rs = dataBaseConnection.getQueryTable(selectProducts);
            while (rs.next()){
                productlist.add(new product(rs.getInt("product_ID"),
                rs.getString("name"), rs.getInt("price")));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return productlist;
    }

    //A getproductsbyname method which is a return of observablelist of product which we are searching when we enter a name and click search this function is connected to the database for access the products in it.
    public static ObservableList<product> getProductsByName(String productName)
    {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        ObservableList<product> productlist = FXCollections.observableArrayList();
        String selectProducts = String.format("select * from productsd WHERE lower(name) like '%%%s%%' ", productName.toLowerCase());
        try {
            ResultSet rs = dataBaseConnection.getQueryTable(selectProducts);
            while (rs.next()){
                productlist.add(new product(rs.getInt("product_ID"),
                rs.getString("name"), rs.getInt("price")));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return productlist;
    }
}
