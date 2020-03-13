package models;

public class User {

    private int userId;
    private String username;
    private Basket basket;
    private static int userCount = 1;

    public User(String inUsername) {
        userId = userCount;
        username = inUsername;
        basket = new Basket();

        userCount++;
    }

    public String getUsername() {
        return username;
    }

    public Basket getBasket() {
        return basket;
    }

}
