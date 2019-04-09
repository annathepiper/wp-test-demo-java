import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestCategories
 * @author Angela Korra'ti
 *
 * Last updated 4/9/2019
 * This class contains test cases related to categories on the Wordpress test site.
 */
public class TestCategories extends BaseTest {

    /**
     * TestGetCategoriesReturnsCategories
     * Verify that the GetCategories endpoint actually returns data. There should be a JSON array of length > 0.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetCategoriesReturnsCategories() throws UnirestException {
        wpLogger.info("Testing that the Get Categories endpoint returns categories.");
        JSONArray response = wpTC.getCategories();
        Assert.assertTrue(response.length() > 0,
                "Get Categories endpoint not returning at least one object in JSONArray.");
        Assert.assertNotNull(response,"Get Categories endpoint returned a null response.");
    }

    /**
     * TestGetCategoryById
     * Verify that you can get a category by a specific ID off the GetGategories endpoint. Uses a test ID set in the
     * properties file and retrieved by the BaseTest class.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetCategoryById() throws UnirestException {
        wpLogger.info("Testing that the Get Category by Id endpoint returns data for a valid category ID.");
        JSONObject response = wpTC.getCategory(getCategoryId);
        String categoryName = response.getString("name");
        Assert.assertNotNull(response,"Get Category by Id endpoint returned a null object. Category may not exist.");
        Assert.assertEquals(response.get("id").toString(), getCategoryId,
                "Get Category by Id endpoint didn't return correct ID number.");
        Assert.assertEquals(categoryName, getCategoryName,
                "Retrieved category from Get Category by Id endpoint does not have expected name.");
    }

    /**
     * TestGetCategoryIdThatDoesNotExist
     * Verify that the Get Category by Id endpoint exhibits expected error behavior if you throw it a category ID that
     * doesn't actually exist.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetCategoryIdThatDoesNotExist() throws UnirestException {
        wpLogger.info("Testing giving a category ID that doesn't exist to the Get Category by Id endpoint.");
        JSONObject response = wpTC.getCategory(getCategoryNonExistentId);
        Assert.assertEquals(response.get("code"), getCategoryNonExistentCode,
                "Get Category by Id endpoint thinks this category ID actually exists.");
        Assert.assertEquals(response.get("message"), getCategoryNonExistentMessage,
                "Get Category by Id endpoint didn't throw the expected error message.");
        JSONObject responseData = response.getJSONObject("data");
        Assert.assertNotNull(responseData, "Get Category by Id endpoint didn't include data object in response.");
        Assert.assertEquals(responseData.get("status").toString(), "404",
                "Get Category by Id endpoint didn't return expected error code.");
    }

    /**
     * TestGetCategoryIdBadId
     * Verify that the Get Category by Id endpoint throws expected error behavior if given invalid data for its category
     * ID.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetCategoryIdBadId() throws UnirestException {
        wpLogger.info("Testing giving a bad category ID to the Get Category by Id endpoint.");
        JSONObject response = wpTC.getCategory(getInvalidId);
        Assert.assertEquals(response.get("code"), getInvalidCode,
                "Get Category by Id endpoint thinks this category ID is actually valid.");
        Assert.assertEquals(response.get("message"), getInvalidMessage,
                "Get Category by Id endpoint didn't throw the expected error message.");
        JSONObject responseData = response.getJSONObject("data");
        Assert.assertNotNull(responseData, "Get Category by Id endpoint didn't include data object in response.");
        Assert.assertEquals(responseData.get("status").toString(), "404",
                "Get Category by Id endpoint didn't return expected error code.");
    }

    /**
     * TestGetCategoryIdMaxInt
     * Verify that the Get Category by Id endpoint throws error behavior when using Integer.MAX_VALUE as a category ID.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetCategoryIdMaxInt() throws UnirestException {
        wpLogger.info("Testing giving MAX_VALUE Integer to the Get Category by Id endpoint.");
        JSONObject response = wpTC.getCategory(Integer.toString(Integer.MAX_VALUE));
        Assert.assertEquals(response.get("code"), getCategoryNonExistentCode,
                "Get Category by Id endpoint thinks this category ID actually exists.");
        Assert.assertEquals(response.get("message"), getCategoryNonExistentMessage,
                "Get Category by Id endpoint didn't throw the expected error message.");
        JSONObject responseData = response.getJSONObject("data");
        Assert.assertNotNull(responseData, "Get Category by Id endpoint didn't include data object in response.");
        Assert.assertEquals(responseData.get("status").toString(), "404",
                "Get Category by Id endpoint didn't return expected error code.");
    }

    /**
     * TestGetCategoryIdMinInt
     * Verify that the Get Category by Id endpoint throws error behavior when using Integer.MIN_VALUE as a category ID.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetCategoryIdMinInt() throws UnirestException {
        wpLogger.info("Testing giving MIN_VALUE Integer to the Get Category by Id endpoint.");
        JSONObject response = wpTC.getPost(Integer.toString(Integer.MIN_VALUE));
        Assert.assertEquals(response.get("code"), getInvalidCode,
                "Get Category by Id endpoint thinks this category ID is actually valid.");
        Assert.assertEquals(response.get("message"), getInvalidMessage,
                "Get Category by Id endpoint didn't throw the expected error message.");
        JSONObject responseData = response.getJSONObject("data");
        Assert.assertNotNull(responseData, "Get Category by Id endpoint didn't include data object in response.");
        Assert.assertEquals(responseData.get("status").toString(), "404",
                "Get Category by Id endpoint didn't return expected error code.");
    }
}
