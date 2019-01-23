import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestPosts
 * @author Angela Korra'ti
 *
 * Last updated 1/23/2019
 * This class contains test cases related to posts on the Wordpress test site.
 */
public class TestPosts extends BaseTest {

    /**
     * TestGetPostsReturnsPosts
     * Verify that the GetPosts endpoint actually returns data. There should be a JSON array of length > 0.
     * @throws UnirestException
     */
    @Test
    public void TestGetPostsReturnsPosts() throws UnirestException {
        JSONArray response = wpTC.getPosts();
        Assert.assertTrue(response.length() > 0, "GetPosts endpoint not returning at least one object in JSONArray.");
        Assert.assertNotNull(response,"GetPosts endpoint returned a null response.");
    }

    /**
     * TestGetPostById
     * Verify that you can get a post by a specific ID off the GetPosts endpoint. Uses a test ID set in the
     * properties file and retrieved by the BaseTest class.
     * @throws UnirestException
     */
    @Test
    public void TestGetPostById() throws UnirestException {
        JSONObject response = wpTC.getPost(getPostId);
        JSONObject renderedTitle = response.getJSONObject("title");
        Assert.assertNotNull(response,"GetPosts endpoint returned a null object. Post may not exist.");
        Assert.assertEquals(response.get("id").toString(), getPostId,
                "GetPosts endpoint didn't return correct ID number.");
        Assert.assertEquals(renderedTitle.get("rendered"), getPostTitle,
                "Retrieved post from GetPosts endpoint does not have expected title.");
    }
}
