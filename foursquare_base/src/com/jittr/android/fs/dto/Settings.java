package com.jittr.android.fs.dto;

public class Settings {

	 private String pings;
	 private boolean sendTofacebook;
	 private boolean sendTotwitter;
	
	public Settings() {
		
	}
	public String getPings() {
		return pings;
	}



	public void setPings(String pings) {
		this.pings = pings;
	}



	public boolean isSendTofacebook() {
		return sendTofacebook;
	}



	public void setSendTofacebook(boolean sendTofacebook) {
		this.sendTofacebook = sendTofacebook;
	}



	public boolean isSendTotwitter() {
		return sendTotwitter;
	}



	public void setSendTotwitter(boolean sendTotwitter) {
		this.sendTotwitter = sendTotwitter;
	}
	
	 @Override
		public String toString() {
			return "Settings [pings=" + pings + ", sendTofacebook="
					+ sendTofacebook + ", sendTotwitter=" + sendTotwitter + "]";
		}

}
