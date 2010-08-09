package com.jittr.android.fs.impl;


import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import android.util.Log;

import com.jittr.android.fs.core.FSClientInterface;
import com.jittr.android.fs.dto.Category;
import com.jittr.android.fs.dto.CheckIn;
import com.jittr.android.fs.dto.CheckinResonse;
import com.jittr.android.fs.dto.Tip;
import com.jittr.android.fs.dto.User;
import com.jittr.android.fs.dto.Venue;
import com.jittr.android.fs.handlers.UserHandler;
import com.jittr.android.fs.utils.Constants;
import com.jittr.android.fs.utils.FSConnectionHandler;
import com.jittr.android.fs.utils.NVPair;
import com.jittr.android.fs.utils.URLBuilder;

public class FSClientAPIImpl implements FSClientInterface {

	FSConnectionHandler fsc = null;
	URLBuilder ub = null;
	
	//OAuth based Todo
	public FSClientAPIImpl(String type, String user, String pwd) {
		
		fsc = new FSConnectionHandler(user,pwd);
	
	}

	//Gets the User details 
	public User getUserDetails(String uid, String twitterId, boolean badges,boolean mayor)  {
		
		try {
			//Build User URL
			NVPair nvps [] = {new NVPair("uid",uid),
							  new NVPair("twitter",twitterId),
							  new NVPair("badges", (badges) ? "1" : "0"),
							  new NVPair("mayor", (mayor) ? "1" : "0") 
							 };
			
			String url = URLBuilder.createUrl(Constants.User_URL,nvps);
			Log.d("","Url :"+url);
		    
			String data = fsc.getContent(new URL(url));
			
			// Use XML/JSON Handler based on Factory But for now use only xml handler
			UserHandler uh = new UserHandler(data);
			User userObj = (User)uh.parse();
			Log.d("", "Got the User Object from XML  ");
			//Let's Verify 
			Log.d("", "User ID "+userObj.getId());
			Log.d("", "User FName "+userObj.getFirstName());
			Log.d("", "User LName "+userObj.getLastName());
			Log.d("", "User Phone "+userObj.getPhone());
			Log.d("", "User Email "+userObj.getEmail());
			
			return userObj;
		}
		catch (Exception e) {
			Log.w("", "Exceptio while getting user details  "+e.getMessage());
			return null;
		}
	}

	
	public List<Venue> getCheckInHistory() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<CheckIn> getRecentCheckins(String geolat, String geolong) {
		// TODO Auto-generated method stub
		return null;
	}

	

	public Tip addTip(String vid, String text, String type, String geolat,
			String geolong) {
		// TODO Auto-generated method stub
		return null;
	}

	public Venue addvenue(String name, String address, String crossstreet,
			String city, String state, String zip, String phone, String geolat,
			String geolong, String categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	public User approveFriendRequest(String uid) {
		// TODO Auto-generated method stub
		return null;
	}

	public User denyFriendRequest(String uid) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> findFriendsByName(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> findFriendsByPhone(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> findFriendsByTwitterId(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean flagVenueAsClosed(String vid) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean flagVenueAsDuplicate(String vid) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean flagVenueAsMislocated(String vid) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Category> getAllcategories() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getFriends(String uid) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getPendingFriendRequests() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Tip> getTips(String geolat, String geolong, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	public Venue getVenueDetails(String vid) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Venue> getVenues(String geolat, String geolong, int limit,
			String query) {
		// TODO Auto-generated method stub
		return null;
	}

	public Tip markTipAsToDo(String tid) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean proposeVenueEdit(String name, String address,
			String crossstreet, String city, String state, String zip,
			String phone, String geolat, String geolong, String categoryId) {
		// TODO Auto-generated method stub
		return false;
	}

	public User sendFriendRequest(String uid) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setNotifications(boolean onoffFlag) {
		// TODO Auto-generated method stub
		
	}

	public boolean testFS() {
		// TODO Auto-generated method stub
		return false;
	}

	public Tip unMarkTip(String tid) {
		// TODO Auto-generated method stub
		return null;
	}



	public CheckinResonse checkin(String vid, String venueName, String shout,
			boolean isPrivate, boolean sendToTwitter, boolean sendToFaceBook,
			String geoLat, String geoLang) {
		// TODO Auto-generated method stub
		return null;
	}
}
