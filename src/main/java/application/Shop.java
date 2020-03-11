package application;

import data.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class Shop extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent homeRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("home.fxml")));
        Scene homeScene = new Scene(homeRoot, 800, 600);
        // homeScene.getStylesheets().add(String.valueOf(getClass().getClassLoader().getResource("search.css")));

        primaryStage.setScene(homeScene);
        // primaryStage.getIcons().add(new Image(String.valueOf(getClass().getClassLoader().getResource("icon.png"))));
        primaryStage.setTitle("Team Four | Shop");
        primaryStage.show();

        DBConnection.executeStatement("SELECT * FROM Product;");
    }

}
