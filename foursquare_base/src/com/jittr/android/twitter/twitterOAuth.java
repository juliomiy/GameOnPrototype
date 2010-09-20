/**
 * 
 */
package com.jittr.android.twitter;

import java.util.ArrayList;

import twitter4j.IDs;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.http.AccessToken;
import twitter4j.http.RequestToken;

import com.jittr.android.BetSquaredApplication;
import com.jittr.android.betsquared.GameOnSocialNetworkBase;
import static com.jittr.android.util.Consts.TWITTER_NETWORK;;

/**
 * @author juliomiyares
 *
 */
public class twitterOAuth extends GameOnSocialNetworkBase {

	private Twitter twitter4j;
	/**
	 * 
	 */
	public twitterOAuth(BetSquaredApplication appContext) {
	   
       this(appContext, TWITTER_NETWORK, appContext.getUserSettings().getTwitterOAuthToken(), 
    		      appContext.getUserSettings().getTwitterOAuthTokenSecret());
	}  //default Constructor

	/**
	 * @param network
	 * @param token
	 * @param secret
	 */
	@SuppressWarnings("deprecation")
	public twitterOAuth(BetSquaredApplication appContext, int network, String token, String tokenSecret) {
		super(appContext, network, token, tokenSecret);
	    twitter4j = new TwitterFactory().getInstance();
	    twitter4j.setOAuthConsumer(getConsumerKey(), getConsumerKeySecret());
	    if (isAuthorized()) twitter4j.setOAuthAccessToken(new AccessToken(token,tokenSecret));
	
	}  //Constructor

	/* (non-Javadoc)
	 * @see com.jittr.android.betsquared.GameOnSocialNetworkInterface#signRequest()
	 */
	@Override
	public void signRequest() {
		// TODO Auto-generated method stub

	} //signRequest
	
	@Override
	public String getAuthUrl() {
		String authorizationUrl = null;
		try {
			RequestToken currentRequestToken = twitter4j.getOAuthRequestToken();
			authorizationUrl = currentRequestToken.getAuthorizationURL();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return authorizationUrl;
	}  //getAuthUrl

	public Twitter getTwitter4j() {
		return twitter4j;
	}

	public boolean authorized() throws TwitterException {
		boolean authorized = false;
	    try {
			AccessToken accessToken = twitter4j.getOAuthAccessToken();
			setAccessToken(accessToken.getToken());
			setAccessTokenSecret(accessToken.getTokenSecret());
			authorized=true;
		} catch (TwitterException e) {
			e.printStackTrace();
			throw e;
		} finally {
		   
		}  
		return authorized;
	}  //authorized

	/* @purpose Get Twitter friends, assumption that Oauth tokens are set for the user 
	 * @version 1.0
	 * @return ArrayList<SocialNetworkFriend>
	 */
	public ArrayList <SocialNetworkFriend> getFriends() {
		String cursor;
		String screenName = appContext.getUserSettings().getTwitterSN();
		int[] intids = null;
		IDs ids = null;
		ArrayList<SocialNetworkFriend>arrayList = new ArrayList<SocialNetworkFriend>();
		try {
			ids = twitter4j.getFriendsIDs(screenName);
			intids = ids.getIDs();
            for (int x=0; x < intids.length; x++) {
            	User user = twitter4j.showUser(intids[x]);
            	SocialNetworkFriend friend = new SocialNetworkFriend(); 
            	friend.setUserID(String.valueOf(intids[x]));
            	friend.setName(user.getName());	
            	friend.setUserName(user.getScreenName());
            	friend.setProfileImageURL(user.getProfileImageURL());
            	arrayList.add(friend);
            }  //for
		} catch (TwitterException e) {
			e.printStackTrace();
		} finally {
			ids=null;
			intids = null;
		}  //finally
		return arrayList;
	}  //getFriends
} //class
