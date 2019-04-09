import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestPosts
 * @author Angela Korra'ti
 *
 * Last updated 4/9/2019
 * This class contains test cases related to posts on the Wordpress test site.
 */
public class TestPosts extends BaseTest {

    /**
     * TestGetPostsReturnsPosts
     * Verify that the GetPosts endpoint actually returns data. There should be a JSON array of length > 0.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetPostsReturnsPosts() throws UnirestException {
        wpLogger.info("Testing that the Get Posts endpoint returns posts.");
        JSONArray response = wpTC.getPosts();
        Assert.assertTrue(response.length() > 0, "GetPosts endpoint not returning at least one object in JSONArray.");
        Assert.assertNotNull(response,"GetPosts endpoint returned a null response.");
    }

    /**
     * TestGetPostById
     * Verify that you can get a post by a specific ID off the GetPosts endpoint. Uses a test ID set in the
     * properties file and retrieved by the BaseTest class.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetPostById() throws UnirestException {
        wpLogger.info("Testing that the Get Post by Id endpoint returns a post for a valid Id.");
        JSONObject response = wpTC.getPost(getPostId);
        JSONObject renderedTitle = response.getJSONObject("title");
        Assert.assertNotNull(response,"Get Post by Id endpoint returned a null object. Post may not exist.");
        Assert.assertEquals(response.get("id").toString(), getPostId,
                "Get Post by Id endpoint didn't return correct ID number.");
        Assert.assertEquals(renderedTitle.get("rendered"), getPostTitle,
                "Retrieved post from Get Post by Id endpoint does not have expected title.");
    }

    /**
     * TestGetPostIdThatDoesNotExist
     * Verify that the Get Post by Id endpoint exhibits expected error behavior if you throw it a post ID that
     * doesn't actually exist.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetPostIdThatDoesNotExist() throws UnirestException {
        wpLogger.info("Testing giving a post ID that doesn't exist to the Get Post by Id endpoint.");
        JSONObject response = wpTC.getPost(getPostNonExistentId);
        Assert.assertEquals(response.get("code"), getPostNonExistentCode,
                "Get Post by Id endpoint thinks this post ID actually exists.");
        Assert.assertEquals(response.get("message"), getPostNonExistentMessage,
                "Get Post by Id endpoint didn't throw the expected error message.");
        JSONObject responseData = response.getJSONObject("data");
        Assert.assertNotNull(responseData, "Get Post by Id endpoint didn't include data object in response.");
        Assert.assertEquals(responseData.get("status").toString(), "404",
                "Get Post by Id endpoint didn't return expected error code.");
    }

    /**
     * TestGetPostIdBadId
     * Verify that the Get Post by Id endpoint throws expected error behavior if given invalid data for its post ID.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetPostIdBadId() throws UnirestException {
        wpLogger.info("Testing giving a bad post ID to the Get Post by Id endpoint.");
        JSONObject response = wpTC.getPost(getInvalidId);
        Assert.assertEquals(response.get("code"), getInvalidCode,
                "Get Post by Id endpoint thinks this post ID is actually valid.");
        Assert.assertEquals(response.get("message"), getInvalidMessage,
                "Get Post by Id endpoint didn't throw the expected error message.");
        JSONObject responseData = response.getJSONObject("data");
        Assert.assertNotNull(responseData, "Get Post by Id endpoint didn't include data object in response.");
        Assert.assertEquals(responseData.get("status").toString(), "404",
                "Get Post by Id endpoint didn't return expected error code.");
    }

    /**
     * TestGetPostIdMaxInt
     * Verify that the Get Post by Id endpoint throws error behavior when using Integer.MAX_VALUE as a post ID.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetPostIdMaxInt() throws UnirestException {
        wpLogger.info("Testing giving MAX_VALUE Integer to the Get Post by Id endpoint.");
        JSONObject response = wpTC.getPost(Integer.toString(Integer.MAX_VALUE));
        Assert.assertEquals(response.get("code"), getPostNonExistentCode,
                "Get Post by Id endpoint thinks this post ID actually exists.");
        Assert.assertEquals(response.get("message"), getPostNonExistentMessage,
                "Get Post by Id endpoint didn't throw the expected error message.");
        JSONObject responseData = response.getJSONObject("data");
        Assert.assertNotNull(responseData, "Get Post by Id endpoint didn't include data object in response.");
        Assert.assertEquals(responseData.get("status").toString(), "404",
                "Get Post by Id endpoint didn't return expected error code.");
    }

    /**
     * TestGetPostIdMinInt
     * Verify that the Get Post by Id endpoint throws error behavior when using Integer.MIN_VALUE as a post ID.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetPostIdMinInt() throws UnirestException {
        wpLogger.info("Testing giving MIN_VALUE Integer to the Get Post by Id endpoint.");
        JSONObject response = wpTC.getPost(Integer.toString(Integer.MIN_VALUE));
        Assert.assertEquals(response.get("code"), getInvalidCode,
                "Get Post by Id endpoint thinks this post ID is actually valid.");
        Assert.assertEquals(response.get("message"), getInvalidMessage,
                "Get Post by Id endpoint didn't throw the expected error message.");
        JSONObject responseData = response.getJSONObject("data");
        Assert.assertNotNull(responseData, "Get Post by Id endpoint didn't include data object in response.");
        Assert.assertEquals(responseData.get("status").toString(), "404",
                "Get Post by Id endpoint didn't return expected error code.");
    }
}
