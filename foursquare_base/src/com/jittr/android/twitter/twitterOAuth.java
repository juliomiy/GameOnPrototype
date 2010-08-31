/**
 * 
 */
package com.jittr.android.twitter;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.http.AccessToken;
import twitter4j.http.RequestToken;

import com.jittr.android.BetSquaredApplication;
import com.jittr.android.betsquared.GameOnSocialNetworkBase;

/**
 * @author juliomiyares
 *
 */
public class twitterOAuth extends GameOnSocialNetworkBase {

	private Twitter twitter4j;
	/**
	 * 
	 */
	public twitterOAuth() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param network
	 * @param token
	 * @param secret
	 */
	public twitterOAuth(BetSquaredApplication appContext, int network, String token, String secret) {
		super(appContext, network, token, secret);
	    twitter4j = new TwitterFactory().getInstance();
	    twitter4j.setOAuthConsumer(getConsumerKey(), getConsumerKeySecret());
	
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return authorizationUrl;
	}

	public Twitter getTwitter4j() {
		return twitter4j;
	}

	public boolean authorized() {
		boolean authorized = false;
	    try {
			AccessToken accessToken = twitter4j.getOAuthAccessToken();
			setAccessToken(accessToken.getToken());
			setAccessTokenSecret(accessToken.getTokenSecret());
			authorized=true;
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return authorized;
	}  //authorized
} //class