package com.jittr.android.bs.dto;

import java.util.HashMap;

import com.jittr.android.bs.adapters.BSListViewable;


public class GameInvite implements BSListViewable {
	String gameid;
	String createdByUserID;
	String createdByUserName;
	String eventname;
	String eventdatetime;
	String closedatetime;
	String wagertype;
	String wagerunits;
	int sportID;
	String sportName;
	int leagueID;
	String leagueName;
	int numberOfSubscribers;
	private String sportIDStr;
	private String leagueIDStr;
	
	public String getGameid() {
		return gameid;
	}
	public void setGameid(String gameid) {
		this.gameid = gameid;
	}
	public String getCreatedByUserID() {
		return createdByUserID;
	}
	public void setCreatedByUserID(String createdByUserID) {
		this.createdByUserID = createdByUserID;
	}
	public String getCreatedByUserName() {
		return createdByUserName;
	}
	public void setCreatedByUserName(String createdByUserName) {
		this.createdByUserName = createdByUserName;
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
	public String getClosedatetime() {
		return closedatetime;
	}
	public void setClosedatetime(String closedatetime) {
		this.closedatetime = closedatetime;
	}
	public String getWagertype() {
		return wagertype;
	}
	public void setWagertype(String wagertype) {
		this.wagertype = wagertype;
	}
	public String getWagerunits() {
		return wagerunits;
	}
	public void setWagerunits(String wagerunits) {
		this.wagerunits = wagerunits;
	}
	public int getSportID() {
		return sportID;
	}
	public String getSportIDStr() {
		return sportIDStr;
	}
	public void setSportID(int sportID) {
		this.sportID = sportID;
		sportIDStr = String.valueOf(sportID);
	}
	public void setSportID(String sportID) {
		sportIDStr = sportID;
		try {
            this.sportID = Integer.parseInt(sportID);
		} catch (NumberFormatException e)  {
			this.sportID=0;
		}
	}
	
	public int getLeagueID() {
		return leagueID;
	}
	public String getLeagueIDStr() {
		return leagueIDStr;
	}
	
	public void setLeagueID(int leagueID) {
		this.leagueID = leagueID;
		leagueIDStr = String.valueOf(leagueID);
	}
	public void setLeagueID(String leagueID) {
		this.leagueIDStr = leagueID;
		try {
		  this.leagueID = Integer.parseInt(leagueID);
		} catch (NumberFormatException e) {
			this.leagueID=0;
		} 
	}
	
	public String getSportName() {
		return sportName;
	}
	public void setSportName(String sportName) {
		this.sportName = sportName;
	}
	public String getLeagueName() {
		return leagueName;
	}
	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}
	public int getNumberOfSubscribers() {
		return numberOfSubscribers;
	}
	public void setNumberOfSubscribers(int numberOfSubscribers) {
		this.numberOfSubscribers = numberOfSubscribers;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GameInvite [closedatetime=");
		builder.append(closedatetime);
		builder.append(", createdByUserID=");
		builder.append(createdByUserID);
		builder.append(", createdByUserName=");
		builder.append(createdByUserName);
		builder.append(", eventdatetime=");
		builder.append(eventdatetime);
		builder.append(", eventname=");
		builder.append(eventname);
		builder.append(", gameid=");
		builder.append(gameid);
		builder.append(", leagueID=");
		builder.append(leagueID);
		builder.append(", leagueIDStr=");
		builder.append(leagueIDStr);
		builder.append(", leagueName=");
		builder.append(leagueName);
		builder.append(", numberOfSubscribers=");
		builder.append(numberOfSubscribers);
		builder.append(", sportID=");
		builder.append(sportID);
		builder.append(", sportIDStr=");
		builder.append(sportIDStr);
		builder.append(", sportName=");
		builder.append(sportName);
		builder.append(", wagertype=");
		builder.append(wagertype);
		builder.append(", wagerunits=");
		builder.append(wagerunits);
		builder.append("]");
		return builder.toString();
	}
	@Override
	public String getListViewText() {
		return getEventname();
	}
	@Override
	public HashMap<String,String> getListViewArray() {
        HashMap<String,String>hm = new HashMap<String,String>();
        hm.put("eventname", getEventname());
        hm.put("eventdatetime", getEventdatetime());
        hm.put("createdbyusername", this.getCreatedByUserName());
        hm.put("leagueid", this.getLeagueIDStr());
		return hm;
	} //getListViewArray
} //class
