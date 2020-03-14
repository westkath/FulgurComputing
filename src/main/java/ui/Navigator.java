package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Navigator {

    private static Navigator instance;

    private Navigator() {
        // helper method
    }

    public static Navigator getInstance() {
        if (instance == null) {
            instance = new Navigator();
        }
        return instance;
    }

    public void viewHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Objects.requireNonNull(getClass().getClassLoader().getResource("home.fxml")));

        Parent homeRoot = loader.load();
        Scene homeScene = new Scene(homeRoot, 800, 600);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homeScene);
    }

    public void viewProducts(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Objects.requireNonNull(getClass().getClassLoader().getResource("products.fxml")));

        Parent productsRoot = loader.load();
        Scene productsScene = new Scene(productsRoot, 800, 600);

        ProductsController controller = loader.getController();
        controller.loadProducts();

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(productsScene);
    }

    public void viewProduct(ActionEvent event, int productId) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Objects.requireNonNull(getClass().getClassLoader().getResource("product.fxml")));

        Parent productRoot = loader.load();
        Scene productScene = new Scene(productRoot, 800, 600);

        ProductController controller = loader.getController();
        controller.loadProduct(productId);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(productScene);
    }

    public void viewBasket(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Objects.requireNonNull(getClass().getClassLoader().getResource("basket.fxml")));

        Parent basketRoot = loader.load();
        Scene basketScene = new Scene(basketRoot, 800, 600);

        BasketController controller = loader.getController();
        controller.loadProducts();

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(basketScene);
    }

}
