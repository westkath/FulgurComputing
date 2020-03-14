package models;

import java.util.HashMap;
import java.util.Map;

public class Basket {

    private double basketTotal;
    private Map<Integer, Integer> basketContents;
    private Map<Integer, Product> productsInBasket;

    public double getBasketTotal() { return basketTotal; }
    public Map<Integer, Product> getProductsInBasket() { return productsInBasket; }
    public Map<Integer, Integer> getBasketContents() { return basketContents; }

    public void setBasketTotal(double inBasketTotal) { basketTotal = inBasketTotal; }
    public void setProductsInBasket(HashMap<Integer, Product> inProductsInBasket) { productsInBasket = inProductsInBasket; }
    public void setBasketContents(HashMap<Integer, Integer> inBasketContents) { basketContents = inBasketContents; }

    public Basket() {

        basketTotal = 0;
        basketContents = new HashMap<>();
        productsInBasket = new HashMap<>();
    }

    public double calculateBasketTotal(){

        double total = 0;

        for (Map.Entry<Integer, Integer> basketEntry : basketContents.entrySet()){
            int productId = basketEntry.getKey();
            int quantity = basketEntry.getValue();
            Product currentProduct = productsInBasket.get(productId);
            double price = currentProduct.getPrice();

            total += (price * quantity);
        }
        return total;
    }

    public void addProduct(Product product) {
        int productId = product.getProductID();

        if (basketContents.containsKey(productId)) {
            basketContents.replace(productId, basketContents.get(productId) + 1);
        } else {
            basketContents.put(productId, 1);
            productsInBasket.put(productId, product);
        }
    }

    public void removeOne(Product product) {
        int productId = product.getProductID();

        if (basketContents.get(productId) == 1) {
            basketContents.remove(productId);
            productsInBasket.remove(productId);
        } else {
            basketContents.replace(productId, basketContents.get(productId) - 1);
        }
    }

}
