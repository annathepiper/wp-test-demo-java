import org.testng.annotations.BeforeClass;
import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * BaseTest
 * @author Angela Korra'ti
 *
 * Last updated 4/9/2019
 * This test class is the master class for the TestNG suite. Does necessary setup work for all classes.
 */
public abstract class BaseTest {
    // Use these for test default values
    String getPostId;
    String getPostTitle;
    String getNonExistentId;
    String getNonExistentMessage;
    String getNonExistentCode;
    String getInvalidId;
    String getInvalidMessage;
    String getInvalidCode;
    String getCategoryId;
    String getCategoryName;
    String getCategoryNonExistentId;
    String getCategoryNonExistentMessage;
    String getCategoryNonExistentCode;
    String getTagId;
    String getTagName;
    String getTagNonExistentId;
    String getTagNonExistentMessage;
    String getTagNonExistentCode;
    String getPageId;
    String getPageTitle;
    String getCommentId;
    String getCommentContent;
    String getTaxonomyTag;
    String getTaxonomyName;
    String getMediaId;
    String getMediaTitle;
    String getUserId;
    String getUserName;
    String getPostTypeTag;
    String getPostTypeName;
    String getPostStatusTag;
    String getPostStatusName;

    // For logging purposes
    protected final Logger wpLogger = LogManager.getLogger(this.getClass().getName());

    // Resource bundle we're using to pull all the property strings out of
    private static ResourceBundle rb = ResourceBundle.getBundle("wp-test-demo");

    // For generating the instance of the WP Test Client
    protected WPTestClient wpTC;

    // For generating the instance of the WPTestLib class
    WPTestLib wpTestLib;

    @BeforeClass
    protected void setup() {
        // Get the needed properties out of the file
        String protocol = rb.getString("protocol");
        String host = rb.getString("host");
        getPostId = rb.getString("getPostId");
        getPostTitle = rb.getString("getPostTitle");
        getNonExistentId = rb.getString("getNonExistentId");
        getNonExistentMessage = rb.getString("getNonExistentMessage");
        getNonExistentCode=rb.getString("getNonExistentCode");
        getInvalidId = rb.getString("getInvalidId");
        getInvalidMessage = rb.getString("getInvalidMessage");
        getInvalidCode = rb.getString("getInvalidCode");
        getCategoryId = rb.getString("getCategoryId");
        getCategoryName = rb.getString("getCategoryName");
        getCategoryNonExistentId = rb.getString("getCategoryNonExistentId");
        getCategoryNonExistentMessage = rb.getString("getCategoryNonExistentMessage");
        getCategoryNonExistentCode=rb.getString("getCategoryNonExistentCode");
        getTagId = rb.getString("getTagId");
        getTagName = rb.getString("getTagName");
        getTagNonExistentId = rb.getString("getTagNonExistentId");
        getTagNonExistentMessage = rb.getString("getTagNonExistentMessage");
        getTagNonExistentCode=rb.getString("getTagNonExistentCode");
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

        // And a WPTestLib object
        wpTestLib = new WPTestLib();
    }
}
