package com.jittr.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.jittr.android.R.id;
import com.jittr.android.api.betsquared.BSClientAPIImpl;
import com.jittr.android.fs.dto.User;
import com.jittr.android.fs.dto.Venue;
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
	private Button getUserDetailsButton;
	private Button getNearbyVenuesButton;
	private Button getPublicGamesButton;
	private Button getUserDashBoardButton;

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
        getUserDetailsButton = (Button)findViewById(R.id.getuserdetails_button);
        getNearbyVenuesButton = (Button)findViewById(R.id.getnearbyvenues_button);
        getPublicGamesButton = (Button)findViewById(R.id.getpublicgames_button);
        getUserDashBoardButton=(Button)findViewById(R.id.getuserdashboard_button);
        
        getUserDashBoardButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
               getUserDashBoardButtonClicked();
			}
		});
        
        getPublicGamesButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
		        getPublicGameButtonClicked();		
			}
		});
        
        getNearbyVenuesButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
			    getNearbyVenuesButtonClicked(); {
			    }
			}
		});
        
        getUserDetailsButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				getUserDetailsButtonClicked();
			}
		});
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
    
	protected void getUserDashBoardButtonClicked() {
       	Intent intent = new Intent(this,GetUserDashBoardActivity.class);
		startActivity(intent);		
	}
    protected void getPublicGameButtonClicked() {
       	Intent intent = new Intent(this,GetPublicGamesActivity.class);
		startActivity(intent);		
	}

	protected void getNearbyVenuesButtonClicked() {
    	Intent intent = new Intent(this,GetNearbyVenuesActivity.class);
		startActivity(intent);		}

	protected void getUserDetailsButtonClicked() {
    	Intent intent = new Intent(this,GetUserDetailsActivity.class);
		startActivity(intent);	
	}

	// finish the app 
    private void cancelButtonClicked(View v) {
    	
		Log.d("", "Test User Method ");
		//Create client 
		
		FSClientAPIImpl fs = new FSClientAPIImpl("xml", "9259485368", "findme3366");
		//Call FS implemnted methods
		
		User userObj = fs.getUserDetails("2478174", null, false,false);
		List users = fs.getFriends("2478174");
		
		
		/*
		List venues = fs.getNearByVenues("40.7204","-73.9933",10,""); //NY geolat:40.7204, geolong:-73.9933
		//Lets Verify the returned data..
		Log.d("", "Venues Size.... "+venues.size());
		for(int i=0;i<venues.size();i++) {
			Venue temp = (Venue)venues.get(i);
			Log.d("", " i  "+i);
			Log.d("", "Venue:  "+temp);
		}
		*/
		
		/*
		List users = fs.getCurrentCheckedInUsers("7834042");
		Log.d("Current checkedIn users :"+users.size());
		
		
		fs.checkin("79153", null, "just checked in using API", false, false, false, null, null);
		fs.getAllcategories();
		BSClientAPIImpl bs = new BSClientAPIImpl("xml", "9259485368", "findme3366");
		bs.getUserDashBoard("20");
		
		HashMap<String, String> params = new HashMap();
		params.put("league", "NFL");
		params.put("team", "New York Giants");
		params.put("sport", "baseball");
		params.put("latitude", "");
		params.put("longitude", "");
		params.put("timeframe", "0");
		bs.getPublicGames(params);
		
		
		BSClientAPIImpl bs = new BSClientAPIImpl("xml", "9259485368", "findme3366");
		bs.getUserDashBoard("20");
		
		
		HashMap<String, String> params = new HashMap();
		params.put("league", "NFL");
		params.put("team", "New York Giants");
		params.put("sport", "baseball");
		params.put("latitude", "");
		params.put("longitude", "");
		params.put("timeframe", "0");
		bs.getPublicGames(params);
		
		
		bs.addUser("ravindergade_test4");
		*/
		Log.d("", "Test  Method complted ");
		
    	finish();
    }  //cancelButton_pressed
    
    //start foursquare oauth 
    private void foursquareOauthButtonClicked(View v) {
		Intent intent = new Intent(this,FoursquareOauth.class);
		startActivity(intent);
    }
} //class