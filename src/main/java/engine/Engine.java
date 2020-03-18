package engine;

import data.DBConnection;
import models.Basket;
import models.Product;

import java.io.File;
import java.util.Map;

public class Engine {

    private static Engine instance;
    private Basket basket;
    private DBConnection db;

    private Engine() {
        basket = new Basket();
        db = DBConnection.getInstance();
    }

    public static Engine getInstance() {
        if (instance == null) {
            instance = new Engine();
        }
        return instance;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    private boolean isDatabaseOnline() {
        File db = new File("shop.db");
        return db.exists();
    }

    public void prepareDatabase() {
        if (!isDatabaseOnline()) {
            db.setupDatabase();
        }
    }

    public void addProductToBasket(Product product) {
        basket.addProduct(product);
        db.adjustStockLevel(product.getProductID(), -1);
    }

    public void removeOneFromBasket(Product product) {
        basket.removeOne(product);
        db.adjustStockLevel(product.getProductID(), 1);
    }

    public void adjustStockIfNoCheckout() {
        if (basket.isBasketEmpty()) {
            return;
        }

        for (Map.Entry<Integer, Integer> basketEntry : basket.getBasketContents().entrySet()) {
            int productId = basketEntry.getKey();
            int quantity = basketEntry.getValue();

            db.adjustStockLevel(productId, quantity);
        }
    }

}
