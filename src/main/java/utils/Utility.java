package utils;

import javafx.scene.control.Alert;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class Utility {

    public static String getProperty(String property) {
        return getPropertiesFile().getProperty(property);
    }

    public static Properties getPropertiesFile() {
        Properties prop = new Properties();

        try {
            prop.load(Objects.requireNonNull(Utility.class.getClassLoader().getResourceAsStream("db.properties")));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return prop;
    }

    public static void showErrorPopup(String error, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(error);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void showInfoPopup(String status, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(status);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
