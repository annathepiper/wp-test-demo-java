import org.json.JSONObject;
import org.testng.Assert;

/**
 * WPTestLib
 * @author Angela Korra'ti
 *
 * Last updated 4/9/2019
 * This class is a helper library with methods used by the test classes.
 */
class WPTestLib {

    /**
     * VerifyResponseItemDoesNotExist
     * Helper method to take a JSON response object, expected error code, and expected error message, and make sure
     * the response contains the appropriate data to reflect that the tested-for item does not exist.
     * @param response JSONObject response to test
     * @param errorCode Expected error code
     * @param errorMessage Expected error message
     */
    void VerifyResponseItemDoesNotExist(JSONObject response, String errorCode, String errorMessage) {
        Assert.assertEquals(response.get("code"), errorCode,
                "Target endpoint thinks target item ID actually exists.");
        Assert.assertEquals(response.get("message"), errorMessage,
                "Target endpoint didn't throw the expected error message.");
        JSONObject responseData = response.getJSONObject("data");
        Assert.assertNotNull(responseData, "Target endpoint didn't include data object in response.");
        Assert.assertEquals(responseData.get("status").toString(), "404",
                "Target endpoint didn't return expected error code.");
    }

    /**
     * VerifyResponseItemIsInvalid
     * Helper method to take a JSONObject response, an errorCode String, and an errorMessage String, and verify that
     * the response contains appropriate data to reflect that the tested-for item is invalid.
     * @param response JSONObject response data to test
     * @param errorCode String containing the expected error code
     * @param errorMessage String containing the expected error message
     */
    void VerifyResponseItemIsInvalid(JSONObject response, String errorCode, String errorMessage) {
        Assert.assertEquals(response.get("code"), errorCode,
                "Target endpoint thinks target item ID is actually valid.");
        Assert.assertEquals(response.get("message"), errorMessage,
                "Target endpoint didn't throw the expected error message.");
        JSONObject responseData = response.getJSONObject("data");
        Assert.assertNotNull(responseData, "Target endpoint didn't include data object in response.");
        Assert.assertEquals(responseData.get("status").toString(), "404",
                "Target endpoint didn't return expected error code.");
    }
}
