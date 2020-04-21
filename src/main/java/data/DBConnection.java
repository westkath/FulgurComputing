package data;

import models.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static utils.DatabaseConstants.*;
import static utils.Utility.getProperty;

public class DBConnection {

    private static DBConnection instance;
    private static Connection conn;
    private DBSetup setup;

    private DBConnection() {
        try {
            String dbhost = getProperty(DB_HOST);
            String username = getProperty(USERNAME);
            String password = getProperty(PASSWORD);

            conn = DriverManager.getConnection(dbhost, username, password);
            setup = new DBSetupFromFile(SETUP_FILE);
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

    public void selectSetup(DBSetup setup) {
        this.setup = setup;
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

    public ResultSet getProductsInTable() {
        return execute(getProperty(SELECT_ALL));
    }

    public Product getProductById(int productId) {
        String query = getProperty(PRODUCT_BY_ID).replace(REPLACE_PRODUCT_ID, String.valueOf(productId));
        ResultSet results = execute(query);

        return processProduct(productId, results);
    }

    private ResultSet execute(String query) {
        ResultSet results = null;

        try {
            Statement statement = conn.createStatement();
            results = statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return results;
    }

    private Product processProduct(int productId, ResultSet results) {
        Product result = null;

        try {
            while (results.next()) {
                String name = results.getString(NAME);
                String description = results.getString(DESCRIPTION);
                double price = results.getDouble(PRICE);
                int stockLevel = results.getInt(STOCK_LEVEL);

                result = new Product(productId, name, description, price, stockLevel);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    public void adjustStockLevel(int productId, int quantity) {
        String update = getProperty(ADJUST_STOCK_LEVEL);
        update = update.replace(REPLACE_PRODUCT_ID, String.valueOf(productId));
        update = update.replace(REPLACE_ADJUST_VALUE, String.valueOf(quantity));

        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(update);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private List<String> readDatabaseSetup() {
        return setup.readDatabaseSetup();
    }

}
