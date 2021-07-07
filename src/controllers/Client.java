package controllers;

import database.mysqlconnect;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Dish;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Client implements Initializable {
    //Table of dishes
    @FXML
    public TableView<Dish> tableDish;
    @FXML
    public TableColumn<Dish, Integer> colId;
    @FXML
    public TableColumn<Dish, String> colName;
    @FXML
    public TableColumn<Dish, String> colType;
    @FXML
    public TableColumn<Dish, Integer> colPrice;
    @FXML
    public ObservableList<Dish> listDish;
    //Table of ordered dishes
    @FXML
    private TableView<Dish> chnDish;
    @FXML
    private TableColumn<Dish, Integer> chnId;
    @FXML
    private TableColumn<Dish, String> chnName;
    @FXML
    private TableColumn<Dish, String> chnType;
    @FXML
    private TableColumn<Dish, Integer> chnPrice;
    @FXML
    private ObservableList<Dish> yourOrder;

    int index = -1;
    Connection conn;
    ResultSet rs = null;
    PreparedStatement pst = null;

    //Client elements controllers
    @FXML
    private Button logOutButton;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button orderButton;
    @FXML
    public TextField txtsum;
    @FXML
    public TextField txtcity;
    @FXML
    public TextField txtstreet;
    @FXML
    public TextField txthouse;
    @FXML
    private DatePicker txtdate;
    @FXML
    private AnchorPane window5;

    Stage stage;
    int sum = 0;


    @FXML
    public void totalSum(){
        sum = 0;
        for(Dish p: yourOrder){
            sum = sum + p.getPrice();
        }
        String sumt = String.valueOf(sum);
        txtsum.setText(sumt);
    }
    @FXML
    public void reduceSum(int loss){
        sum = sum - loss;
        String sumt = String.valueOf(sum);
        txtsum.setText(sumt);
    }
    @FXML
    public void orderDish(ActionEvent event){
        index = tableDish.getSelectionModel().getSelectedIndex();
        conn = mysqlconnect.ConnectDb();
        String sql = "insert into yourorder (name, type, price) values(?,?,?)";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1, colName.getCellData(index));
            pst.setString(2, colType.getCellData(index));
            pst.setString(3, colPrice.getCellData(index).toString());
            pst.execute();
            refresh2();
            totalSum();
        }catch(Exception e){
        }
    }
    @FXML
    public String getDate(ActionEvent event){
        LocalDate orderDate = txtdate.getValue();
        return orderDate.toString();
    }
    @FXML
    public void logOut(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to logout!");
        alert.setContentText("Do you want to save before exiting?: ");

        if(alert.showAndWait().get() == ButtonType.OK){
            stage = (Stage) window5.getScene().getWindow();
            System.out.println("You successfully logged out!");
            stage.close();
        }
    }
    @FXML
    public void deleteDish(ActionEvent event){
        index = chnDish.getSelectionModel().getSelectedIndex();
        conn = mysqlconnect.ConnectDb();
        String sql = "delete from yourorder where id = ?";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1, chnId.getCellData(index).toString());
            int loss = chnPrice.getCellData(index);
            pst.execute();
            refresh2();
            reduceSum(loss);

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    @FXML
    public void refresh1(){
        colId.setCellValueFactory(new PropertyValueFactory<Dish, Integer>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<Dish,String>("name"));
        colType.setCellValueFactory(new PropertyValueFactory<Dish,String>("type"));
        colPrice.setCellValueFactory(new PropertyValueFactory<Dish,Integer>("price"));

        listDish = mysqlconnect.getDataDish();
        tableDish.setItems(listDish);
    }
    @FXML
    public void refresh2(){
        chnId.setCellValueFactory(new PropertyValueFactory<Dish, Integer>("id"));
        chnName.setCellValueFactory(new PropertyValueFactory<Dish,String>("name"));
        chnType.setCellValueFactory(new PropertyValueFactory<Dish,String>("type"));
        chnPrice.setCellValueFactory(new PropertyValueFactory<Dish,Integer>("price"));

        yourOrder = mysqlconnect.getDataYourOrder();
        chnDish.setItems(yourOrder);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb){
        refresh1();
        refresh2();
    }
}
