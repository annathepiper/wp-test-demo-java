import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.testng.annotations.Test;;

/**
 * TestPosts
 * @author Angela Korra'ti
 *
 * Last updated 1/21/2019
 * This class contains test cases related to posts on the Wordpress test site.
 */
public class TestPosts extends BaseTest {

    @Test
    public void TestGetPosts() throws UnirestException {
        wpTC.getPosts();
    }
}
