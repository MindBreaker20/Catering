package models;

public class Stock {
    int id;
    String name, type;
    int quantity;

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public String getType() { return type;
    }

    public Stock(int id, String name, String type, int quantity) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.quantity = quantity;
    }


}
