import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestComments
 * @author Angela Korra'ti
 *
 * Last updated 4/9/2019
 * This class contains test cases related to comments on the Wordpress test site.
 */
public class TestComments extends BaseTest {

    /**
     * TestGetCommentsReturnsComments
     * Verify that the GetComments endpoint actually returns data. There should be a JSON array of length > 0.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetCommentsReturnsComments() throws UnirestException {
        wpLogger.info("Testing the Get Comments endpoint.");
        JSONArray response = wpTC.getComments();
        Assert.assertTrue(response.length() > 0,
                "GetComments endpoint not returning at least one object in JSONArray.");
        Assert.assertNotNull(response,"GetComments endpoint returned a null response.");
    }

    /**
     * TestGetCommentById
     * Verify that you can get a comment by a specific ID off the GetComments endpoint. Uses a test ID set in the
     * properties file and retrieved by the BaseTest class.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetCommentById() throws UnirestException {
        wpLogger.info("Testing the Get Comment by Id endpoint.");
        JSONObject response = wpTC.getComment(getCommentId);
        JSONObject renderedContent = response.getJSONObject("content");
        Assert.assertNotNull(response,"GetComment endpoint returned a null object. Comment may not exist.");
        Assert.assertEquals(response.get("id").toString(), getCommentId,
                "GetComment endpoint didn't return correct ID number.");
        Assert.assertTrue(renderedContent.get("rendered").toString().contains(getCommentContent),
                "Retrieved comment from GetComment endpoint does not have expected content.");
    }

    /**
     * TestGetCommentIdThatDoesNotExist
     * Verify that the Get Comment by Id endpoint exhibits expected error behavior if you throw it a comment ID that
     * doesn't actually exist.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetCommentIdThatDoesNotExist() throws UnirestException {
        wpLogger.info("Testing giving a comment ID that doesn't exist to the Get Comment by Id endpoint.");
        JSONObject response = wpTC.getComment(getNonExistentId);
        wpTestLib.VerifyResponseItemDoesNotExist(response, getCommentNonExistentCode, getCommentNonExistentMessage);
    }

    /**
     * TestGetCommentIdBadId
     * Verify that the Get Comment by Id endpoint throws expected error behavior if given invalid data for its comment
     * ID.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetCommentIdBadId() throws UnirestException {
        wpLogger.info("Testing giving a bad comment ID to the Get Comment by Id endpoint.");
        JSONObject response = wpTC.getComment(getInvalidId);
        wpTestLib.VerifyResponseItemIsInvalid(response, getInvalidCode, getInvalidMessage);
    }

    /**
     * TestGetCommentIdMaxInt
     * Verify that the Get Comment by Id endpoint throws error behavior when using Integer.MAX_VALUE as a comment ID.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetCommentIdMaxInt() throws UnirestException {
        wpLogger.info("Testing giving MAX_VALUE Integer to the Get Comment by Id endpoint.");
        JSONObject response = wpTC.getComment(Integer.toString(Integer.MAX_VALUE));
        wpTestLib.VerifyResponseItemDoesNotExist(response, getCommentNonExistentCode, getCommentNonExistentMessage);
    }

    /**
     * TestGetCommentIdMinInt
     * Verify that the Get Comment by Id endpoint throws error behavior when using Integer.MIN_VALUE as a comment ID.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetCommentIdMinInt() throws UnirestException {
        wpLogger.info("Testing giving MIN_VALUE Integer to the Get Comment by Id endpoint.");
        JSONObject response = wpTC.getComment(Integer.toString(Integer.MIN_VALUE));
        wpTestLib.VerifyResponseItemIsInvalid(response, getInvalidCode, getInvalidMessage);
    }
}
