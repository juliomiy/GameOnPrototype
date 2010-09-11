package com.jittr.android.bs.dto;

import java.util.ArrayList;

/**
 * @author rg230v
 * 
 */
public class UserGamesDetails {
     
	String status_message;
    int status_code;
    int numberOfGames;
    ArrayList<UserGame> usergames = new ArrayList<UserGame>();
    
	public String getStatus_message() {
		return status_message;
	}
	public void setStatus_message(String statusMessage) {
		status_message = statusMessage;
	}
	public int getStatus_code() {
		return status_code;
	}
	public void setStatus_code(int statusCode) {
		status_code = statusCode;
	}
	public int getNumberOfGames() {
		return numberOfGames;
	}
	public void setNumberOfGames(int numberOfGames) {
		this.numberOfGames = numberOfGames;
	}
	public ArrayList<UserGame> getUsergames() {
		return usergames;
	}
	public void setUsergames(ArrayList<UserGame> usergames) {
		this.usergames = usergames;
	}
	@Override
	public String toString() {
		return "UserGamesDetails [numberOfGames=" + numberOfGames
				+ ", status_code=" + status_code + ", status_message="
				+ status_message + ", usergames=" + usergames + "]";
	}
    
}
