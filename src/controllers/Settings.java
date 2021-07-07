package controllers;

import database.mysqlconnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.net.URL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Settings implements Initializable {
    @FXML
    private AnchorPane window6;

    //Edit Admin Login
    @FXML
    private TextField chfirstname;
    @FXML
    private TextField chlastname;
    @FXML
    private TextField chphone;
    @FXML
    private TextField chpss;
    @FXML
    private Button save;

    int index = -1;
    Connection conn;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @FXML
    public void edit(ActionEvent event) throws Exception{
        try{
            conn = mysqlconnect.ConnectDb();
            String value0 = "1";
            String value1 = chfirstname.getText();
            String value2 = chlastname.getText();
            String value3 = chphone.getText();
            String value4 = chpss.getText();

            String sql = "update users set id= '"+value0+"', firstname= '"+value1+"', lastname= '"+value2+"', phone='"+value3+"', password='"+value4+
                    "' where id= '"+value0+"' ";
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Changes successfully saved");
        }catch(Exception e){

        }


    }



    @Override
    public void initialize(URL url, ResourceBundle rb){

    }
}
