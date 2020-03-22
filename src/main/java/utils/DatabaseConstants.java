package utils;

public class DatabaseConstants {

    private DatabaseConstants() {
        // helper class
    }

    // Database Constants
    public static final String DB_HOST = "db_host";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String SETUP_FILE = "setup_file";

    // Re-Order Level Constants
    public static final String REORDER_LEVEL = "reorder_level";
    public static final String REORDER_STOCK = "reorder_stock";

    // Query Constants
    public static final String SELECT_ALL = "select_all";
    public static final String PRODUCT_BY_ID = "product_by_id";
    public static final String ADJUST_STOCK_LEVEL = "adjust_stock_level";

    // Replace Constants
    public static final String REPLACE_PRODUCT_ID = "REPLACE_PRODUCT_ID";
    public static final String REPLACE_ADJUST_VALUE = "REPLACE_ADJUST_VALUE";

    // Product Attribute Constants
    public static final String ID = "product_id";
    public static final String NAME = "product_name";
    public static final String DESCRIPTION = "product_description";
    public static final String PRICE = "product_price";
    public static final String STOCK_LEVEL = "product_stock_level";

}
