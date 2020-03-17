package ui;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

public class Controller {

    Navigator navigator = Navigator.getInstance();

    public void viewHome(ActionEvent event) {
        navigator.viewHome(event);
    }

    public void viewProducts(ActionEvent event) {
        navigator.viewProducts(event);
    }

    public void viewBasket(ActionEvent event) {
        navigator.viewBasket(event);
    }

    public void showPopup() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Cannot Navigate to Page");
        alert.setContentText("You are already on this page!");
        alert.showAndWait();
    }

}
