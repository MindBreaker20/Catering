package controllers;
import database.mysqlconnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Login implements Initializable {
    @FXML
    private AnchorPane form1;

    //Login
    @FXML
    private TextField logfirstname;
    @FXML
    private TextField loglastname;
    @FXML
    private PasswordField logpassword;
    @FXML
    private Button submit;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    String value1 = null;
    String value2 = null;

    @FXML
    private void login(ActionEvent event) throws Exception{
        conn = mysqlconnect.ConnectDb();
        String sql = "Select * from users where firstname = ? and lastname = ? and password = ?";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1, logfirstname.getText());
            pst.setString(2, loglastname.getText());
            pst.setString(3, logpassword.getText());
            rs = pst.executeQuery();

            if(rs.next()){
                if(rs.getInt("id")==1){
                    submit.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("/views/home.fxml"));
                    Stage mainStage = new Stage();
                    Scene scene = new Scene(root);
                    mainStage.setScene(scene);
                    mainStage.show();
                }else{
                    submit.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("/views/client.fxml"));
                    Stage mainStage = new Stage();
                    Scene scene = new Scene(root);
                    mainStage.setScene(scene);
                    mainStage.show();
                }

                value1 = rs.getString("firstname");
                value2 = rs.getString("lastname");
            }
            else{
                JOptionPane.showMessageDialog(null, "Invalid First name, Last name or Password");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //Registration
    @FXML
    private TextField regfirstname;
    @FXML
    private TextField reglastname;
    @FXML
    private TextField regphone;
    @FXML
    private PasswordField regpassword;
    @FXML
    private Button register;

    @FXML
    public void signup(ActionEvent event) throws Exception{
        conn = mysqlconnect.ConnectDb();
        String sql = "insert into users (firstname,lastname,phone,password) values (?,?,?,?)";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1,regfirstname.getText());
            pst.setString(2,reglastname.getText());
            pst.setString(3,regphone.getText());
            pst.setString(4,regpassword.getText());
            pst.execute();

            JOptionPane.showMessageDialog(null,"Saved");

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle rb){
    }

}
