package com.example.supplychainmanagementsystem;

//In the signup method we are inserting the new user values to the customer table which is created in the database.
public class SignUp {
    private int i = 10;
    public boolean customerSignUp(String email, String password, String first_name, String last_name, float mobile_no) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        int customer_id = i;
        String query = String.format("INSERT INTO customer(customer_id, email, password, first_name, last_name, mobile) values (%s, '%s', '%s','%s','%s',%s)", customer_id, email, password, first_name, last_name, mobile_no);
        i++;
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
