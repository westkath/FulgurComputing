package data;

import models.Product;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import static utils.DatabaseConstants.*;

public class DBConnection {

    private static DBConnection instance;
    private static Connection conn;

    private DBConnection() {
        try {
            String dbhost = getProperty(DB_HOST);
            String username = getProperty(USERNAME);
            String password = getProperty(PASSWORD);

            conn = DriverManager.getConnection(dbhost, username, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public void setupDatabase() {
        List<String> commands = readDatabaseSetup(getProperty(SETUP_FILE));
        try {
            Statement statement = conn.createStatement();
            for (String command : commands) {
                statement.addBatch(command);
            }
            statement.executeBatch();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ResultSet getProductsInTable() throws SQLException {
        Statement statement = conn.createStatement();
        return statement.executeQuery(getProperty(SELECT_ALL));
    }

    public Product getProductById(int productId) throws SQLException {
        Product product = null;
        String query = getProperty(PRODUCT_BY_ID).replace(REPLACE_PRODUCT_ID, String.valueOf(productId));

        Statement statement = conn.createStatement();
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
            String name = results.getString(NAME);
            String description = results.getString(DESCRIPTION);
            double price = results.getDouble(PRICE);
            int stockLevel = results.getInt(STOCK_LEVEL);

            product = new Product(productId, name, description, price, stockLevel);
        }

        return product;
    }

    public void decreaseStockLevel(int productId) {
        String update = getProperty(DECREASE_STOCK_LEVEL).replace(REPLACE_PRODUCT_ID, String.valueOf(productId));
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(update);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void increaseStockLevel(int productId) {
        String update = getProperty(INCREASE_STOCK_LEVEL).replace(REPLACE_PRODUCT_ID, String.valueOf(productId));
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(update);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private List<String> readDatabaseSetup(String filename) {
        List<String> commands = new ArrayList<>();
        try (InputStream inStream = getClass().getClassLoader().getResourceAsStream(filename);
             InputStreamReader inStreamReader = new InputStreamReader(Objects.requireNonNull(inStream), StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(inStreamReader)) {
            reader.lines().forEach(commands::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return commands;
    }

    private String getProperty(String property) {
        Properties prop = new Properties();

        try {
            prop.load(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("db.properties")));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return prop.getProperty(property);
    }

}
