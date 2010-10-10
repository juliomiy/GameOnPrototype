package com.jittr.android.foursquare;

import android.content.Context;
import android.util.Log;
//added JHM 10/7/2010
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;

import net.oauth.OAuth;
import net.oauth.OAuthAccessor;
import net.oauth.OAuthConsumer;
import net.oauth.OAuthException;
import net.oauth.OAuthMessage;
import net.oauth.OAuthProblemException;
import net.oauth.OAuthServiceProvider;
import net.oauth.client.OAuthClient;
import net.oauth.client.httpclient4.HttpClient4;
//end added JHM 107/2010

import com.jittr.android.GameOnProperties;
import com.jittr.android.util.Consts;

public class GOFoursquareWrapper {
	private final static String TAG = "GOFoursquareWraper";
	//private CommonsHttpOAuthConsumer consumer;
	//private DefaultOAuthProvider provider;
	
	private GameOnProperties gameOnProperties;
	private Context context;

	private String consumerKey;
	private String consumerSecretKey;
	private String accessToken;
	private String accessTokenSecret;
	
	private OAuthServiceProvider serviceProvider;
	private OAuthClient client;
	private  OAuthAccessor accessor;
	
	/* prepare the object - currently the Oauth tokens are hardcoded via the defaultValue in the retrieveSharedPreference call
	 * TODO - replace the defaultValue with Null.
	 */
	public GOFoursquareWrapper(Context context) {
		 Log.d(TAG,"In constructor");
		 this.context=context;
		 consumerKey = Consts.FOURSQUARE_CONSUMER_KEY;   //obtain consumer gameon keys from properties
		 consumerSecretKey = Consts.FOURSQUARE_CONSUMER_SECRET;
         Log.d(TAG,"ConsumerKey = " + consumerKey + " ConsumerSecret = " + consumerSecretKey);
		 serviceProvider = defaultProvider();
		 accessor = defaultClient();
	} //constructor
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

	public void convertToAccessToken(String oauthVerifier) {
		try {
			OAuthClient oAuthClient = new OAuthClient(new HttpClient4());
			ArrayList<Map.Entry<String, String>> params = new ArrayList<Map.Entry<String, String>>();
			params.add(new OAuth.Parameter("oauth_verifier",
					oauthVerifier));
			OAuthMessage oAuthMessage = oAuthClient.getAccessToken(accessor,
					"GET", params);
			accessToken = oAuthMessage.getParameter("oauth_token");
			accessTokenSecret = oAuthMessage
					.getParameter("oauth_token_secret");
		} catch (OAuthProblemException error) {
			error.printStackTrace();
		} catch (Exception error) {
			error.printStackTrace();
		}
	}  //convertToAccessToken

    public OAuthServiceProvider defaultProvider()
	{
		return new OAuthServiceProvider(Consts.FOURSQUARE_REQUEST_TOKEN_ENDPOINT_URL,Consts.FOURSQUARE_AUTHORIZE_WEBSITE_URL,Consts.FOURSQUARE_ACCESS_TOKEN_ENDPOINT_URL);
	}  //default Provider

    public OAuthAccessor defaultClient(){
    	OAuthAccessor accessor = null;
    	try
		{
			if (null == serviceProvider) serviceProvider = defaultProvider();
			OAuthConsumer consumer = new OAuthConsumer(Consts.FOURSQUARE_CALLBACK_URL,Consts.FOURSQUARE_CONSUMER_KEY, Consts.FOURSQUARE_CONSUMER_SECRET, serviceProvider);
		    accessor = new OAuthAccessor(consumer);
			OAuthClient client = new OAuthClient(new HttpClient4());
			client.getRequestToken(accessor);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		} 
		catch (OAuthException e)
		{
			e.printStackTrace();
		} 
		catch (URISyntaxException e)
		{
			e.printStackTrace();
		}
		return accessor;
	}  //defaultClient
    

	/* return the url to take the user to grant rights foursquare page. If user selected  "Approve" , Gameon
	 * will be given rights to act on their behalf
	 */
	public String getAuthUrl()  {
		
		String url = null;
		if (null == accessor ) {
           accessor = defaultClient();			
		}

		url = accessor.consumer.serviceProvider.userAuthorizationURL +
		 "?oauth_token=" +
		 accessor.requestToken +
		 "&oauth_callback=" +
		 accessor.consumer.callbackURL;
	
		 Log.d(TAG,"Authorization url = " + url);
		 return url;
	//	provider = new DefaultOAuthProvider(FOURSQUARE_REQUEST_TOKEN_ENDPOINT_URL,
    //            FOURSQUARE_ACCESS_TOKEN_ENDPOINT_URL,
    //            FOURSQUARE_AUTHORIZE_WEBSITE_URL);
//		try {
	//		authUrl = provider.retrieveRequestToken(consumer, GAMEON_FOURSQUARE_CALLBACK_URL);
	//	} catch (OAuthMessageSignerException e) {
			// TODO Auto-generated catch block
	//		e.printStackTrace();
	//	} catch (OAuthNotAuthorizedException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
	//	} catch (OAuthExpectationFailedException e) {
			// TODO Auto-generated catch block
	//		e.printStackTrace();
//		} catch (OAuthCommunicationException e) {
			// TODO Auto-generated catch block
	//		e.printStackTrace();
	//	}
	//	return authUrl;
	}  //getAuthUrl
	
	/* Return true if the user has authorized application consumer, false otherwise
	 * 
	 */
	public boolean isAuthorized() {
	    if (accessToken == null || accessTokenSecret == null)
		    return false;
	    else
	    	return true;
	}  //is Authorized
	
	public String getConsumerKey() {
		return consumerKey;
	}

	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}

	public String getConsumerSecretKey() {
		return consumerSecretKey;
	}

	public void setConsumerSecretKey(String consumerSecretKey) {
		this.consumerSecretKey = consumerSecretKey;
	}

	/* Store OAuth Token/Secret in sharedPreferences */
	 public void storeAccessToken(String accessToken, String accessTokenSecret) {
		    gameOnProperties.storeSharedPreference("GAMEON_FOURSQUARE_OAUTH_TOKEN", accessToken);
		    gameOnProperties.storeSharedPreference("GAMEON_FOURSQUARE_OAUTH_TOKEN_SECRET", accessTokenSecret);
            this.accessToken= accessToken;
            this.accessTokenSecret=accessTokenSecret;
	 } //storeAccessToken
}  //class
