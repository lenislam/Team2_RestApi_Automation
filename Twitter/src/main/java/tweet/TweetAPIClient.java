package tweet;

import base.RestAPI;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class TweetAPIClient extends RestAPI {
    // OAuth
    // https://www.programcreek.com/java-api-examples/?api=com.github.scribejava.core.model.OAuthRequest



// https://developer.twitter.com/en/docs/twitter-api/v1/tweets/post-and-engage/overview

    // https://api.twitter.com/1.1/statuses/update.json
    private final String CREATE_TWEET_ENDPOINT = "/statuses/update.json";
    //
    private final String DELETE_TWEET_ENDPOINT = "/statuses/destroy.json";
    //https://developer.twitter.com/en/docs/twitter-api/v1/tweets/timelines/api-reference/get-statuses-user_timeline
    private final String GET_USER_TWEET_ENDPOINT = "/statuses/user_timeline.json";
    private final String CREATE_RETWEET_ENDPOINT = "/statuses/retweet.json";
    private final String UNRETWEET_POST_USER_ENDPOINT = "/statuses/unretweet.json";
    private final String SHOW_GET_USER_ENDPOINT = "/statuses/show.json";
    private final String FAVORITES_POST_USER_ENDPOINT = "/favorites/create.json";
    private final String FAVORITES_DESTROY_POST_USER_ENDPOINT = "/favorites/destroy.json";

    private final String FAVORITES_LIST_GET_USER_ENDPOINT = "/favorites/list.json";
    private final String STATUS_LOOKUP_GET_USER_ENDPOINT = "/statuses/lookup.json";
    private final String FAVORITES_LIST_USER_ENDPOINT="/favorites/list.json";
    private final String GET_RETWEETS_USER_ENDPOINT = "/statuses/retweets.json";
    private final String GET_HOME_TIMELINE_USER_ENDPOINT="/statuses/home_timeline.json";
    private final String GET_ENTRIES_USER_ENDPOINT="/collections/entries.json";


    // GET ALL Tweet Information
    public ValidatableResponse getUserTimeTweet() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_USER_TWEET_ENDPOINT)
                .then();
    }

    // Create a Tweet from user twitter
    public ValidatableResponse createTweet(String tweet)  {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("status", tweet)
                .when().post(this.baseUrl+this.CREATE_TWEET_ENDPOINT)
                .then();
    }

    /**
     * Create tweet with invalid end point
     */
    public ValidatableResponse createTweetWithEndPoint()  {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("status")
                .when().post(this.baseUrl+this.CREATE_TWEET_ENDPOINT+"1243")
                .then();
    }
    // Delete a tweet from users twitter
    public ValidatableResponse deleteTweet(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().post(this.baseUrl + this.DELETE_TWEET_ENDPOINT)
                .then();
    }
    /**
     * Create reTweet with valid data
     */
    public ValidatableResponse createReTweet(Long reTweetId){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", reTweetId)
                .when().post(this.baseUrl+this.CREATE_RETWEET_ENDPOINT)
                .then();
    }
    /**
     * Create reTweet with invalid invalid point
     */
    public ValidatableResponse createReTweetWithInvalidData(Long reTweetId){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", reTweetId)
                .when().post(this.baseUrl+this.CREATE_RETWEET_ENDPOINT+"1234")
                .then();
    }
    /**
     * Un reTweet with valid data
     */
    public ValidatableResponse unReTweet(Long tweetId) {
    return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
            .queryParam("id", tweetId)
            .when().post(this.baseUrl + this.UNRETWEET_POST_USER_ENDPOINT)
            .then();
    }
    /**
     * Un reTweet with invalid data
     */
    public ValidatableResponse unReTweetInvalidID(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().post(this.baseUrl + this.UNRETWEET_POST_USER_ENDPOINT)
                .then();
    }
    /**
     * Favorites Tweet create valid data
     */
    public ValidatableResponse favoritesTweet(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", tweetId)
                .when().post(this.baseUrl + this.FAVORITES_POST_USER_ENDPOINT)
                .then();
    }
    /**
     * Favorites with invalid end point
     */
    public ValidatableResponse favoritesTweetWithWrongFavoritesEndPoint(long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", tweetId)
                .when().post(this.baseUrl + this.FAVORITES_POST_USER_ENDPOINT + "1234")
                .then();
    }
    /**
     * Favorites Tweet destroy valid data
     */
    public ValidatableResponse unlikeFavoritesTweet(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().post(this.baseUrl + this.FAVORITES_DESTROY_POST_USER_ENDPOINT)
                .then();
    }
    /**
     * Favorites Tweet destroy invalid data
     */
    public ValidatableResponse unlikeFavoritesTweetWithInvalidData(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().post(this.baseUrl + this.FAVORITES_DESTROY_POST_USER_ENDPOINT)
                .then();
    }
    /**
     * Favorites list with valid data
     */
    public ValidatableResponse favoritesListTweet(String userId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("user_id",userId)
                .when().get(this.baseUrl + this.FAVORITES_LIST_GET_USER_ENDPOINT)
                .then();
    }
    /**
     * Favorites list with invalid end point
     */
    public ValidatableResponse favoritesListWithInvalidTweet(String userId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("user_id",userId)
                .when().get(this.baseUrl + this.FAVORITES_LIST_GET_USER_ENDPOINT+"1234")
                .then();
    }
    /**
     * Show tweet with valid data
     */
    public ValidatableResponse showTweetIDWithValidData(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().get(this.baseUrl + this.SHOW_GET_USER_ENDPOINT)
                .then();
    }
    /**
     * Show tweet with invalid data
     */
    public ValidatableResponse showTweetIDWithInvalidData(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().get(this.baseUrl + this.SHOW_GET_USER_ENDPOINT)
                .then();
    }
    /**
     * create Status LookUp with valid data
     */
    public ValidatableResponse getStatusLookUp(int id,String tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id",id, tweetId)
                .when().get(this.baseUrl + this.STATUS_LOOKUP_GET_USER_ENDPOINT)
                .then();
    }
    /**
     * create Status LookUp with invalid end point
     */
    public ValidatableResponse getStatusLookUpWithInvalidData(int id,String tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id",id, tweetId)
                .when().get(this.baseUrl + this.STATUS_LOOKUP_GET_USER_ENDPOINT+"1234")
                .then();
    }
    /**
     * Get Status Retweets
     */
    public ValidatableResponse getRetweets(long tweetId){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", tweetId)
                .when().get(this.baseUrl + this.GET_RETWEETS_USER_ENDPOINT )
                .then();
    }
    /**
     * Home time line with valid data
     */
    public ValidatableResponse getHomeTimeLineTweet(String userId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("count", userId)
                .when().get(this.baseUrl + this.GET_HOME_TIMELINE_USER_ENDPOINT )
                .then();
    }
    /**
     * GET All Tweet Information with valid data
     */
    public ValidatableResponse getUserTimeTweetWithValidData(int count, String screenName) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("screen_Name", count, screenName)
                .when().get(this.baseUrl + this.GET_USER_TWEET_ENDPOINT)
                .then();
    }
    /**
     * GET All Tweet Information with invalid end point
     */
    public ValidatableResponse getUserTimeTweetWithInvalidPoint(int count, String screenName) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("screen_Name", count, screenName)
                .when().get(this.baseUrl + this.GET_USER_TWEET_ENDPOINT+"1234")
                .then();
    }
    /**
     * Get collections entries
     */
    public ValidatableResponse getEntriesCollectionsTweet(String id)  {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", id)
                .when().get(this.baseUrl+this.GET_ENTRIES_USER_ENDPOINT)
                .then();
    }




}
