package com.jittr.android.fs.dto;

import java.util.List;

/**
 * @author rg230v
 *
 */
public class User {
	
	private String id;
	private String created;
	private String firstName;
	private String lastName;
	private String photo;
	private String gender;
	private String email;
	private String phone;
	private String facebook;
	private String twitter;
	private String friendStatus;
	private String homecity;
	
	private CheckIn checkIn;
	private List<Badge> badges;
    private Settings settings;
    private List<Venue> mayorships;
    
    public User() {
    	
    }
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFacebook() {
		return facebook;
	}
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	public String getTwitter() {
		return twitter;
	}
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}
	public CheckIn getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(CheckIn checkIn) {
		this.checkIn = checkIn;
	}
	public List<Badge> getBadges() {
		return badges;
	}
	public void setBadges(List<Badge> badges) {
		this.badges = badges;
	}
	public String getFriendStatus() {
		return friendStatus;
	}
	public void setFriendStatus(String friendStatus) {
		this.friendStatus = friendStatus;
	}
	
	public Settings getSettings() {
		return settings;
	}
	public void setSettings(Settings settings) {
		this.settings = settings;
	}
	public List<Venue> getMayorships() {
		return mayorships;
	}
	public void setMayorships(List<Venue> mayorships) {
		this.mayorships = mayorships;
	}
	@Override
	public String toString() {
		return "User [badges=" + badges + ", checkIn=" + checkIn + ", created="
				+ created + ", email=" + email + ", facebook=" + facebook
				+ ", firstName=" + firstName + ", friendStatus=" + friendStatus
				+ ", gender=" + gender + ", id=" + id + ", lastName="
				+ lastName + ", mayorships=" + mayorships + ", phone=" + phone
				+ ", photo=" + photo + ", settings=" + settings + ", twitter="
				+ twitter + "]";
	}
	public String getHomecity() {
		return homecity;
	}
	public void setHomecity(String homecity) {
		this.homecity = homecity;
	}

    	 
}
