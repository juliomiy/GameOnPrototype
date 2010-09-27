/**
 * 
 */
package com.jittr.android;

import java.util.ArrayList;

import com.jittr.android.GameOnProperties;
import com.jittr.android.api.betsquared.db.GameOnDatabase;
import com.jittr.android.bs.dto.Friend;
import com.jittr.android.bs.dto.GameOnUserSettings;
import static com.jittr.android.util.Consts.*;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.location.*;
import android.util.Log;

/**
 * @author juliomiyares
 * @version 1.0
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
            gameOnUserSettings = refreshUserSettings(userID);
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
    	gameOnUserSettings = null;  //blow away existing userSettings
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
              gameOnUserSettings = refreshUserSettings(loggedInAsInt);
              cursor.close();
           } //if
      
        } //if
        return loggedInAsInt;
	 } //isLoggedIn
	 
	 /* register a user on the device 
	  * This eventually will register an entirely new user for the application 
	  * Is an involved function as the user may have already registered on the website and will need to ascertain that 
	  * @returns integer userId
	  */
	// public int registerNewUser(int userID, String userName, String password, String firstName, String lastName, String email) {
     public int registerNewUser(GameOnUserSettings userSettings) {
        if (null == userSettings) return 0;
    	 //int userID = 0;		 
  
        //insert to HOST first - if this succeeds 
        
         //insert to device go_user sqlite - uses the userID generated and provided by call to host to add new user
 		ContentValues values = new ContentValues();
 		values.put(GameOnDatabase.DB_USER_TABLE_USERID,userSettings.getUserID());
 		values.put(GameOnDatabase.DB_USER_TABLE_USERNAME, userSettings.getUserName());
 		values.put(GameOnDatabase.DB_USER_TABLE_PASSWORD, userSettings.getPassword());
 		values.put(GameOnDatabase.DB_USER_TABLE_FIRSTNAME, userSettings.getFirstName());
 		values.put(GameOnDatabase.DB_USER_TABLE_LASTNAME, userSettings.getLastName());
 		values.put(GameOnDatabase.DB_USER_TABLE_EMAIL, userSettings.getEmail());
 		values.put(GameOnDatabase.DB_USER_TABLE_PHONENUMBER, userSettings.getPhoneNumber());
 		values.put(GameOnDatabase.DB_USER_TABLE_PRIMARY_NETWORKID, userSettings.getPrimaryNetworkID());
 		values.put(GameOnDatabase.DB_USER_TABLE_PRIMARY_NETWORKNAME, userSettings.getPrimaryNetworkName());

 		switch (userSettings.getPrimaryNetworkID()) {
 		    case TWITTER_NETWORK:
 		    	values.put(GameOnDatabase.DB_USER_TABLE_TWITTER_TOKEN, userSettings.getTwitterOAuthToken());
 		    	values.put(GameOnDatabase.DB_USER_TABLE_TWITTER_TOKEN_SECRET, userSettings.getTwitterOAuthTokenSecret());
 		    	values.put(GameOnDatabase.DB_USER_TABLE_TWITTER_SCREENNAME, userSettings.getTwitterSN());
 		    	break;
 		    case FOURSQUARE_NETWORK:
 		    	values.put(GameOnDatabase.DB_USER_TABLE_FS_TOKEN, userSettings.getFoursquareOAuthToken());
 		    	values.put(GameOnDatabase.DB_USER_TABLE_FS_TOKEN_SECRET, userSettings.getFoursquareOAuthTokenSecret());
 		    	break;
 		    case FACEBOOK_NETWORK:
 		    	break;
 		} //switch
 		userID = (int) database.insert(GameOnDatabase.DB_USER_TABLE, null, values);

 		//update gameOnProperties to note userID on the handset. There is an implied Login while registering
 		//so just call login function which will take care of all the scaffolding
 		
 		boolean rv = login(userSettings.getUserName(),userSettings.getPassword());
 		return (rv) ? userID : 0;
	 } //registerNewUser
	
	 /* pass raw sql and apply to sqlite database 
	  * returns number of rows affected
	  */
	 public int updateDatabaseSQL(String sql) {
		    int rowsAffected = -1;
	        Log.d(TAG,sql);
	        try {
	    	   database.execSQL(sql);
	    	   rowsAffected++;    //TODO - find way to determine recordsAffected
	        } catch (SQLException e) {
	           e.printStackTrace();	
	        }  //try
	    	return rowsAffected;
	 }  //updateDatabaseSQL
	 
	 /*
	  * Get friends from go_userFriends handset table
	  * This function does not require going across the network
	  */
	 public ArrayList<Friend> getFriends() {
		  ArrayList<Friend>arrayList = null;
		  int userID;
		  
		  userID = getLoginID();
	      Cursor cursor = database.query(GameOnDatabase.DB_FRIENDS_TABLE, 
	    		  new String[] { GameOnDatabase.DB_FRIENDS_TABLE_USERNAME,
	    		                 GameOnDatabase.DB_FRIENDS_TABLE_USERID,
	    		                 GameOnDatabase.DB_FRIENDS_TABLE_NAME },"userID ='" + userID + "'", null, null, null, null);	 
	       if (null != cursor && cursor.getCount() >0 && cursor.moveToFirst() ) {
	    	   Log.d(TAG, "Count of Friends in cursor = " + cursor.getCount());
               arrayList = new ArrayList<Friend>(); 
	           do {
                   Friend friend = new Friend();
	    	       friend.setFriendusername(cursor.getString(0));
	    	       friend.setFrienduserid(cursor.getInt(1));
	    	       friend.setFriendname(cursor.getString(2));
	    	       arrayList.add(friend);
	           } while (cursor.moveToNext());     
	       } //if
	       cursor.close();
	       cursor = null;
		   return arrayList;
	 }  //getFriends
	 /*
	  * @params userID userid of the logged in user
	  */
	 public GameOnUserSettings refreshUserSettings(int userID) {
	      GameOnUserSettings settings = new GameOnUserSettings(userID);	
	    
	      Cursor cursor = database.query(GameOnDatabase.DB_USER_TABLE, 
	    		  new String[] { GameOnDatabase.DB_USER_TABLE_USERNAME,
	    		                 GameOnDatabase.DB_USER_TABLE_FIRSTNAME,
	    		                 GameOnDatabase.DB_USER_TABLE_LASTNAME,
	    		                 GameOnDatabase.DB_USER_TABLE_EMAIL,
	    		                 GameOnDatabase.DB_USER_TABLE_PHONENUMBER,
	    		                 
	    		                 GameOnDatabase.DB_USER_TABLE_PRIMARY_NETWORKNAME,
	    		                 GameOnDatabase.DB_USER_TABLE_PRIMARY_NETWORKID,
	    		                 
	    		                 GameOnDatabase.DB_USER_TABLE_TWITTER_TOKEN, 
	    		                 GameOnDatabase.DB_USER_TABLE_TWITTER_TOKEN_SECRET,
	    		                 GameOnDatabase.DB_USER_TABLE_TWITTER_SCREENNAME,
	    		                 GameOnDatabase.DB_USER_TABLE_TWITTER_USERID,
	    		                 
	    		                 GameOnDatabase.DB_USER_TABLE_FS_TOKEN, 
	    		                 GameOnDatabase.DB_USER_TABLE_FS_TOKEN_SECRET
	    		                 }, 
	                "userID ='" + userID + "'", null, null, null, null);
	      /* will return at most 1 record so no loop is necessary. 0 rowcount means the credentials were not correct */
	       if (null != cursor && cursor.getCount() >0 && cursor.moveToFirst() ) {
	    	   settings.setUserName(cursor.getString(0));
	    	   settings.setFirstName(cursor.getString(1));
	    	   settings.setLastName(cursor.getString(2));
	    	   settings.setEmail(cursor.getString(3));
	    	   settings.setPhoneNumber(cursor.getString(4));
	    	   settings.setPrimaryNetworkName(cursor.getString(5));
	    	   settings.setPrimaryNetworkID(cursor.getInt(6));
               settings.setTwitterOAuthToken(cursor.getString(7));   
               settings.setTwitterOAuthTokenSecret(cursor.getString(8));
               settings.setTwitterSN(cursor.getString(9));		      
               settings.setTwitterID(cursor.getString(10));		      
	       } //if
	       cursor.close();
           Log.d(TAG,settings.toString());
	       return settings;
	 } //refreshUserSettings
	 
	 public int getLoginID() {
		 Log.d(TAG,(gameOnUserSettings != null ? gameOnUserSettings.toString() : " GameOnUserSettings null"));
		 return (null != gameOnUserSettings ? gameOnUserSettings.getUserID() : 0);
	 }

	 public void setUserSettings(GameOnUserSettings in) {
		 gameOnUserSettings = in;
	 }
	 
	 public GameOnUserSettings getUserSettings() {

		   return gameOnUserSettings; 
	 }

	public String getUserName() {
		return gameOnUserSettings.getUserName();
	}
}  //class
