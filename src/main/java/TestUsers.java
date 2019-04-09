import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestUsers
 * @author Angela Korra'ti
 *
 * Last updated 4/9/2019
 * This class contains test cases related to users on the Wordpress test site.
 */
public class TestUsers extends BaseTest {

    /**
     * TestGetUsersReturnsUsers
     * Verify that the GetUsers endpoint actually returns data. There should be a JSON array of length > 0.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetUsersReturnsUsers() throws UnirestException {
        wpLogger.info("Testing the Get Users endpoint.");
        JSONArray response = wpTC.getUsers();
        Assert.assertTrue(response.length() > 0,
                "GetUsers endpoint not returning at least one object in JSONArray.");
        Assert.assertNotNull(response,"GetUsers endpoint returned a null response.");
    }

    /**
     * TestGetUserById
     * Verify that you can get a user by a specific ID off the GetUsers endpoint. Uses a test ID set in the
     * properties file and retrieved by the BaseTest class.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetUserById() throws UnirestException {
        wpLogger.info("Testing the Get User by Id endpoint.");
        JSONObject response = wpTC.getUserById(getUserId);
        String userName = response.getString("name");
        Assert.assertNotNull(response,"GetUsers endpoint returned a null object. User may not exist.");
        Assert.assertEquals(response.get("id").toString(), getUserId,
                "GetUsers endpoint didn't return correct ID number.");
        Assert.assertEquals(userName, getUserName,
                "Retrieved comment from GetComment endpoint does not have expected content.");
    }

    /**
     * TestGetUserIdThatDoesNotExist
     * Verify that the Get User by Id endpoint exhibits expected error behavior if you throw it a user ID that
     * doesn't actually exist.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetUserIdThatDoesNotExist() throws UnirestException {
        wpLogger.info("Testing giving a user ID that doesn't exist to the Get User by Id endpoint.");
        JSONObject response = wpTC.getUserById(getNonExistentId);
        wpTestLib.VerifyResponseItemDoesNotExist(response, getUserNonExistentCode, getUserNonExistentMessage);
    }

    /**
     * TestGetUserIdBadId
     * Verify that the Get User by Id endpoint throws expected error behavior if given invalid data for its user ID.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetUserIdBadId() throws UnirestException {
        wpLogger.info("Testing giving a bad user ID to the Get User by Id endpoint.");
        JSONObject response = wpTC.getUserById(getInvalidId);
        wpTestLib.VerifyResponseItemIsInvalid(response, getInvalidCode, getInvalidMessage);
    }

    /**
     * TestGetUserIdMaxInt
     * Verify that the Get User by Id endpoint throws error behavior when using Integer.MAX_VALUE as a user ID.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetUserIdMaxInt() throws UnirestException {
        wpLogger.info("Testing giving MAX_VALUE Integer to the Get User by Id endpoint.");
        JSONObject response = wpTC.getUserById(Integer.toString(Integer.MAX_VALUE));
        wpTestLib.VerifyResponseItemDoesNotExist(response, getUserNonExistentCode, getUserNonExistentMessage);
    }

    /**
     * TestGetUserIdMinInt
     * Verify that the Get User by Id endpoint throws error behavior when using Integer.MIN_VALUE as a user ID.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetUserIdMinInt() throws UnirestException {
        wpLogger.info("Testing giving MIN_VALUE Integer to the Get User by Id endpoint.");
        JSONObject response = wpTC.getUserById(Integer.toString(Integer.MIN_VALUE));
        wpTestLib.VerifyResponseItemIsInvalid(response, getInvalidCode, getInvalidMessage);
    }
}
