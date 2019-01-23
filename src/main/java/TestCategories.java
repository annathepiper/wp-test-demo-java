import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestCategories
 * @author Angela Korra'ti
 *
 * Last updated 1/23/2019
 * This class contains test cases related to categories on the Wordpress test site.
 */
public class TestCategories extends BaseTest {

    /**
     * TestGetCategoriesReturnsCategories
     * Verify that the GetCategories endpoint actually returns data. There should be a JSON array of length > 0.
     * @throws UnirestException
     */
    @Test
    public void TestGetCategoriesReturnsCategories() throws UnirestException {
        JSONArray response = wpTC.getCategories();
        Assert.assertTrue(response.length() > 0,
                "GetCategories endpoint not returning at least one object in JSONArray.");
        Assert.assertNotNull(response,"GetCategories endpoint returned a null response.");
    }

    /**
     * TestGetCategoryById
     * Verify that you can get a category by a specific ID off the GetGategories endpoint. Uses a test ID set in the
     * properties file and retrieved by the BaseTest class.
     * @throws UnirestException
     */
    @Test
    public void TestGetCategoryById() throws UnirestException {
        JSONObject response = wpTC.getCategory(getCategoryId);
        String categoryName = response.getString("name");
        Assert.assertNotNull(response,"GetCategory endpoint returned a null object. Category may not exist.");
        Assert.assertEquals(response.get("id").toString(), getCategoryId,
                "GetCategory endpoint didn't return correct ID number.");
        Assert.assertEquals(categoryName, getCategoryName,
                "Retrieved category from GetCategory endpoint does not have expected name.");
    }
}
