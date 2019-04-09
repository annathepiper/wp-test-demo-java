import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestMedia
 * @author Angela Korra'ti
 *
 * Last updated 4/9/2019
 * This class contains test cases related to media on the Wordpress test site.
 */
public class TestMedia extends BaseTest {

    /**
     * TestGetMediaReturnsMedia
     * Verify that the GetMedia endpoint actually returns data. There should be a JSON array of length > 0.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetMediaReturnsMedia() throws UnirestException {
        wpLogger.info("Testing the Get Media endpoint.");
        JSONArray response = wpTC.getMedia();
        Assert.assertTrue(response.length() > 0,
                "GetMedia endpoint not returning at least one object in JSONArray.");
        Assert.assertNotNull(response,"GetMedia endpoint returned a null response.");
    }

    /**
     * TestGetMediaById
     * Verify that you can get a media item by a specific ID off the GetMedia endpoint. Uses a test ID set in the
     * properties file and retrieved by the BaseTest class.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetMediaById() throws UnirestException {
        wpLogger.info("Testing the Get Media by Id endpoint.");
        JSONObject response = wpTC.getMediaById(getMediaId);
        JSONObject renderedTitle = response.getJSONObject("title");
        Assert.assertNotNull(response,"GetMedia endpoint returned a null object. Media may not exist.");
        Assert.assertEquals(response.get("id").toString(), getMediaId,
                "GetMedia endpoint didn't return correct media ID.");
        Assert.assertEquals(renderedTitle.get("rendered"), getMediaTitle,
                "Retrieved media from GetMedia endpoint does not have expected title.");
    }

    /**
     * TestGetMediaIdThatDoesNotExist
     * Verify that the Get Media by Id endpoint exhibits expected error behavior if you throw it a media ID that
     * doesn't actually exist.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetMediaIdThatDoesNotExist() throws UnirestException {
        wpLogger.info("Testing giving a media ID that doesn't exist to the Get Media by Id endpoint.");
        JSONObject response = wpTC.getMediaById(getNonExistentId);
        wpTestLib.VerifyResponseItemDoesNotExist(response, getNonExistentCode, getNonExistentMessage);
    }

    /**
     * TestGetMediaIdBadId
     * Verify that the Get Media by Id endpoint throws expected error behavior if given invalid data for its media ID.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetMediaIdBadId() throws UnirestException {
        wpLogger.info("Testing giving a bad media ID to the Get Media by Id endpoint.");
        JSONObject response = wpTC.getMediaById(getInvalidId);
        wpTestLib.VerifyResponseItemIsInvalid(response, getInvalidCode, getInvalidMessage);
    }

    /**
     * TestGetMediaIdMaxInt
     * Verify that the Get Media by Id endpoint throws error behavior when using Integer.MAX_VALUE as a media ID.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetMediaIdMaxInt() throws UnirestException {
        wpLogger.info("Testing giving MAX_VALUE Integer to the Get Media by Id endpoint.");
        JSONObject response = wpTC.getMediaById(Integer.toString(Integer.MAX_VALUE));
        wpTestLib.VerifyResponseItemDoesNotExist(response, getNonExistentCode, getNonExistentMessage);
    }

    /**
     * TestGetMedidaIdMinInt
     * Verify that the Get Media by Id endpoint throws error behavior when using Integer.MIN_VALUE as a media ID.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetMediaIdMinInt() throws UnirestException {
        wpLogger.info("Testing giving MIN_VALUE Integer to the Get Media by Id endpoint.");
        JSONObject response = wpTC.getMediaById(Integer.toString(Integer.MIN_VALUE));
        wpTestLib.VerifyResponseItemIsInvalid(response, getInvalidCode, getInvalidMessage);
    }
}
