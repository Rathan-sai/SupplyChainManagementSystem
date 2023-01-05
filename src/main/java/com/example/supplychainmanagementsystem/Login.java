package com.example.supplychainmanagementsystem;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.ResultSet;

//login class which is used the access of logins and their respective password in the sql and determined with the both are match are not.
public class Login {

    //Here we used a messagedigest class which is used to secure our password as we see here we use a algorithm SHA-256 which is used to
    //conversion of our password and make secure.
    private static byte[] getSHA(String input){
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            return messageDigest.digest(input.getBytes(StandardCharsets.UTF_8));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //Here in getEncryptedpassword is used to conversion of our password from int to hexadecimal to security of the password.
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

    //In customerlogin function use wrote the sql statement to get the login email and password and checking whether they are present are not.
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
