/**
 * 
 */
package com.jittr.android;

import com.jittr.android.twitter.twitterOAuth;

import android.content.Intent;
import android.os.Bundle;

/**
 * @author juliomiyares
 *
 */
public class TestTwitterActivity extends GameOnBaseActivity {
	twitterOAuth twitter;
	/**
	 * 
	 */
	public TestTwitterActivity() {
		// TODO Auto-generated constructor stub
	}
	public void OnCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
         setUpViews();
	}
	
	  private void setUpViews() {
		// TODO Auto-generated method stub
		
	}
	public void onResume() {
	        
			super.onResume();
		    if (null == twitter ) {
				 twitter = new twitterOAuth(getAppContext());
				 twitter.getFriends();		    	
		    }
	    } //resume

}  //class
