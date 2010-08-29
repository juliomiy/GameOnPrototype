package com.jittr.android;
/* Class that manages the application & user properties/preferences
 * 
 */
import java.io.IOException;

import java.io.InputStream;
import java.util.Properties;


import com.jittr.android.R;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;

public class GameOnProperties  {

	private static final String APPLICATION_PREFERENCES = "app_prefs";
	private Properties properties;
    private Resources resources; // = appContext.getResources();
    private BetSquaredApplication appContext;
    private SharedPreferences prefs;
    private final String TAG="GameOnProperties";
    
    //private AssetManager assetManager = new AssetManager();
    
	public GameOnProperties(BetSquaredApplication appContext) {
		// Read from the /res/raw directory
		
		this.appContext=appContext;
		prefs = appContext.getSharedPreferences(APPLICATION_PREFERENCES, Context.MODE_PRIVATE);
		try {
			resources = appContext.getResources();
		    final InputStream rawResource =  resources.openRawResource(R.raw.gameonv1);
		    properties = new Properties();
		    properties.load(rawResource);
		} catch (final NotFoundException e) {
		    System.err.println("Did not find raw resource: "+e);
		} catch (final IOException e) {
		    System.err.println("Failed to open microlog property file");
		}  //catch
	} //constructor

	/* Store a key/value pair in shared perferences*/
	public boolean storeSharedPreference(String key, String value) {
		if (prefs == null) return false;
	    Editor editor = prefs.edit();
	    editor.putString(key, value);
	    editor.commit();
	    return true;
	} //string
	public boolean storeSharedPreference(String key, int value) {
		if (prefs == null) return false;
	    Editor editor = prefs.edit();
	    editor.putInt(key, value);
	    editor.commit();
	    return true;
	} //int

	/* retrieve shared Preference value based on key */
	public String retrieveSharedPreference(String key) {
        return retrieveSharedPreference(key,null);
	}

	public String retrieveSharedPreference(String key, String defaultValue) {
        return prefs.getString(key, defaultValue);
	}

	public int retrieveSharedPreference(String key, int defaultInt) {
        return prefs.getInt(key, defaultInt);
	}

	public String getProperty(final String key) {
        String value = null;
        value = properties.getProperty(key);
		return value;
	}  //getProperty

/* Checks if this application has been previously run, returns true if this is first run, false if it has been run */	
	public boolean firstRun() {
		 if (prefs == null) return false;
		 boolean value = prefs.getBoolean("firstrun", true);
         return value;
	}
/*Update Shared Preferences to indicate first run has occurred */
	public void setFirstRun() {
		if (prefs == null) return;
	    Editor editor = prefs.edit();
	    editor.putBoolean("firstrun", false);  //set firstRun to false
	    editor.commit();
	}

	/* stub - eventually will clean up , persist etc */
	public void close() {
		// TODO Auto-generated method stub
		
	}

	public void getProperty(String string, boolean b) {
		// TODO Auto-generated method stub
		
	}

}  //class	
