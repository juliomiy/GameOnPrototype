package com.jittr.android.api.betsquared.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class GameOnDatabase extends SQLiteOpenHelper {
	public static final int VERSION = 5;
	public static final String DB_NAME  = "betsquared_db.sqlite";
	public static final String GAME_TABLE = "go_games";
	public static final String DB_USER_TABLE = "go_user";
	public static final String DB_USER_TABLE_USERID = "userID";
	public static final String DB_USER_TABLE_USERNAME = "username";
	public static final String DB_USER_TABLE_PASSWORD = "password";
	public static final String DB_USER_TABLE_FIRSTNAME = "firstName";
	public static final String DB_USER_TABLE_LASTNAME = "lastName";
	public static final String DB_USER_TABLE_EMAIL = "email";
	public static final String DB_USER_TABLE_FS_TOKEN="foursquareToken";
	public static final String DB_USER_TABLE_FS_TOKEN_SECRET="foursquareTokenSecret";
	public static final String DB_USER_TABLE_TWITTER_TOKEN="twitterToken";
	public static final String DB_USER_TABLE_TWITTER_TOKEN_SECRET="twitterTokenSecret";
	public static final String DB_USER_TABLE_FB_TOKEN="facebookToken";
	public static final String DB_USER_TABLE_FB_TOKEN_SECRET="facebookTokenSecret";	
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

	private boolean createTables(SQLiteDatabase db) {
		String sql = "create table " + DB_USER_TABLE  +
		" ("  + DB_USER_TABLE_USERID + " integer primary key autoincrement not null," +
	      DB_USER_TABLE_USERNAME + " text null ," +
	      DB_USER_TABLE_PASSWORD + " text null," +
	      DB_USER_TABLE_FIRSTNAME + " text null," +
	      DB_USER_TABLE_LASTNAME + " text null," +
	      DB_USER_TABLE_EMAIL + " text null," +
	      DB_USER_TABLE_FS_TOKEN + " text null," +
	      DB_USER_TABLE_FS_TOKEN_SECRET + " text null," +
	      DB_USER_TABLE_FB_TOKEN + " text null," +
	      DB_USER_TABLE_FB_TOKEN_SECRET + " text null," +
	      DB_USER_TABLE_TWITTER_TOKEN + " text null," +
	      DB_USER_TABLE_TWITTER_TOKEN_SECRET + " text null," +
	      "loggedInSince timestamp null, " + 
	      "bankBalance float not null default 0," +
	      "createdDate timestamp not null default CURRENT_TIMESTAMP," +
	      "modifiedDate timestamp null" +
			");";
	      db.execSQL(sql);   
	return true;
	}

	private boolean dropTables(SQLiteDatabase db) {
		db.execSQL("Drop table if exists " + DB_USER_TABLE + ";");
		return true;
	}
	/*temporary Convenience method for development to set a record in go_user and go_userSettings */
	private void setDefaultSettings(SQLiteDatabase db) {
		//insert to go_user
		ContentValues values = new ContentValues();
		values.put(DB_USER_TABLE_USERNAME, "jittrdev");
		values.put(DB_USER_TABLE_PASSWORD, "miyrab");
		values.put(DB_USER_TABLE_USERID, 1);
		values.put(DB_USER_TABLE_FIRSTNAME, "julio");
		values.put(DB_USER_TABLE_LASTNAME, "Miyares");
		values.put(DB_USER_TABLE_EMAIL, "julio@jittr.com");

		long id = db.insert(DB_USER_TABLE, null, values);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		dropAndCreate(db);
	}

}  //class
