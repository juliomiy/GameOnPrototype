package com.jittr.android.bs.dto;

public class BSUserDashBoard {
	String statusCode;
	String statusMessage;
	String userid;
	String totalbets;
	String totalbetsinitiated;
	String totalbetsaccepted;
	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return the statusMessage
	 */
	public String getStatusMessage() {
		return statusMessage;
	}

	/**
	 * @param statusMessage the statusMessage to set
	 */
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	String totalwins;
	String totalloses;
	
	
	
	public BSUserDashBoard() {
		super();
	}
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getTotalbets() {
		return totalbets;
	}
	public void setTotalbets(String totalbets) {
		this.totalbets = totalbets;
	}
	public String getTotalbetsinitiated() {
		return totalbetsinitiated;
	}
	public void setTotalbetsinitiated(String totalbetsinitiated) {
		this.totalbetsinitiated = totalbetsinitiated;
	}
	public String getTotalbetsaccepted() {
		return totalbetsaccepted;
	}
	public void setTotalbetsaccepted(String totalbetsaccepted) {
		this.totalbetsaccepted = totalbetsaccepted;
	}
	public String getTotalwins() {
		return totalwins;
	}
	public void setTotalwins(String totalwins) {
		this.totalwins = totalwins;
	}
	public String getTotalloses() {
		return totalloses;
	}
	public void setTotalloses(String totalloses) {
		this.totalloses = totalloses;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BSUserDashBoard [statusCode=");
		builder.append(statusCode);
		builder.append(", statusMessage=");
		builder.append(statusMessage);
		builder.append(", totalbets=");
		builder.append(totalbets);
		builder.append(", totalbetsaccepted=");
		builder.append(totalbetsaccepted);
		builder.append(", totalbetsinitiated=");
		builder.append(totalbetsinitiated);
		builder.append(", totalloses=");
		builder.append(totalloses);
		builder.append(", totalwins=");
		builder.append(totalwins);
		builder.append(", userid=");
		builder.append(userid);
		builder.append("]");
		return builder.toString();
	}
}
