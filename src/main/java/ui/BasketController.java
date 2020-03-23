package ui;

import engine.Engine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.BasketRow;
import models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static utils.Utility.showInfoPopup;

public class BasketController extends Controller {

    @FXML private TableView<BasketRow> basketTable;
    @FXML private TableColumn<BasketRow, String> productsColumn;
    @FXML private TableColumn<BasketRow, Integer> quantityColumn;
    @FXML private TableColumn<BasketRow, Button> removeOneColumn;
    @FXML private TextField basketTotal;

    private Engine engine = Engine.getInstance();

    @Override
    public void viewBasket(ActionEvent event) {
        super.showPopup();
    }

    public void loadProducts() {
        productsColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        removeOneColumn.setCellValueFactory(new PropertyValueFactory<>("removeOne"));

        basketTable.getItems().setAll(parseRows());
        basketTotal.setText("£" + engine.calculateBasketTotal());
    }

    public List<BasketRow> parseRows() {
        List<BasketRow> rows = new ArrayList<>();

        Map<Integer, Product> productsInBasket = engine.getProductsInBasket();
        for (Map.Entry<Integer, Integer> basketEntry : engine.getBasketContents().entrySet()) {
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
        engine.removeOneFromBasket(product);
        loadProducts();
    }

    public void checkout(ActionEvent event) {
        if (engine.isBasketEmpty()) {
            showInfoPopup("Empty Basket", "Cannot Checkout with an Empty Basket - Get Shopping!");
            return;
        }

        engine.clearBasket();
        showInfoPopup("Success!", "You have ordered your products, basket total was " + basketTotal.getText());
        navigator.viewHome(event);
    }

}
