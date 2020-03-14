package ui;

import engine.Engine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Basket;
import models.BasketRow;
import models.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BasketController {

    @FXML private TableView<BasketRow> basketTable;
    @FXML private TableColumn<BasketRow, String> productsColumn;
    @FXML private TableColumn<BasketRow, Integer> quantityColumn;
    @FXML private TableColumn<BasketRow, Button> removeOneColumn;
    @FXML private TextField basketTotal;

    private Engine engine = Engine.getInstance();
    private Basket basket;
    private Navigator navigator = Navigator.getInstance();

    public void viewHome(ActionEvent event) {
        try {
            navigator.viewHome(event);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void viewProducts(ActionEvent event) {
        try {
            navigator.viewProducts(event);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadProducts() {
        productsColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        removeOneColumn.setCellValueFactory(new PropertyValueFactory<>("removeOne"));

        basketTable.getItems().setAll(parseRows());
        basketTotal.setText("£" + basket.calculateBasketTotal());
    }

    public List<BasketRow> parseRows() {
        List<BasketRow> rows = new ArrayList<>();

        basket = engine.getBasket();
        Map<Integer, Product> productsInBasket = basket.getProductsInBasket();
        for (Map.Entry<Integer, Integer> basketEntry : basket.getBasketContents().entrySet()) {
            int productId = basketEntry.getKey();
            String quantity = String.valueOf(basketEntry.getValue());
            Product product = productsInBasket.get(productId);
            String name = product.getProductName();

            Button removeOne = new Button("Remove One");
            removeOne.setOnAction(actionEvent -> {
                removeOneProduct(product);
            });

            BasketRow row = new BasketRow(name, quantity, removeOne);
            rows.add(row);
        }

        return rows;
    }

    public void removeOneProduct(Product product) {
        engine.removeOneProductFromBasket(product);
        loadProducts();
    }

}
