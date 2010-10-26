/**
 * 
 */
package com.jittr.android.bs.dto;

/**
 * @author juliomiyares
 * @version 1.0
 * @date October 24,2010
 * @purpose - class model for the user's bank balance
 * 1 to 1 with userID
 */
public class BSUserBankStatement {

	private String status_code;
	private String status_message;
	
	private int userID;
	private int duckettsBankBalance;
	private int duckettsInPlay;
	private int duckettOverDraftLimit;
	private int duckettOverDraftAvailable;
	private int duckettsOverDraftInUse;
	/**
	 * 
	 */
	public BSUserBankStatement() {
		// TODO Auto-generated constructor stub
	}  //constructor
	public int getDuckettsBankBalance() {
		return duckettsBankBalance;
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
	public int getUserID() {
		return userID;
	}
	public void setDuckettsBankBalance(int duckettsBankBalance) {
		this.duckettsBankBalance = duckettsBankBalance;
	}
	public int getDuckettsInPlay() {
		return duckettsInPlay;
	}
	public void setDuckettsInPlay(int duckettsInPlay) {
		this.duckettsInPlay = duckettsInPlay;
	}
	public int getDuckettOverDraftLimit() {
		return duckettOverDraftLimit;
	}
	public void setDuckettOverDraftLimit(int duckettOverDraftLimit) {
		this.duckettOverDraftLimit = duckettOverDraftLimit;
	}
	public int getDuckettOverDraftAvailable() {
		return duckettOverDraftAvailable;
	}
	public void setDuckettOverDraftAvailable(int duckettOverDraftAvailable) {
		this.duckettOverDraftAvailable = duckettOverDraftAvailable;
	}
	public int getDuckettsOverDraftInUse() {
		return duckettsOverDraftInUse;
	}
	public void setDuckettsOverDraftInUse(int duckettsOverDraftInUse) {
		this.duckettsOverDraftInUse = duckettsOverDraftInUse;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BSUserBankStatement [duckettOverDraftAvailable=");
		builder.append(duckettOverDraftAvailable);
		builder.append(", duckettOverDraftLimit=");
		builder.append(duckettOverDraftLimit);
		builder.append(", duckettsBankBalance=");
		builder.append(duckettsBankBalance);
		builder.append(", duckettsInPlay=");
		builder.append(duckettsInPlay);
		builder.append(", duckettsOverDraftInUse=");
		builder.append(duckettsOverDraftInUse);
		builder.append(", userID=");
		builder.append(userID);
		builder.append("]");
		return builder.toString();
	}
	public void setUserID(int tmpInt) {
        userID = tmpInt;
	}

}
