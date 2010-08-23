/**
 * 
 */
package com.jittr.android;

import com.jittr.android.GameOnProperties;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.location.*;

/**
 * @author juliomiyares
 * Location ; Database ; Preferences Services be encapsulated in Application Object
 *
 */
public class BetSquaredApplication extends Application {

	private LocationManager location; //global positioning
	private GameOnLocationManager locationListener;
	private GameOnProperties gameOnProperties;  //user data - currently stored as preferences
	private GameOnUserSettings gameOnUserSettings;
	private boolean isLoggedIn;
	private int userID;
	private String userName;
	/**
	 * 
	 */
	public BetSquaredApplication() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate() {
		super.onCreate();
		if (null == location) {
			location = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (null != location) {
	   	        Criteria criteria = new Criteria();
		        criteria.setAccuracy(Criteria.NO_REQUIREMENT);
		        criteria.setPowerRequirement(Criteria.NO_REQUIREMENT);
                String bestProvider = location.getBestProvider(criteria, true);
                locationListener = new GameOnLocationManager();
                location.requestLocationUpdates(bestProvider, 0, 0, locationListener);
               // location.
            } //if
		} //if
		if (null == gameOnProperties ) {
			gameOnProperties = new GameOnProperties(this);
			/* Check if logged in, if true, instantiate a go_user object, else fire up intent to log in*/
			int userID = isLoggedIn();
			if (userID > 0) {
                gameOnUserSettings = new GameOnUserSettings(userID);				
			} else {
				Intent intent = new Intent(this,GameOnLoginActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
		}  //if
    } //onCreate
	
	@Override
	public void onTerminate() {
		//database.close();
		if (null != location) location.removeUpdates(locationListener);
		if (null != gameOnProperties) gameOnProperties.close();
		super.onTerminate();
	} //Terminate
	
	/* check if the user is logged in - returns go_user ID if loggedIn or false
	 */
	 public int isLoggedIn() {
		gameOnProperties.retrieveSharedPreference("isloggedin", "false");
		return 0;
	 } //isLoggedIn
}  //class
