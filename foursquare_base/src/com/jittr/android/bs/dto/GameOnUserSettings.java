package com.jittr.android.bs.dto;

import static com.jittr.android.util.Consts.*;

import android.os.Parcel;
import android.os.Parcelable;

/*
 * @author juliomiyares
 * @version 1.0
 */
public class GameOnUserSettings implements Parcelable {

	private int userID;
	private String password;
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String imageURL;
	private String fullName;
	private String twitterID; //= null; //"juliomiy";
	private String twitterSN; //twitter screenName
	private String facebookID; //=null; //"juliomiyares@mac.com";
	private String foursquareID;//=null; //"9173702880";
	private String aimID;
	private String icqID;
	private int primaryNetworkID;
	private String primaryNetworkName;
	private boolean twitterDefault; //=true;
	private boolean facebookDefault;//=false;
	private boolean foursquareDefault;//=false;
	private String  twitterOAuthToken;
	private String  twitterOAuthTokenSecret;
	private String  facebookOAuthToken;
	private String  facebookOAuthTokenSecret;
	private String  foursquareOAuthToken;
	private String  foursquareOAuthTokenSecret;
	private String foursquarePassword;
	private final String TAG = "GameOnUserSettings";
	private String foursquareName;
	private String foursquareUserID;

	 public static final Parcelable.Creator<GameOnUserSettings> CREATOR
	    = new Parcelable.Creator<GameOnUserSettings>() {
	   	
	        public GameOnUserSettings createFromParcel(Parcel in) {
	           return new GameOnUserSettings(in);
	        }
	        
	        public GameOnUserSettings[] newArray(int arg0) {
	    		// TODO Auto-generated method stub
	    		return null;
	    	}
	        
	     };  //Parcelable.Creator
	     
    private GameOnUserSettings(Parcel in) {
    	 readFromParcel(in);
    }  //Constructor

	public void readFromParcel(Parcel in) {
		userID = in.readInt();
		password = in.readString();
		userName = in.readString();
		firstName = in.readString();
		lastName = in.readString();
		email = in.readString();
		phoneNumber = in.readString();
		twitterID = in.readString();
		twitterSN = in.readString();
		facebookID = in.readString();
		foursquareID = in.readString();
		aimID = in.readString();
		icqID = in.readString();
		primaryNetworkID = in.readInt();
		primaryNetworkName = in.readString();
		twitterDefault = (in.readInt() == 1 ? true : false );
		facebookDefault = (in.readInt() == 1 ? true : false );
		foursquareDefault = (in.readInt() == 1 ? true : false );
        twitterOAuthToken = in.readString();
        facebookOAuthToken = in.readString();
        foursquareOAuthToken = in.readString();
        twitterOAuthTokenSecret = in.readString();
        facebookOAuthTokenSecret = in.readString();
        foursquareOAuthTokenSecret = in.readString();
	}  //readFromParcel
	
	public int describeContents() {
		return 0;
	}
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(getUserID());
		dest.writeString(password);
		dest.writeString(userName);
		dest.writeString(firstName);
		dest.writeString(lastName);
		dest.writeString(email);
		dest.writeString(phoneNumber);
		dest.writeString(twitterID);
		dest.writeString(twitterSN);
		dest.writeString(facebookID);
		dest.writeString(foursquareID);
		dest.writeString(aimID);
		dest.writeString(icqID);
		dest.writeInt(getPrimaryNetworkID());
		dest.writeString(primaryNetworkName);
		dest.writeInt(isTwitterDefaultInt());
		dest.writeInt(isFacebookDefaultInt());
		dest.writeInt(isFoursquareDefaultInt());
		dest.writeString(twitterOAuthToken);
		dest.writeString(facebookOAuthToken);
		dest.writeString(foursquareOAuthToken);
		dest.writeString(twitterOAuthTokenSecret);
		dest.writeString(facebookOAuthTokenSecret);
		dest.writeString(foursquareOAuthTokenSecret);
	} //writeToParcel
	
	public GameOnUserSettings(int newUserID, String newUserName, String newPassword, String newFirstName, String newLastName, 
			    String newEmail, int newPrimaryNetworkID, String newPrimaryNetworkName, String accessToken, String accessTokenSecret, String newImageURL, String newFullName) {
		userID=newUserID;
		userName = newUserName;
		password = newPassword;
		firstName = newFirstName;
		lastName = newLastName;
		email = newEmail;
		primaryNetworkID = newPrimaryNetworkID;
		primaryNetworkName = newPrimaryNetworkName;
		imageURL = newImageURL;
		fullName = newFullName;
		switch (primaryNetworkID) {
		  case TWITTER_NETWORK:
			 twitterOAuthToken = accessToken; 
			 twitterOAuthTokenSecret = accessTokenSecret;
             twitterSN = newUserName;
			 break;
		  case FOURSQUARE_NETWORK:
		     foursquareOAuthToken = accessToken; 
		     foursquareOAuthTokenSecret = accessTokenSecret;
			 break;
		  case FACEBOOK_NETWORK:
			  break;
		}  //switch
	}
	public GameOnUserSettings() {
		
	} //default Constructor
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @return the imageURL
	 */
	public String getImageURL() {
		return imageURL;
	}

	/**
	 * @param imageURL the imageURL to set
	 */
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return the twitterSN
	 */
	public String getTwitterSN() {
		return twitterSN;
	}
	/**
	 * @param twitterSN the twitterSN to set
	 */
	public void setTwitterSN(String twitterSN) {
		this.twitterSN = twitterSN;
	}
	/**
	 * @return the primaryNetworkID
	 */
	public int getPrimaryNetworkID() {
		return primaryNetworkID;
	}
	/**
	 * @param primaryNetworkID the primaryNetworkID to set
	 */
	public void setPrimaryNetworkID(int primaryNetworkID) {
		this.primaryNetworkID = primaryNetworkID;
	}
	/**
	 * @return the primaryNetworkName
	 */
	public String getPrimaryNetworkName() {
		return primaryNetworkName;
	}
	/**
	 * @param primaryNetworkName the primaryNetworkName to set
	 */
	public void setPrimaryNetworkName(String primaryNetworkName) {
		this.primaryNetworkName = primaryNetworkName;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	public GameOnUserSettings(int userID) {
        this.userID = userID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName( String userName) {
		this.userName= userName;
	}

	public String getTwitterID() {
		return twitterID;
	}
	public void setTwitterID(String twitterID) {
		this.twitterID = twitterID;
	}
	public String getFacebookID() {
		return facebookID;
	}
	public void setFacebookID(String facebookID) {
		this.facebookID = facebookID;
	}
	public String getFoursquareID() {
    	return foursquareID;
	}
	public void setFoursquareID(String foursquareID) {
		this.foursquareID = foursquareID;
	}
	public String getAimID() {
		return aimID;
	}
	public void setAimID(String aimID) {
		this.aimID = aimID;
	}
	public String getIcqID() {
		return icqID;
	}
	public void setIcqID(String icqID) {
		this.icqID = icqID;
	}
	public boolean isTwitterDefault() {
		return twitterDefault;
	}
	protected int isTwitterDefaultInt() {
		if (twitterDefault) return 1;
		else return 0;                  
	}
	protected int isFacebookDefaultInt() {
		if (facebookDefault) return 1;
		else return 0;                  
	}
	protected int isFoursquareDefaultInt() {
		if (foursquareDefault) return 1;
		else return 0;                  
	}

	public void setTwitterDefault(boolean twitterDefault) {
		this.twitterDefault = twitterDefault;
	}
	public boolean isFacebookDefault() {
		return facebookDefault;
	}
	public void setFacebookDefault(boolean facebookDefault) {
		this.facebookDefault = facebookDefault;
	}
	public boolean isFoursquareDefault() {
		return foursquareDefault;
	}
	public void setFoursquareDefault(boolean foursquareDefault) {
		this.foursquareDefault = foursquareDefault;
	}
	public String getFacebookOAuthToken() {
		return facebookOAuthToken;
	}
	public void setFacebookOAuthToken(String facebookOAuthToken) {
		this.facebookOAuthToken = facebookOAuthToken;
	}
	public String getFacebookOAuthTokenSecret() {
		return facebookOAuthTokenSecret;
	}
	public void setFacebookOAuthTokenSecret(String facebookOAuthTokenSecret) {
		this.facebookOAuthTokenSecret = facebookOAuthTokenSecret;
	}
	public String getTwitterOAuthToken() {
		return twitterOAuthToken;
	}
	public void setTwitterOAuthToken(String twitterOAuthToken) {
		this.twitterOAuthToken = twitterOAuthToken;
	}
	public String getTwitterOAuthTokenSecret() {
		return twitterOAuthTokenSecret;
	}
	public void setTwitterOAuthTokenSecret(String twitterOAuthTokenSecret) {
		this.twitterOAuthTokenSecret = twitterOAuthTokenSecret;
	}
	public String getFoursquareOAuthToken() {
		return foursquareOAuthToken;
	}
	public void setFoursquareOAuthToken(String foursquareOAuthToken) {
		this.foursquareOAuthToken = foursquareOAuthToken;
	}
	public String getFoursquareOAuthTokenSecret() {
		return foursquareOAuthTokenSecret;
	}
	public void setFoursquareOAuthTokenSecret(String foursquareOAuthTokenSecret) {
		this.foursquareOAuthTokenSecret = foursquareOAuthTokenSecret;
	}

	public void setFoursquareName(String name) {
         foursquareName= name;		
	}

	public void setFoursquareUserID(String userID) {
         foursquareUserID=userID;		
	}

//deprecated once oauth is required
	public String getFoursquarePassword() {
		//TODO - remove hardcoded value
		return "cuba1a";
		//return foursquarePassword;
	}
	public void setFoursquarePassword(String password) {
         foursquarePassword = password;		
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GameOnUserSettings [TAG=");
		builder.append(TAG);
		builder.append(", aimID=");
		builder.append(aimID);
		builder.append(", email=");
		builder.append(email);
		builder.append(", facebookDefault=");
		builder.append(facebookDefault);
		builder.append(", facebookID=");
		builder.append(facebookID);
		builder.append(", facebookOAuthToken=");
		builder.append(facebookOAuthToken);
		builder.append(", facebookOAuthTokenSecret=");
		builder.append(facebookOAuthTokenSecret);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", foursquareDefault=");
		builder.append(foursquareDefault);
		builder.append(", foursquareID=");
		builder.append(foursquareID);
		builder.append(", foursquareName=");
		builder.append(foursquareName);
		builder.append(", foursquareOAuthToken=");
		builder.append(foursquareOAuthToken);
		builder.append(", foursquareOAuthTokenSecret=");
		builder.append(foursquareOAuthTokenSecret);
		builder.append(", foursquarePassword=");
		builder.append(foursquarePassword);
		builder.append(", foursquareUserID=");
		builder.append(foursquareUserID);
		builder.append(", fullName=");
		builder.append(fullName);
		builder.append(", icqID=");
		builder.append(icqID);
		builder.append(", imageURL=");
		builder.append(imageURL);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", password=");
		builder.append(password);
		builder.append(", phoneNumber=");
		builder.append(phoneNumber);
		builder.append(", primaryNetworkID=");
		builder.append(primaryNetworkID);
		builder.append(", primaryNetworkName=");
		builder.append(primaryNetworkName);
		builder.append(", twitterDefault=");
		builder.append(twitterDefault);
		builder.append(", twitterID=");
		builder.append(twitterID);
		builder.append(", twitterOAuthToken=");
		builder.append(twitterOAuthToken);
		builder.append(", twitterOAuthTokenSecret=");
		builder.append(twitterOAuthTokenSecret);
		builder.append(", twitterSN=");
		builder.append(twitterSN);
		builder.append(", userID=");
		builder.append(userID);
		builder.append(", userName=");
		builder.append(userName);
		builder.append("]");
		return builder.toString();
	}

	public boolean isFacebookAuthorized() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isTwitterAuthorized() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isFoursquareAuthorized() {
		// TODO Auto-generated method stub
		return false;
	}
	
	} //class
