package com.jittr.android;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

public class GameOnLocationManager implements LocationListener {

	private static double latitude;
	private  static double longitude;
	
	@Override
	public void onLocationChanged(Location location) {
       	latitude = location.getLatitude();
       	longitude = location.getLongitude();
       	String s = "";
        s += "Time: "        + location.getTime() + "\n";
        s += "\tLatitude:  " + location.getLatitude()  + "\n";
        s += "\tLongitude: " + location.getLongitude() + "\n";
        s += "\tAccuracy:  " + location.getAccuracy()  + "\n";
        Log.d("GameOnLocationManager",s);
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	public static double getLatitude() {
	    return latitude;
	}

	public static double getLongitude() {
	    return longitude;
	}

	@Override
	public String toString() {
		return "GameOnLocationManager [toString()=" + super.toString() + "]";
	}
}
