package com.jittr.android.api.betsquared.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
 * @author juliomiyares
 * @version 1.0
 * @purpose - define sqlite local storage on the handset. Some of the tables are used to store 
 * locally , the same information that is stored on the host. This is to limit the bandwidth requirements
 * of the application
 * So far, the following tables are synchronized on the handset
 *              BetSquared Friends
 *              Social Network Friends
 *              
 */
public class GameOnDatabase extends SQLiteOpenHelper {
	public static final int VERSION = 16;
	public static final String DB_NAME  = "betsquared_db.sqlite";
	public static final String GAME_TABLE = "go_games";
	public static final String DB_USER_TABLE = "go_user";
	public static final String DB_USER_TABLE_USERID = "userID";
	public static final String DB_USER_TABLE_USERNAME = "username";
	public static final String DB_USER_TABLE_PASSWORD = "password";
	public static final String DB_USER_TABLE_FIRSTNAME = "firstName";
	public static final String DB_USER_TABLE_LASTNAME = "lastName";
	public static final String DB_USER_TABLE_EMAIL = "email";
	public static final String DB_USER_TABLE_FS_DEFAULT="foursquareDefault";
	public static final String DB_USER_TABLE_FS_TOKEN="foursquareToken";
	public static final String DB_USER_TABLE_FS_TOKEN_SECRET="foursquareTokenSecret";
	public static final String DB_USER_TABLE_FS_AVATAR = "foursquareAvatar";	
	public static final String DB_USER_TABLE_TWITTER_DEFAULT="twitterDefault";
	public static final String DB_USER_TABLE_TWITTER_TOKEN="twitterToken";
	public static final String DB_USER_TABLE_TWITTER_TOKEN_SECRET="twitterTokenSecret";
	public static final String DB_USER_TABLE_FB_DEFAULT="facebookDefault";
	public static final String DB_USER_TABLE_FB_TOKEN="facebookToken";
	public static final String DB_USER_TABLE_FB_TOKEN_SECRET="facebookTokenSecret";
	public static final String DB_USER_TABLE_TWITTER_USERID = "twitterUserID";	
	public static final String DB_USER_TABLE_TWITTER_SCREENNAME = "twitterScreenName";	
	public static final String DB_USER_TABLE_TWITTER_NAME = "twitterName";
	public static final String DB_USER_TABLE_TWITTER_AVATAR = "twitterAvatar";	
	public static final String DB_USER_TABLE_PRIMARY_NETWORKNAME = "primaryNetworkName";
	public static final String DB_USER_TABLE_PHONENUMBER = "phoneNumber";
	public static final String DB_USER_TABLE_PRIMARY_NETWORKID = "primaryNetworkID";	
    public static final String  DB_USER_TABLE_UPDATE_HOST="updateHostFlag";
	public static final String DB_FRIENDS_TABLE = "go_userFriends";
    public static final String DB_FRIENDS_TABLE_USERID = "friendUserID";
    public static final String DB_FRIENDS_TABLE_USERNAME = "friendUserName";
    public static final String DB_FRIENDS_TABLE_NAME = "friendName";
	public static final String DB_SOCIALNETWORK_FRIENDS_TABLE = "go_socialNetworkFriends";
	public static final String DB_SOCIALNETWORK_FRIENDS_NETWORKID="networkID";
	public static final String DB_SOCIALNETWORK_FRIENDS_USERID="userID";
	public static final String DB_SOCIALNETWORK_FRIENDS_USERNAME="userName";
	public static final String DB_SOCIALNETWORK_FRIENDS_ISFRIEND = "isFriend";
	public static final String DB_SOCIALNETWORK_FRIENDS_SYNCDATE = "syncDate";
	public static final String DB_SOCIALNETWORK_FRIENDS_NAME = "name";
	public static final String DB_SOCIALNETWORK_FRIENDS_AVATARURL = "avatarURL";
	public static final String DB_USER_TABLE_FS_NAME = "foursquareName";
	public static final String DB_USER_TABLE_FS_USERID = "foursquareUserID";
	
	public GameOnDatabase(Context context) {

		super(context, DB_NAME, null, VERSION);
	
	} //constructor

	@Override
	public void onCreate(SQLiteDatabase db) {
		dropAndCreate(db);
	}

	private void dropAndCreate(SQLiteDatabase db) {
		dropTables(db);
		createTables(db);
		setDefaultSettings(db);
	}

	// added foursquare and twitter avatar fields
	private boolean createTables(SQLiteDatabase db) {
		String sql = "create table " + DB_USER_TABLE  +
		" ("  + DB_USER_TABLE_USERID + " integer primary key autoincrement not null," +
	      DB_USER_TABLE_USERNAME + " text null ," +
	      DB_USER_TABLE_PASSWORD + " text null," +
	      DB_USER_TABLE_FIRSTNAME + " text null," +
	      DB_USER_TABLE_LASTNAME + " text null," +
	      DB_USER_TABLE_EMAIL + " text null," +
	      DB_USER_TABLE_PHONENUMBER + " text null," +
	      DB_USER_TABLE_PRIMARY_NETWORKNAME + " text null," +
	      DB_USER_TABLE_PRIMARY_NETWORKID + " integer not null default 0," +
	  	  DB_USER_TABLE_FS_DEFAULT + " integer not null default 0," +
	      DB_USER_TABLE_FS_TOKEN + " text null," +
	      DB_USER_TABLE_FS_TOKEN_SECRET + " text null," +
	      DB_USER_TABLE_FS_NAME + " text null," +
	      DB_USER_TABLE_FS_USERID + " text null," +
	      DB_USER_TABLE_FS_AVATAR + " text null, " +
	      DB_USER_TABLE_FB_DEFAULT + " integer not null default 0," + 
	      DB_USER_TABLE_FB_TOKEN + " text null," +
	      DB_USER_TABLE_FB_TOKEN_SECRET + " text null," +
	      DB_USER_TABLE_TWITTER_DEFAULT + " integer not null default 0," + 
	      DB_USER_TABLE_TWITTER_TOKEN + " text null," +
	      DB_USER_TABLE_TWITTER_TOKEN_SECRET + " text null," +
	      DB_USER_TABLE_TWITTER_USERID + " text null," +
	      DB_USER_TABLE_TWITTER_SCREENNAME + " text null," +
	      DB_USER_TABLE_TWITTER_NAME + " text null," +
	      DB_USER_TABLE_TWITTER_AVATAR + " text null, " +
	      DB_USER_TABLE_UPDATE_HOST + " integer not null default 0," +
	      "loggedInSince timestamp null, " + 
	      "bankBalance float not null default 0," +
	      "createdDate timestamp not null default CURRENT_TIMESTAMP," +
	      "modifiedDate timestamp null" +
			");";
	      db.execSQL(sql); 
	      
	      //friends table - go_userFriends - mimics the one on the host
	      sql = "create table " + DB_FRIENDS_TABLE + 
	          "(" +
	            DB_USER_TABLE_USERID + " integer  not null," +
                DB_FRIENDS_TABLE_USERID + " integer not null," +
                DB_FRIENDS_TABLE_USERNAME + " text null," +
                DB_FRIENDS_TABLE_NAME + " text null," +
                "primary key (" + DB_USER_TABLE_USERID + "," + DB_FRIENDS_TABLE_USERID + ")" +
	          ")";
	      db.execSQL(sql); 
	
	      //foursquare friends/ twitter followers/facebook friends
	      //minimize the network demands to find these - need to sync with the
	      //network on a time to time basis
	      sql = "create table " + DB_SOCIALNETWORK_FRIENDS_TABLE +
                "(" +
                    DB_SOCIALNETWORK_FRIENDS_NETWORKID + " integer not null," +
                    DB_SOCIALNETWORK_FRIENDS_USERID + " text not null," +
                    DB_SOCIALNETWORK_FRIENDS_USERNAME + " text not null," +
                    DB_SOCIALNETWORK_FRIENDS_NAME + " text null," +
                    DB_SOCIALNETWORK_FRIENDS_AVATARURL + " text null," +
                    DB_SOCIALNETWORK_FRIENDS_ISFRIEND + " boolean not null default false," +
                    DB_SOCIALNETWORK_FRIENDS_SYNCDATE + " date null default CURRENT_TIMESTAMP, " +
                    
                    "PRIMARY KEY (" + DB_SOCIALNETWORK_FRIENDS_NETWORKID + "," +
                                      DB_SOCIALNETWORK_FRIENDS_USERID + ")" +
                    ")";
	      db.execSQL(sql); 
	      
	return true;
	}  //createTable

	private boolean dropTables(SQLiteDatabase db) {
		db.execSQL("Drop table if exists " + DB_USER_TABLE + ";");
		db.execSQL("Drop Table if exists " + DB_FRIENDS_TABLE + ";");
		db.execSQL("Drop table if exists " + DB_SOCIALNETWORK_FRIENDS_TABLE + ";");
		return true;
	}
	/*temporary Convenience method for development to set a record in go_user and go_userSettings */
    //TODO - remove this function at prototype/production
	private void setDefaultSettings(SQLiteDatabase db) {
		//insert to go_user
		ContentValues values = new ContentValues();
		values.put(DB_USER_TABLE_USERNAME, "jittrdev");
		values.put(DB_USER_TABLE_PASSWORD, "miyrab");
		values.put(DB_USER_TABLE_USERID, 1);
		values.put(DB_USER_TABLE_FIRSTNAME, "julio");
		values.put(DB_USER_TABLE_LASTNAME, "Miyares");
		values.put(DB_USER_TABLE_EMAIL, "julio@jittr.com");
		values.put(DB_USER_TABLE_PHONENUMBER, "9173702880");
		values.put(DB_USER_TABLE_PRIMARY_NETWORKID, 4);
		values.put(DB_USER_TABLE_PRIMARY_NETWORKNAME, "BETSQUARED");

		long id = db.insert(DB_USER_TABLE, null, values);
	
		//add friends to the handset table
		values = new ContentValues();
		values.put(DB_USER_TABLE_USERID, 1);
		values.put(DB_FRIENDS_TABLE_USERID, 132);
		values.put(DB_FRIENDS_TABLE_USERNAME, "jittr132");
		values.put(DB_FRIENDS_TABLE_NAME, "jittr132");
		db.insert(DB_FRIENDS_TABLE, null, values);

		values = new ContentValues();
		values.put(DB_USER_TABLE_USERID, 1);
		values.put(DB_FRIENDS_TABLE_USERID, 141);
		values.put(DB_FRIENDS_TABLE_USERNAME, "laura");
		values.put(DB_FRIENDS_TABLE_NAME, "laura");
		db.insert(DB_FRIENDS_TABLE, null, values);

		values = new ContentValues();
		values.put(DB_USER_TABLE_USERID, 1);
		values.put(DB_FRIENDS_TABLE_USERID, 145);
		values.put(DB_FRIENDS_TABLE_USERNAME, "jittr");
		values.put(DB_FRIENDS_TABLE_NAME, "jittr");
		db.insert(DB_FRIENDS_TABLE, null, values);
	}   //TODO - Remove this method
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		dropAndCreate(db);
	}

}  //class
