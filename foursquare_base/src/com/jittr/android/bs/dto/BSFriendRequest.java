package com.jittr.android.bs.dto;

public class BSFriendRequest {
	String status_message;
	String user_name;
	String social_network_name;
	String social_network_id;
   
	public String getStatus_message() {
		return status_message;
	}
	public void setStatus_message(String statusMessage) {
		status_message = statusMessage;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String userName) {
		user_name = userName;
	}
	public String getSocial_network_name() {
		return social_network_name;
	}
	public void setSocial_network_name(String socialNetworkName) {
		social_network_name = socialNetworkName;
	}
	public String getSocial_network_id() {
		return social_network_id;
	}
	public void setSocial_network_id(String socialNetworkId) {
		social_network_id = socialNetworkId;
	}
	@Override
	public String toString() {
		return "BSFriendRequest [social_network_id=" + social_network_id
				+ ", social_network_name=" + social_network_name
				+ ", status_message=" + status_message + ", user_name=" + user_name
				+ "]";
	}
}
