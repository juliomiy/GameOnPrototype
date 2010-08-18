package com.jittr.android.fs.dto;

public class Mayor {
	
	String type;
	String checkins;
	User user;
	String message;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCheckins() {
		return checkins;
	}
	public void setCheckins(String checkins) {
		this.checkins = checkins;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "Mayor [checkins=" + checkins + ", message=" + message
				+ ", type=" + type + ", user=" + user + "]";
	}
	
	
}
