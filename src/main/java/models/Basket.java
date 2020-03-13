package models;

import java.util.HashMap;
import java.util.Map;

public class Basket {

    private double basketTotal;
    private HashMap<Product, Integer> productsInBasket;

    public double getBasketTotal() { return basketTotal; }
    public HashMap<Product, Integer> getProductsInBasket() { return productsInBasket; }

    public void setBasketTotal(double inBasketTotal) { basketTotal = inBasketTotal; }
    public void setProductsInBasket(HashMap<Product, Integer> inProductsInBasket) { productsInBasket = inProductsInBasket; }

    public Basket() {

        basketTotal = 0;
        productsInBasket = new HashMap<>();
    }

    public double calculateBasketTotal(){

        double total = 0;

        for (Map.Entry<Product, Integer> product : this.productsInBasket.entrySet()){
            Product currentProduct = product.getKey();
            double quantity = product.getValue();
             total += (currentProduct.getPrice() * quantity);
        }
        return total;
    }
}
