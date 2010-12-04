package com.jittr.android.bs.dto;

import java.util.HashMap;

import com.jittr.android.bs.adapters.BSListViewable;

import android.os.Parcel;
import android.os.Parcelable;

/* @author juliomiyares
 * @version 1.0
 * @date May 2010
 */
public class Game implements Parcelable, BSListViewable {

	private String id;
	private String publicGameID;
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
	private int typeID;
	private String typeName;
	private int sportID;
	private String sportName;
	private String StatusCode;
	private String StatusMessage;
	
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
		publicGameID = in.readString();
		team1 = in.readString();
        team2 = in.readString();
        eventname = in.readString();
        eventdatetime = in.readString();
        sportName = in.readString();
        sportID = in.readInt();
        typeName = in.readString();
        typeID = in.readInt();
        stadiumname = in.readString();
        city = in.readString();
        state = in.readString();
        latitude = in.readString();
        longitude = in.readString();
	}  //readFromParcel
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(id);
		dest.writeString(publicGameID);
		dest.writeString(getHomeTeam());
		dest.writeString(getVisitingTeam());
        dest.writeString(eventname);
        dest.writeString(eventdatetime);
        dest.writeString(sportName);
        dest.writeInt(sportID);
        dest.writeString(typeName);
        dest.writeInt(typeID);
        dest.writeString(stadiumname);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(latitude);
        dest.writeString(longitude);

	}  //writeToParcel
	
	public Game() {
	    super();	
	}
	
	//build Game Object from GameInvite Object
	public Game(GameInvite gameInvite) {
	   this.setId(gameInvite.getGameid());
	   this.setPublicGameID(gameInvite.getPublicGameIDStr());
	   this.setTypeID(gameInvite.getTypeID());
	   this.setEventname( gameInvite.getEventname());
	   this.setEventdatetime( gameInvite.getEventdatetime());
	   //gameInvite.g
	   
	}  //constructor - create Game Object from GameInvite Object
	
	public String getStatusCode() {
		return StatusCode;
	}

	public void setStatusCode(String statusCode) {
		StatusCode = statusCode;
	}

	public String getStatusMessage() {
		return StatusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		StatusMessage = statusMessage;
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
	
	/**
	 * @return the typeID
	 */
	public int getTypeID() {
		return typeID;
	}

	/**
	 * @param typeID the typeID to set
	 */
	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}

	/**
	 * @return the typeName
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * @param typeName the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * @return the sportID
	 */
	public int getSportID() {
		return sportID;
	}

	/**
	 * @return the publicGameID
	 */
	public String getPublicGameID() {
		return publicGameID;
	}

	/**
	 * @param publicGameID the publicGameID to set
	 */
	public void setPublicGameID(String publicGameID) {
		this.publicGameID = publicGameID;
	}

	/**
	 * @param sportID the sportID to set
	 */
	public void setSportID(int sportID) {
		this.sportID = sportID;
	}

	/**
	 * @return the sportName
	 */
	public String getSportName() {
		return sportName;
	}

	/**
	 * @param sportName the sportName to set
	 */
	public void setSportName(String sportName) {
		this.sportName = sportName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Game [StatusCode=");
		builder.append(StatusCode);
		builder.append(", StatusMessage=");
		builder.append(StatusMessage);
		builder.append(", TAG=");
		builder.append(TAG);
		builder.append(", address=");
		builder.append(address);
		builder.append(", city=");
		builder.append(city);
		builder.append(", eventdatetime=");
		builder.append(eventdatetime);
		builder.append(", eventname=");
		builder.append(eventname);
		builder.append(", fsvenueid=");
		builder.append(fsvenueid);
		builder.append(", id=");
		builder.append(id);
		builder.append(", latitude=");
		builder.append(latitude);
		builder.append(", leaguename=");
		builder.append(leaguename);
		builder.append(", longitude=");
		builder.append(longitude);
		builder.append(", publicGameID=");
		builder.append(publicGameID);
		builder.append(", seasonweek=");
		builder.append(seasonweek);
		builder.append(", sportID=");
		builder.append(sportID);
		builder.append(", sportName=");
		builder.append(sportName);
		builder.append(", sportname=");
		builder.append(sportname);
		builder.append(", stadiumname=");
		builder.append(stadiumname);
		builder.append(", state=");
		builder.append(state);
		builder.append(", team1=");
		builder.append(team1);
		builder.append(", team2=");
		builder.append(team2);
		builder.append(", typeID=");
		builder.append(typeID);
		builder.append(", typeName=");
		builder.append(typeName);
		builder.append("]");
		return builder.toString();
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public HashMap getListViewArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getListViewText() {
		// TODO Auto-generated method stub
		return null;
	}



}  //class
