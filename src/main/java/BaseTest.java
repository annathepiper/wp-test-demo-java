import org.testng.annotations.BeforeClass;
import java.util.ResourceBundle;

/**
 * BaseTest
 * @author Angela Korra'ti
 *
 * Last updated 1/22/2019
 * This test class is the master class for the TestNG suite. Does necessary setup work for all classes.
 */
public class BaseTest {
    public String protocol;
    public String host;
    public String getPostId;
    private static ResourceBundle rb = ResourceBundle.getBundle("wp-test-demo");
    public WPTestClient wpTC;

    @BeforeClass
    public void setup() {
        // Get the needed properties out of the file
        protocol = rb.getString("protocol");
        host = rb.getString("host");
        getPostId = rb.getString("getPostId");

        // We also need a WPTestClient object for all the tests to use
        wpTC = new WPTestClient(protocol, host, getPostId);
    }
}
