package com.jittr.android;

import com.jittr.android.fs.foursquareOAuth;
import static com.jittr.android.util.Consts.*;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class FoursquareOauthActivity extends GameOnBaseActivity {

	private static final String TAG = "FoursquareOauthActivity";
	private WebView webView;
	private foursquareOAuth foursquare;
	private Context appContext;
	private String authURL;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        foursquare = new foursquareOAuth(getAppContext(),FOURSQUARE_NETWORK, null, null);
        setUpViews();
    }
	  /* get the authorization url for foursquare Oauth and fire off webview
	   * (non-Javadoc)
	   * @see android.app.Activity#onResume()
	   */
	  @Override
	  protected void onResume() {
	  //  super.onResume();

	   // if (null == authURL) {
           authURL = foursquare.getAuthUrl();
	       Log.d(TAG,authURL);
           webView.loadUrl(authURL);
	   // } //if
          super.onResume();
	  }  //onResume

    private void setUpViews() {
	  	    webView = (WebView)findViewById(R.id.webView);
	  	    webView.setWebViewClient(webViewClient);
	  	//    webView.getSettings().setJavaScriptEnabled(true);
	 }   //setUpViews

	 private WebViewClient webViewClient = new WebViewClient() {
	  	    @Override
	  	    public void onLoadResource(WebView view, String url) {
	  	      // the URL we're looking for looks like this:
	  	      // http://otweet.com/authenticated?oauth_token=1234567890qwertyuiop
	  	      Uri uri = Uri.parse(url);
	  	      Log.d(TAG,url);
	  	      if (uri.getHost().equals("jittr.com")) {
	  	        String token = uri.getQueryParameter("oauth_token");
	  	        if (null != token) {
	  	          webView.setVisibility(View.INVISIBLE);
	   	        }  //if   	     
	  	      }  //if
		      super.onLoadResource(view, url);
	  	    }  //onLoadResource

	  	    public boolean shouldOverrideUrlLoading(WebView view, String url) {
	  	    	
	  	    	Log.d(TAG + "from shouldOverride" , url );
	  	    	view.loadUrl(url);
	  	    	return true;
	  	    }
	      };  //onLoadResource
} //class
