import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestPages
 * @author Angela Korra'ti
 *
 * Last updated 4/9/2019
 * This class contains test cases related to pages on the Wordpress test site.
 */
public class TestPages extends BaseTest {

    /**
     * TestGetPagesReturnsPages
     * Verify that the GetPages endpoint actually returns data. There should be a JSON array of length > 0.
     * @throws UnirestException if the Unirest call goes wrong somehow
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
     * @throws UnirestException if the Unirest call goes wrong somehow
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

    /**
     * TestGetPageIdThatDoesNotExist
     * Verify that the Get Page by Id endpoint exhibits expected error behavior if you throw it a page ID that
     * doesn't actually exist.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetPageIdThatDoesNotExist() throws UnirestException {
        wpLogger.info("Testing giving a page ID that doesn't exist to the Get Page by Id endpoint.");
        JSONObject response = wpTC.getPage(getNonExistentId);
        wpTestLib.VerifyResponseItemDoesNotExist(response, getNonExistentCode, getNonExistentMessage);
    }

    /**
     * TestGetPageIdBadId
     * Verify that the Get Page by Id endpoint throws expected error behavior if given invalid data for its page ID.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetPageIdBadId() throws UnirestException {
        wpLogger.info("Testing giving a bad page ID to the Get Page by Id endpoint.");
        JSONObject response = wpTC.getPage(getInvalidId);
        wpTestLib.VerifyResponseItemIsInvalid(response, getInvalidCode, getInvalidMessage);
    }

    /**
     * TestGetPageIdMaxInt
     * Verify that the Get Page by Id endpoint throws error behavior when using Integer.MAX_VALUE as a page ID.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetPageIdMaxInt() throws UnirestException {
        wpLogger.info("Testing giving MAX_VALUE Integer to the Get Page by Id endpoint.");
        JSONObject response = wpTC.getPage(Integer.toString(Integer.MAX_VALUE));
        wpTestLib.VerifyResponseItemDoesNotExist(response, getNonExistentCode, getNonExistentMessage);
    }

    /**
     * TestGetPageIdMinInt
     * Verify that the Get Page by Id endpoint throws error behavior when using Integer.MIN_VALUE as a page ID.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetPageIdMinInt() throws UnirestException {
        wpLogger.info("Testing giving MIN_VALUE Integer to the Get Page by Id endpoint.");
        JSONObject response = wpTC.getPage(Integer.toString(Integer.MIN_VALUE));
        wpTestLib.VerifyResponseItemIsInvalid(response, getInvalidCode, getInvalidMessage);
    }
}
