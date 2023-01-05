package com.example.supplychainmanagementsystem;

//The order class is used to check the order is confirmed or not here after ordering the product we are updating the table of orders by a customer id and email.
public class order {
    public static boolean placeOrder(String customerEmail, product product){
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        String query = String.format("INSERT INTO orders (customer_id, product_id) VALUES ((SELECT customer_id FROM customer WHERE email = '%s'), %s)", customerEmail, product.getId());
        int rowCount = 0;
        try {
            rowCount = dataBaseConnection.exexuteupdatequery(query);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return rowCount != 0;
    }
}
