import data.DBConnection;
import data.DBSetupFromStub;
import org.junit.Before;

public class DatabaseTest {

    private DBConnection connection;

    @Before
    public void setup() {
        connection = DBConnection.getInstance();
        connection.selectSetup(new DBSetupFromStub());
    }

}
