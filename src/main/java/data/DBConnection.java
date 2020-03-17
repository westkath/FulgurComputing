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

public class DBConnection {

    private static DBConnection instance;
    private static Connection conn;
    private static String dbhost = "jdbc:sqlite:shop.db";
    private static String username = "root";
    private static String password = "password";

    private DBConnection() {
        try {
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
        List<String> commands = readDatabaseSetup();
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
        ResultSet results = statement.executeQuery("SELECT * FROM Product;");
        return results;
    }

    public Product getProductById(int productId) throws SQLException {
        Product product = null;

        Statement statement = conn.createStatement();
        ResultSet results = statement.executeQuery("SELECT * FROM Product WHERE product_id=" + productId + ";");

        while (results.next()) {
            String name = results.getString("product_name");
            String description = results.getString("product_description");
            double price = results.getDouble("product_price");
            int stockLevel = results.getInt("product_stock_level");

            product = new Product(productId, name, description, price, stockLevel);
        }

        return product;
    }

    public void decreaseStockLevel(int productId) {
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("UPDATE Product SET product_stock_level=product_stock_level - 1 WHERE product_id=" + productId + ";");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void increaseStockLevel(int productId) {
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("UPDATE Product SET product_stock_level=product_stock_level + 1 WHERE product_id=" + productId + ";");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private List<String> readDatabaseSetup() {
        List<String> commands = new ArrayList<>();
        try (InputStream inStream = getClass().getClassLoader().getResourceAsStream("setup.txt");
                 InputStreamReader inStreamReader = new InputStreamReader(inStream, StandardCharsets.UTF_8);
                 BufferedReader reader = new BufferedReader(inStreamReader)) {
            reader.lines().forEach(commands::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return commands;
    }

}
