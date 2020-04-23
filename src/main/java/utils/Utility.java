package utils;

import javafx.scene.control.Alert;
import java.util.Properties;

public class Utility {

    private static DBProperties properties = new DBPropertiesFromFile();
    private static Properties props;

    public static void setProperties(DBProperties properties) {
        Utility.properties = properties;
    }

    public static String getProperty(String property) {
        if (props == null) {
            props = properties.getProperties();
        }

        return props.getProperty(property);
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
