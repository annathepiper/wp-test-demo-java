import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestComments
 * @author Angela Korra'ti
 *
 * Last updated 1/23/2019
 * This class contains test cases related to comments on the Wordpress test site.
 */
public class TestComments extends BaseTest {

    /**
     * TestGetCommentsReturnsComments
     * Verify that the GetComments endpoint actually returns data. There should be a JSON array of length > 0.
     * @throws UnirestException
     */
    @Test
    public void TestGetCommentsReturnsComments() throws UnirestException {
        JSONArray response = wpTC.getComments();
        Assert.assertTrue(response.length() > 0,
                "GetComments endpoint not returning at least one object in JSONArray.");
        Assert.assertNotNull(response,"GetComments endpoint returned a null response.");
    }

    /**
     * TestGetCommentById
     * Verify that you can get a comment by a specific ID off the GetComments endpoint. Uses a test ID set in the
     * properties file and retrieved by the BaseTest class.
     * @throws UnirestException
     */
    @Test
    public void TestGetCommentById() throws UnirestException {
        JSONObject response = wpTC.getComment(getCommentId);
        JSONObject renderedContent = response.getJSONObject("content");
        Assert.assertNotNull(response,"GetComment endpoint returned a null object. Comment may not exist.");
        Assert.assertEquals(response.get("id").toString(), getCommentId,
                "GetComment endpoint didn't return correct ID number.");
        Assert.assertTrue(renderedContent.get("rendered").toString().contains(getCommentContent),
                "Retrieved comment from GetComment endpoint does not have expected content.");
    }
}
