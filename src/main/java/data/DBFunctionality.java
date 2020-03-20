package data;

import models.Product;

import java.sql.ResultSet;

public interface DBFunctionality {

    void prepareDatabase();
    ResultSet getProductsInTable();
    Product getProductById(int productId);
    void adjustStockIfNoCheckout();
    void addProductToBasket(Product product);
    void removeOneFromBasket(Product product);

}
