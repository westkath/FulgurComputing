package models;

public class Product {

    private int productID;
    private String productName;
    private String description;
    private double price;
    private int stockLevel;

    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getStockLevel() {
        return stockLevel;
    }

    public Product(int inProductID, String inProductName, String inDescription, double inPrice, int inStockLevel) {
        productID = inProductID;
        productName = inProductName;
        description = inDescription;
        price = inPrice;
        stockLevel = inStockLevel;
    }

}
