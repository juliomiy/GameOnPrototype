package com.jittr.android.bs.dto;

import com.jittr.android.bs.adapters.BSListViewable;

public class Friend implements BSListViewable {
	int status_code;
	String status_message;
	String username;
	int frienduserid;
	String friendusername;
	String friendname;
	String friendImageUrl;
	int numberofbets;
	//int twiiterid;
	//int numberfriends;
	
	
	
	@Override
	public String toString() {
		return "Friend [friendImageUrl=" + friendImageUrl + ", friendname="
				+ friendname + ", frienduserid=" + frienduserid
				+ ", friendusername=" + friendusername + ", numberofbets="
				+ numberofbets + ", status_code=" + status_code
				+ ", status_message=" + status_message + ", username="
				+ username + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}



	public int getStatus_code() {
		return status_code;
	}



	public void setStatus_code(int statusCode) {
		status_code = statusCode;
	}



	public String getStatus_message() {
		return status_message;
	}



	public void setStatus_message(String statusMessage) {
		status_message = statusMessage;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public int getFrienduserid() {
		return frienduserid;
	}



	public void setFrienduserid(int frienduserid) {
		this.frienduserid = frienduserid;
	}



	public String getFriendusername() {
		return friendusername;
	}



	public void setFriendusername(String friendusername) {
		this.friendusername = friendusername;
	}



	public String getFriendname() {
		return friendname;
	}



	public void setFriendname(String friendname) {
		this.friendname = friendname;
	}



	public String getFriendImageUrl() {
		return friendImageUrl;
	}



	public void setFriendImageUrl(String friendImageUrl) {
		this.friendImageUrl = friendImageUrl;
	}



	public int getNumberofbets() {
		return numberofbets;
	}



	public void setNumberofbets(int numberofbets) {
		this.numberofbets = numberofbets;
	}



	@Override
	public String getListViewText() {
		return (null != friendname && ! "".equals(friendname) ? friendname : friendusername);
	}
	
	
}
