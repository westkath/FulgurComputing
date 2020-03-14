package models;

import javafx.scene.control.Button;

public class ProductRow {

    private int id;
    private String name;
    private String description;
    private String price;
    private String stockLevel;
    private Button button;

    public ProductRow(int id, String name, String description, String price, String stockLevel, Button button) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockLevel = stockLevel;
        this.button = button;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getStockLevel() {
        return stockLevel;
    }

    public Button getButton() {
        return button;
    }

}
