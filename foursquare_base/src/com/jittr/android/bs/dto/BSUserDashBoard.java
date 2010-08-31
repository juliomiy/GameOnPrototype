package com.jittr.android.bs.dto;

public class BSUserDashBoard {
	String userid;
	String totalbets;
	String totalbetsinitiated;
	String totalbetsaccepted;
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
	
	@Override
	public String toString() {
		return "BSUserDashBoard [totalbets=" + totalbets
				+ ", totalbetsaccepted=" + totalbetsaccepted
				+ ", totalbetsinitiated=" + totalbetsinitiated
				+ ", totalloses=" + totalloses + ", totalwins=" + totalwins
				+ ", userid=" + userid + "]";
	}
}