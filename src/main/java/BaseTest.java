import org.testng.annotations.BeforeClass;
import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * BaseTest
 * @author Angela Korra'ti
 *
 * Last updated 1/25/2019
 * This test class is the master class for the TestNG suite. Does necessary setup work for all classes.
 */
public class BaseTest {
    // Use these for the WP Test Client
    public String protocol;
    public String host;

    // Use these for test default values
    public String getPostId;
    public String getPostTitle;
    public String getCategoryId;
    public String getCategoryName;
    public String getTagId;
    public String getTagName;
    public String getPageId;
    public String getPageTitle;
    public String getCommentId;
    public String getCommentContent;
    public String getTaxonomyTag;
    public String getTaxonomyName;
    public String getMediaId;
    public String getMediaTitle;
    public String getUserId;
    public String getUserName;
    public String getPostTypeTag;
    public String getPostTypeName;

    // For logging purposes
    public final Logger wpLogger = LogManager.getLogger(this.getClass().getName());

    // Resource bundle we're using to pull all the property strings out of
    private static ResourceBundle rb = ResourceBundle.getBundle("wp-test-demo");

    // For generating the instance of the WP Test Client
    public WPTestClient wpTC;

    @BeforeClass
    public void setup() {
        // Get the needed properties out of the file
        protocol = rb.getString("protocol");
        host = rb.getString("host");
        getPostId = rb.getString("getPostId");
        getPostTitle = rb.getString("getPostTitle");
        getCategoryId = rb.getString("getCategoryId");
        getCategoryName = rb.getString("getCategoryName");
        getTagId = rb.getString("getTagId");
        getTagName = rb.getString("getTagName");
        getPageId = rb.getString("getPageId");
        getPageTitle = rb.getString("getPageTitle");
        getCommentId = rb.getString("getCommentId");
        getCommentContent = rb.getString("getCommentContent");
        getTaxonomyTag = rb.getString("getTaxonomyTag");
        getTaxonomyName = rb.getString("getTaxonomyName");
        getMediaId = rb.getString("getMediaId");
        getMediaTitle = rb.getString("getMediaTitle");
        getUserId = rb.getString("getUserId");
        getUserName = rb.getString("getUserName");
        getPostTypeTag = rb.getString("getPostTypeTag");
        getPostTypeName = rb.getString("getPostTypeName");

        // We also need a WPTestClient object for all the tests to use
        wpTC = new WPTestClient(protocol, host);
    }
}
