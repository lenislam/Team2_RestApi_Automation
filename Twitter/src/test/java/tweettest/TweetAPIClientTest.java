package tweettest;


import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tweet.TweetAPIClient;

import java.util.UUID;

public class TweetAPIClientTest {

    private TweetAPIClient tweetAPIClient;

    @BeforeClass
    public void setUpTweetAPI(){
        this.tweetAPIClient=new TweetAPIClient();
    }

    @Test(enabled = true)
    public void testUserCanTweetSuccessfully(){
        // 1. user send a tweet
        String tweet="Check user ID"+ UUID.randomUUID().toString();
        ValidatableResponse response= this.tweetAPIClient.createTweet(tweet);
        // 2. Verify that the tweet was successful
        response.statusCode(200);
        System.out.println(response.extract().body().asString());
        String actualTweet= response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }

    /**
     * Create tweet with invalid end point
     */
    @Test(enabled = true)
    public void testUserCreateTweetWithInvalidEndPoint(){
        ValidatableResponse response=this.tweetAPIClient.createTweetWithEndPoint();
        response.statusCode(404);
//        String expectedMessage = "Status is wrong end point";
//        String actualMessage = response.extract().body().path("message");
//        Assert.assertEquals(actualMessage, expectedMessage);
         Assert.assertNotSame(200,404);
    }
    @Test(enabled = true)
    public void testUserCanNotTweetTheSameTweetTwiceInARow(){
        // 1. user send a tweet
       // String tweet="We are learning RestAPI Automation and Tweet check"+ UUID.randomUUID().toString();
        String tweet="We are learning RestAPI Automation and Tweet check";
        ValidatableResponse response= this.tweetAPIClient.createTweet(tweet);
        // 2. Verify that the tweet was successful
        response.statusCode(200);

        System.out.println(response.extract().body().asString());
        String actualTweet= response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
        // User send the same tweet again
      response= this.tweetAPIClient.createTweet(tweet);
        // Verify that the tweet was unsuccessful
       response.statusCode(403);
        //System.out.println(response.extract().body().asString());
        String expectedMessage = "Status is a duplicate.";
        String actualMessage = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualMessage, expectedMessage);
        Assert.assertNotSame("200", 403);
    }
    @Test(enabled = true)
    public void testDelete(){
        String tweet="Aweesomeee! I got $196.46 this week so far just taking some surveys.";
        ValidatableResponse response=this.tweetAPIClient.deleteTweet(324236500424335362l);
        // Verify that the tweet was successfully deleted
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }
    /**
     *  Create reTweet with valid data
     */
    @Test(enabled = true)
    public void testCreateRetweet(){
        String retweet="RT @cnnbrk: Nearly 130 people were arrested Wednesday in Louisville protests over the Breonna Taylor case, the city's interim police chief…";
        ValidatableResponse response=this.tweetAPIClient.createReTweet(1309188858433724422l);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(retweet,actualTweet);
    }
    /**
     *  Create reTweet with invalid data
     */
    @Test(enabled = true)
    public void testCreateRetweetWithInvalidData(){
        ValidatableResponse response=this.tweetAPIClient.createReTweetWithInvalidData(1309188858433724422l);
        int actualReTweet=response.extract().statusCode();
        Assert.assertEquals(404,actualReTweet);
    }
    /**
     * Un reTweet with valid data
     */
    @Test(enabled = true)
public void testUnReTweet(){
    String tweet="RT @cnnbrk: Nearly 130 people were arrested Wednesday in Louisville protests over the Breonna Taylor case, the city's interim police chief…";
    ValidatableResponse response=this.tweetAPIClient.unReTweet(1309188858433724422l);
    response.statusCode(200);
    String actualTweet=response.extract().body().path("text");
    Assert.assertEquals(tweet,actualTweet);
}
    /**
     * Un reTweet with invalid data
     */
    @Test(enabled = true)
    public void testUnReTweetInvalidId(){
        ValidatableResponse response=this.tweetAPIClient.unReTweetInvalidID(324236500424335363l);
        int actualUnRetweet=response.extract().statusCode();
        Assert.assertEquals(404,actualUnRetweet);
    }
    /**
     * Favorites Tweet create valid data
     */
    @Test(enabled = true)
    public void FavoritesTweetID(){
        String tweet="Check user ID042a5d91-b156-4b9d-9dfa-ca94b5638801";
        ValidatableResponse response=this.tweetAPIClient.favoritesTweet(1308874571995664386L);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }
    /**
     * Favorites with invalid data
     */
    @Test
    public void TestCreateTweetWithWrongFavoritesEndPoint(){
        ValidatableResponse response = tweetAPIClient.favoritesTweetWithWrongFavoritesEndPoint(1308874571995664386L);
        int actualCode = response.extract().statusCode();
        Assert.assertEquals(404, actualCode);
    }
    /**
     * Favorites Tweet destroy valid data
     */
    @Test(enabled = true)
    public void testDestroyFavoritesTweet(){
        String tweet="Check user ID042a5d91-b156-4b9d-9dfa-ca94b5638801";
        ValidatableResponse response=this.tweetAPIClient.unlikeFavoritesTweet(1308874571995664386L);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }
    /**
     * Favorites Tweet destroy invalid data
     */
    @Test(enabled = true)
    public void testDestroyFavoritesTweetWithInvalidData(){
        String tweet="Check user ID042a5d91-b156-4b9d-9dfa-ca94b5638801";
        ValidatableResponse response=this.tweetAPIClient.unlikeFavoritesTweet(1308874571995664L);
        int actualCode = response.extract().statusCode();
        Assert.assertEquals(404, actualCode);
    }
    /**
     * Favorites list with valid data
     */
    @Test(enabled = true)
    public void testFavoritesListTweet(){
        ValidatableResponse response=this.tweetAPIClient.favoritesListTweet("Shohel41710088");
        int actualCode = response.extract().statusCode();
        System.out.println(response.extract().body().asString());
        Assert.assertEquals(200,actualCode);
    }
    /**
     * Favorites list with invalid data
     */
    @Test(enabled = true)
    public void testFavoritesListWithInvalidTweet(){
        ValidatableResponse response=this.tweetAPIClient.favoritesListWithInvalidTweet("Shohel41710088");
        int actualCode = response.extract().statusCode();
        System.out.println(response.extract().body().asString());
        Assert.assertEquals(404,actualCode);
    }
    /**
     * Show tweet with valid data
     */
    @Test(enabled = true)
    public void testShowTweetID(){
        String tweet="Today is cloudy.";
        ValidatableResponse response=this.tweetAPIClient.showTweetIDWithValidData(1309196682865840128l);
        response.statusCode(200);
        System.out.println(response.extract().body().asString());
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }
    /**
     * Show tweet with invalid data
     */
    @Test(enabled = true)
    public void testShowTweetIDWithInvalidData(){
        ValidatableResponse response=this.tweetAPIClient.showTweetIDWithInvalidData(1309196682865840000l);
        System.out.println(response.extract().body().asString());
        int actualCode = response.extract().statusCode();
        Assert.assertEquals(404, actualCode);
    }

    /**
     * create Status LookUp with valid data
     */
    @Test(enabled = true)
    public void testGetStatusLookUp(){
        ValidatableResponse response=this.tweetAPIClient.getStatusLookUp(20,"Shohel41710088");
        int actualResult=response.extract().statusCode();
        System.out.println(actualResult);
        System.out.println(response.extract().body().asString());
        Assert.assertEquals(200,actualResult);
    }
    /**
     * create Status LookUp with invalid data
     */
    @Test(enabled = true)
    public void testGetStatusLookUpWithInvalidData(){
        ValidatableResponse response=this.tweetAPIClient.getStatusLookUpWithInvalidData(20,"Shohel41710088");
        int actualResult=response.extract().statusCode();
        Assert.assertEquals(404,actualResult);
    }
    /**
     * Get Status Retweets
     */
    @Test(enabled = true)
    public void testGetRetweets(){
        String tweet="Nearly 130 people were arrested Wednesday in Louisville protests over the Breonna Taylor case, the city's interim police chief says";
        ValidatableResponse response=this.tweetAPIClient.getRetweets(1309188858433724422l);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }
    /**
     * Home time line with valid data
     */
    @Test(enabled = true)
    public void testGetHomeTimeLineTweets(){
        ValidatableResponse response=this.tweetAPIClient.getHomeTimeLineTweet("Shohel41710088");
        int actualCode = response.extract().statusCode();
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(200,actualCode);
    }
    /**
     * GET All Tweet Information with valid data
     */
    @Test
    public void testGetUserTimeTweetWithValidData(){
        ValidatableResponse response = this.tweetAPIClient.getUserTimeTweetWithValidData(10,"Shohel41710088");
        int actualCode = response.extract().statusCode();
        System.out.println(actualCode);
        Assert.assertEquals(200,actualCode);
    }
    /**
     * GET All Tweet Information with invalid end point
     */
    @Test
    public void testGetUserTimeTweetWithInValidData(){
        ValidatableResponse response = this.tweetAPIClient.getUserTimeTweetWithInvalidPoint(0,"Shohel41710088");
        int actualCode = response.extract().statusCode();
        System.out.println(actualCode);
        Assert.assertEquals(404,actualCode);
    }



}
