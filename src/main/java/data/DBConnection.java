package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DBConnection {

    private static Connection conn;
    private static String dbhost = "jdbc:sqlite:shop.db";
    private static String username = "root";
    private static String password = "password";

    private String dropProducts = "DROP TABLE IF EXISTS Product;";
    private String createProducts = "CREATE TABLE IF NOT EXISTS Product (" +
            "product_id INTEGER NOT NULL PRIMARY KEY," +
            "product_name VARCHAR(50) NOT NULL," +
            "product_description VARCHAR(255) NOT NULL," +
            "product_price DECIMAL(13,2) NOT NULL," +
            "product_stock_level INT NOT NULL);";

    private String[] populateProducts = {
            "INSERT INTO Product(product_id, product_name, product_description, product_price, product_stock_level) " +
            "VALUES (NULL, \"Intel Core i9 9900K\", \"top tier processor\", 504.99, 29);",
            "INSERT INTO Product(product_id, product_name, product_description, product_price, product_stock_level) " +
            "VALUES (NULL, \"Intel Core i7 9700K\", \"8-core @ 3.6GHz\", 369.99, 38);",
            "INSERT INTO Product(product_id, product_name, product_description, product_price, product_stock_level) " +
            "VALUES (NULL, \"Intel Core i5 9600K\", \"6-core @ 3.7GHz\", 219.99, 35);",
            "INSERT INTO Product(product_id, product_name, product_description, product_price, product_stock_level) " +
            "VALUES (NULL, \"AMD Ryzen 9 3900X\", \"12-core @ 3.8GHz\", 418.95, 22);",
            "INSERT INTO Product(product_id, product_name, product_description, product_price, product_stock_level) " +
            "VALUES (NULL, \"AMD Ryzen 7 3700X\", \"8-core @ 3.6GHz\", 289.99, 30);",
            "INSERT INTO Product(product_id, product_name, product_description, product_price, product_stock_level) " +
            "VALUES (NULL, \"AMD Ryzen 5 3600X\", \"6-core @ 3.8GHz\", 199.99, 26);",
            "INSERT INTO Product(product_id, product_name, product_description, product_price, product_stock_level) " +
            "VALUES (NULL, \"Corsair H100i RGB PLATINUM\", \"2400RPM Fan @ 37dB\", 159.49, 12);",
            "INSERT INTO Product(product_id, product_name, product_description, product_price, product_stock_level) " +
            "VALUES (NULL, \"Corsair H115i PRO\", \"1200RPM Fan @ 20.4dB\", 158.47, 16);",
            "INSERT INTO Product(product_id, product_name, product_description, product_price, product_stock_level) " +
            "VALUES (NULL, \"MSI B450 TOMAHAWK\", \"64GB Max Memory\", 110.23, 19);",
            "INSERT INTO Product(product_id, product_name, product_description, product_price, product_stock_level) " +
            "VALUES (NULL, \"MSI Z390-A PRO\", \"128GB Max Memory\", 122.48, 23);",
            "INSERT INTO Product(product_id, product_name, product_description, product_price, product_stock_level) " +
            "VALUES (NULL, \"Corsair Vengeance RGB Pro\", \"16GB @ DDR4-3200\", 94.99, 7);",
            "INSERT INTO Product(product_id, product_name, product_description, product_price, product_stock_level) " +
            "VALUES (NULL, \"Kingston HyperX Fury\", \"16GB @ DDR4-3200\", 97.08, 11);",
            "INSERT INTO Product(product_id, product_name, product_description, product_price, product_stock_level) " +
            "VALUES (NULL, \"NVIDIA Star Wars Jedi Order\", \"Expensive Star Wars GPU\", 2476.99, 3);",
            "INSERT INTO Product(product_id, product_name, product_description, product_price, product_stock_level) " +
            "VALUES (NULL, \"AMD RADEON PRO WX 4100\", \"Budget Friendly GPU\", 234.99, 28);",
            "INSERT INTO Product(product_id, product_name, product_description, product_price, product_stock_level) " +
            "VALUES (NULL, \"Asus ROG Swift PG65UQ\", \"64.5in Expensive Monitor\", 4499.00, 2);",
            "INSERT INTO Product(product_id, product_name, product_description, product_price, product_stock_level) " +
            "VALUES (NULL, \"Sceptre C248W-1920RN\", \"23.6in Cheaper Monitor\", 104.97, 55);"
    };

    public DBConnection() {
        try {
            conn = DriverManager.getConnection(dbhost, username, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropProductsTable() {
        try (Statement dropTable = conn.createStatement()){
            dropTable.execute(dropProducts);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createProductsTable() {
        try (Statement createTable = conn.createStatement()) {
            createTable.execute(createProducts);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void populateProductsTable() {
        try (Statement populateTable = conn.createStatement();) {
            for (String row : populateProducts) {
                populateTable.executeUpdate(row);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void displayProductsTable(String query) {
        try (Statement statement = conn.createStatement();) {
            ResultSet results = statement.executeQuery(query);

            System.out.println("Products Table: ");
            while (results.next()) {
                System.out.println(results.getString("product_id") + "\t" +
                        results.getString("product_name") + "\t" +
                        results.getString("product_description") + "\t" +
                        results.getString("product_price") + "\t" +
                        results.getString("product_stock_level"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
