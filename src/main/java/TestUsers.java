import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestUsers
 * @author Angela Korra'ti
 *
 * Last updated 1/25/2019
 * This class contains test cases related to users on the Wordpress test site.
 */
public class TestUsers extends BaseTest {

    /**
     * TestGetUsersReturnsUsers
     * Verify that the GetUsers endpoint actually returns data. There should be a JSON array of length > 0.
     * @throws UnirestException
     */
    @Test
    public void TestGetUsersReturnsUsers() throws UnirestException {
        JSONArray response = wpTC.getUsers();
        Assert.assertTrue(response.length() > 0,
                "GetUsers endpoint not returning at least one object in JSONArray.");
        Assert.assertNotNull(response,"GetUsers endpoint returned a null response.");
    }

    /**
     * TestGetUserById
     * Verify that you can get a user by a specific ID off the GetUsers endpoint. Uses a test ID set in the
     * properties file and retrieved by the BaseTest class.
     * @throws UnirestException
     */
    @Test
    public void TestGetUserById() throws UnirestException {
        JSONObject response = wpTC.getUserById(getUserId);
        String userName = response.getString("name");
        Assert.assertNotNull(response,"GetUsers endpoint returned a null object. User may not exist.");
        Assert.assertEquals(response.get("id").toString(), getUserId,
                "GetUsers endpoint didn't return correct ID number.");
        Assert.assertEquals(userName, getUserName,
                "Retrieved comment from GetComment endpoint does not have expected content.");
    }
}
