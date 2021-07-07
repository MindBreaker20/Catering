package controllers;

import database.mysqlconnect;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Stock;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Inventory implements Initializable {
    //Table of stocks
    @FXML
    private TableView<Stock> tableStock;
    @FXML
    private TableColumn<Stock, Integer> colId;
    @FXML
    private TableColumn<Stock, String> colName;
    @FXML
    private TableColumn<Stock, String> colType;
    @FXML
    private TableColumn<Stock, Integer> colQuantity;

    ObservableList<Stock> listStock;
    int index = -1;
    Connection conn;
    ResultSet rs = null;
    PreparedStatement pst = null;

    //Inventory elements controllers
    @FXML
    private Button newStock;
    @FXML
    private ImageView deleteStock;
    @FXML
    private ImageView refreshStock;

    @FXML
    public void deleteStock(MouseEvent event){
        index = tableStock.getSelectionModel().getSelectedIndex();
        conn = mysqlconnect.ConnectDb();
        String sql = "delete from inventory where id = ?";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1, colId.getCellData(index).toString());
            pst.execute();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    public void refresh(){
        colId.setCellValueFactory(new PropertyValueFactory<Stock,Integer>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<Stock,String>("name"));
        colType.setCellValueFactory(new PropertyValueFactory<Stock,String>("type"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<Stock,Integer>("quantity"));

        listStock = mysqlconnect.getDataStock();
        tableStock.setItems(listStock);
    }

    @FXML
    private AnchorPane parent;
    @FXML
    Stage stage;

    @FXML
    public void switchToAddStock(ActionEvent event){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/addStock.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }catch(Exception e){
            System.out.println("Can't open new window");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        refresh();
    }

}
