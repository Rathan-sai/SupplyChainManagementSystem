package com.example.supplychainmanagementsystem;

import javafx.beans.Observable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class product {
    private SimpleIntegerProperty Id;
    private SimpleIntegerProperty price;
    private SimpleStringProperty name;

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

    public product(int Id, String name, int price)
    {
        this.Id = new SimpleIntegerProperty(Id);
        this.name = new  SimpleStringProperty(name);
        this.price = new SimpleIntegerProperty(price);
    }

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
