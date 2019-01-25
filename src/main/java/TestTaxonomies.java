import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestTaxonomies
 * @author Angela Korra'ti
 *
 * Last updated 1/25/2019
 * This class contains test cases related to taxonomies on the Wordpress test site.
 */
public class TestTaxonomies extends BaseTest {

    /**
     * TestGetTaxonomiesReturnsTaxonomies
     * Verify that the GetTaxonomies endpoint actually returns data. There should be a JSON array of length > 0.
     * @throws UnirestException
     */
    @Test
    public void TestGetTaxonomiesReturnsTaxonomies() throws UnirestException {
        JSONArray response = wpTC.getTaxonomies();
        Assert.assertTrue(response.length() > 0,
                "GetTaxonomies endpoint not returning at least one object in JSONArray.");
        Assert.assertNotNull(response,"GetTaxonomies endpoint returned a null response.");
    }

    /**
     * TestGetTaxonomyByTag
     * Verify that you can get a taxonomy by a specific tag off the GetTaxonomies endpoint. Uses a test tag set in the
     * properties file and retrieved by the BaseTest class.
     * @throws UnirestException
     */
    @Test
    public void TestGetTaxonomyByTag() throws UnirestException {
        JSONObject response = wpTC.getTaxonomy(getTaxonomyTag);
        String taxonomyName = response.getString("name");
        Assert.assertNotNull(response,"GetTaxonomy endpoint returned a null object. Taxonomy may not exist.");
        Assert.assertEquals(response.get("slug"), getTaxonomyTag,
                "GetTaxonomies endpoint didn't return correct taxonomy slug.");
        Assert.assertEquals(taxonomyName, getTaxonomyName,
                "Retrieved taxonomy from GetTaxonomy endpoint does not have expected name.");
    }
}
