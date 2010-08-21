package com.jittr.android.fs.dto;

/**
 * @author rg230v
 *
 */

public class CheckIn {

	  String id;
	  User user;
	  Venue venue;
	  String distance;
      String display; 
      String shout;
      String created;
      
    public CheckIn() {
    	
    }
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Venue getVenue() {
		return venue;
	}
	public void setVenue(Venue venue) {
		this.venue = venue;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public String getShout() {
		return shout;
	}
	public void setShout(String shout) {
		this.shout = shout;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	@Override
	public String toString() {
		return "CheckIn [created=" + created + ", display=" + display
				+ ", distance=" + distance + ", id=" + id + ", shout=" + shout
				+ ", user=" + user + ", venue=" + venue + "]";
	}

}
