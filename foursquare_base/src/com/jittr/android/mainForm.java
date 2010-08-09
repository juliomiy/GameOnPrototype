package com.jittr.android;

import com.jittr.android.fs.dto.User;
import com.jittr.android.fs.impl.FSClientAPIImpl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class mainForm extends Activity {
 	private Button cancelButton;
	private Button foursquareOauthButton;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setUpViews();
        System.out.println("Inside Create...");
        //System.setProperty("proxySet", "true");
    	//System.setProperty("proxyHost", "ftpproxy.wdc.cingular.net");
    	//System.setProperty("proxyPort", "8080");
    	
    }   //onCreate
    
    private void setUpViews() {
		cancelButton = (Button) findViewById(R.id.cancel_button);
        foursquareOauthButton = (Button)findViewById(R.id.foursquareoauth);
        cancelButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				cancelButtonClicked(v);
			}
		});
        foursquareOauthButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				foursquareOauthButtonClicked(v);
			}
		});

    } //setupViews
    
    // finish the app 
    private void cancelButtonClicked(View v) {
    	
		Log.d("", "Test User Method ");
		//Create client 
		FSClientAPIImpl fs = new FSClientAPIImpl("xml", "9259485368", "findme3366");
		//Call FS implemnted methods
		User userObj = fs.getUserDetails("2478174", null, false,false);
		System.out.println("Got the USer Obj ..."+userObj.getFirstName());
		
    	finish();
    }  //cancelButton_pressed
    
    //start foursquare oauth 
    private void foursquareOauthButtonClicked(View v) {
		Intent intent = new Intent(this,FoursquareOauth.class);
		startActivity(intent);
    }
} //class