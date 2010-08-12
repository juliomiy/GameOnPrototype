package com.jittr.android.fs.core;

import java.util.List;

import com.jittr.android.fs.dto.Category;
import com.jittr.android.fs.dto.CheckIn;
import com.jittr.android.fs.dto.CheckinResonse;
import com.jittr.android.fs.dto.Tip;
import com.jittr.android.fs.dto.User;
import com.jittr.android.fs.dto.Venue;

/**
 * @author rg230v
 *
 */

//Contains All the methods exposed by FourSquare

public interface FSClientInterface  {
   
	
	// User methods
	User getUserDetails(String uid, String twitter, boolean badges, boolean mayor);
	List<User> getFriends(String uid);
	
	// Venue methods
	List<Venue> getNearByVenues(String geolat, String geolong,int limit, String query);
	Venue getVenueDetails(String vid);
	
	
	// Check-in methods
	List<CheckIn> getRecentCheckins(String geolat, String geolong);
	CheckinResonse checkin(String vid, String venueName, String shout, boolean isPrivate, boolean sendToTwitter, boolean sendToFaceBook,String geoLat, String geoLang);
	List<Venue> getCheckInHistory();

	
	
	
	
	List <Category> getAllcategories();
	Venue addvenue(String name, String address, String crossstreet, String city, String state,String zip, String phone, String geolat, String geolong, String categoryId);
	boolean proposeVenueEdit(String name, String address, String crossstreet, String city, String state,String zip, String phone, String geolat, String geolong, String categoryId);
	boolean flagVenueAsClosed(String vid);
	boolean flagVenueAsMislocated(String vid);
	boolean flagVenueAsDuplicate(String vid);
	
	// Tip Methods
	List<Tip> getTips(String geolat, String geolong,int limit);
	Tip addTip(String vid, String text, String type, String geolat, String geolong);
	Tip markTipAsToDo(String tid);
	Tip unMarkTip(String tid);
	
	// Friend Methods
	List<User> getPendingFriendRequests();
	User approveFriendRequest(String uid);
	User denyFriendRequest(String uid);
	User sendFriendRequest(String uid);
	List<User> findFriendsByName(String query);
	List<User> findFriendsByPhone(String query);
	List<User> findFriendsByTwitterId(String query);
	
	//Setting methods
	void setNotifications(boolean onoffFlag);
	
	//Test Methods
	boolean testFS();
}
