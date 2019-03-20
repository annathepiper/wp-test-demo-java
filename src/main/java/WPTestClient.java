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
 * Last updated 3/20/2019
 * This class is the helper client the tests will use to interact with the WordPress API endpoints. It uses Unirest
 * to do the actual GET, POST, etc. calls.
 */
class WPTestClient {
    // Service endpoint templates
    private String wpSite = "%s://%s";

    /**
     * WPTestClient
     * Constructor for the class.
     * @param incomingHost: String containing the host address for the test site
     * @param incomingProtocol: String containing the protocol to use for URIs
     */
    WPTestClient(String incomingHost, String incomingProtocol) {
        // Set the site template string to use with endpoints
        wpSite = String.format(wpSite, incomingHost, incomingProtocol);
    }

    /**
     * getPosts
     * Get a JSONArray of posts on the test WordPress site.
     * @return JSONArray from the Get call
     * @throws UnirestException: If the Get call throws an error
     */
    JSONArray getPosts() throws UnirestException {
        String uri = wpSite + "/wp-json/wp/v2/posts";
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getArray();
    }

    /**
     * getPost
     * Get a specific post on the test WordPress site by post ID.
     * @param postId: String containing the post ID to work with
     * @return JSONObject from the Get call
     * @throws UnirestException: If the Get call throws an error
     */
    JSONObject getPost(String postId) throws UnirestException {
        String uri = wpSite + String.format("/wp-json/wp/v2/posts/%s", postId);
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getObject();
    }

    /**
     * getCategories
     * Get a JSONArray of categories on the test WordPress site.
     * @return JSONArray from the Get call
     * @throws UnirestException: If the Get call throws an error
     */
    JSONArray getCategories() throws UnirestException {
        String uri = wpSite + "/wp-json/wp/v2/categories";
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getArray();
    }

    /**
     * getCategory
     * Get a specific category on the test WordPress site by category ID.
     * @param categoryId: String containing the category ID to work with
     * @return JSONObject from the Get call
     * @throws UnirestException: If the Get call throws an error
     */
    JSONObject getCategory(String categoryId) throws UnirestException {
        String uri = wpSite + String.format("/wp-json/wp/v2/categories/%s", categoryId);
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getObject();
    }

    /**
     * getTags
     * Get a JSONArray of tags on the test WordPress site.
     * @return JSONArray from the Get call
     * @throws UnirestException: If the Get call throws an error
     */
    JSONArray getTags() throws UnirestException {
        String uri = wpSite + "/wp-json/wp/v2/tags";
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getArray();
    }

    /**
     * getTag
     * Get a specific tag on the test WordPress site by tag ID.
     * @param tagId: String containing the tag ID to work with
     * @return JSONObject from the Get call
     * @throws UnirestException: If the Get call throws an error
     */
    JSONObject getTag(String tagId) throws UnirestException {
        String uri = wpSite + String.format("/wp-json/wp/v2/tags/%s", tagId);
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getObject();
    }

    /**
     * getPages
     * Get a JSONArray of pages on the test WordPress site.
     * @return JSONArray from the Get call
     * @throws UnirestException: If the Get call throws an error
     */
    JSONArray getPages() throws UnirestException {
        String uri = wpSite + "/wp-json/wp/v2/pages";
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getArray();
    }

    /**
     * getPage
     * Get a specific page on the test WordPress site by page ID.
     * @param pageId: String containing the ID of the page to work with
     * @return JSONObject containing the page data
     * @throws UnirestException: If the Get call throws an error
     */
    JSONObject getPage(String pageId) throws UnirestException {
        String uri = wpSite + String.format("/wp-json/wp/v2/pages/%s", pageId);
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getObject();
    }

    /**
     * getComments
     * Get a JSONArray of comments on the test WordPress site.
     * @return JSONArray from the Get call
     * @throws UnirestException: If the Get call throws an error
     */
    JSONArray getComments() throws UnirestException {
        String uri = wpSite + "/wp-json/wp/v2/comments";
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getArray();
    }

    /**
     * getComment
     * Get a specific comment on the test WordPress site by comment ID.
     * @param commentId: String containing the comment ID to work with
     * @return JSONObject containing the comment data
     * @throws UnirestException: If the Get call throws an error
     */
    JSONObject getComment(String commentId) throws UnirestException {
        String uri = wpSite + String.format("/wp-json/wp/v2/comments/%s", commentId);
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getObject();
    }

    /**
     * getTaxonomies
     * Get a JSONObject of taxonomies on the test WordPress site.
     * @return JSONObject from the Get call
     * @throws UnirestException: If the Get call throws an error
     */
    JSONObject getTaxonomies() throws UnirestException {
        String uri = wpSite + "/wp-json/wp/v2/taxonomies";
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getObject();
    }

    /**
     * getTaxonomy
     * Get a specific taxonomy on the test WordPress site by its tag.
     * @param taxonomyTag: String containing the tag for the taxonomy to work with
     * @return JSONObject containing the taxonomy data
     * @throws UnirestException: If the Get call throws an error
     */
    JSONObject getTaxonomy(String taxonomyTag) throws UnirestException {
        String uri = wpSite + String.format("/wp-json/wp/v2/taxonomies/%s", taxonomyTag);
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getObject();
    }

    /**
     * getMedia
     * Get a JSONArray of media on the test WordPress site.
     * @return JSONArray from the Get call
     * @throws UnirestException: If the Get call throws an error
     */
    JSONArray getMedia() throws UnirestException {
        String uri = wpSite + "/wp-json/wp/v2/media";
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getArray();
    }

    /**
     * getMediaById
     * Get a specific media item on the test WordPress site by its ID.
     * @param mediaId: String containing the ID of the media item to work with
     * @return JSONObject containing the media data
     * @throws UnirestException: If the Get call throws an error
     */
    JSONObject getMediaById(String mediaId) throws UnirestException {
        String uri = wpSite + String.format("/wp-json/wp/v2/media/%s", mediaId);
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getObject();
    }

    /**
     * getUsers
     * Get a JSONArray of users on the test WordPress site.
     * @return JSONArray from the Get call
     * @throws UnirestException: If the Get call throws an error
     */
    JSONArray getUsers() throws UnirestException {
        String uri = wpSite + "/wp-json/wp/v2/users";
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getArray();
    }

    /**
     * getUserById
     * Get a specific user on the test WordPress site by their ID.
     * @param userId: String containing the ID of the user to work with
     * @return JSONObject containing the user data
     * @throws UnirestException: If the Get call throws an error
     */
    JSONObject getUserById(String userId) throws UnirestException {
        String uri = wpSite + String.format("/wp-json/wp/v2/users/%s", userId);
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getObject();
    }

    /**
     * getPostTypes
     * Get a JSONObject of post types on the test WordPress site.
     * @return JSONObject from the Get Call
     * @throws UnirestException: If the Get call throws an error
     */
    JSONObject getPostTypes() throws UnirestException {
        String uri = wpSite + "/wp-json/wp/v2/types";
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getObject();
    }

    /**
     * getPostType
     * Get a specific post type on the test WordPress site by its tag.
     * @param postTypeTag: String containing the tag for the post type to work with
     * @return JSONObject containing the post type data
     * @throws UnirestException: If the Get call throws an error
     */
    JSONObject getPostType(String postTypeTag) throws UnirestException {
        String uri = wpSite + String.format("/wp-json/wp/v2/types/%s", postTypeTag);
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getObject();
    }

    /**
     * getPostStatuses
     * Get a JSONArray of post types on the test WordPress site.
     * @return JSONArray from the Get call
     * @throws UnirestException: If the Get call throws an error
     */
    JSONArray getPostStatuses() throws UnirestException {
        String uri = wpSite + "/wp-json/wp/v2/statuses";
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getArray();
    }

    /**
     * getPostStatus
     * Get a specific post status on the test WordPress site by its tag.
     * @param postStatusTag: String containing the tag for the post status to work with
     * @return JSONObject containing the post status data
     * @throws UnirestException: If the Get call throws an error
     */
    JSONObject getPostStatus(String postStatusTag) throws UnirestException {
        String uri = wpSite + String.format("/wp-json/wp/v2/statuses/%s", postStatusTag);
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getObject();
    }
}