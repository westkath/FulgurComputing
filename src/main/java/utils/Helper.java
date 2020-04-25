package utils;

import javafx.scene.control.Alert;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class Helper {

    private Properties properties;

    public Helper() {

    }

    public String getProperty(String property) {
        if (properties == null) {
            properties = getProperties();
        }

        return properties.getProperty(property);
    }

    public void showErrorPopup(String error, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(error);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void showInfoPopup(String status, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(status);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public Properties getProperties() {
        Properties prop = new Properties();

        try {
            prop.load(Objects.requireNonNull(Helper.class.getClassLoader().getResourceAsStream("db.properties")));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return prop;
    }

}
