/**
 * 
 */
package com.jittr.android;

import com.jittr.android.GameOnProperties;
import com.jittr.android.api.betsquared.db.GameOnDatabase;
import com.jittr.android.bs.dto.GameOnUserSettings;
import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.*;
import android.util.Log;

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
	private SQLiteDatabase database;
	private final static String TAG = "BetSquaredApplication";
	private final String LOGGEDINAS = "loggedinas";
	/**
	 * 
	 */
	public BetSquaredApplication() {
		// TODO Auto-generated constructor stub
 	}


	@Override
	public void onCreate() {
		super.onCreate();
		GameOnDatabase helper = new GameOnDatabase(this);
		database = helper.getWritableDatabase();
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
		}
		userID = isLoggedIn();
		if (userID > 0) {
            gameOnUserSettings = new GameOnUserSettings(userID);
 		} else {
		 /*   Intent intent = new Intent(this,GameOnLoginActivity.class);
		    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		    startActivity(intent); */
     	}  //if
	
    } //onCreate
	
	@Override
	public void onTerminate() {
		//database.close();
		if (null != location) location.removeUpdates(locationListener);
		if (null != gameOnProperties) {
			gameOnProperties.close();
			gameOnProperties = null;
		}
		if (null != database) database.close();
		super.onTerminate();
	} //Terminate
	
	public void Destroy() {
		gameOnProperties = null;
	}
/* Log into the application - 
 * TODO - sync between application handset device credentials and host based credentials 
 */
	public boolean login(String userName, String password) {
        boolean loginSuccessful = false;
        String sql = "select userName,userID from " + GameOnDatabase.DB_USER_TABLE +
        		     " where userName='" + userName + "' and password = '" + password + "'";
        Log.d(TAG, sql);

        /* will return at most 1 record so no loop is necessary. 0 rowcount means the credentials were not correct */
        Cursor cursor = database.rawQuery(sql,null); 
        if (null != cursor && cursor.getCount() >0 && cursor.moveToFirst() ) {
        	userID = cursor.getInt(1);
        	sql = "update " + GameOnDatabase.DB_USER_TABLE + " set loggedInSince = CURRENT_TIMESTAMP where userID = " + userID; 
            Log.d(TAG,sql);
        	database.execSQL(sql);
        	gameOnProperties.storeSharedPreference(LOGGEDINAS, userID);
        	Log.d(TAG,"UserID of Login = " + String.valueOf(userID));
            gameOnUserSettings = new GameOnUserSettings(userID);
        	loginSuccessful = true;
        } //if
        if (null != cursor) cursor.close();
 		return loginSuccessful;
	} //login
	
	/* Logout by setting the loggedInSince back to null 
	 * TODO - figure out how to get the count of records updated to confirm this worked*/
	public boolean logout() {
		boolean logoutSuccessful = false;
		String sql = "update " + GameOnDatabase.DB_USER_TABLE + " set loggedInSince = null where userID = " + userID;
        Log.d(TAG,sql);
    	database.execSQL(sql);
    	gameOnProperties.storeSharedPreference(LOGGEDINAS, 0);
    
    	logoutSuccessful = true;
    	Log.d(TAG,"Result of logout = " + String.valueOf(logoutSuccessful));
		return logoutSuccessful;
	} //logout
	/* check if the user is logged in - returns go_user ID if loggedIn or false
	 */
	 public int isLoggedIn() {

		int loggedInAsInt = 0;
		int preferredUserID = gameOnProperties.retrieveSharedPreference(LOGGEDINAS, 0);
        
		if (preferredUserID > 0) {
           String sql = "select loggedInSince from " + GameOnDatabase.DB_USER_TABLE + " where userID = " + preferredUserID;   
           Log.d(TAG,sql);
           Cursor cursor = database.rawQuery(sql,null); 
           if (null != cursor && cursor.getCount() >0 && cursor.moveToFirst() ) {
              long loggedInSince = cursor.getLong(0);
              if (loggedInSince > 0) {
            	  loggedInAsInt = preferredUserID;
              }
              Log.d(TAG,"Value of LoggedInSince = " + loggedInSince);
          /*    gameOnUserSettings = new GameOnUserSettings(userID);
             
              gameOnUserSettings.setFoursquareID("juliomiy");  //temporary
              gameOnUserSettings.setFoursquarePassword("cuba1a");  //temporary              loggedInAsInt = preferredUserID;
          */
              cursor.close();
           } //if
      
        } //if
        return loggedInAsInt;
	 } //isLoggedIn
	 
	 /* register a user on the device 
	  * This eventually will register an entirely new user for the application 
	  * Is an involved function as the user may have already registered on the website and will need to ascertain that 
	  */
	 public int registerNewUser(int userID, String userName, String password, String firstName, String lastName) {
         //int userID = 0;		 
  
         //insert to device go_user sqlite - uses the userID generated and provided by call to host to add new user
 		ContentValues values = new ContentValues();
 		values.put(GameOnDatabase.DB_USER_TABLE_USERID,userID);
 		values.put(GameOnDatabase.DB_USER_TABLE_USERNAME, userName);
 		values.put(GameOnDatabase.DB_USER_TABLE_PASSWORD, password);
 		values.put(GameOnDatabase.DB_USER_TABLE_FIRSTNAME, firstName);
 		values.put(GameOnDatabase.DB_USER_TABLE_LASTNAME, lastName);
 		userID = (int) database.insert(GameOnDatabase.DB_USER_TABLE, null, values);

 		//update gameOnProperties to note userID on the handset. There is an implied Login while registering
 		//so just call login function which will take care of all the scaffolding
 		
 		boolean rv = login(userName,password);
 		return userID;
	 } //registerNewUser
	
	 public int getLoginID() {
		   Log.d(TAG,gameOnUserSettings.toString());
		 return (null != gameOnUserSettings ? gameOnUserSettings.getUserID() : 0);
	 }

	 public GameOnUserSettings getUserSettings() {

		   return gameOnUserSettings; 
	 }
}  //class
