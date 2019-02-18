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
 * Last updated 2/18/2019
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
    private String wpGetTaxonomiesTemplate = "/wp-json/wp/v2/taxonomies";
    private String wpGetTaxonomyByTagTemplate = "/wp-json/wp/v2/taxonomies/%s";
    private String wpGetMediaTemplate = "/wp-json/wp/v2/media";
    private String wpGetMediaByIdTemplate = "/wp-json/wp/v2/media/%s";
    private String wpGetUsersTemplate = "/wp-json/wp/v2/users";
    private String wpGetUserByIdTemplate = "/wp-json/wp/v2/users/%s";
    private String wpGetPostTypesTemplate = "/wp-json/wp/v2/types";
    private String wpGetPostTypeByTagTemplate = "/wp-json/wp/v2/types/%s";
    private String wpGetPostStatusesTemplate = "/wp-json/wp/v2/statuses";
    private String wpGetPostStatusByTagTemplate = "/wp-json/wp/v2/statuses/%s";

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
     * @return JSONArray from the Get call
     * @throws UnirestException
     */
    public JSONArray getPosts() throws UnirestException {
        String uri = wpSite + wpGetPostsTemplate;
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getArray();
    }

    /**
     * getPost
     * Get a specific post on the test Wordpress site by post ID.
     * @param postId: String containing the post ID to work with
     * @return JSONObject from the Get call
     * @throws UnirestException
     */
    public JSONObject getPost(String postId) throws UnirestException {
        String uri = wpSite + String.format(wpGetPostByIdTemplate, postId);
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getObject();
    }

    /**
     * getCategories
     * Get a JSONArray of categories on the test Wordpress site.
     * @return JSONArray from the Get call
     * @throws UnirestException
     */
    public JSONArray getCategories() throws UnirestException {
        String uri = wpSite + wpGetCategoriesTemplate;
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getArray();
    }

    /**
     * getCategory
     * Get a specific category on the test Wordpress site by category ID.
     * @param categoryId: String containing the category ID to work with
     * @return JSONObject from the Get call
     * @throws UnirestException
     */
    public JSONObject getCategory(String categoryId) throws UnirestException {
        String uri = wpSite + String.format(wpGetCategoryByIdTemplate, categoryId);
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getObject();
    }

    /**
     * getTags
     * Get a JSONArray of tags on the test Wordpress site.
     * @return JSONArray from the Get call
     * @throws UnirestException
     */
    public JSONArray getTags() throws UnirestException {
        String uri = wpSite + wpGetTagsTemplate;
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getArray();
    }

    /**
     * getTag
     * Get a specific tag on the test Wordpress site by tag ID.
     * @param tagId: String containing the tag ID to work with
     * @return JSONObject from the Get call
     * @throws UnirestException
     */
    public JSONObject getTag(String tagId) throws UnirestException {
        String uri = wpSite + String.format(wpGetTagByIdTemplate, tagId);
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getObject();
    }

    /**
     * getPages
     * Get a JSONArray of pages on the test Wordpress site.
     * @return JSONArray from the Get call
     * @throws UnirestException
     */
    public JSONArray getPages() throws UnirestException {
        String uri = wpSite + wpGetPagesTemplate;
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getArray();
    }

    /**
     * getPage
     * Get a specific page on the test Wordpress site by page ID.
     * @param pageId
     * @return JSONObject containing the page data
     * @throws UnirestException
     */
    public JSONObject getPage(String pageId) throws UnirestException {
        String uri = wpSite + String.format(wpGetPageByIdTemplate, pageId);
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getObject();
    }

    /**
     * getComments
     * Get a JSONArray of comments on the test Wordpress site.
     * @return JSONArray from the Get call
     * @throws UnirestException
     */
    public JSONArray getComments() throws UnirestException {
        String uri = wpSite + wpGetCommentsTemplate;
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getArray();
    }

    /**
     * getComment
     * Get a specific comment on the test Wordpress site by comment ID.
     * @param commentId: String containing the comment ID to work with
     * @return JSONObject containing the comment data
     * @throws UnirestException
     */
    public JSONObject getComment(String commentId) throws UnirestException {
        String uri = wpSite + String.format(wpGetCommentByIdTemplate, commentId);
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getObject();
    }

    /**
     * getTaxonomies
     * Get a JSONArray of taxonomies on the test Wordpress site.
     * @return JSONArray from the Get call
     * @throws UnirestException
     */
    public JSONArray getTaxonomies() throws UnirestException {
        String uri = wpSite + wpGetTaxonomiesTemplate;
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getArray();
    }

    /**
     * getTaxonomy
     * Get a specific taxonomy on the test Wordpress site by its tag.
     * @param taxonomyTag: String containing the tag for the taxonomy to work with
     * @return JSONObject containing the taxonomy data
     * @throws UnirestException
     */
    public JSONObject getTaxonomy(String taxonomyTag) throws UnirestException {
        String uri = wpSite + String.format(wpGetTaxonomyByTagTemplate, taxonomyTag);
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getObject();
    }

    /**
     * getMedia
     * Get a JSONArray of media on the test Wordpress site.
     * @return JSONArray from the Get call
     * @throws UnirestException
     */
    public JSONArray getMedia() throws UnirestException {
        String uri = wpSite + wpGetMediaTemplate;
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getArray();
    }

    /**
     * getMediaById
     * Get a specific media item on the test Wordpress site by its ID.
     * @param mediaId: String containing the ID of the media item to work with
     * @return JSONObject containing the media data
     * @throws UnirestException
     */
    public JSONObject getMediaById(String mediaId) throws UnirestException {
        String uri = wpSite + String.format(wpGetMediaByIdTemplate, mediaId);
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getObject();
    }

    /**
     * getUsers
     * Get a JSONArray of users on the test Wordpress site.
     * @return JSONArray from the Get call
     * @throws UnirestException
     */
    public JSONArray getUsers() throws UnirestException {
        String uri = wpSite + wpGetUsersTemplate;
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getArray();
    }

    /**
     * getUserById
     * Get a specific user on the test Wordpress site by their ID.
     * @param userId: String containing the ID of the user to work with
     * @return JSONObject containing the user data
     * @throws UnirestException
     */
    public JSONObject getUserById(String userId) throws UnirestException {
        String uri = wpSite + String.format(wpGetUserByIdTemplate, userId);
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getObject();
    }

    /**
     * getPostTypes
     * Get a JSONArray of post types on the test Wordpress site.
     * @return JSONArray from the Get Call
     * @throws UnirestException
     */
    public JSONArray getPostTypes() throws UnirestException {
        String uri = wpSite + wpGetPostTypesTemplate;
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getArray();
    }

    /**
     * getPostType
     * Get a specific post type on the test Wordpress site by its tag.
     * @param postTypeTag: String containing the tag for the post type to work with
     * @return JSONObject containing the post type data
     * @throws UnirestException
     */
    public JSONObject getPostType(String postTypeTag) throws UnirestException {
        String uri = wpSite + String.format(wpGetPostTypeByTagTemplate, postTypeTag);
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getObject();
    }

    /**
     * getPostStatuses
     * Get a JSONArray of post types on the test Wordpress site.
     * @return JSONArray from the Get call
     * @throws UnirestException
     */
    public JSONArray getPostStatuses() throws UnirestException {
        String uri = wpSite + wpGetPostStatusesTemplate;
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getArray();
    }

    /**
     * getPostStatus
     * Get a specific post status on the test Wordpress site by its tag.
     * @param postStatusTag: String containing the tag for the post status to work with
     * @return JSONObject containing the post status data
     * @throws UnirestException
     */
    public JSONObject getPostStatus(String postStatusTag) throws UnirestException {
        String uri = wpSite + String.format(wpGetPostStatusByTagTemplate, postStatusTag);
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getObject();
    }
}