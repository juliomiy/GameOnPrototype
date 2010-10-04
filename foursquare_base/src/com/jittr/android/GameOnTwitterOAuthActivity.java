/**
 * 
 */
package com.jittr.android;

import java.net.URL;
import java.util.HashMap;

import twitter4j.ResponseList;
import twitter4j.TwitterException;
import twitter4j.User;
import twitter4j.http.AccessToken;

import com.jittr.android.api.betsquared.BSClientAPIImpl;
import com.jittr.android.bs.dto.GameOnUserSettings;
import com.jittr.android.bs.dto.UserAddResponse;
import com.jittr.android.twitter.twitterOAuth;
import com.jittr.android.util.Consts;

import static com.jittr.android.util.Consts.*;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @author juliomiyares
 * @version 1.0
 *
 */
public class GameOnTwitterOAuthActivity extends GameOnBaseActivity {

	//private Twitter twitter4j;
	private twitterOAuth twitter;
	private boolean isNewRegistration;
	private WebView webView;
	private Intent callingIntent;
	private final static String TAG="GameOnTwitterOAuthActivity";
	/**
	 * 
	 */
	public GameOnTwitterOAuthActivity() {
         super();
	}  //default Constructor
	
    private WebViewClient webViewClient = new WebViewClient() {
		private String name;
		@Override
	    public void onLoadResource(WebView view, String url) {
            boolean rv = false;
            boolean errorState = false;
            String twitterSN=null;
            String imageURL = null;
            String name = null;
            int twitterID=0;
			Log.d(TAG,url);
	    	String token = null;
	    	AccessToken accessToken = null;
  		    Uri uri = Uri.parse(url);
  		    if (uri.getHost().equals("jittr.com")) {
   	    	    token = uri.getQueryParameter("oauth_token");
   	    	    if (null != token) {
   	    	    		try {
   	    	    			twitter.authorized();
							twitterSN = twitter.getTwitter4j().getScreenName();
							twitterID = twitter.getTwitter4j().getId();
							//twitterName = twitter.getTwitter4j().get
							Log.d(TAG,"twitter SN: " + twitterSN);
							int[] i = {twitterID};
							ResponseList<User> response = twitter.getTwitter4j().lookupUsers(i);
							User user = response.get(0);
						    imageURL = user.getProfileImageURL().toString();
							name = user.getName();
						} catch (IllegalStateException e) {
                            errorState=true;
							e.printStackTrace();
						} catch (TwitterException e) {
                            errorState=true;
							e.printStackTrace();
						}  //try/catch
						finally {
	   	   	    	        webView.setVisibility(View.INVISIBLE);
						}   //finally
						if (!isNewRegistration && !errorState ) {
							    Log.d(TAG, "In Not New Registration");
   	    	       		        twitter.saveUserAuthCredentials(getAppContext().getLoginID(), TWITTER_NETWORK,twitter.getAccessToken(), 
   	    	    				twitter.getAccessTokenSecret(),
   	    	    				String.valueOf(twitterID), twitterSN, null);
   	    	    	    		getAppContext().refreshUserSettings(getAppContext().getLoginID());
						} else if (isNewRegistration && !errorState) {
							Log.d(TAG,"In New Registration");
						    GameOnUserSettings userSettings = new GameOnUserSettings(twitterID,twitterSN,null,null,null,null,
						    		Consts.TWITTER_NETWORK,"TWITTER",twitter.getAccessToken(),twitter.getAccessTokenSecret(), imageURL, name);
						    Log.d(TAG,userSettings.toString());
						    rv = registerNewUserHost(userSettings);  //register on host
						    if (rv) getAppContext().registerNewUser(userSettings);  //register on device
						    callingIntent.putExtra(Consts.INTENT_USER_SETTINGS, userSettings);
						    setResult(RESULT_OK,callingIntent);
						    //
						} //if
   	   	    	        finish();
   	    	    	} //if
   	    	    } else {
   	    	    	//TODO Deal with Error
   	    	    }
  		   // } //if
            super.onLoadResource(view, url);
	    }  //

    };   //WebViewClient
 /*
  * @uathor juliomiyares
  * @version 1.0
  * @purpose - register new user authenticated via Twitter onto the host
  * 
  */
private boolean registerNewUserHost(GameOnUserSettings userSettings) {
    HashMap<String,String> hm = new HashMap<String,String>();

    hm.put(  "newusername", userSettings.getTwitterSN());
    hm.put("avatarurl", userSettings.getImageURL());
	hm.put("primarynetworkid", String.valueOf(Consts.TWITTER_NETWORK));
	hm.put("primarynetworkname", "TWITTER");
	hm.put("oauthtoken", userSettings.getTwitterOAuthToken());
    hm.put("oauthtokensecret", userSettings.getTwitterOAuthTokenSecret());
    hm.put("twitterid", userSettings.getTwitterSN());
	BSClientAPIImpl bs = new BSClientAPIImpl();
	if (null == bs ) return false;  //TODO Deal with error
	UserAddResponse newUser = bs.addUser(hm);  //attempt to add New User to host
	if (null == newUser) return false;  //TODO Deal with Error 
    Log.d(TAG,newUser.toString());
    if (newUser.getStatus_code().equals("200")) {
	       int newUserID = Integer.parseInt(newUser.getUserid());
	       userSettings.setUserID(newUserID);
	       userSettings.setUserName(userSettings.getUserName() + Consts.TWITTER_SUFFIX);  //TODO - replace - bug in response that doesnt return userName from webservice call
    } else return false;
    return true;
} //registerNewUserHost

  @Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    callingIntent = this.getIntent();
    twitter = new twitterOAuth(getAppContext(),TWITTER_NETWORK,null, null);
    setContentView(R.layout.webview);
    setUpViews();
  }
  
  @Override
  protected void onResume() {
    super.onResume();
    String authURL = null;
    
    isNewRegistration = getIntent().getBooleanExtra(Consts.NEW_REGISTRATION, false) ;  //type of operation
//	try {
		authURL = twitter.getAuthUrl();
		webView.loadUrl(authURL);
//	} catch (GameOnTwitterException e) {
/* TODO - finish is for convenience at moment */
//		finish();
//		return;
//	}  //try block
//    webView.loadUrl(authURL);
  }  //onResume
  
  private void setUpViews() {
       webView = (WebView)findViewById(R.id.webView);
	   webView.setWebViewClient(webViewClient);
  }  //setUpViews

} //class
