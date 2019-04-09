import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestTags
 * @author Angela Korra'ti
 *
 * Last updated 4/9/2019
 * This class contains test cases related to tags on the Wordpress test site.
 */
public class TestTags extends BaseTest {

    /**
     * TestGetTagsReturnsTags
     * Verify that the GetTags endpoint actually returns data. There should be a JSON array of length > 0.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetTagsReturnsTags() throws UnirestException {
        wpLogger.info("Testing that the Get Tags endpoint returns tags.");
        JSONArray response = wpTC.getTags();
        Assert.assertTrue(response.length() > 0,
                "Get Tags endpoint not returning at least one object in JSONArray.");
        Assert.assertNotNull(response,"Get Tags endpoint returned a null response.");
    }

    /**
     * TestGetTagById
     * Verify that you can get a tag by a specific ID off the GetTags endpoint. Uses a test ID set in the
     * properties file and retrieved by the BaseTest class.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetTagById() throws UnirestException {
        wpLogger.info("Testing that the Get Tag by Id endpoint returns data for a valid tag ID.");
        JSONObject response = wpTC.getTag(getTagId);
        String tagName = response.getString("name");
        Assert.assertNotNull(response,"Get Tag by Id endpoint returned a null object. Tag may not exist.");
        Assert.assertEquals(response.get("id").toString(), getTagId,
                "Get Tag by Id endpoint didn't return correct ID number.");
        Assert.assertEquals(tagName, getTagName,
                "Retrieved tag from Get Tag by Id endpoint does not have expected name.");
    }

    /**
     * TestGetTagIdThatDoesNotExist
     * Verify that the Get Tag by Id endpoint exhibits expected error behavior if you throw it a tag ID that
     * doesn't actually exist.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetTagIdThatDoesNotExist() throws UnirestException {
        wpLogger.info("Testing giving a tag ID that doesn't exist to the Get Tag by Id endpoint.");
        JSONObject response = wpTC.getTag(getNonExistentId);
        wpTestLib.VerifyResponseItemDoesNotExist(response, getTermNonExistentCode, getTermNonExistentMessage);
    }

    /**
     * TestGetTagIdBadId
     * Verify that the Get Tag by Id endpoint throws expected error behavior if given invalid data for its tag
     * ID.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetTagIdBadId() throws UnirestException {
        wpLogger.info("Testing giving a bad tag ID to the Get Tag by Id endpoint.");
        JSONObject response = wpTC.getTag(getInvalidId);
        wpTestLib.VerifyResponseItemDoesNotExist(response, getInvalidCode, getInvalidMessage);
    }

    /**
     * TestGetTagIdMaxInt
     * Verify that the Get Tag by Id endpoint throws error behavior when using Integer.MAX_VALUE as a tag ID.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetTagIdMaxInt() throws UnirestException {
        wpLogger.info("Testing giving MAX_VALUE Integer to the Get Tag by Id endpoint.");
        JSONObject response = wpTC.getTag(Integer.toString(Integer.MAX_VALUE));
        wpTestLib.VerifyResponseItemDoesNotExist(response, getTermNonExistentCode, getTermNonExistentMessage);
    }

    /**
     * TestGetTagIdMinInt
     * Verify that the Get Tag by Id endpoint throws error behavior when using Integer.MIN_VALUE as a tag ID.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetTagIdMinInt() throws UnirestException {
        wpLogger.info("Testing giving MIN_VALUE Integer to the Get Tag by Id endpoint.");
        JSONObject response = wpTC.getTag(Integer.toString(Integer.MIN_VALUE));
        wpTestLib.VerifyResponseItemDoesNotExist(response, getInvalidCode, getInvalidMessage);
    }
}
