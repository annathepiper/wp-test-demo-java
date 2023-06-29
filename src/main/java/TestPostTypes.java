import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestPostTypes
 * @author Angela Korra'ti
 *
 * Last updated 4/9/2019
 * This class contains test cases related to post types on the Wordpress test site.
 */
public class TestPostTypes extends BaseTest {

    /**
     * TestGetPostTypesReturnsPostTypes
     * Verify that the GetPostTypes endpoint actually returns data. There should be a JSON object of length == 4.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetPostTypesReturnsPostTypes() throws UnirestException {
        wpLogger.info("Testing the Get Post Types endpoint.");
        JSONObject response = wpTC.getPostTypes();
        Assert.assertEquals(response.length(), 8,
                "GetPostTypes endpoint not returning at least one object in JSONArray.");
        Assert.assertNotNull(response,"GetPostTypes endpoint returned a null response.");
    }

    /**
     * TestGetPostTypeByTag
     * Verify that you can get a post type by a specific tag off the GetPostTypes endpoint. Uses a test tag set in the
     * properties file and retrieved by the BaseTest class.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetPostTypeByTag() throws UnirestException {
        wpLogger.info("Testing the Get Post Type by Tag endpoint.");
        JSONObject response = wpTC.getPostType(getPostTypeTag);
        String postTypeName = response.getString("name");
        Assert.assertNotNull(response,"GetPostTypes endpoint returned a null object. Post Type may not exist.");
        Assert.assertEquals(response.get("slug"), getPostTypeTag,
                "GetPostTypes endpoint didn't return correct post type tag.");
        Assert.assertEquals(postTypeName, getPostTypeName,
                "Retrieved post type from GetPostTypes endpoint does not have expected name.");
    }

    /**
     * TestGetPostTypeTagThatDoesNotExist
     * Verify that the Get Post Type by Tag endpoint exhibits expected error behavior if you throw it a post type tag
     * that doesn't actually exist.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetPostTypeTagThatDoesNotExist() throws UnirestException {
        wpLogger.info("Testing giving a post type tag that doesn't exist to the Get Post Type by Tag endpoint.");
        JSONObject response = wpTC.getPostType(getNonExistentTag);
        wpTestLib.VerifyResponseItemDoesNotExist(response, getPostTypeNonExistentCode, getPostTypeNonExistentMessage);
    }

    /**
     * TestGetPostTypeBadTag
     * Verify that the Get Post Type by Tag endpoint throws expected error behavior if given invalid data for its post
     * type tag.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetPostTypeBadTag() throws UnirestException {
        wpLogger.info("Testing giving a bad post type tag to the Get Post Type by Tag endpoint.");
        JSONObject response = wpTC.getPostType(getInvalidTag);
        wpTestLib.VerifyResponseItemIsInvalid(response, getInvalidCode, getInvalidMessage);
    }

    /**
     * TestGetPostTypeTagMaxInt
     * Verify that the Get Post Type by Tag endpoint throws error behavior when using Integer.MAX_VALUE as a post
     * type tag.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetPostTypeTagMaxInt() throws UnirestException {
        wpLogger.info("Testing giving MAX_VALUE Integer to the Get Post Type by Tag endpoint.");
        JSONObject response = wpTC.getPostType(Integer.toString(Integer.MAX_VALUE));
        wpTestLib.VerifyResponseItemDoesNotExist(response, getPostTypeNonExistentCode, getPostTypeNonExistentMessage);
    }

    /**
     * TestGetPostTypeTagMinInt
     * Verify that the Get Post Type by Tag endpoint throws error behavior when using Integer.MIN_VALUE as a post
     * type tag.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetPostTypeTagMinInt() throws UnirestException {
        wpLogger.info("Testing giving MIN_VALUE Integer to the Get Post Type by Tag endpoint.");
        JSONObject response = wpTC.getPostType(Integer.toString(Integer.MIN_VALUE));
        wpTestLib.VerifyResponseItemIsInvalid(response, getPostTypeNonExistentCode, getPostTypeNonExistentMessage);
    }
}
