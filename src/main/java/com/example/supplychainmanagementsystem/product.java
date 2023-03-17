package com.example.supplychainmanagementsystem;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;

//product class is used to define what are the details of the every products which are seen in tableview.
public class product {

    //here we initialized an id, price, name for the product with in the product tableview.
    private SimpleIntegerProperty Id;
    private SimpleIntegerProperty price;
    private SimpleStringProperty name;
    private SimpleStringProperty colour;
    private SimpleStringProperty memory;
    private SimpleStringProperty ram;

    static int id=1;
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

    public String getColour() {
        return colour.get();
    }

    public SimpleStringProperty colourProperty() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour.set(colour);
    }

    public String getMemory() {
        return memory.get();
    }

    public SimpleStringProperty memoryProperty() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory.set(memory);
    }

    public String getRam() {
        return ram.get();
    }

    public SimpleStringProperty ramProperty() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram.set(ram);
    }

    //created a constructor for the product with the details of id, name, price.
    public product(int Id, String name, int price, String colour, String memory, String ram)
    {
        this.Id = new SimpleIntegerProperty(Id);
        this.name = new  SimpleStringProperty(name);
        this.price = new SimpleIntegerProperty(price);
        this.colour = new SimpleStringProperty(colour);
        this.memory = new SimpleStringProperty(memory);
        this.ram = new SimpleStringProperty(ram);
    }

    //A getallproducts method which is a return of observablelist of product and this function is connected to the database for access the products in it.
    //ObservableList is a list that if there are changes, it will be automatically updated. List is a collection of ordered objects in the java.util.List package.
    // The objects in the list are called elements.
    public static ObservableList<product> getAllProducts()
    {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        ObservableList<product> productlist = FXCollections.observableArrayList();
        String selectProducts = "select * from products";
        try {
            ResultSet rs = dataBaseConnection.getQueryTable(selectProducts);
            while (rs.next()){
                productlist.add(new product(rs.getInt("product_ID"),
                rs.getString("name"), rs.getInt("price"), rs.getString("colour"), rs.getString("memory"), rs.getString("ram")));
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
        String selectProducts = String.format("select * from products WHERE lower(name) like '%%%s%%' ", productName.toLowerCase());
        try {
            ResultSet rs = dataBaseConnection.getQueryTable(selectProducts);
            while (rs.next()){
                productlist.add(new product(rs.getInt("product_ID"),
                rs.getString("name"), rs.getInt("price"), rs.getString("colour"), rs.getString("memory"), rs.getString("ram")));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return productlist;
    }

    public static String getImage() {
        productdetails productdetails = new productdetails();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();

//        SupplyChain.selectimage = true;
//        int id = productdetails.getSelectProduct().getId();
        String selectProducts = String.format("select image from products WHERE product_id ="+ id);
//        Blob img = null;
        Image image = null;
        String img = null;
        try {
            ResultSet rs = dataBaseConnection.getQueryTable(selectProducts);
            while (rs.next()) {
                img = rs.getString("image");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return img;
    }
}
