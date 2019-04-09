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
    String getCategoryId;
    String getCategoryName;
    String getTagId;
    String getTagName;
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

    // For negative test cases
    String getNonExistentId;
    String getNonExistentMessage;
    String getNonExistentCode;
    String getInvalidId;
    String getInvalidMessage;
    String getInvalidCode;
    String getTermNonExistentId;
    String getTermNonExistentMessage;
    String getTermNonExistentCode;
    String getCommentNonExistentId;
    String getCommentNonExistentMessage;
    String getCommentNonExistentCode;
    String getTaxonomyNonExistentTag;
    String getTaxonomyNonExistentMessage;
    String getTaxonomyNonExistentCode;
    String getTaxonomyInvalidTag;

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

        // For negative test cases
        getNonExistentId = rb.getString("getNonExistentId");
        getNonExistentMessage = rb.getString("getNonExistentMessage");
        getNonExistentCode=rb.getString("getNonExistentCode");
        getInvalidId = rb.getString("getInvalidId");
        getInvalidMessage = rb.getString("getInvalidMessage");
        getInvalidCode = rb.getString("getInvalidCode");
        getTermNonExistentId = rb.getString("getTermNonExistentId");
        getTermNonExistentMessage = rb.getString("getTermNonExistentMessage");
        getTermNonExistentCode=rb.getString("getTermNonExistentCode");
        getCommentNonExistentId = rb.getString("getCommentNonExistentId");
        getCommentNonExistentMessage = rb.getString("getCommentNonExistentMessage");
        getCommentNonExistentCode=rb.getString("getCommentNonExistentCode");
        getTaxonomyNonExistentTag = rb.getString("getTaxonomyNonExistentTag");
        getTaxonomyNonExistentMessage = rb.getString("getTaxonomyNonExistentMessage");
        getTaxonomyNonExistentCode=rb.getString("getTaxonomyNonExistentCode");
        getTaxonomyInvalidTag = rb.getString("getTaxonomyInvalidTag");

        // We also need a WPTestClient object for all the tests to use
        wpTC = new WPTestClient(protocol, host);

        // And a WPTestLib object
        wpTestLib = new WPTestLib();
    }
}
