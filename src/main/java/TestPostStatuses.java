import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestPostStatuses
 * @author Angela Korra'ti
 *
 * Last updated 3/20/2019
 * This class contains test cases related to post statuses on the Wordpress test site.
 */
public class TestPostStatuses extends BaseTest {

    /**
     * TestGetPostStatusesReturnsPostStatuses
     * Verify that the GetPostStatuses endpoint actually returns data. There should be a JSON array of length > 0.
     * @throws UnirestException
     */
    @Test
    public void TestGetPostStatusesReturnsPostStatuses() throws UnirestException {
        wpLogger.info("Testing the Get Post Statuses endpoint.");
        JSONObject response = wpTC.getPostStatuses();
        Assert.assertEquals(response.length(), 1,
                "GetPostStatuses endpoint not returning at least one object in JSONArray.");
        Assert.assertNotNull(response,"GetPostStatuses endpoint returned a null response.");
    }

    /**
     * TestGetPostStatusByTag
     * Verify that you can get a post status by a specific tag off the GetPostStatus endpoint. Uses a test tag set in the
     * properties file and retrieved by the BaseTest class.
     * @throws UnirestException
     */
    @Test
    public void TestGetPostStatusByTag() throws UnirestException {
        wpLogger.info("Testing the Get Post Status by Tag endpoint.");
        JSONObject response = wpTC.getPostStatus(getPostStatusTag);
        String postStatusName = response.getString("name");
        Assert.assertNotNull(response,"GetPostStatuses endpoint returned a null object. Post Status may not exist.");
        Assert.assertEquals(response.get("slug"), getPostStatusTag,
                "GetPostStatuses endpoint didn't return correct post status tag.");
        Assert.assertEquals(postStatusName, getPostStatusName,
                "Retrieved post status from GetPostStatuses endpoint does not have expected name.");
    }
}
