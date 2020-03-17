package engine;

import data.DBConnection;
import models.Basket;
import models.Product;

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

    public void addProductToBasket(Product product) {
        basket.addProduct(product);
    }

    public void removeOneProductFromBasket(Product product) {
        basket.removeOne(product);
    }

    public void prepareDatabase() {
        db.setupDatabase();
    }

}
