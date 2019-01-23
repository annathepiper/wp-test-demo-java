import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * WPTestClient
 * @author Angela Korra'ti
 *
 * Last updated 1/23/2019
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
    private String wpGetPostByIdTemplate = "/wp-json/wp/v2/posts/%s";
    private String wpGetCategoriesTemplate = "/wp-json/wp/v2/categories";
    private String wpGetCategoryByIdTemplate = "/wp-json/wp/v2/categories/%s";
    private String wpGetTagsTemplate = "/wp-json/wp/v2/tags";
    private String wpGetTagByIdTemplate = "/wp-json/wp/v2/tags/%s";
    private String wpGetPagesTemplate = "/wp-json/wp/v2/pages";
    private String wpGetPageByIdTemplate = "/wp-json/wp/v2/pages/%s";
    private String wpGetCommentsTemplate = "/wp-json/wp/v2/comments";
    private String wpGetCommentByIdTemplate = "/wp-json/wp/v2/comments/%s";

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
     * Get a JSONArray of posts on the test Wordpress site.
     */
    public JSONArray getPosts() throws UnirestException {
        String uri = wpSite + wpGetPostsTemplate;
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getArray();
    }

    /**
     * getPost
     * Get a specific post on the test Wordpress site by post ID.
     * @return JSONObject containing the post data
     */
    public JSONObject getPost(String postId) throws UnirestException {
        String uri = wpSite + String.format(wpGetPostByIdTemplate, postId);
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getObject();
    }

    /**
     * getCategories
     * Get a JSONArray of categories on the test Wordpress site.
     */
    public JSONArray getCategories() throws UnirestException {
        String uri = wpSite + wpGetCategoriesTemplate;
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getArray();
    }

    /**
     * getCategory
     * Get a specific category on the test Wordpress site by category ID.
     * @return JSONObject containing the category data
     */
    public JSONObject getCategory(String categoryId) throws UnirestException {
        String uri = wpSite + String.format(wpGetCategoryByIdTemplate, categoryId);
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getObject();
    }

    /**
     * getTags
     * Get a JSONArray of tags on the test Wordpress site.
     */
    public JSONArray getTags() throws UnirestException {
        String uri = wpSite + wpGetTagsTemplate;
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getArray();
    }

    /**
     * getTag
     * Get a specific tag on the test Wordpress site by tag ID.
     * @return JSONObject containing the tag data
     */
    public JSONObject getTag(String tagId) throws UnirestException {
        String uri = wpSite + String.format(wpGetTagByIdTemplate, tagId);
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getObject();
    }

    /**
     * getPages
     * Get a JSONArray of pages on the test Wordpress site.
     */
    public JSONArray getPages() throws UnirestException {
        String uri = wpSite + wpGetPagesTemplate;
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getArray();
    }

    /**
     * getPage
     * Get a specific page on the test Wordpress site by page ID.
     * @return JSONObject containing the page data
     */
    public JSONObject getPage(String pageId) throws UnirestException {
        String uri = wpSite + String.format(wpGetPageByIdTemplate, pageId);
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getObject();
    }
    /**
     * getComments
     * Get a JSONArray of comments on the test Wordpress site.
     */
    public JSONArray getComments() throws UnirestException {
        String uri = wpSite + wpGetCommentsTemplate;
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getArray();
    }

    /**
     * getComment
     * Get a specific comment on the test Wordpress site by comment ID.
     * @return JSONObject containing the comment data
     */
    public JSONObject getComment(String commentId) throws UnirestException {
        String uri = wpSite + String.format(wpGetCommentByIdTemplate, commentId);
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getObject();
    }
}