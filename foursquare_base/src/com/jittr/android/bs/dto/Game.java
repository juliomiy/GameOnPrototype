package com.jittr.android.bs.dto;

import com.jittr.android.fs.dto.Venue;

import android.os.Parcel;
import android.os.Parcelable;

public class Game implements Parcelable {

	private String id;
	private String sportname;
	private String leaguename;
	private String seasonweek;
	private String eventname;
	private String eventdatetime;
	private String stadiumname;
	private String address;
	private String city;
	private String state;
	private String fsvenueid;
	private String latitude;
	private String longitude;
	private String team1;
	private String team2;
	private final String TAG = "Game";
	
    public static final Parcelable.Creator<Game> CREATOR
    = new Parcelable.Creator<Game>() {
   	
        public Game createFromParcel(Parcel in) {
           return new Game(in);
        }
        
        public Game[] newArray(int arg0) {
    		// TODO Auto-generated method stub
    		return null;
    	}
        
     };
     
    private Game(Parcel in) {
    	 readFromParcel(in);
     }

	public void readFromParcel(Parcel in) {
		id = in.readString();
		team1 = in.readString();
        team2 = in.readString();
        eventname = in.readString();
        eventdatetime = in.readString();
		// TODO Auto-generated method stub
	}

	public Game() {
	    super();	
	}
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
	
	//TODO - improve getHome and Visiting team getters
	public String getHomeTeam() {
	  return team1;	
	}
	public String getVisitingTeam() {
	   return team2;	
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
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(id);
		dest.writeString(getHomeTeam());
		dest.writeString(getVisitingTeam());
        dest.writeString(eventname);
        dest.writeString(eventdatetime);
	}
}  //class
