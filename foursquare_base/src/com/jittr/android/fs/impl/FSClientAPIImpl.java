package com.jittr.android.fs.impl;


import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.jittr.android.fs.core.FSClientInterface;
import com.jittr.android.fs.dto.Category;
import com.jittr.android.fs.dto.CheckIn;
import com.jittr.android.fs.dto.CheckinResponse;
import com.jittr.android.fs.dto.Tip;
import com.jittr.android.fs.dto.User;
import com.jittr.android.fs.dto.Venue;
import com.jittr.android.fs.handlers.CategoriesHandler;
import com.jittr.android.fs.handlers.CheckInHandler;
import com.jittr.android.fs.handlers.UserHandler;
import com.jittr.android.fs.handlers.VenueHandler;
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

	public List<User> getFriends(String uid) {
		try {
			
			NVPair nvps [] = {new NVPair("uid",uid) };
	
			String url = URLBuilder.createUrl(Constants.USER_Friends_URL,nvps);
			Log.d("","Getting Friends Url :"+url);
  
			String data = fsc.getContent(new URL(url));
	
			// Use XML/JSON Handler based on Factory But for now use only xml handler
			UserHandler uh = new UserHandler(data);
			List users = uh.parseList();
			
			//Lets Verify the returned data..will remove this
			System.out.println("Users Size "+users.size());
			for(int i=0;i<users.size();i++) {
				User temp = (User)users.get(i);
				Log.d("", " i  "+i);
				Log.d("", "User ID "+temp.getId());
				Log.d("", "User FName "+temp.getFirstName());
				Log.d("", "User LName "+temp.getLastName());
				Log.d("", "User Phone "+temp.getPhone());
				Log.d("", "User Email "+temp.getEmail());
			}
			return  users;
		}
		catch(Exception e) {
			Log.w("", "Exception while getting user friends  "+e.getMessage());
			return null;
		}
		
		
	}
	
	
	public List<Venue> getNearByVenues(String geolat, String geolong, int limit,String query) {
		
		try {
			NVPair nvps [] = {new NVPair("geolat",geolat),
					  new NVPair("geolong",geolong),
					  new NVPair("limit", String.valueOf(limit)),
					  new NVPair("query", query) 
					 };
	
			String url = URLBuilder.createUrl(Constants.Venues_Search_URL,nvps);
			Log.d("","Url :"+url);
  
			String data = fsc.getContent(new URL(url));
			Log.d("","data :"+data);
			VenueHandler vh = new VenueHandler(data);
			
			List venues = vh.parseList();
			
			//Lets Verify the returned data..will remove this
			System.out.println("venues Size.... "+venues.size());
			for(int i=0;i<venues.size();i++) {
				Venue temp = (Venue)venues.get(i);
				Log.d("", " i  "+i);
				Log.d("", "Venue ID "+temp.getId());
				Log.d("", "Venue Name "+temp.getName());
				Log.d("", "Venue CStreet "+temp.getCrossstreet());
				Log.d("", "Venue Address "+temp.getAddress());
				Log.d("", "Venue City "+temp.getCity());
				Log.d("", "Venue Group Type  "+temp.getGroupType());
				Log.d("", "Venue Category "+temp.getCategory());
				Log.d("", "Venue Stats  "+temp.getStats());
			}
			return venues;
		}
		catch(Exception e) {
			Log.w("", "Exception while getNearByVenues "+e.getMessage());
			return null;
		}
		
	}
	
	public Venue getVenueDetails(String vid) {
		try {
			NVPair nvps [] = {new NVPair("vid",vid) };
	
			String url = URLBuilder.createUrl(Constants.Venue_Detail_URL,nvps);
			Log.d("","Url :"+url);
  
			String data = fsc.getContent(new URL(url));
			Log.d("","data :"+data);
			
			VenueHandler vh = new VenueHandler(data);
			Venue venu = (Venue)vh.parse();
			Log.d("", "Venue ID "+venu.getId());
			Log.d("", "Venue Name "+venu.getName());
			Log.d("", "Venue CStreet "+venu.getCrossstreet());
			Log.d("", "Venue Address "+venu.getAddress());
			Log.d("", "Venue City "+venu.getCity());
			return venu;
		}
		catch(Exception e) {
			Log.w("", "Exception while getNearByVenues "+e.getMessage());
			return null;
		}
		
	}
	
	public List<User> getCurrentCheckedInUsers(String vid) {
		try {
			NVPair nvps [] = {new NVPair("vid",vid) };
	
			String url = URLBuilder.createUrl(Constants.Venue_Detail_URL,nvps);
			Log.d("","Url :"+url);
  
			String data = fsc.getContent(new URL(url));
			Log.d("","data :"+data);
			
			VenueHandler vh = new VenueHandler(data);
			Venue venue = (Venue)vh.parse();
			return venue.getCurrentCheckedInUsers();
		}
		catch(Exception e) {
			Log.w("", "Exception while getCurrentCheckedInUsers "+e.getMessage());
			return null;
		}
	}
	

	public CheckinResponse checkin(String vid, String venue, String shout,
			boolean isPrivate, boolean sendToTwitter, boolean sendToFaceBook,
			String geoLat, String geoLang) {
		
		try {
			NVPair nvps [] = {new NVPair("vid",vid),
						  new NVPair("venue",venue),
						  new NVPair("shout",shout),
						  new NVPair("private",(isPrivate) ? "1" : "0"),
						  new NVPair("twitter",(sendToTwitter) ? "1" : "0"),
						  new NVPair("facebook",(sendToFaceBook) ? "1" : "0"),
						  new NVPair("geolat",geoLat),
						  new NVPair("geolong",geoLang),
						  
						  };
	        
			Log.d("","Url :"+Constants.Venue_Check_In_URL);
		  
			String response = fsc.submitToFs(new URL(Constants.Venue_Check_In_URL),nvps);
			Log.d("","response :"+response);
			//Parse response
			CheckInHandler ch = new CheckInHandler(response);
			CheckinResponse chResponse = (CheckinResponse)ch.parse();
			Log.d("","chResponse ID :"+chResponse.getId());
			Log.d("","chResponse Created :"+chResponse.getCreated());
			Log.d("","chResponse Message :"+chResponse.getMessage());
			Log.d("","chResponse Venue :"+chResponse.getVenue());
			Log.d("","chResponse Venue Address:"+chResponse.getVenue().getAddress());
			Log.d("","chResponse Mayor :"+chResponse.getMayor());
			Log.d("","chResponse Mayor message:"+chResponse.getMayor().getMessage());
			Log.d("","chResponse Mayor user:"+chResponse.getMayor().getUser());
			return chResponse;
		}
		catch (Exception e) {
			Log.w("", "Exception while checkin "+e.getMessage());
			return null;
		}
		
	}
	
	public List<Category> getAllcategories() {
		try {
			
			Log.d("","Url :"+Constants.Categories_URL);
  
			String data = fsc.getContent(new URL(Constants.Categories_URL));
			Log.d("","data :"+data);
			
			CategoriesHandler ch = new CategoriesHandler(data);
			List categories = ch.parseList();
			Log.d("","Categories :"+categories.size());
			return categories;
		}
		catch(Exception e) {
			Log.w("", "Exception while getNearByVenues "+e.getMessage());
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

	

	

	public List<User> getPendingFriendRequests() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Tip> getTips(String geolat, String geolong, int limit) {
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



	
}
