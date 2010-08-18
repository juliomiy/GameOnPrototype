package com.jittr.android.fs.dto;

public class Game {

	String id;
	String sportname;
	String leaguename;
	String seasonweek;
	String eventname;
	String eventdatetime;
	String stadiumname;
	String address;
	String city;
	String state;
	String fsvenueid;
	String latitude;
	String longitude;
	String team1;
	String team2;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getSportname() {
		return sportname;
	}
	public void setSportname(String sportname) {
		this.sportname = sportname;
	}
	public String getLeaguename() {
		return leaguename;
	}
	public void setLeaguename(String leaguename) {
		this.leaguename = leaguename;
	}
	public String getSeasonweek() {
		return seasonweek;
	}
	public void setSeasonweek(String seasonweek) {
		this.seasonweek = seasonweek;
	}
	public String getEventname() {
		return eventname;
	}
	public void setEventname(String eventname) {
		this.eventname = eventname;
	}
	public String getEventdatetime() {
		return eventdatetime;
	}
	public void setEventdatetime(String eventdatetime) {
		this.eventdatetime = eventdatetime;
	}
	public String getStadiumname() {
		return stadiumname;
	}
	public void setStadiumname(String stadiumname) {
		this.stadiumname = stadiumname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getFsvenueid() {
		return fsvenueid;
	}
	public void setFsvenueid(String fsvenueid) {
		this.fsvenueid = fsvenueid;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getTeam1() {
		return team1;
	}
	public void setTeam1(String team1) {
		this.team1 = team1;
	}
	public String getTeam2() {
		return team2;
	}
	public void setTeam2(String team2) {
		this.team2 = team2;
	}
	
	@Override
	public String toString() {
		return "Game [address=" + address + ", city=" + city
				+ ", eventdatetime=" + eventdatetime + ", eventname="
				+ eventname + ", fsvenueid=" + fsvenueid + ", id=" + id
				+ ", latitude=" + latitude + ", leaguename=" + leaguename
				+ ", longitude=" + longitude + ", seasonweek=" + seasonweek
				+ ", sportname=" + sportname + ", stadiumname=" + stadiumname
				+ ", state=" + state + ", team1=" + team1 + ", team2=" + team2
				+ "]";
	}
}
