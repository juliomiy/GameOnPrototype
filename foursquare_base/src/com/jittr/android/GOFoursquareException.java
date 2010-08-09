package com.jittr.android;

import com.jittr.android.GameOnException;

public class GOFoursquareException extends GameOnException {
	private String userFriendlyMessage;
	private static final String TAG="GameOnFoursquareException";
	
	public GOFoursquareException(String userMessage ,String errorMessage, Exception e, String generatorTag) {
        super(errorMessage,e,generatorTag);   
        userFriendlyMessage = userMessage;
        
	}  //constructor

	/* type of message displayed to user - non-technical */
	public String getUserFriendlyMessage() {
		return userFriendlyMessage;
	}

}
