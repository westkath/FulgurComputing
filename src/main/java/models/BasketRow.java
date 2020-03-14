package models;

import javafx.scene.control.Button;

public class BasketRow {

    private String name;
    private String quantity;
    private Button removeOne;

    public BasketRow(String name, String quantity, Button removeOne) {
        this.name = name;
        this.quantity = quantity;
        this.removeOne = removeOne;
    }

    public String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }

    public Button getRemoveOne() {
        return removeOne;
    }

}
