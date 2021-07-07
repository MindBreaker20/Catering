package database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Dish;
import models.Stock;
import models.User;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class mysqlconnect {
    Connection conn = null;
    public static Connection ConnectDb(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/catering","root","");
            return conn;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    //Populating Inventory tabel with gathered data from database Inventory
    public static ObservableList<Stock> getDataStock(){
        Connection conn = ConnectDb();
        ObservableList<Stock> list = FXCollections.observableArrayList();
        try{
            PreparedStatement ps = conn.prepareStatement("select * from inventory");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new Stock(Integer.parseInt(rs.getString("id")), rs.getString("name"), rs.getString("type"), Integer.parseInt(rs.getString("quantity"))));
            }
        }catch(Exception e){

        }
        return list;
    }

    //Populating Menu tabel with gathered data from database menu
    public static ObservableList<Dish> getDataDish(){
        Connection conn = ConnectDb();
        ObservableList<Dish> list = FXCollections.observableArrayList();
        try{
            PreparedStatement ps = conn.prepareStatement("select * from menu");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new Dish(Integer.parseInt(rs.getString("id")), rs.getString("name"), rs.getString("type"), Integer.parseInt(rs.getString("price"))));
            }
        }catch(Exception e){

        }
        return list;
    }

    //Populating Users tabel with gathered data from database users
    public static ObservableList<User> getDataUser(){
        Connection conn = ConnectDb();
        ObservableList<User> list = FXCollections.observableArrayList();
        try{
            PreparedStatement ps = conn.prepareStatement("select * from users");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new User(Integer.parseInt(rs.getString("id")), rs.getString("firstname"), rs.getString("lastname"), Integer.parseInt(rs.getString("phone")), rs.getString("password")));
            }
        }catch(Exception e){

        }
        return list;
    }
    //Populating Your Order table with chosen data from table Menu
    public static ObservableList<Dish> getDataYourOrder(){
        Connection conn = ConnectDb();
        ObservableList<Dish> list = FXCollections.observableArrayList();
        try{
            PreparedStatement ps = conn.prepareStatement("select * from yourorder");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new Dish(Integer.parseInt(rs.getString("id")), rs.getString("name"), rs.getString("type"), Integer.parseInt(rs.getString("price"))));
            }
        }catch(Exception e){

        }
        return list;
    }

}
