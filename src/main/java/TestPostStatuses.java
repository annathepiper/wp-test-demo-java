import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestPostStatuses
 * @author Angela Korra'ti
 *
 * Last updated 4/9/2019
 * This class contains test cases related to post statuses on the Wordpress test site.
 */
public class TestPostStatuses extends BaseTest {

    /**
     * TestGetPostStatusesReturnsPostStatuses
     * Verify that the GetPostStatuses endpoint actually returns data. There should be a JSON array of length > 0.
     * @throws UnirestException if the Unirest call goes wrong somehow
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
     * @throws UnirestException if the Unirest call goes wrong somehow
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

    /**
     * TestGetPostStatusTagThatDoesNotExist
     * Verify that the Get Post Status by Tag endpoint exhibits expected error behavior if you throw it a post status
     * tag that doesn't actually exist.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetPostStatusTagThatDoesNotExist() throws UnirestException {
        wpLogger.info("Testing giving a post status tag that doesn't exist to the Get Post Status by Tag endpoint.");
        JSONObject response = wpTC.getPostStatus(getPostStatusNonExistentTag);
        wpTestLib.VerifyResponseItemDoesNotExist(response, getPostStatusNonExistentCode,
                getPostStatusNonExistentMessage);
    }

    /**
     * TestGetPostStatusBadTag
     * Verify that the Get Post Status by Tag endpoint throws expected error behavior if given invalid data for its post
     * status tag.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetPostStatusBadTag() throws UnirestException {
        wpLogger.info("Testing giving a bad post status tag to the Get Post Status by Tag endpoint.");
        JSONObject response = wpTC.getPostStatus(getPostStatusInvalidTag);
        wpTestLib.VerifyResponseItemIsInvalid(response, getInvalidCode, getInvalidMessage);
    }

    /**
     * TestGetPostStatusTagMaxInt
     * Verify that the Get Post Status by Tag endpoint throws error behavior when using Integer.MAX_VALUE as a post
     * status tag.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetPostStatusTagMaxInt() throws UnirestException {
        wpLogger.info("Testing giving MAX_VALUE Integer to the Get Post Status by Tag endpoint.");
        JSONObject response = wpTC.getPostStatus(Integer.toString(Integer.MAX_VALUE));
        wpTestLib.VerifyResponseItemDoesNotExist(response, getPostStatusNonExistentCode,
                getPostStatusNonExistentMessage);
    }

    /**
     * TestGetPostStatusTagMinInt
     * Verify that the Get Post Status by Tag endpoint throws error behavior when using Integer.MIN_VALUE as a post
     * status tag.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetPostStatusTagMinInt() throws UnirestException {
        wpLogger.info("Testing giving MIN_VALUE Integer to the Get Post Status by Tag endpoint.");
        JSONObject response = wpTC.getPostStatus(Integer.toString(Integer.MIN_VALUE));
        wpTestLib.VerifyResponseItemIsInvalid(response, getPostStatusNonExistentCode, getPostStatusNonExistentMessage);
    }
}
