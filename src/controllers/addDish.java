package controllers;

import database.mysqlconnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class addDish implements Initializable {
    //ChoiceBox dish type
    @FXML
    private AnchorPane window8;
    @FXML
    private TextField txtname;
    @FXML
    private ChoiceBox<String> txttype;
    private String[] food = {"Drink","Appetizer","Main dish","Stew and soup","Sweet"};
    @FXML
    private TextField txtprice;
    @FXML
    private Button cancel;
    @FXML
    private Button ok;
    Stage stage;
    public void closeWindow(ActionEvent event){
        stage = (Stage) window8.getScene().getWindow();
        stage.close();
    }

    Connection conn;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public void createDish(ActionEvent event){
        conn = mysqlconnect.ConnectDb();
        String sql = "insert into menu (name, type, price) values(?,?,?)";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtname.getText());
            pst.setString(2, txttype.getValue());
            pst.setString(3,txtprice.getText());
            pst.execute();
        }catch(Exception e){

        }

        stage = (Stage) window8.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        txttype.getItems().addAll(food);
    }
}
