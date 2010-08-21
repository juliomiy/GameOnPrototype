package com.jittr.android.fs.dto;

public class CheckinResponse {

	String id;
	String created;
	String message;
	Venue venue;
	Mayor mayor;
	String specials;

	public CheckinResponse() {
		
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Venue getVenue() {
		return venue;
	}
	public void setVenue(Venue venue) {
		this.venue = venue;
	}
	public String getSpecials() {
		return specials;
	}
	public void setSpecials(String specials) {
		this.specials = specials;
	}
	
	@Override
	public String toString() {
		return "CheckinResponse [created=" + created + ", id=" + id
				+ ", mayor=" + mayor + ", message=" + message + ", specials="
				+ specials + ", venue=" + venue + "]";
	}
	public Mayor getMayor() {
		return mayor;
	}
	public void setMayor(Mayor mayor) {
		this.mayor = mayor;
	}
	
	
}
