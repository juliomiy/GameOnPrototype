package com.jittr.android.bs.dto;


public class GameInvite {
	String gameid;
	String createdByUserID;
	String createdByUserName;
	String eventname;
	String eventdatetime;
	String closedatetime;
	String wagertype;
	String wagerunits;
	
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
	@Override
	public String toString() {
		return "GameInvite [closedatetime=" + closedatetime
				+ ", createdByUserID=" + createdByUserID
				+ ", createdByUserName=" + createdByUserName
				+ ", eventdatetime=" + eventdatetime + ", eventname="
				+ eventname + ", gameid=" + gameid + ", wagertype=" + wagertype
				+ ", wagerunits=" + wagerunits + "]";
	}
}
