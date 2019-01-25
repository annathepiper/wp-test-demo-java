import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestMedia
 * @author Angela Korra'ti
 *
 * Last updated 1/25/2019
 * This class contains test cases related to media on the Wordpress test site.
 */
public class TestMedia extends BaseTest {

    /**
     * TestGetMediaReturnsMedia
     * Verify that the GetMedia endpoint actually returns data. There should be a JSON array of length > 0.
     * @throws UnirestException
     */
    @Test
    public void TestGetMediaReturnsMedia() throws UnirestException {
        JSONArray response = wpTC.getMedia();
        Assert.assertTrue(response.length() > 0,
                "GetMedia endpoint not returning at least one object in JSONArray.");
        Assert.assertNotNull(response,"GetMedia endpoint returned a null response.");
    }

    /**
     * TestGetMediaById
     * Verify that you can get a media item by a specific ID off the GetMedia endpoint. Uses a test ID set in the
     * properties file and retrieved by the BaseTest class.
     * @throws UnirestException
     */
    @Test
    public void TestGetMediaById() throws UnirestException {
        JSONObject response = wpTC.getMediaById(getMediaId);
        JSONObject renderedTitle = response.getJSONObject("title");
        Assert.assertNotNull(response,"GetMedia endpoint returned a null object. Media may not exist.");
        Assert.assertEquals(response.get("id").toString(), getMediaId,
                "GetMedia endpoint didn't return correct media ID.");
        Assert.assertEquals(renderedTitle.get("rendered"), getMediaTitle,
                "Retrieved media from GetMedia endpoint does not have expected title.");
    }
}
