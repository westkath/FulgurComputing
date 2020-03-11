package data;

import java.sql.*;

public class DBConnection {

    private static String dbhost = "jdbc:mysql://localhost:3306/shopdb";
    private static String username = "root";
    private static String password = "password";

    public static void executeStatement(String query) {
        try (Connection conn = DriverManager.getConnection(dbhost, username, password)) {
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(query);

            System.out.println("Response:");
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
