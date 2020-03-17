package ui;

import data.DBConnection;
import engine.Engine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Basket;
import models.BasketRow;
import models.Product;

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
        navigator.viewHome(event);
    }

    public void viewProducts(ActionEvent event) {
        navigator.viewProducts(event);
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
        DBConnection connection = new DBConnection();
        connection.increaseStockLevel(product.getProductID());
        loadProducts();
    }

    public void checkout(ActionEvent event) {
        basket = new Basket();
        engine.setBasket(basket);
        showCheckoutMessage("Success!", "You have ordered your products, basket total was " + basketTotal.getText());
        navigator.viewHome(event);
    }

    public void showCheckoutMessage(String status, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(status);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
