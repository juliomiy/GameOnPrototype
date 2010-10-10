package com.jittr.android.bs.dto;

import java.util.List;

public class BSFriendRequests {
    String status_code;
    String status_message;
    String type;
    int numberofRequests;
    List<BSFriendRequest> requests;
    
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getNumberofRequests() {
		return numberofRequests;
	}
	public void setNumberofRequests(int numberofRequests) {
		this.numberofRequests = numberofRequests;
	}
	public List<BSFriendRequest> getRequests() {
		return requests;
	}
	public void setRequests(List<BSFriendRequest> requests) {
		this.requests = requests;
	}
	@Override
	public String toString() {
		return "BSFriendRequests [numberofRequests=" + numberofRequests
				+ ", requests=" + requests + ", status_code=" + status_code
				+ ", status_message=" + status_message + ", type=" + type + "]";
	}
}
