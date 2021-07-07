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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Dish;
import models.Stock;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Menu implements Initializable {
    //Table of dishes
    @FXML
    private TableView<Dish> tableDish;
    @FXML
    private TableColumn<Dish, Integer> colId;
    @FXML
    private TableColumn<Dish, String> colName;
    @FXML
    private TableColumn<Dish, String> colType;
    @FXML
    private TableColumn<Dish, Integer> colPrice;

    ObservableList<Dish> listDish;
    int index = -1;
    Connection conn;
    ResultSet rs = null;
    PreparedStatement pst = null;
    //Menu elements controllers
    @FXML
    private Button addButton;
    @FXML
    private Label newDish;
    @FXML
    private ImageView deleteDish;
    @FXML
    private AnchorPane parent;
    Stage stage;

    @FXML
    public void refresh(){
        colId.setCellValueFactory(new PropertyValueFactory<Dish, Integer>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<Dish,String>("name"));
        colType.setCellValueFactory(new PropertyValueFactory<Dish,String>("type"));
        colPrice.setCellValueFactory(new PropertyValueFactory<Dish,Integer>("price"));

        listDish = mysqlconnect.getDataDish();
        tableDish.setItems(listDish);
    }

    @FXML
    void switchToAddDish(ActionEvent event){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/addDish.fxml"));
            Parent root2 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root2));
            stage.show();
        }catch(Exception e){
            System.out.println("Can't open new window");
        }
    }
    @FXML
    public void deleteDish(MouseEvent event){
        index = tableDish.getSelectionModel().getSelectedIndex();
        conn = mysqlconnect.ConnectDb();
        String sql = "delete from menu where id = ?";
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
