import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestTags
 * @author Angela Korra'ti
 *
 * Last updated 4/8/2019
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
        JSONObject response = wpTC.getTag(getTagNonExistentId);
        Assert.assertEquals(response.get("code"), getTagNonExistentCode,
                "Get Tag by Id endpoint thinks this tag ID actually exists.");
        Assert.assertEquals(response.get("message"), getTagNonExistentMessage,
                "Get Tag by Id endpoint didn't throw the expected error message.");
        JSONObject responseData = response.getJSONObject("data");
        Assert.assertNotNull(responseData, "Get Tag by Id endpoint didn't include data object in response.");
        Assert.assertEquals(responseData.get("status").toString(), "404",
                "Get Tag by Id endpoint didn't return expected error code.");
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
        Assert.assertEquals(response.get("code"), getInvalidCode,
                "Get Tag by Id endpoint thinks this tag ID is actually valid.");
        Assert.assertEquals(response.get("message"), getInvalidMessage,
                "Get Tag by Id endpoint didn't throw the expected error message.");
        JSONObject responseData = response.getJSONObject("data");
        Assert.assertNotNull(responseData, "Get Tag by Id endpoint didn't include data object in response.");
        Assert.assertEquals(responseData.get("status").toString(), "404",
                "Get Tag by Id endpoint didn't return expected error code.");
    }
}
