package ui;

import javafx.event.ActionEvent;
import java.io.IOException;

public class HomeController {

    private Navigator navigator = Navigator.getInstance();

    public void viewProducts(ActionEvent event) {
        try {
            navigator.viewProducts(event);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void viewBasket(ActionEvent event) {
        try {
            navigator.viewBasket(event);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
