import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestTaxonomies
 * @author Angela Korra'ti
 *
 * Last updated 4/9/2019
 * This class contains test cases related to taxonomies on the Wordpress test site.
 */
public class TestTaxonomies extends BaseTest {

    /**
     * TestGetTaxonomiesReturnsTaxonomies
     * Verify that the GetTaxonomies endpoint actually returns data. There should be a JSON object with two
     * items in it.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetTaxonomiesReturnsTaxonomies() throws UnirestException {
        wpLogger.info("Testing the Get Taxonomies endpoint.");
        JSONObject response = wpTC.getTaxonomies();
        Assert.assertEquals(response.length(), 3,
                "GetTaxonomies endpoint not returning expected JSONObject length.");
        Assert.assertNotNull(response,"GetTaxonomies endpoint returned a null response.");
    }

    /**
     * TestGetTaxonomyByTag
     * Verify that you can get a taxonomy by a specific tag off the GetTaxonomies endpoint. Uses a test tag set in the
     * properties file and retrieved by the BaseTest class.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetTaxonomyByTag() throws UnirestException {
        wpLogger.info("Testing the Get Taxonomy by Tag endpoint.");
        JSONObject response = wpTC.getTaxonomy(getTaxonomyTag);
        String taxonomyName = response.getString("name");
        Assert.assertNotNull(response,"GetTaxonomy endpoint returned a null object. Taxonomy may not exist.");
        Assert.assertEquals(response.get("slug"), getTaxonomyTag,
                "GetTaxonomies endpoint didn't return correct taxonomy slug.");
        Assert.assertEquals(taxonomyName, getTaxonomyName,
                "Retrieved taxonomy from GetTaxonomy endpoint does not have expected name.");
    }

    /**
     * TestGetTaxonomyTagThatDoesNotExist
     * Verify that the Get Taxonomy by Tag endpoint exhibits expected error behavior if you throw it a taxonomy tag that
     * doesn't actually exist.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetTaxonomyTagThatDoesNotExist() throws UnirestException {
        wpLogger.info("Testing giving a taxonomy tag that doesn't exist to the Get Taxonomy by Tag endpoint.");
        JSONObject response = wpTC.getTaxonomy(getNonExistentTag);
        wpTestLib.VerifyResponseItemDoesNotExist(response, getTaxonomyNonExistentCode, getTaxonomyNonExistentMessage);
    }

    /**
     * TestGetTaxonomyTagBadTag
     * Verify that the Get Taxonomy by Tag endpoint throws expected error behavior if given invalid data for its
     * taxonomy tag.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetTaxonomyTagBadTag() throws UnirestException {
        wpLogger.info("Testing giving a bad taxonomy tag to the Get Taxonomy by Tag endpoint.");
        JSONObject response = wpTC.getTaxonomy(getInvalidTag);
        wpTestLib.VerifyResponseItemIsInvalid(response, getInvalidCode, getInvalidMessage);
    }

    /**
     * TestGetTaxonomyTagMaxInt
     * Verify that the Get Taxonomy by Tag endpoint throws error behavior when using Integer.MAX_VALUE as a taxonomy
     * tag.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetTaxonomyTagMaxInt() throws UnirestException {
        wpLogger.info("Testing giving MAX_VALUE Integer to the Get Taxonomy by Tag endpoint.");
        JSONObject response = wpTC.getTaxonomy(Integer.toString(Integer.MAX_VALUE));
        wpTestLib.VerifyResponseItemDoesNotExist(response, getTaxonomyNonExistentCode, getTaxonomyNonExistentMessage);
    }

    /**
     * TestGetTaxonomyTagMinInt
     * Verify that the Get Taxonomy by Tag endpoint throws error behavior when using Integer.MIN_VALUE as a taxonomy
     * tag.
     * @throws UnirestException if the Unirest call goes wrong somehow
     */
    @Test
    public void TestGetTaxonomyTagMinInt() throws UnirestException {
        wpLogger.info("Testing giving MIN_VALUE Integer to the Get Taxonomy by Tag endpoint.");
        JSONObject response = wpTC.getTaxonomy(Integer.toString(Integer.MIN_VALUE));
        wpTestLib.VerifyResponseItemDoesNotExist(response, getTaxonomyNonExistentCode, getTaxonomyNonExistentMessage);
    }
}
