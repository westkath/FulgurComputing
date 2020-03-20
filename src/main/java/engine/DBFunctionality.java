package engine;

import models.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DBFunctionality {

    void prepareDatabase();
    ResultSet getProductsInTable() throws SQLException;
    Product getProductById(int productId) throws SQLException;
    void adjustStockIfNoCheckout();
    void addProductToBasket(Product product);
    void removeOneFromBasket(Product product);

}
