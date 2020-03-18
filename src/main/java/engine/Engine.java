package engine;

import data.DBConnection;
import models.Basket;
import models.Product;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        File status = new File("status.txt");
        return status.exists();
    }

    private void createStatusFile() {
        Path statusFile = Paths.get("status.txt");

        String content = "Database is Online!";
        try {
            Files.write(statusFile, content.getBytes());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void prepareDatabase() {
        if (!isDatabaseOnline()) {
            db.setupDatabase();
            createStatusFile();
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
