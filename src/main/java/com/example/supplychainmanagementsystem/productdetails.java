package com.example.supplychainmanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import org.w3c.dom.events.MouseEvent;

//A productdetails class which is used to display and selecting of the products.
public class productdetails {
    //A tableview inbuilt class which is used to display our table of products with the custom columns.
    public TableView<product> productTable;

    //A pane to consists of all products that are in the tableview and displays.
    public Pane getAllProducts(){
        //Tablecolumn method is used for adding the names to the column as per our product details.
        //PropertyvalueFactory is a convenience implementation of the Callback interface, designed specifically for use within the TableColumn cell value factory.
//        TableColumn Id = new TableColumn("ID");
//        Id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        TableColumn name = new TableColumn("Products");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
//        TableColumn color = new TableColumn("color");
//        color.setCellValueFactory(new PropertyValueFactory<>("color"));
        TableColumn memory = new TableColumn("memory");
        memory.setCellValueFactory(new PropertyValueFactory<>("memory"));
        TableColumn ram = new TableColumn("ram");
        ram.setCellValueFactory(new PropertyValueFactory<>("ram"));
//        TableColumn range = new TableColumn("Range");
//        range.setCellValueFactory(new PropertyValueFactory<>("range"));

        TableColumn tc = new TableColumn<>();
        tc.setStyle("-fx-background-color:#008000");

//we can use a manual adding of product in the observableArrayList by using Fxcollections.
//        ObservableList<product> data = FXCollections.observableArrayList();
//        data.add(new product(1,"lenova ai",10000));
//        data.add(new product(2,"lenova gt",11200));
//        data.add(new product(3,"lenova max",14500));
//        data.add(new product(4,"lenova xt",15500));

        ObservableList<product> details = product.getAllProducts();

        //initialized the tableview as product table with some columns as our requirements.
        productTable = new TableView<>();
        productTable.setStyle("-fx-background-color:#0000FF");
        productTable.setItems(details);
//        productTable.getColumns().addAll(Id, name, price, memory, ram);
        productTable.getColumns().addAll(name, price, memory, ram);
        productTable.setMinSize(SupplyChain.width-300, SupplyChain.height);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //pane to store the UIs we created and displayed by add to the table(root).
        Pane table = new Pane();
        table.setStyle("-fx-background-color:#008000");
        table.setMinSize(SupplyChain.width-300, SupplyChain.height);
        table.getChildren().addAll(productTable);

        return table;
    }

    //this getproductsbyname is used for to display the products which are we searching.
    public Pane getProductsByName(String productName){
//        TableColumn Id = new TableColumn("ID");
//        Id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
//        TableColumn color = new TableColumn("color");
//        color.setCellValueFactory(new PropertyValueFactory<>("color"));
        TableColumn memory = new TableColumn("memory");
        memory.setCellValueFactory(new PropertyValueFactory<>("memory"));
        TableColumn ram = new TableColumn("ram");
        ram.setCellValueFactory(new PropertyValueFactory<>("ram"));

        ObservableList<product> details = product.getProductsByName(productName);

        productTable = new TableView<>();
        productTable.setItems(details);
//        productTable.getColumns().addAll(Id, name, price, memory, ram);
        productTable.getColumns().addAll(name, price, memory, ram);
        productTable.setMinSize(SupplyChain.width-300, SupplyChain.height);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Pane table = new Pane();
        table.setStyle("-fx-background-color:#82273C");
        table.setMinSize(SupplyChain.width, SupplyChain.height);
        table.getChildren().addAll(productTable);

        return table;
    }
    ObservableList<product> cartdetails = FXCollections.observableArrayList();
    public void cartproducts(product addproduct){
        cartdetails.add(addproduct);
    }
    public Pane mycartproducts(){
//        TableColumn Id = new TableColumn("ID");
//        Id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
//        TableColumn color = new TableColumn("color");
//        color.setCellValueFactory(new PropertyValueFactory<>("color"));
        TableColumn memory = new TableColumn("memory");
        memory.setCellValueFactory(new PropertyValueFactory<>("memory"));
        TableColumn ram = new TableColumn("ram");
        ram.setCellValueFactory(new PropertyValueFactory<>("ram"));

        productTable = new TableView<>();
        productTable.setItems(cartdetails);
//        productTable.getColumns().addAll(Id, name, price, memory, ram);
        productTable.getColumns().addAll(name, price, memory, ram);
        productTable.setMinSize(SupplyChain.width, SupplyChain.height);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Pane table = new Pane();
        table.setStyle("-fx-background-color:#40E0D0");
        table.setMinSize(SupplyChain.width, SupplyChain.height);
        table.getChildren().addAll(productTable);

        return table;
    }

    //getselectproduct function is used to select a product to buy by clicking on that product which are in the table.
    public product getSelectProduct(){
        if(productTable.getSelectionModel().isSelected(2)){

        }
        try {
            product selectProduct = productTable.getSelectionModel().getSelectedItem();
            SupplyChain.selectimage = true;
            return selectProduct;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
