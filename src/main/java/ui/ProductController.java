package ui;

import data.DBConnection;
import engine.Engine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import models.Product;

import java.sql.SQLException;

public class ProductController extends Controller {

    @FXML private TextField productName;
    @FXML private TextField productDescription;
    @FXML private TextField productPrice;
    @FXML private TextField productStockLevel;

    private Product currentProduct;
    private Engine engine = Engine.getInstance();

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

            DBConnection connection = new DBConnection();
            connection.decreaseStockLevel(currentProduct.getProductID());

            loadProduct(currentProduct.getProductID());
        }
    }

    public void showErrorMessage(String error, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(error);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
