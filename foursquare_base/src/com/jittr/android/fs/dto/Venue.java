package com.jittr.android.fs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rg230v
 *
 */

public class Venue {
	private String groupType;
	
	private String id;
	private String phone;
	
	private String address;
	private String crossstreet;
	private String city;
	private String cityid;
	private String state;
	private Category category;
	private String distance;
    private String geolat;
    private String geolong;
    
    private String name;
    private String twitter;
    
    
	private Stats stats;
    private String zip;
    
    private List<User> currentCheckedInUsers;
    
   
    public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	
    public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
    
	public String getTwitter() {
		return twitter;
	}
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}
	
	public String getCity() {
		return city;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCityid() {
		return cityid;
	}
	public void setCityid(String cityid) {
		this.cityid = cityid;
	}
	public String getCrossstreet() {
		return crossstreet;
	}
	public void setCrossstreet(String crossstreet) {
		this.crossstreet = crossstreet;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getGeolat() {
		return geolat;
	}
	public void setGeolat(String geolat) {
		this.geolat = geolat;
	}
	public String getGeolong() {
		return geolong;
	}
	public void setGeolong(String geolong) {
		this.geolong = geolong;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Stats getStats() {
		return stats;
	}
	public void setStats(Stats stats) {
		this.stats = stats;
	}
	
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	
	public List<User> getCurrentCheckedInUsers() {
		return currentCheckedInUsers;
	}
	public void setCurrentCheckedInUsers(List<User> currentCheckedInUsers) {
		this.currentCheckedInUsers = currentCheckedInUsers;
	}
	@Override
	public String toString() {
		return "Venue [address=" + address + ", category=" + category
				+ ", city=" + city + ", cityid=" + cityid + ", crossstreet="
				+ crossstreet + ", currentCheckedInUsers="
				+ currentCheckedInUsers + ", distance=" + distance
				+ ", geolat=" + geolat + ", geolong=" + geolong
				+ ", groupType=" + groupType + ", id=" + id + ", name=" + name
				+ ", phone=" + phone + ", state=" + state + ", stats=" + stats
				+ ", twitter=" + twitter + ", zip=" + zip + "]";
	}

}
