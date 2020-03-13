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

        Parent resultsRoot = loader.load();
        Scene resultsScene = new Scene(resultsRoot, 800, 600);

        // any necessary changes

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(resultsScene);
    }

    public void viewProducts(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Objects.requireNonNull(getClass().getClassLoader().getResource("products.fxml")));

        Parent resultsRoot = loader.load();
        Scene resultsScene = new Scene(resultsRoot, 800, 600);

        // any necessary changes

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(resultsScene);
    }

    public void viewProduct(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Objects.requireNonNull(getClass().getClassLoader().getResource("product.fxml")));

        Parent resultsRoot = loader.load();
        Scene resultsScene = new Scene(resultsRoot, 800, 600);

        // any necessary changes

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(resultsScene);
    }

    public void viewBasket(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Objects.requireNonNull(getClass().getClassLoader().getResource("basket.fxml")));

        Parent resultsRoot = loader.load();
        Scene resultsScene = new Scene(resultsRoot, 800, 600);

        // any necessary changes

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(resultsScene);
    }

}
