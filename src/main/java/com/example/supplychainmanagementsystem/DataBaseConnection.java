package com.example.supplychainmanagementsystem;
import java.sql.*;

public class DataBaseConnection {
    private static final String databaseUrl = "jdbc:mysql://localhost:3306/supply_chain_dec";
    private static final String user = "root";
    private static final String password = "Rathansai@ece3";

    public Statement getStatement(){
        Statement statement = null;
        Connection conn;
        try{
            conn = DriverManager.getConnection(databaseUrl, user, password);
            statement = conn.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
        return statement;
    }

    public ResultSet getQueryTable(String query){
        Statement statement = getStatement();
        try{
            return statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        ResultSet rs = dataBaseConnection.getQueryTable("SELECT email, first_name FROM CUSTOMER");
        try{
            while(rs.next()){
                System.out.println(rs.getString("email")+" "+rs.getString("first_name"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
