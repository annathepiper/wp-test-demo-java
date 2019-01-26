import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestPages
 * @author Angela Korra'ti
 *
 * Last updated 1/25/2019
 * This class contains test cases related to pages on the Wordpress test site.
 */
public class TestPages extends BaseTest {

    /**
     * TestGetPagesReturnsPages
     * Verify that the GetPages endpoint actually returns data. There should be a JSON array of length > 0.
     * @throws UnirestException
     */
    @Test
    public void TestGetPagesReturnsPages() throws UnirestException {
        wpLogger.info("Testing the Get Pages endpoint.");
        JSONArray response = wpTC.getPages();
        Assert.assertTrue(response.length() > 0,
                "GetPages endpoint not returning at least one object in JSONArray.");
        Assert.assertNotNull(response,"GetPages endpoint returned a null response.");
    }

    /**
     * TestPageTagById
     * Verify that you can get a page by a specific ID off the GetPages endpoint. Uses a test ID set in the
     * properties file and retrieved by the BaseTest class.
     * @throws UnirestException
     */
    @Test
    public void TestGetPageById() throws UnirestException {
        wpLogger.info("Testing the Get Page by Id endpoint.");
        JSONObject response = wpTC.getPage(getPageId);
        JSONObject renderedTitle = response.getJSONObject("title");
        Assert.assertNotNull(response,"GetPages endpoint returned a null object. Page may not exist.");
        Assert.assertEquals(response.get("id").toString(), getPageId,
                "GetPage endpoint didn't return correct ID number.");
        Assert.assertEquals(renderedTitle.get("rendered"), getPageTitle,
                "Retrieved page from GetPage endpoint does not have expected title.");
    }
}
