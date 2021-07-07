package controllers;

import database.mysqlconnect;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.User;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Users implements Initializable {

    //Table of stocks
    @FXML
    private TableView<User> tableUser;
    @FXML
    private TableColumn<User, Integer> colId;
    @FXML
    private TableColumn<User, String> colFirstName;
    @FXML
    private TableColumn<User, String> colLastName;
    @FXML
    private TableColumn<User, Integer> colPhone;
    @FXML
    private TableColumn<User, String> colPassword;

    ObservableList<User> listUser;
    int index = -1;
    Connection conn;
    ResultSet rs = null;
    PreparedStatement pst = null;

    //Inventory elements controllers
    @FXML
    private ImageView deleteUser;
    @FXML
    private ImageView refreshUser;


    @FXML
    public void refresh(){
        colId.setCellValueFactory(new PropertyValueFactory<User,Integer>("id"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<User,String>("firstname"));
        colLastName.setCellValueFactory(new PropertyValueFactory<User,String>("lastname"));
        colPhone.setCellValueFactory(new PropertyValueFactory<User,Integer>("phone"));
        colPassword.setCellValueFactory(new PropertyValueFactory<User,String>("password"));

        listUser = mysqlconnect.getDataUser();
        tableUser.setItems(listUser);
    }
    @FXML
    private AnchorPane parent;

    @FXML
    public void deleteUser(MouseEvent event){
        index = tableUser.getSelectionModel().getSelectedIndex();
        conn = mysqlconnect.ConnectDb();
        String sql = "delete from users where id = ?";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1, colId.getCellData(index).toString());
            pst.execute();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        refresh();
    }
}
