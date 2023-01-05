package com.example.supplychainmanagementsystem;

import javafx.scene.Node;

//this addressdetails class is used to update the address_details table in the MySQL workbench withe customer address details where to place the order.
public class addressDetails extends Node {
    public static boolean placeOrder(String door_no, String street, String city, String district, String state) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        String query = String.format("INSERT INTO address_details(door_no, street, city, district, state) values ('%s', '%s', '%s', '%s', '%s')", door_no, state, city, district, state);
        int rowCount = 0;
        try {
            rowCount = dataBaseConnection.exexuteupdatequery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rowCount != 0;
    }

    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }
}
