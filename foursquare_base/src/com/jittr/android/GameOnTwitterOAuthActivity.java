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
   	    	   // 	rv = twitter.authorized();   //grab the accessToken and secret
   	    	   // 	if (rv) {
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
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (TwitterException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						finally {
							Log.d(TAG,"In Finally");
	   	   	    	        webView.setVisibility(View.INVISIBLE);
						}
						if (!isNewRegistration ) {
							    Log.d(TAG," in Not New Registration");
   	    	       		        twitter.saveUserAuthCredentials(getAppContext().getLoginID(), TWITTER_NETWORK,twitter.getAccessToken(), 
   	    	    				twitter.getAccessTokenSecret(),
   	    	    				String.valueOf(twitterID), twitterSN, null);
   	    	    	    		getAppContext().refreshUserSettings(getAppContext().getLoginID());
						} else {
						    Log.d(TAG," in New Registration");
							
						    GameOnUserSettings userSettings = new GameOnUserSettings(twitterID,twitterSN,null,null,null,null,
						    		Consts.TWITTER_NETWORK,"TWITTER",twitter.getAccessToken(),twitter.getAccessTokenSecret(), imageURL, name);
						    Log.d(TAG,userSettings.toString());
						    registerNewUserHost(userSettings);
						    getAppContext().registerNewUser(userSettings);
						    callingIntent.putExtra(Consts.INTENT_USER_SETTINGS, userSettings);
						    setResult(RESULT_OK,callingIntent);
						    //
						} //if
						Log.d(TAG,"About to call finish");
   	   	    	        finish();
   	    	    	} //if
   	    	    } else {
   	    	    	Log.d(TAG,"In Empty else block");
   	    	    	//TODO Deal with Error
   	    	    }
  		   // } //if
            super.onLoadResource(view, url);
	    }  //

    };   //WebViewClient
private void registerNewUserHost(GameOnUserSettings userSettings) {
    HashMap<String,String> hm = new HashMap<String,String>();

    hm.put( (String) "newusername", userSettings.getTwitterSN());
	hm.put("primarynetworkid", String.valueOf(Consts.TWITTER_NETWORK));
	hm.put("primarynetworkname", "TWITTER");
	hm.put("oauthtoken", userSettings.getTwitterOAuthToken());
    hm.put("oauthtokensecret", userSettings.getTwitterOAuthTokenSecret());
	BSClientAPIImpl bs = new BSClientAPIImpl();
	if (null == bs ) return;  //TODO Deal with error
	UserAddResponse newUser = bs.addUser(hm);  //attempt to add New User to host
	if (null == newUser) return;  //TODO Deal with Error 
    Log.d(TAG,newUser.toString());
    if (newUser.getStatus_code().equals("200")) {
	       int newUserID = Integer.parseInt(newUser.getUserid());
    }
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
