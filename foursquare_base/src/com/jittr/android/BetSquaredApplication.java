/**
 * 
 */
package com.jittr.android;

import java.util.ArrayList;

import com.jittr.android.GameOnProperties;
import com.jittr.android.api.betsquared.db.GameOnDatabase;
import com.jittr.android.bs.dto.Friend;
import com.jittr.android.bs.dto.GameOnUserSettings;
import com.jittr.android.bs.dto.SocialNetworkFriend;

import static com.jittr.android.util.Consts.*;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
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
	private String appVersionCodeMajorMinor;
	private int userID;
	private String userIDString;
	private String userName;
	private SQLiteDatabase database;
	private final static String TAG = "BetSquaredApplication";
	private final String LOGGEDINAS = "loggedinas";
	private final String LOGGEDINNETWORK = "loggedinnetwork";
	/**
	 * 
	 */
	public BetSquaredApplication() {
		// TODO Auto-generated constructor stub
 	}


	@Override
	public void onCreate() {
		super.onCreate();
		setAppVersionCodeMajorMinor();
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
 			//TODO - perhaps show a list of userIDs on the handset device
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
    public void setAppVersionCodeMajorMinor() {
        PackageInfo info = null;;
        PackageManager pm = getPackageManager();
        try {
			info = pm.getPackageInfo("com.jittr.android", 0);
		   	appVersionCodeMajorMinor = "Package Info " + info.packageName + " Version " + info.versionCode  + info.versionName;
		    } catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			   e.printStackTrace();
		    } finally {
    	      info = null;
    	      pm = null;
		    }
    }  //setAppVersionCodeMajorMinor
    
/* Log into the application - 
 * TODO - sync between application handset device credentials and host based credentials 
 * TODO - If you logged in via one of the supported social networks, the password will be blank and 
 * the oauth credentials needs to be validated instead
 */
	public boolean login(String userName, String password) {
        boolean loginSuccessful = false;
        String sql = "select userName,userID,primaryNetworkID from " + GameOnDatabase.DB_USER_TABLE +
        		     " where userName='" + userName + "' and password = '" + password + "'";
        Log.d(TAG, sql);

        /* will return at most 1 record so no loop is necessary. 0 rowcount means the credentials were not correct */
        Cursor cursor = database.rawQuery(sql,null); 
        if (null != cursor && cursor.getCount() >0 && cursor.moveToFirst() ) {
        	userID = cursor.getInt(1);
        	int primaryNetworkID = cursor.getInt(2);
        	cursor.close();
        	sql = "update " + GameOnDatabase.DB_USER_TABLE + " set loggedInSince = CURRENT_TIMESTAMP where userID = " + userID; 
            Log.d(TAG,sql);
        	database.execSQL(sql);
        	gameOnProperties.storeSharedPreference(LOGGEDINAS, userID);
        	gameOnProperties.storeSharedPreference(LOGGEDINNETWORK, primaryNetworkID);
        	Log.d(TAG,"UserID of Login = " + String.valueOf(userID));
            gameOnUserSettings = refreshUserSettings(userID);
        	loginSuccessful = true;
        } else  if (null != cursor) cursor.close();
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
    	gameOnProperties.storeSharedPreference(LOGGEDINNETWORK, 0); //jhm 10/9/2010
    
    	logoutSuccessful = true;
    	gameOnUserSettings = null;  //blow away existing userSettings
    	Log.d(TAG,"Result of logout = " + String.valueOf(logoutSuccessful));
		return logoutSuccessful;
	} //logout
	/* check if the user is logged in - returns go_user ID if loggedIn or false
	 * JHM - October 9,2010 - handle a user that is logged in with a socialNetwork (OATH)
	 */
	 public int isLoggedIn() {

		int loggedInAsInt = 0;
		int preferredUserID = gameOnProperties.retrieveSharedPreference(LOGGEDINAS, 0);
        int preferredNetworkID = gameOnProperties.retrieveSharedPreference(LOGGEDINNETWORK, 0);  //JHM 10/9/2010
	
        if (preferredUserID > 0) {
           String sql = "select loggedInSince from " + GameOnDatabase.DB_USER_TABLE + " where userID = " + preferredUserID;   
           Log.d(TAG,sql);
           Cursor cursor = database.rawQuery(sql,null); 
           if (null != cursor && cursor.getCount() >0 && cursor.moveToFirst() ) {
              long loggedInSince = cursor.getLong(0);
              cursor.close();
              if (loggedInSince > 0) {
                  gameOnUserSettings = refreshUserSettings(preferredUserID);
            	  switch (preferredNetworkID) {
            	     case BETSQUARED_NETWORK:
                      	 loggedInAsInt = preferredUserID;
                      	 break;
            	     case FACEBOOK_NETWORK:
            	    	 loggedInAsInt = (gameOnUserSettings.isFacebookAuthorized() ? preferredUserID : 0);
            	    	 break;
            	     case TWITTER_NETWORK:
            	    	 loggedInAsInt = (gameOnUserSettings.isTwitterAuthorized() ? preferredUserID : 0);
            	    	 break;
            	     case FOURSQUARE_NETWORK:
            	    	 loggedInAsInt = (gameOnUserSettings.isFoursquareAuthorized() ? preferredUserID : 0);
            	    	 break;
            	  }  //switch
              } else if (null != cursor) cursor.close();
              Log.d(TAG,"Value of LoggedInSince = " + loggedInSince);
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
	  * This is a convenience to speed up the application but requires synchronization 
	  * with the HOST data to make sure the handset table is not out of date.
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
	    		                 GameOnDatabase.DB_USER_TABLE_FS_TOKEN_SECRET,
	    		                 GameOnDatabase.DB_USER_TABLE_FS_NAME,
	    		                 GameOnDatabase.DB_USER_TABLE_FS_USERID
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
               settings.setFoursquareOAuthToken(cursor.getString(11));
               settings.setFoursquareOAuthTokenSecret(cursor.getString(12));
               settings.setFoursquareName(cursor.getString(13));
               settings.setFoursquareUserID(cursor.getString(14));
	       } //if
	       cursor.close();
           Log.d(TAG,settings.toString());
	       return settings;
	 } //refreshUserSettings
	
	 /* update the betsquared friends on the handset
	  * convenience to not have to go across the network constantly.
	  * Add synchronization problem with the host data that needs to be addressed
	  */
	 public boolean updateBetsquaredFriends(ArrayList<Friend> betsquaredFriends) {
         String sqlTable= "insert or replace into " + GameOnDatabase.DB_FRIENDS_TABLE +
         "(" +
            GameOnDatabase.DB_USER_TABLE_USERID + "," +
        	GameOnDatabase.DB_FRIENDS_TABLE_USERID + "," +
         	GameOnDatabase.DB_FRIENDS_TABLE_USERNAME + "," +
         	GameOnDatabase.DB_FRIENDS_TABLE_NAME + 
         ") Values (";
         for (Friend friend : betsquaredFriends) {
        	 StringBuffer sql = new StringBuffer(sqlTable);
        	 sql.append( this.getLoginID() + ",");
        	 sql.append("'" + friend.getFrienduserid() + "','");
        	 sql.append(friend.getFriendusername() + "','");
        	 sql.append(friend.getFriendname() + "')");
        	 Log.d(TAG,sql.toString());
    		 database.execSQL(sql.toString());
        } //for
		 
		 return true;
	 }  //updteBetsquaredFriends
	 
	 /* update handset sqlite  database with "friends" from the supported social Networks
	  * TODO- add error handling 
	  */
	 public boolean updateSocialNetworkFriends(int networkID , ArrayList<SocialNetworkFriend> socialNetworkFriend) {
         String sqlTable= "insert or replace into " + GameOnDatabase.DB_SOCIALNETWORK_FRIENDS_TABLE +
         "(" +
        	GameOnDatabase.DB_SOCIALNETWORK_FRIENDS_USERID + "," +
         	GameOnDatabase.DB_SOCIALNETWORK_FRIENDS_NETWORKID + "," +
         	GameOnDatabase.DB_SOCIALNETWORK_FRIENDS_USERNAME + "," +
         	GameOnDatabase.DB_SOCIALNETWORK_FRIENDS_NAME + "," +
            GameOnDatabase.DB_SOCIALNETWORK_FRIENDS_AVATARURL +
         ") Values (";
         for (SocialNetworkFriend friend : socialNetworkFriend) {
        	 StringBuffer sql = new StringBuffer(sqlTable); 
        	 sql.append("'" + friend.getUserID() + "',");
        	 sql.append(networkID + ",'");
        	 sql.append(friend.getUserName() + "','");
        	 sql.append(friend.getName() + "','");
        	 sql.append(friend.getProfileImageURL() + "')"); 
        	 Log.d(TAG,sql.toString());
    		 database.execSQL(sql.toString());
        } //
 		 return true;
	 }  //updateSocialNetworkFriend
	 
	 /* get the list of "friends" from the various social networks supported by BetSquare
	  * These friend records stored on the handset to mitigate against excessive and expensive webservice calls
	  * against the native social network api. Still need to sync in a system configurable manor
	  */
	 public ArrayList<SocialNetworkFriend> getSocialNetworkFriends(int networkID) {
	
	  ArrayList<SocialNetworkFriend> arrayList = null;	 
      Cursor cursor = database.query(GameOnDatabase.DB_SOCIALNETWORK_FRIENDS_TABLE, 
    		  new String[] { GameOnDatabase.DB_SOCIALNETWORK_FRIENDS_USERNAME,
    		                 GameOnDatabase.DB_SOCIALNETWORK_FRIENDS_USERID,
    		                 GameOnDatabase.DB_SOCIALNETWORK_FRIENDS_NAME,
    		                 GameOnDatabase.DB_SOCIALNETWORK_FRIENDS_AVATARURL  }, 
    			                "networkID =" + networkID , null, null, null, null);
      if (null != cursor && cursor.getCount() >0 && cursor.moveToFirst() ) { 
    	  arrayList = new ArrayList<SocialNetworkFriend>();
    	  do {
    		  SocialNetworkFriend friend = new SocialNetworkFriend();
    		  friend.setUserName(cursor.getString(0));
    		  friend.setUserID(cursor.getString(1));
    		  friend.setName(cursor.getString(2));
    //		  friend.setProfileImageURL(cursor.getString(3));
    	      arrayList.add(friend);	  
          } while (cursor.moveToNext()); //do
      } //if
      if (null != cursor) cursor.close();
      return arrayList; 
	 }  //getSocialNetworkFriends
	 
	 /* return existing Betsquared Properties else instantiate new object and return. Contains preferences 
	  * 
	  */
	 public GameOnProperties getGameOnProperties() {
		 return (   (null!=gameOnProperties) ? gameOnProperties: new GameOnProperties((BetSquaredApplication)this.getApplicationContext()));
	 }
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

	public String getUserIDString() {
		if (null==userIDString) userIDString = String.valueOf(getLoginID());
		return userIDString;
	}
	public String getUserName() {
		return gameOnUserSettings.getUserName();
	}

	public String getAppVersionCodeMajorMinor() {
		return appVersionCodeMajorMinor;
	}
}  //class
