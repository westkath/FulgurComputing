package engine;

import models.Basket;
import models.Product;

public class Engine {

    private static Engine instance;
    private Basket basket;

    private Engine() {
        basket = new Basket();
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

    public void addProductToBasket(Product product) {
        basket.addProduct(product);
    }

    public void removeOneProductFromBasket(Product product) {
        basket.removeOne(product);
    }

}
