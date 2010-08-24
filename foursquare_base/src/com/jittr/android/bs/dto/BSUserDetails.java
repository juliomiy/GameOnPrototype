package com.jittr.android.bs.dto;

public class BSUserDetails {

	String status_code;
	String status_message;
	String userid;
	String username;
	String firstname;
	String lastname;
	String email;
	String facebookuserid;
	String facebookprofileimageurl;
	String twitteruserid;
	String twitterprofileimageurl;
	String foursquareuserid;
	String foursquareprofileimageurl;
	String facebookdefault;
	String twitterdefault;
	String foursquaredefault;
	
	public BSUserDetails() {
		
	}

	public String getStatus_code() {
		return status_code;
	}

	public void setStatus_code(String statusCode) {
		status_code = statusCode;
	}

	public String getStatus_message() {
		return status_message;
	}

	public void setStatus_message(String statusMessage) {
		status_message = statusMessage;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFacebookuserid() {
		return facebookuserid;
	}

	public void setFacebookuserid(String facebookuserid) {
		this.facebookuserid = facebookuserid;
	}

	public String getFacebookprofileimageurl() {
		return facebookprofileimageurl;
	}

	public void setFacebookprofileimageurl(String facebookprofileimageurl) {
		this.facebookprofileimageurl = facebookprofileimageurl;
	}

	public String getTwitteruserid() {
		return twitteruserid;
	}

	public void setTwitteruserid(String twitteruserid) {
		this.twitteruserid = twitteruserid;
	}

	public String getTwitterprofileimageurl() {
		return twitterprofileimageurl;
	}

	public void setTwitterprofileimageurl(String twitterprofileimageurl) {
		this.twitterprofileimageurl = twitterprofileimageurl;
	}

	public String getFoursquareuserid() {
		return foursquareuserid;
	}

	public void setFoursquareuserid(String foursquareuserid) {
		this.foursquareuserid = foursquareuserid;
	}

	public String getFoursquareprofileimageurl() {
		return foursquareprofileimageurl;
	}

	public void setFoursquareprofileimageurl(String foursquareprofileimageurl) {
		this.foursquareprofileimageurl = foursquareprofileimageurl;
	}

	public String getFacebookdefault() {
		return facebookdefault;
	}

	public void setFacebookdefault(String facebookdefault) {
		this.facebookdefault = facebookdefault;
	}

	public String getTwitterdefault() {
		return twitterdefault;
	}

	public void setTwitterdefault(String twitterdefault) {
		this.twitterdefault = twitterdefault;
	}

	public String getFoursquaredefault() {
		return foursquaredefault;
	}

	public void setFoursquaredefault(String foursquaredefault) {
		this.foursquaredefault = foursquaredefault;
	}

	@Override
	public String toString() {
		return "BSUser [email=" + email + ", facebookdefault="
				+ facebookdefault + ", facebookprofileimageurl="
				+ facebookprofileimageurl + ", facebookuserid="
				+ facebookuserid + ", firstname=" + firstname
				+ ", foursquaredefault=" + foursquaredefault
				+ ", foursquareprofileimageurl=" + foursquareprofileimageurl
				+ ", foursquareuserid=" + foursquareuserid + ", lastname="
				+ lastname + ", status_code=" + status_code
				+ ", status_message=" + status_message + ", twitterdefault="
				+ twitterdefault + ", twitterprofileimageurl="
				+ twitterprofileimageurl + ", twitteruserid=" + twitteruserid
				+ ", userid=" + userid + ", username=" + username + "]";
	}
}






