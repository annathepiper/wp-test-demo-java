import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestTags
 * @author Angela Korra'ti
 *
 * Last updated 1/23/2019
 * This class contains test cases related to tags on the Wordpress test site.
 */
public class TestTags extends BaseTest {

    /**
     * TestGetTagsReturnsTags
     * Verify that the GetTags endpoint actually returns data. There should be a JSON array of length > 0.
     * @throws UnirestException
     */
    @Test
    public void TestGetTagsReturnsTags() throws UnirestException {
        JSONArray response = wpTC.getTags();
        Assert.assertTrue(response.length() > 0,
                "GetTags endpoint not returning at least one object in JSONArray.");
        Assert.assertNotNull(response,"GetTags endpoint returned a null response.");
    }

    /**
     * TestGetTagById
     * Verify that you can get a tag by a specific ID off the GetTags endpoint. Uses a test ID set in the
     * properties file and retrieved by the BaseTest class.
     * @throws UnirestException
     */
    @Test
    public void TestGetTagById() throws UnirestException {
        JSONObject response = wpTC.getTag(getTagId);
        String tagName = response.getString("name");
        Assert.assertNotNull(response,"GetTag endpoint returned a null object. Tag may not exist.");
        Assert.assertEquals(response.get("id").toString(), getTagId,
                "GetTag endpoint didn't return correct ID number.");
        Assert.assertEquals(tagName, getTagName,
                "Retrieved tag from GetTag endpoint does not have expected name.");
    }
}
