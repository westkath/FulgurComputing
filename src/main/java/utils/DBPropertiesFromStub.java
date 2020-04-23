package utils;

import java.util.Properties;

import static utils.DatabaseConstants.*;

public class DBPropertiesFromStub implements DBProperties {

    public DBPropertiesFromStub() {

    }

    @Override
    public Properties getProperties() {
        Properties properties = new Properties();

        properties.setProperty(DB_HOST, "jdbc:sqlite:test.db");
        properties.setProperty(PASSWORD, "password");
        properties.setProperty(SETUP_FILE, "setup.txt");
        properties.setProperty(REORDER_LEVEL, "3");
        properties.setProperty(REORDER_STOCK, "7");
        properties.setProperty(SELECT_ALL, "SELECT * FROM Product;");
        properties.setProperty(PRODUCT_BY_ID, "SELECT * FROM Product WHERE product_id=REPLACE_PRODUCT_ID;");
        properties.setProperty(ADJUST_STOCK_LEVEL, "UPDATE Product SET product_stock_level=product_stock_level + REPLACE_ADJUST_VALUE WHERE product_id=REPLACE_PRODUCT_ID;");

        return properties;
    }

}
