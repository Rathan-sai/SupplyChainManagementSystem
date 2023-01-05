package com.example.supplychainmanagementsystem;
import java.sql.*;

//A databaseconnection class which is used to connect the MySQL workbench.
public class DataBaseConnection {

    //here we used some final string of our mysql workbench databaseurl, user, password which used as credentials to access our created database.
    private static final String databaseUrl = "jdbc:mysql://localhost:3306/supply_chain_dec";
    private static final String user = "root";
    private static final String password = "Rathansai@ece3";

    //The statement interface is used to create SQL basic statements in Java it provides methods to execute queries with the database.
    //Here the conn.createStatement() is connection between the statement and the sql.
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

    //The resultSet is a fucntion which is defined for executing the sql statement in the workbench.
    public ResultSet getQueryTable(String query){
        Statement statement = getStatement();
        try{
            return statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //the Exexuteupdatequery statement is used to update the query.
    public int exexuteupdatequery(String query){
        Statement statement = getStatement();
        try{
            return statement.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

//    public static void main(String[] args) {
//        DataBaseConnection dataBaseConnection = new DataBaseConnection();
//        ResultSet rs = dataBaseConnection.getQueryTable("SELECT email, first_name FROM CUSTOMER");
//        try{
//            while(rs.next()){
//                System.out.println(rs.getString("email")+" "+rs.getString("first_name"));
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
}
