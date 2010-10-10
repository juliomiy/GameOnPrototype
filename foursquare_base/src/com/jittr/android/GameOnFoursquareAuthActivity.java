package com.jittr.android;

import java.io.IOException;
import java.net.URISyntaxException;

import net.oauth.OAuthAccessor;
import net.oauth.OAuthConsumer;
import net.oauth.OAuthException;
import net.oauth.OAuthServiceProvider;
import net.oauth.client.OAuthClient;
import net.oauth.client.httpclient4.HttpClient4;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.jittr.android.foursquare.Constants;
import com.jittr.android.foursquare.GameOnFoursquare;
import com.jittr.android.util.Consts;
/**
 * 
 * @author System Integration
 *
 */
public class GameOnFoursquareAuthActivity extends GameOnBaseActivity {
	private GameOnFoursquare foursquare;
	private static final String TAG = "SuccessActivity";
	public static OAuthAccessor accessor = null;
	
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appContext = (BetSquaredApplication) getAppContext();
        foursquare = new GameOnFoursquare(appContext,Consts.FOURSQUARE_NETWORK,null,null);
        setContentView(R.layout.main);
        try {
        	Constants.userList.clear();
			doLogin();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	/** Called when the activity is Resume. */
	protected void onResume(){
		super.onResume();
		Uri uri = this.getIntent().getData();
		Log.i(TAG, "onResume");
		if (uri != null)
		{
			Log.d(TAG,uri.getEncodedQuery());
			Constants.REQUEST_TOKEN = uri.getQueryParameter("oauth_token");
			Constants.OAUTH_VERIFIER = uri.getQueryParameter("oauth_verifier");
			//GameOnFoursquare foursquare = new GameOnFoursquare(appContext,Consts.FOURSQUARE_NETWORK);
			//Util.convertToAccessToken();
			foursquare.convertToAccessToken();
			Log.i(TAG, "Access_Token" +Constants.ACCESS_TOKEN);
			Log.i(TAG, "OAUTH_TOKEN_SECRET" +Constants.OAUTH_TOKEN_SECRET);
			Log.i(TAG, "Request_Token" +Constants.REQUEST_TOKEN);

			launchMainActivity();
			foursquare.readXMLFile();
			foursquare.friendsList();
		}
	}
	/** goLogin Method to Launch Browser. */
	public void doLogin() throws Exception{
		try
		{
			defaultClient();
			Intent i = new Intent(Intent.ACTION_VIEW);
			i.setData(Uri.parse(accessor.consumer.serviceProvider.userAuthorizationURL
					+ "?oauth_token="
					+ accessor.requestToken
					+ "&oauth_callback="
					+ accessor.consumer.callbackURL));
			startActivity(i);
		}
		catch (Exception e)
		{	}
	}
	
  /*  public static OAuthServiceProvider defaultProvider()
	{
		//return new OAuthServiceProvider(Constants.OAUTH_REQUEST_URL,Constants.OAUTH_AUTHORIZATION_URL,Constants.OAUTH_ACCESS_URL);
		return new OAuthServiceProvider(Consts.FOURSQUARE_REQUEST_TOKEN_ENDPOINT_URL,Consts.FOURSQUARE_AUTHORIZE_WEBSITE_URL,Consts.FOURSQUARE_ACCESS_TOKEN_ENDPOINT_URL);
	}
*/    
    public static OAuthAccessor defaultClient(){
		OAuthClient client = null;
		try
		{
			OAuthServiceProvider provider = GameOnFoursquare.defaultProvider();
//			OAuthConsumer consumer = new OAuthConsumer(Constants.CALLBACK_URL,Constants.CONSUMER_KEY, Constants.CONSUMER_SECRET, provider);
			OAuthConsumer consumer = new OAuthConsumer(Consts.FOURSQUARE_CALLBACK_URL,Consts.FOURSQUARE_CONSUMER_KEY, Consts.FOURSQUARE_CONSUMER_SECRET, provider);
			if (GameOnFoursquareAuthActivity.accessor == null)
			{
				accessor = new OAuthAccessor(consumer);
				client = new OAuthClient(new HttpClient4());
				client.getRequestToken(accessor);
			}
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
	}
    
    public void launchMainActivity(){
    	Intent authorizationIntent = new Intent(this,mainForm.class);
		startActivity(authorizationIntent);
    }
}
