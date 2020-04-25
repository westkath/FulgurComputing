import data.DBConnection;
import data.DBSetupFromStub;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import utils.Helper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static utils.DatabaseConstants.*;

public class DatabaseTest {

    private DBConnection connection;

    @Before
    public void setup() {
        Helper helper = Mockito.spy(Helper.class);
        Mockito.doReturn("jdbc:sqlite:test.db").when(helper).getProperty(DB_HOST);

        connection = DBConnection.getInstance();
        connection.selectSetup(new DBSetupFromStub());
    }

    @Test
    public void checkup() {
        connection.setupDatabase();
        ResultSet results = connection.getProductsInTable();

        if (results != null) {
            try {
                while (results.next()) {
                    int productId = results.getInt(ID);
                    String name = results.getString(NAME);
                    String description = results.getString(DESCRIPTION);
                    String price = results.getString(PRICE);
                    String stockLevel = results.getString(STOCK_LEVEL);

                    System.out.println("productId: " + productId);
                    System.out.println("name: " + name);
                    System.out.println("description: " + description);
                    System.out.println("price: " + price);
                    System.out.println("stockLevel: " + stockLevel);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
