import models.Basket;
import models.Product;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class BasketTest {

    @Mock
    Basket basket;

    Product product = new Product(7, "Super RAM", "An amazing source of memory...", 99.99, 1);
    Product product2 = new Product(8, "Bad RAM", "Slow memory...", 75.00, 1);

    List<Product> products = Arrays.asList(product, product, product2);

    @Before
    public void setup() {
        basket = new Basket();
    }

    @Test
    public void testBasketEmpty() {
        boolean isBasketEmpty = basket.isBasketEmpty();
        assertTrue(isBasketEmpty);
    }

    @Test
    public void testBasketPopulation() {
        populateBasket();

        int totalItems = getTotalItemsInBasket();
        int uniqueItems = getUniqueItemsInBasket();

        assertThat("Expected 2 unique items in basket...", uniqueItems, is(2));
        assertThat("Expected 3 items in basket...", totalItems, is(3));
    }

    @Test
    public void testAddOneProduct() {
        basket.addProduct(product);
        int totalItems = getTotalItemsInBasket();

        assertThat("Expected 1 item in basket...", totalItems, is(1));
    }

    @Test
    public void testRemoveOneProduct() {
        populateBasket();

        int totalItems = getTotalItemsInBasket();
        int expectedItemCount = totalItems - 1;

        basket.removeOne(product);

        assertThat("Expected 1 less item in basket...", expectedItemCount, is(2));
    }

    @Test
    public void testCalculateTotal() {
        double basketTotal = basket.calculateBasketTotal();
        assertThat("Expected basket total of £0.00", basketTotal, is(0.00));

        populateBasket();

        basketTotal = basket.calculateBasketTotal();
        assertThat("Expected basket total of £274.98", basketTotal, is(274.98));
    }

    private void populateBasket() {
        products.forEach(basket::addProduct);
    }

    private int getTotalItemsInBasket() {
        int totalItems = 0;

        for (Map.Entry<Integer, Integer> entry : basket.getBasketContents().entrySet()) {
            totalItems += entry.getValue();
        }

        return totalItems;
    }

    private int getUniqueItemsInBasket() {
        return basket.getBasketContents().size();
    }

}
