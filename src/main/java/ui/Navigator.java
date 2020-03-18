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

    public void viewHome(ActionEvent event) {
        FXMLLoader loader = getLoader("ui/home.fxml");
        Scene homeScene = loadScene(loader);
        setScene(event, homeScene);
    }

    public void viewProducts(ActionEvent event) {
        FXMLLoader loader = getLoader("ui/products.fxml");
        Scene productsScene = loadScene(loader);

        ProductsController controller = loader.getController();
        controller.loadProducts();

        setScene(event, productsScene);
    }

    public void viewProduct(ActionEvent event, int productId) {
        FXMLLoader loader = getLoader("ui/product.fxml");
        Scene productScene = loadScene(loader);

        ProductController controller = loader.getController();
        controller.loadProduct(productId);

        setScene(event, productScene);
    }

    public void viewBasket(ActionEvent event) {
        FXMLLoader loader = getLoader("ui/basket.fxml");
        Scene basketScene = loadScene(loader);

        BasketController controller = loader.getController();
        controller.loadProducts();

        setScene(event, basketScene);
    }

    private FXMLLoader getLoader(String fxmlFile) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Objects.requireNonNull(getClass().getClassLoader().getResource(fxmlFile)));
        return loader;
    }

    private Scene loadScene(FXMLLoader loader) {
        Scene scene = null;
        try {
            Parent root = loader.load();
            scene = new Scene(root, 800, 600);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return scene;
    }

    private void setScene(ActionEvent event, Scene scene) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
    }

}
