package com.jittr.android;

import com.jittr.android.foursquare.GOFoursquareWrapper;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class FoursquareOauth extends Activity {

	private WebView webView;
	private GOFoursquareWrapper foursquare;
	private Context appContext;
	private String authURL;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authorization_view);
  	    appContext = this.getApplicationContext();
        foursquare = new GOFoursquareWrapper(appContext);
        setUpViews();
    }
	  /* get the authorization url for foursquare Oauth and fire off webview
	   * (non-Javadoc)
	   * @see android.app.Activity#onResume()
	   */
	  @Override
	  protected void onResume() {
	    super.onResume();
        authURL = foursquare.getAuthUrl();
	    webView.loadUrl(authURL);
	  }  //onResume

    private void setUpViews() {
	  	    webView = (WebView)findViewById(R.id.web_view);
	  	    webView.setWebViewClient(webViewClient);
	  	    webView.getSettings().setJavaScriptEnabled(true);
	 }

	 private WebViewClient webViewClient = new WebViewClient() {
	  	    @Override
	  	    public void onLoadResource(WebView view, String url) {
	  	      // the URL we're looking for looks like this:
	  	      // http://otweet.com/authenticated?oauth_token=1234567890qwertyuiop
	  	      Uri uri = Uri.parse(url);
	  	      if (uri.getHost().equals("jittr.com")) {
	  	        String token = uri.getQueryParameter("oauth_token");
	  	        if (null != token) {
	  	          webView.setVisibility(View.INVISIBLE);
	   	        }  //if   	     
	  	      }  //if
		      super.onLoadResource(view, url);
	  	    } //if
	      };  //onLoadResource
}
