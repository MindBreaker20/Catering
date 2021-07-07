package models;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class Order {
    @FXML
    private String firstname;
    @FXML
    private String lastname;
    @FXML
    private String city;
    @FXML
    private String street;
    @FXML
    private String house;
    @FXML
    private String date;
    @FXML
    private int totalSum;

    @FXML
    private ObservableList<Dish> userOrder;

    public Order(String firstname, String lastname, String city, String street, String house, String date, int totalSum, ObservableList<Dish> userOrder) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
        this.street = street;
        this.house = house;
        this.date = date;
        this.totalSum = totalSum;
        this.userOrder = userOrder;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTotalSum(int totalSum) {
        this.totalSum = totalSum;
    }

    public void setUserOrder(ObservableList<Dish> userOrder) {
        this.userOrder = userOrder;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHouse() {
        return house;
    }

    public String getDate() {
        return date;
    }

    public int getTotalSum() {
        return totalSum;
    }

    public ObservableList<Dish> getUserOrder() {
        return userOrder;
    }
}
