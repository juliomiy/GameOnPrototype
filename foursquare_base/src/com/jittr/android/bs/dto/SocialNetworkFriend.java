package com.jittr.android.bs.dto;

import java.net.URL;

import com.jittr.android.bs.adapters.BSListViewable;

/* @author juliomiyares
 * @version 1.0
 * @purpose data model of "friends" from the supported socialNetworks
 *   facebook, foursquare & twitter. Not all of the properties are supported by all of the 
 *   social networks.
 *   This class backs the arrayAdapters for the ListActivity that display Friends
 *   for the social networks
 */
public class SocialNetworkFriend implements BSListViewable {
	private String userID;
	private String userName;
	private String name;
	private String firstname;
	private String lastname;
	private String phone;
	private String email;
	private String gender;
	private String homecity;
	private String friendstatus;
	URL profileImageURL;
	
	public SocialNetworkFriend() {
		
	}
	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
		this.name = firstname + " " + lastname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
		this.name = this.firstname + " " + lastname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getHomecity() {
		return homecity;
	}
	public void setHomecity(String homecity) {
		this.homecity = homecity;
	}
	public String getFriendstatus() {
		return friendstatus;
	}
	public void setFriendstatus(String friendstatus) {
		this.friendstatus = friendstatus;
	}
	/**
	 * @param userID the userID to set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the avatorURL
	 */
	public URL getProfileImageURL() {
		return profileImageURL;
	}
	/**
	 * @param avatorURL the avatorURL to set
	 */
	public void setProfileImageURL(URL url) {
		profileImageURL = url;
	}
	@Override
	public String getListViewText() {
		return (name != null) ? name : userName; //+ " " + profileImageURL;
	}  //getListView
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SocialNetworkFriend [email=");
		builder.append(email);
		builder.append(", firstname=");
		builder.append(firstname);
		builder.append(", friendstatus=");
		builder.append(friendstatus);
		builder.append(", gender=");
		builder.append(gender);
		builder.append(", homecity=");
		builder.append(homecity);
		builder.append(", lastname=");
		builder.append(lastname);
		builder.append(", name=");
		builder.append(name);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", profileImageURL=");
		builder.append(profileImageURL);
		builder.append(", userID=");
		builder.append(userID);
		builder.append(", userName=");
		builder.append(userName);
		builder.append("]");
		return builder.toString();
	}
	
}