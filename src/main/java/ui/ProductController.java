package ui;

import data.DBConnection;
import engine.Engine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import models.Product;

import java.io.IOException;
import java.sql.SQLException;

public class ProductController {

    @FXML private TextField productName;
    @FXML private TextField productDescription;
    @FXML private TextField productPrice;
    @FXML private TextField productStockLevel;

    private Product currentProduct;
    private Engine engine = Engine.getInstance();
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

    public void viewBasket(ActionEvent event) {
        try {
            navigator.viewBasket(event);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadProduct(int productId) {
        DBConnection db = new DBConnection();

        try {
            currentProduct = db.getProductById(productId);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        productName.setText(currentProduct.getProductName());
        productDescription.setText(currentProduct.getDescription());
        productPrice.setText("£" + currentProduct.getPrice());
        productStockLevel.setText(String.valueOf(currentProduct.getStockLevel()));
    }

    public void orderProduct() {
        if (currentProduct.getStockLevel() == 0) {
            showErrorMessage("Not Enough Stock", "You cannot order a product when the current stock level is zero!");
        } else {
            engine.addProductToBasket(currentProduct);
            loadProduct(currentProduct.getProductID());
        }
    }

    public void showErrorMessage(String error, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(error);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
