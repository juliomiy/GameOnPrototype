/**
 * 
 */
package com.jittr.android;

import android.app.Application;
import android.content.Context;
import android.location.*;

/**
 * @author juliomiyares
 * Location ; Database ; Preferences Services be encapsulated in Application Object
 *
 */
public class BetSquaredApplication extends Application {

	private LocationManager location; //global positioning
	private GameOnLocationManager locationListener;
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
    } //onCreate
	
	@Override
	public void onTerminate() {
		//database.close();
		if (null != location) location.removeUpdates(locationListener);
		super.onTerminate();
	} //Terminate
}  //class
