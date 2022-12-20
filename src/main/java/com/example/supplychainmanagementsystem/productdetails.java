package com.example.supplychainmanagementsystem;

import javafx.beans.Observable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class productdetails {

    public TableView<product> productTable;

    public Pane getAllProducts(){
        TableColumn Id = new TableColumn("ID");
        Id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        ObservableList<product> data = FXCollections.observableArrayList();
        data.add(new product(1,"lenova ai",10000));
        data.add(new product(2,"lenova gt",11200));
        data.add(new product(3,"lenova max",14500));
        data.add(new product(4,"lenova xt",15500));

        productTable = new TableView<>();
        productTable.setItems(data);
        productTable.getColumns().addAll(Id, name, price);

        Pane table = new Pane();
        table.getChildren().addAll(productTable);

        return table;
    }
}
