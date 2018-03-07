package io.muic.ooc.webapp.service;

import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtil {
    private static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/user?useSSL=false", "root", "twpwbq71");
            return con;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String[] getUsername(String username, String password) {
        try {
            Connection con = getConnection();
            String sql = "select * from USERINF where UserName=?";// and Password=?";
            PreparedStatement ps = null;
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            //ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            //PrintWriter out = response.getWriter();

            String dbName = null;
            String dbPassword = null;
            while (rs.next()) {
                dbName = rs.getString(1);
                dbPassword = rs.getString(2);
            }
            //System.out.println(dbName+" "+dbPassword);
            if(dbName != null &&dbPassword != null){
                return new String[]{dbName, dbPassword};
            }
            else{
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static boolean addUser(String userName,String password,String name,String email,String lastName){
        try {
            Connection con = getConnection();
            String sql = "insert into USERINF(UserName,Password,Name,Email,LastName) values(?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, Encryption.encryptPassword(password));
            ps.setString(3, name);
            ps.setString(4, email);
            ps.setString(5, lastName);

            ps.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean deleteUser(String userName,String currentUser){
        try{
            System.out.println(userName+" "+currentUser);
            if(userName.equals(currentUser)){
                return false;
            }
            Connection con = getConnection();
            String sql = "Delete FROM USERINF WHERE UserName = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,userName);
            ps.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean editUser(String userName,String password,String name,String email,String lastName){
        try{
            Connection con = getConnection();
            String sql = "UPDATE USERINF SET Password = ?, Name = ?,Email = ?, LastName = ? WHERE UserName = ?;";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(5,userName);
            ps.setString(1,password);
            ps.setString(2,name);
            ps.setString(3,lastName);
            ps.setString(4,email);
            ps.executeUpdate();

            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<User> getUser(){
        try{
            Connection con = getConnection();
            List<User> userList = new ArrayList<>();

            String sql = "select * from USERINF";
            PreparedStatement ps = null;
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                userList.add(new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
            }
            return userList;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
