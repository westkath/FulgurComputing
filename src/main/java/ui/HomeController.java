package ui;

import javafx.event.ActionEvent;

public class HomeController {

    private Navigator navigator = Navigator.getInstance();

    public void viewProducts(ActionEvent event) {
        navigator.viewProducts(event);
    }

    public void viewBasket(ActionEvent event) {
        navigator.viewBasket(event);
    }

}
