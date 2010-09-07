package com.jittr.android;

import twitter4j.http.AccessToken;

import com.jittr.android.fs.foursquareOAuth;
import static com.jittr.android.util.Consts.*;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

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
      //  setUpViews();
    }  //onCreate
    
	  /* get the authorization url for foursquare Oauth and fire off webview
	   * (non-Javadoc)
	   * @see android.app.Activity#onResume()
	   */
	  @Override
	  protected void onResume() {
	    //  super.onResume();

	   if (null == authURL) {
           authURL = foursquare.getAuthUrl();
	       Log.d(TAG,"Initial Load from onResume" + authURL);
	       Toast.makeText(this, "Please authorize GameON app!", Toast.LENGTH_LONG).show();
			this.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(authURL)));
	     //  authURL= "https://foursquare.com/login?continue=%2Foauth%2Fauthorize%3Foauth_token%3DHIYRI1TIXFBNMNB3NKBUKTVFRI3SMNISLB1A0O4GGLRUT2MR";
         //  webView.loadUrl(authURL);
	   } //if
       super.onResume();
	  }  //onResume

	  
	@Override
	protected void onNewIntent(Intent intent) {
        String verifier=null;
		String userName = null;
		super.onNewIntent(intent);
        AccessToken accessToken =null;
		Uri uri = intent.getData();
		Log.d(TAG,uri.toString());
		}
		
    private void setUpViews() {
	  	    webView = (WebView)findViewById(R.id.webView);
	  	    webView.setWebViewClient(webViewClient);
	  	    webView.getSettings().setJavaScriptEnabled(true);
	  	 //   webView.
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
	  	    	
	  	    	Log.d(TAG + "from shouldOverride" , "original url " + view.getOriginalUrl() + " new url " + url );
	  	    	view.loadUrl(url);
	  	    	return true;
	  	    }
	      };  //onLoadResource
} //class
