package com.jittr.android.bs.dto;

import java.util.HashMap;

import com.jittr.android.bs.adapters.BSListViewable;


public class GameInvite implements BSListViewable {
	private String gameid;
	private int publicGameID;  //added JHM 11/8/2010
	private int typeID; //added JHM 11/8/2010
	private String createdByUserID;
	private String createdByUserName;
	private String eventname;
	private String eventdatetime;
	private String closedatetime;
	private String wagertype;
	private String wagerunits;
	private int sportID;
	private String sportName;
	private int leagueID;
	private String leagueName;
	private int numberOfSubscribers;
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
		builder.append(", publicGameID=");
		builder.append(publicGameID);
		builder.append(", sportID=");
		builder.append(sportID);
		builder.append(", sportIDStr=");
		builder.append(sportIDStr);
		builder.append(", sportName=");
		builder.append(sportName);
		builder.append(", typeID=");
		builder.append(typeID);
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

	//converts to an int type
	public int getWagerunitsInt() {
        int decimalPos = wagerunits.indexOf(".");
        String tmp = wagerunits;
        
        if (decimalPos !=-1) {
           tmp = wagerunits.substring(0, decimalPos);
        }
		// TODO Auto-generated method stub
		return Integer.parseInt(tmp);
	}
	public int getPublicGameID() {
		return publicGameID;
	}
	public String getPublicGameIDStr() {
        return Integer.toString(publicGameID);
	}
	public int getTypeID() {
		return typeID;
	}
	public void setPublicGameID(String string) {
        if (null == string) return;
		try {
			publicGameID = Integer.parseInt(string);
		} catch (NumberFormatException e) {
			
		}
	}
	public void setTypeID(String string) {
        if (null == string) return;
		try {
			typeID = Integer.parseInt(string);
		} catch (NumberFormatException e) {
			
		}
	}
} //class
