package com.jittr.android.bs.dto;

import java.util.Date;



/**
 * @author rg230v
 * Encapsulates User Participating Game Details
 * 
 */

public class UserGame {

	int gameid;
	int createdbyuserid;
	String createdbyusername;
	int publicgameid;
	String eventname;
	String eventdatetimeStr;
	Date eventdatetime_Dt;
	String wagertype;
	String wagerunits;
	int typeid;
	int sportid;
	String typename;
	String sportname;
    int numberofsubscribers;
    
	public int getGameid() {
		return gameid;
	}
	public void setGameid(int gameid) {
		this.gameid = gameid;
	}
	public int getCreatedbyuserid() {
		return createdbyuserid;
	}
	public void setCreatedbyuserid(int createdbyuserid) {
		this.createdbyuserid = createdbyuserid;
	}
	public String getCreatedbyusername() {
		return createdbyusername;
	}
	public void setCreatedbyusername(String createdbyusername) {
		this.createdbyusername = createdbyusername;
	}
	public int getPublicgameid() {
		return publicgameid;
	}
	public void setPublicgameid(int publicgameid) {
		this.publicgameid = publicgameid;
	}
	public String getEventname() {
		return eventname;
	}
	public void setEventname(String eventname) {
		this.eventname = eventname;
	}
	public String getEventdatetimeStr() {
		return eventdatetimeStr;
	}
	public void setEventdatetimeStr(String eventdatetimeStr) {
		this.eventdatetimeStr = eventdatetimeStr;
	}
	public Date getEventdatetime_Dt() {
		return eventdatetime_Dt;
	}
	public void setEventdatetime_Dt(Date eventdatetimeDt) {
		eventdatetime_Dt = eventdatetimeDt;
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
	public int getTypeid() {
		return typeid;
	}
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	public int getSportid() {
		return sportid;
	}
	public void setSportid(int sportid) {
		this.sportid = sportid;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getSportname() {
		return sportname;
	}
	public void setSportname(String sportname) {
		this.sportname = sportname;
	}
	public int getNumberofsubscribers() {
		return numberofsubscribers;
	}
	public void setNumberofsubscribers(int numberofsubscribers) {
		this.numberofsubscribers = numberofsubscribers;
	}
	@Override
	public String toString() {
		return "UserGame [createdbyuserid=" + createdbyuserid
				+ ", createdbyusername=" + createdbyusername
				+ ", eventdatetimeStr=" + eventdatetimeStr
				+ ", eventdatetime_Dt=" + eventdatetime_Dt + ", eventname="
				+ eventname + ", gameid=" + gameid + ", numberofsubscribers="
				+ numberofsubscribers + ", publicgameid=" + publicgameid
				+ ", sportid=" + sportid + ", sportname=" + sportname
				+ ", typeid=" + typeid + ", typename=" + typename
				+ ", wagertype=" + wagertype + ", wagerunits=" + wagerunits
				+ "]";
	} 
   
	
}
