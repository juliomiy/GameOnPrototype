package com.jittr.android.bs.dto;

public class UserAddResponse {
   
	String status_code;
	String status_message;
	String userid;
	String username;
	String networkname;
	
	public UserAddResponse() {
		
	}

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

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNetworkname() {
		return networkname;
	}

	public void setNetworkname(String networkname) {
		this.networkname = networkname;
	}

	@Override
	public String toString() {
		return "UserAddResponse [networkname=" + networkname + ", status_code="
				+ status_code + ", status_message=" + status_message
				+ ", userid=" + userid + ", username=" + username + "]";
	}
	
	
}
