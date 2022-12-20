package com.example.supplychainmanagementsystem;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

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
}
