/**
 * 
 */
package com.jittr.android.betsquared;

import static com.jittr.android.util.Consts.*;

import android.util.Log;

import com.jittr.android.BetSquaredApplication;
import com.jittr.android.api.betsquared.db.GameOnDatabase;
import java.util.HashMap;

import com.jittr.android.util.Consts;

import oauth.signpost.basic.DefaultOAuthProvider;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;

/**
 * @author juliomiyares
 *
 */
public abstract class GameOnSocialNetworkBase implements
		GameOnSocialNetworkInterface {
	
	private static final String TAG = "GameOnSocialNetworkBase";
	private String accessToken;
	private String accessTokenSecret;
	private CommonsHttpOAuthConsumer consumer;
	private DefaultOAuthProvider provider;
	private int socialNetwork;  //which network is being supported by the current instance
	private String consumerKey;
	private String consumerKeySecret;
	private String requestTokenEndpointUrl;
	private String callBackUrl;
	private String accessTokenEndpointUrl;
	private String authorizeAccessUrl;
    private BetSquaredApplication appContext;  //context to the application object
    
	public GameOnSocialNetworkBase() {
		super();
	} //default constructor
	
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessTokenSecret() {
		return accessTokenSecret;
	}

	public void setAccessTokenSecret(String accessTokenSecret) {
		this.accessTokenSecret = accessTokenSecret;
	}

	public GameOnSocialNetworkBase(BetSquaredApplication appObject, int network,String token, String secret) {
	    super();	
	    appContext = appObject;
	    socialNetwork = network;
		accessToken = token;
		accessTokenSecret = secret;
        setOAuthVariables();
		consumer = new CommonsHttpOAuthConsumer(getConsumerKey(),getConsumerKeySecret());

	} //constructor
	
	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}

	public void setConsumerKeySecret(String consumerKeySecret) {
		this.consumerKeySecret = consumerKeySecret;
	}

	private void setOAuthVariables() {

		switch (socialNetwork) {
		   case FACEBOOK_NETWORK:
			   consumerKey = FACEBOOK_CONSUMER_KEY;
			   consumerKeySecret = FACEBOOK_CONSUMER_SECRET;
			   requestTokenEndpointUrl = FACEBOOK_ACCESS_TOKEN_ENDPOINT_URL;
			   callBackUrl = FACEBOOK_CALLBACK_URL;
			   authorizeAccessUrl = FACEBOOK_AUTHORIZE_WEBSITE_URL;
			   break;
		   case TWITTER_NETWORK:
			   consumerKey = TWITTER_CONSUMER_KEY;
			   consumerKeySecret = TWITTER_CONSUMER_SECRET;
			   requestTokenEndpointUrl = TWITTER_REQUEST_TOKEN_ENDPOINT_URL;
			   accessTokenEndpointUrl = TWITTER_ACCESS_TOKEN_ENDPOINT_URL;
			   callBackUrl = TWITTER_CALLBACK_URL;
			   authorizeAccessUrl = TWITTER_AUTHORIZE_WEBSITE_URL;
			   break;
		   case FOURSQUARE_NETWORK:	   
			   consumerKey = FOURSQUARE_CONSUMER_KEY;
			   consumerKeySecret = FOURSQUARE_CONSUMER_SECRET;
			   requestTokenEndpointUrl = FOURSQUARE_REQUEST_TOKEN_ENDPOINT_URL;
			   accessTokenEndpointUrl = FOURSQUARE_ACCESS_TOKEN_ENDPOINT_URL;
			   callBackUrl = FOURSQUARE_CALLBACK_URL;
			   authorizeAccessUrl = FOURSQUARE_AUTHORIZE_WEBSITE_URL;
			   break;
		} //switch
	}


	/* (non-Javadoc)
	 * @see com.jittr.android.betsquared.GameOnSocialNetworkInterface#getAuthUrl()
	 */
	@Override
	public String getAuthUrl() {
		String authUrl = null;
		String callBackUrl = null;
		provider = new DefaultOAuthProvider(getRequestTokenEndpointUrl(),
                getAccessTokenEndpointUrl(),
                getAuthorizeAccessUrl());
		try {
			authUrl = provider.retrieveRequestToken(consumer, getCallBackUrl());
		} catch (OAuthMessageSignerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OAuthNotAuthorizedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OAuthExpectationFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OAuthCommunicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return authUrl;	
	} //getAuthUrl

	private String getCallBackUrl() {
		return callBackUrl;
	}

	public String getConsumerKeySecret() {
		return consumerKeySecret;
	}

	public String getConsumerKey() {
		return consumerKey;
	}

	private String getAuthorizeAccessUrl() {
		return authorizeAccessUrl;
	}

	private String getAccessTokenEndpointUrl() {
		return accessTokenEndpointUrl;
	}

	private String getRequestTokenEndpointUrl() {
        return requestTokenEndpointUrl;
	}

	/* (non-Javadoc)
	 * @see com.jittr.android.betsquared.GameOnSocialNetworkInterface#isAuthorized()
	 */
	@Override
	public boolean isAuthorized() {
		if (null != accessToken && ! "".equals(accessToken) && null != accessTokenSecret && !"".equals(accessTokenSecret) ) return true;
		return false;
	} //isAuthorized

	/* (non-Javadoc)
	 * @see com.jittr.android.betsquared.GameOnSocialNetworkInterface#saveUserAuthCredentials(int, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean saveUserAuthCredentials(int userID,int socialNetwork, String token,
			String tokenSecret, String networkUserID, String networkScreenName, String networkName) {

		String sql = "update go_user set ";
		if (null == token) token = getAccessToken();
		if (null == tokenSecret) tokenSecret = getAccessTokenSecret();
		
		//HashMap hm = new HashMap();
		// define parameters based on network
		switch (socialNetwork) {
			case FACEBOOK_NETWORK:
				  break;
			case TWITTER_NETWORK:
				  sql += GameOnDatabase.DB_USER_TABLE_TWITTER_TOKEN + "='" + token + "'," +
				         GameOnDatabase.DB_USER_TABLE_TWITTER_TOKEN_SECRET + "='" + tokenSecret + "'," +
				         GameOnDatabase.DB_USER_TABLE_TWITTER_USERID + "='" + networkUserID + "'," + 
				         GameOnDatabase.DB_USER_TABLE_TWITTER_SCREENNAME + "='" + networkScreenName + "'," + 
				         GameOnDatabase.DB_USER_TABLE_TWITTER_NAME + "='" + networkUserID + "'" ; 
;
				  break;
			case FOURSQUARE_NETWORK:
				  break;
			
		}  //switch
		sql += " where userID = " + userID;
		Log.d(TAG,sql);
		//first save to device - low risk, minimal letency - stored in sqlite
		appContext.updateDatabaseSQL(sql);
		//save to Host via Http Post
		
		return false;
	}  //saveUserAuthCredentials

}  //class
