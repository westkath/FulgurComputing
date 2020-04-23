package data;

import java.util.ArrayList;
import java.util.List;

public class DBSetupFromStub implements DBSetup {

    public DBSetupFromStub() {

    }

    @Override
    public List<String> readDatabaseSetup() {
        List<String> commands = new ArrayList<>();

        commands.add("DROP TABLE IF EXISTS Product;");
        commands.add("CREATE TABLE IF NOT EXISTS Product (product_id INTEGER NOT NULL PRIMARY KEY, product_name VARCHAR(50) NOT NULL, product_description VARCHAR(255) NOT NULL, product_price DECIMAL(13,2) NOT NULL, product_stock_level INT NOT NULL);");
        commands.add("INSERT INTO Product(product_id, product_name, product_description, product_price, product_stock_level) VALUES (NULL, \"Intel Core i9 9900K\", \"top tier processor\", 504.99, 29);");

        return commands;
    }

}
