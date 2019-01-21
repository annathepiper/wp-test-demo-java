import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;

/**
 * WPTestClient
 * @author Angela Korra'ti
 *
 * This class is the helper client the tests will use to interact with the Wordpress API endpoints. It uses Unirest
 * to do the actual GET, POST, etc. calls.
 */
public class WPTestClient {
    // Pull these in from the properties file to build the service endpoints
    private String host;
    private String protocol;

    // Service endpoint templates
    private String wpSite = "%s://%s";
    private String wpGetPostsTemplate = "/wp-json/wp/v2/posts";

    /**
     * WPTestClient
     * Constructor for the class.
     * @param incomingHost
     * @param incomingProtocol
     */
    public WPTestClient(String incomingHost, String incomingProtocol) {
        host = incomingHost;
        protocol = incomingProtocol;

        // Set the site template string to use with endpoints
        wpSite = String.format("%s://%s", host, protocol);
    }

    /**
     * getPosts
     * Get a list of posts on the test Wordpress site. (GET request)
     * @return
     */
    public void getPosts() throws UnirestException {
        String uri = wpSite + wpGetPostsTemplate;
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
    }

    /**
     * createPost
     * Create a post and send it to the Wordpress site. (POST request)
     */
    public void createPost() {
    }

    /**
     * getPost
     * Get a specific post on the test Wordpress site by post ID. (GET request)
     */
    public void getPost() {
    }

    /**
     * updatePost
     * Update a specific post on the test Wordpress site by post ID. (POST request)
     */
    public void updatePost() {
    }

    /**
     * deletePost
     * Delete a specific post on the test Wordpress site by post ID. (DELETE request)
     */
    public void deletePost() {
    }
}