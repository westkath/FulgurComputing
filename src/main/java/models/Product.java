package models;

public class Product {

    private int productID;
    private String productName;
    private String description;
    private double price;
    private int stockLevel;

    public int getProductID() {return productID;}
    public String getProductName() {return productName;}
    public String getDescription() {return description;}
    public double getPrice() {return price;}
    public int getStockLevel() {return stockLevel;}

    public void setProductID(int inProductID) {productID = inProductID;}
    public void setProductName(String inProductName) {productName = inProductName;}
    public void setDescription(String inDescription) { description = inDescription; }
    public void setPrice(double inPrice) {price = inPrice;}
    public void setStockLevel(int inStockLevel) {stockLevel = inStockLevel;}

    public Product() {

        productID = 0;
        productName = "";
        description = "";
        price = 0;
        stockLevel = 0;
    }

    public Product(int inProductID, String inProductName, String inDescription, double inPrice, int inStockLevel) {

        productID = inProductID;
        productName = inProductName;
        description = inDescription;
        price = inPrice;
        stockLevel = inStockLevel;
    }
}