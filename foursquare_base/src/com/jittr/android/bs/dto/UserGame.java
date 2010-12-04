package com.jittr.android.bs.dto;

import java.util.Date;
import java.util.HashMap;

import com.jittr.android.bs.adapters.BSListViewable;

/**
 * @author rg230v
 * Encapsulates User Participating Game Details
 * @version 1.0
 * @modified - by juliomiyares added initiatorflag - true if this game initiated by the current
 * user, false otherwise, Added numberofInvites
 * 
 */

public class UserGame implements BSListViewable {

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
	int numberofInvites;
	boolean initiatorFlag;
    int numberofsubscribers;
    
    public boolean getInitiatorFlag() {
    	return initiatorFlag;
    }
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
	public int getNumberofInvites() {
		return numberofInvites;
	}
	public void setNumberofInvites(int numberofInvites) {
		this.numberofInvites = numberofInvites;
	}
	public boolean isInitiatorFlag() {
		return initiatorFlag;
	}
	public void setInitiatorFlag(boolean initiatorFlag) {
		this.initiatorFlag = initiatorFlag;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserGame [createdbyuserid=");
		builder.append(createdbyuserid);
		builder.append(", createdbyusername=");
		builder.append(createdbyusername);
		builder.append(", eventdatetimeStr=");
		builder.append(eventdatetimeStr);
		builder.append(", eventdatetime_Dt=");
		builder.append(eventdatetime_Dt);
		builder.append(", eventname=");
		builder.append(eventname);
		builder.append(", gameid=");
		builder.append(gameid);
		builder.append(", initiatorFlag=");
		builder.append(initiatorFlag);
		builder.append(", numberofInvites=");
		builder.append(numberofInvites);
		builder.append(", numberofsubscribers=");
		builder.append(numberofsubscribers);
		builder.append(", publicgameid=");
		builder.append(publicgameid);
		builder.append(", sportid=");
		builder.append(sportid);
		builder.append(", sportname=");
		builder.append(sportname);
		builder.append(", typeid=");
		builder.append(typeid);
		builder.append(", typename=");
		builder.append(typename);
		builder.append(", wagertype=");
		builder.append(wagertype);
		builder.append(", wagerunits=");
		builder.append(wagerunits);
		builder.append("]");
		return builder.toString();
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
   
	
}
