import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestPostTypes
 * @author Angela Korra'ti
 *
 * Last updated 1/25/2019
 * This class contains test cases related to post types on the Wordpress test site.
 */
public class TestPostTypes extends BaseTest {

    /**
     * TestGetPostTypesReturnsPostTypes
     * Verify that the GetPostTypes endpoint actually returns data. There should be a JSON array of length > 0.
     * @throws UnirestException
     */
    @Test
    public void TestGetPostTypesReturnsPostTypes() throws UnirestException {
        JSONArray response = wpTC.getPostTypes();
        Assert.assertTrue(response.length() > 0,
                "GetPostTypes endpoint not returning at least one object in JSONArray.");
        Assert.assertNotNull(response,"GetPostTypes endpoint returned a null response.");
    }

    /**
     * TestGetPostTypeByTag
     * Verify that you can get a post type by a specific tag off the GetPostTypes endpoint. Uses a test tag set in the
     * properties file and retrieved by the BaseTest class.
     * @throws UnirestException
     */
    @Test
    public void TestGetPostTypeByTag() throws UnirestException {
        JSONObject response = wpTC.getPostType(getPostTypeTag);
        String postTypeName = response.getString("name");
        Assert.assertNotNull(response,"GetPostTypes endpoint returned a null object. Post Type may not exist.");
        Assert.assertEquals(response.get("slug"), getPostTypeTag,
                "GetPostTypes endpoint didn't return correct post type tag.");
        Assert.assertEquals(postTypeName, getPostTypeName,
                "Retrieved post type from GetPostTypes endpoint does not have expected name.");
    }
}
