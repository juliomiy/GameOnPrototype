/**
 * 
 */
package com.jittr.android;

import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.http.AccessToken;

import com.jittr.android.twitter.twitterOAuth;
import static com.jittr.android.util.Consts.*;
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
	private WebView webView;
	private final static String TAG="GameOnTwitterOAuthActivity";
	/**
	 * 
	 */
	public GameOnTwitterOAuthActivity() {
         super();
	}  //default Constructor
	
    private WebViewClient webViewClient = new WebViewClient() {

		@Override
	    public void onLoadResource(WebView view, String url) {
            boolean rv = false;
            String twitterSN=null;
            String twitterID=null;
			Log.d(TAG,url);
	    	String token = null;
	    	AccessToken accessToken = null;
  		    Uri uri = Uri.parse(url);
  		    if (uri.getHost().equals("jittr.com")) {
   	    	    token = uri.getQueryParameter("oauth_token");
   	    	    if (null != token) {
   	    	    	rv = twitter.authorized();   //grab the accessToken and secret
   	    	    	if (rv) {
   	    	    		try {
							twitterSN = twitter.getTwitter4j().getScreenName();
							twitterID = String.valueOf( twitter.getTwitter4j().getId());
							//twitterName = twitter.getTwitter4j().get
							Log.d(TAG,"twitter SN: " + twitterSN);
						} catch (IllegalStateException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (TwitterException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						finally {
	   	   	    	        webView.setVisibility(View.INVISIBLE);
						}
   	    	    		twitter.saveUserAuthCredentials(getAppContext().getLoginID(), TWITTER_NETWORK,twitter.getAccessToken(), 
   	    	    				twitter.getAccessTokenSecret(),
   	    	    				twitterID, twitterSN, null);
   	    	    		getAppContext().refreshUserSettings(getAppContext().getLoginID());
   	   	    	        finish();
   	    	    	} //if
   	    	    } else {
   	    	    	//TODO Deal with Error
   	    	    }
  		    } //if
            super.onLoadResource(view, url);
	    }  //
    };   //WebViewClient

  @Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    twitter = new twitterOAuth(getAppContext(),TWITTER_NETWORK,null, null);
    setContentView(R.layout.webview);
    setUpViews();
  }
  
  @Override
  protected void onResume() {
    super.onResume();
    String authURL = null;
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
