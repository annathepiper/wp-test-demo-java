import org.testng.annotations.BeforeClass;
import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * BaseTest
 * @author Angela Korra'ti
 *
 * Last updated 4/8/2019
 * This test class is the master class for the TestNG suite. Does necessary setup work for all classes.
 */
public abstract class BaseTest {
    // Use these for the WP Test Client
    private String protocol;
    private String host;

    // Use these for test default values
    protected String getPostId;
    protected String getPostTitle;
    protected String getPostNonExistentId;
    protected String getPostNonExistentMessage;
    protected String getPostInvalidId;
    protected String getPostInvalidMessage;
    protected String getCategoryId;
    protected String getCategoryName;
    protected String getTagId;
    protected String getTagName;
    protected String getPageId;
    protected String getPageTitle;
    protected String getCommentId;
    protected String getCommentContent;
    protected String getTaxonomyTag;
    protected String getTaxonomyName;
    protected String getMediaId;
    protected String getMediaTitle;
    protected String getUserId;
    protected String getUserName;
    protected String getPostTypeTag;
    protected String getPostTypeName;
    protected String getPostStatusTag;
    protected String getPostStatusName;

    // For logging purposes
    protected final Logger wpLogger = LogManager.getLogger(this.getClass().getName());

    // Resource bundle we're using to pull all the property strings out of
    private static ResourceBundle rb = ResourceBundle.getBundle("wp-test-demo");

    // For generating the instance of the WP Test Client
    protected WPTestClient wpTC;

    @BeforeClass
    protected void setup() {
        // Get the needed properties out of the file
        protocol = rb.getString("protocol");
        host = rb.getString("host");
        getPostId = rb.getString("getPostId");
        getPostTitle = rb.getString("getPostTitle");
        getPostNonExistentId = rb.getString("getPostNonExistentId");
        getPostNonExistentMessage = rb.getString("getPostNonExistentMessage");
        getPostInvalidId = rb.getString("getPostInvalidId");
        getPostInvalidMessage=rb.getString("getPostInvalidMessage");
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
        if (System.getProperty("os.name").startsWith("Windows")) {
            getMediaId = rb.getString("getMediaIdWindows");
        }
        else {
            getMediaId = rb.getString("getMediaId");
        }
        getMediaTitle = rb.getString("getMediaTitle");
        getUserId = rb.getString("getUserId");
        getUserName = rb.getString("getUserName");
        getPostTypeTag = rb.getString("getPostTypeTag");
        getPostTypeName = rb.getString("getPostTypeName");
        getPostStatusTag = rb.getString("getPostStatusTag");
        getPostStatusName = rb.getString("getPostStatusName");

        // We also need a WPTestClient object for all the tests to use
        wpTC = new WPTestClient(protocol, host);
    }
}
