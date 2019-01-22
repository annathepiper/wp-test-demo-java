import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestPosts
 * @author Angela Korra'ti
 *
 * Last updated 1/21/2019
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
    }
}
