package application;

import engine.Engine;
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

        primaryStage.setScene(homeScene);
        primaryStage.setTitle("Fulgur Computing");
        primaryStage.getIcons().add(new Image(String.valueOf(getClass().getClassLoader().getResource("icon.png"))));
        primaryStage.show();

        startEngine();
    }

    private void startEngine() {
        Engine engine = Engine.getInstance();
        engine.prepareDatabase();
    }

}
