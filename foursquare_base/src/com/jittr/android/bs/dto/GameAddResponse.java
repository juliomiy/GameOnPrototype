package com.jittr.android.bs.dto;

public class GameAddResponse {
	String status_code;
	String status_message;
	String gameid;
	public String getStatus_code() {
		return status_code;
	}
	public void setStatus_code(String statusCode) {
		status_code = statusCode;
	}
	public String getStatus_message() {
		return status_message;
	}
	public void setStatus_message(String statusMessage) {
		status_message = statusMessage;
	}
	public String getGameid() {
		return gameid;
	}
	public void setGameid(String gameid) {
		this.gameid = gameid;
	}
	@Override
	public String toString() {
		return "GameAddResponse [gameid=" + gameid + ", status_code="
				+ status_code + ", status_message=" + status_message + "]";
	}
	
	
}
