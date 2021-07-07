package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Home implements Initializable {

    @FXML
    private Pane contentArea;
    @Override
    public void initialize(URL url, ResourceBundle rb){
    }


    @FXML
    private void switchToInventory(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/views/inventory.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
    @FXML
    private void switchToMenu(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/views/menu.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    private void switchToUsers(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/views/users.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    private void switchToOrders(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/views/orders.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    private void switchToSettings(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/views/settings.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    private Button logoutButton;
    @FXML
    private AnchorPane homeArea;
    @FXML
    private ImageView tool;

    Stage stage;

    public void logOut(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to logout!");
        alert.setContentText("Do you want to save before exiting?: ");

        if(alert.showAndWait().get() == ButtonType.OK){
            stage = (Stage) homeArea.getScene().getWindow();
            System.out.println("You successfully logged out!");
            stage.close();
        }
    }


}
