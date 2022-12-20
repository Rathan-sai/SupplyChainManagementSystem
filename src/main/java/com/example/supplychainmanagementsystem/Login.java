package com.example.supplychainmanagementsystem;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.ResultSet;

public class Login {

    private static byte[] getSHA(String input){
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            return messageDigest.digest(input.getBytes(StandardCharsets.UTF_8));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private String getEncryptedPassword(String password){
        String encryptedPassword = "";
        try{
            BigInteger number = new BigInteger(1, getSHA(password));
            StringBuilder hexstring = new StringBuilder(number.toString(16));
            return hexstring.toString();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    public boolean customerLogin(String email, String password) {
        String query = String.format("SELECT * FROM customer WHERE email = '%s' AND password = '%s'", email, password);
        try{

            DataBaseConnection dbCon = new DataBaseConnection();
            ResultSet rs = dbCon.getQueryTable(query);
            if(rs != null && rs.next())
            {
                return true;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

//    public static void main(String[] args) {
//        Login login = new Login();
//        System.out.println(login.customerLogin("rathansai@gmail.com", "abc123"));
//    }

    public static void main(String[] args) {
        Login login = new Login();
        System.out.println(login.getEncryptedPassword("1abc123"));
    }
}
