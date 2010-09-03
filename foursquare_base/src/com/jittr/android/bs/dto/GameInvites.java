package com.jittr.android.bs.dto;

import java.util.ArrayList;

public class GameInvites {
    String status_message;
    String status_code;
	//String userid;
	int openinvites;
	ArrayList<GameInvite> gameinvites;
	
	public String getStatus_message() {
		return status_message;
	}
	public void setStatus_message(String statusMessage) {
		status_message = statusMessage;
	}
	public String getStatus_code() {
		return status_code;
	}
	public void setStatus_code(String statusCode) {
		status_code = statusCode;
	}
	/*
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	*/
	public int getOpeninvites() {
		return openinvites;
	}
	public void setOpeninvites(int openinvites) {
		this.openinvites = openinvites;
	}
	public ArrayList<GameInvite> getGameinvites() {
		return gameinvites;
	}
	public void setGameinvites(ArrayList<GameInvite> gameinvites) {
		this.gameinvites = gameinvites;
	}
	
	@Override
	public String toString() {
		return "GameInvites [gameinvites=" + gameinvites + ", openinvites="
				+ openinvites + ", status_code=" + status_code
				+ ", status_message=" + status_message+ "]";
	}
	
	
	
}
