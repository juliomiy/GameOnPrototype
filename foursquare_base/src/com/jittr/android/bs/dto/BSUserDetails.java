package com.jittr.android.bs.dto;

public class BSUserDetails {

	String status_code;
	String status_message;
	String userid;
	String username;
	String firstname;
	String lastname;
	String email;
	String facebookuserid;
	String facebookprofileimageurl;
	String twitteruserid;
	String twitterprofileimageurl;
	String foursquareuserid;
	String foursquareprofileimageurl;
	String facebookdefault;
	String twitterdefault;
	String foursquaredefault;
	private String twitterAccessToken;
	private String facebookAccessToken;
	private String foursquareAccessToken;
	private String twitterTokenSecret;
	private String foursquareTokenSecret;
	
	public BSUserDetails() {
		
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

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFacebookuserid() {
		return facebookuserid;
	}

	public void setFacebookuserid(String facebookuserid) {
		this.facebookuserid = facebookuserid;
	}

	public String getFacebookprofileimageurl() {
		return facebookprofileimageurl;
	}

	public void setFacebookprofileimageurl(String facebookprofileimageurl) {
		this.facebookprofileimageurl = facebookprofileimageurl;
	}

	public String getTwitteruserid() {
		return twitteruserid;
	}

	public void setTwitteruserid(String twitteruserid) {
		this.twitteruserid = twitteruserid;
	}

	public String getTwitterprofileimageurl() {
		return twitterprofileimageurl;
	}

	public void setTwitterprofileimageurl(String twitterprofileimageurl) {
		this.twitterprofileimageurl = twitterprofileimageurl;
	}

	public String getFoursquareuserid() {
		return foursquareuserid;
	}

	public void setFoursquareuserid(String foursquareuserid) {
		this.foursquareuserid = foursquareuserid;
	}

	public String getFoursquareprofileimageurl() {
		return foursquareprofileimageurl;
	}

	public void setFoursquareprofileimageurl(String foursquareprofileimageurl) {
		this.foursquareprofileimageurl = foursquareprofileimageurl;
	}

	public String getFacebookdefault() {
		return facebookdefault;
	}

	public void setFacebookdefault(String facebookdefault) {
		this.facebookdefault = facebookdefault;
	}

	public String getTwitterdefault() {
		return twitterdefault;
	}

	public void setTwitterdefault(String twitterdefault) {
		this.twitterdefault = twitterdefault;
	}

	public String getFoursquaredefault() {
		return foursquaredefault;
	}

	public void setFoursquaredefault(String foursquaredefault) {
		this.foursquaredefault = foursquaredefault;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BSUserDetails [email=");
		builder.append(email);
		builder.append(", facebookAccessToken=");
		builder.append(facebookAccessToken);
		builder.append(", facebookdefault=");
		builder.append(facebookdefault);
		builder.append(", facebookprofileimageurl=");
		builder.append(facebookprofileimageurl);
		builder.append(", facebookuserid=");
		builder.append(facebookuserid);
		builder.append(", firstname=");
		builder.append(firstname);
		builder.append(", foursquareAccessToken=");
		builder.append(foursquareAccessToken);
		builder.append(", foursquareTokenSecret=");
		builder.append(foursquareTokenSecret);
		builder.append(", foursquaredefault=");
		builder.append(foursquaredefault);
		builder.append(", foursquareprofileimageurl=");
		builder.append(foursquareprofileimageurl);
		builder.append(", foursquareuserid=");
		builder.append(foursquareuserid);
		builder.append(", lastname=");
		builder.append(lastname);
		builder.append(", status_code=");
		builder.append(status_code);
		builder.append(", status_message=");
		builder.append(status_message);
		builder.append(", twitterAccessToken=");
		builder.append(twitterAccessToken);
		builder.append(", twitterTokenSecret=");
		builder.append(twitterTokenSecret);
		builder.append(", twitterdefault=");
		builder.append(twitterdefault);
		builder.append(", twitterprofileimageurl=");
		builder.append(twitterprofileimageurl);
		builder.append(", twitteruserid=");
		builder.append(twitteruserid);
		builder.append(", userid=");
		builder.append(userid);
		builder.append(", username=");
		builder.append(username);
		builder.append("]");
		return builder.toString();
	}

	public boolean getFoursquareDefaultBoolean() {
		if (null != foursquaredefault && 
				("1".equals(foursquaredefault) || "true".equals(foursquaredefault.toLowerCase()))) {
			return true;
		}
		return false;
	}

	public boolean getFacebookDefaultBoolean() {
		if (null != facebookdefault && 
				("1".equals(facebookdefault) || "true".equals(facebookdefault.toLowerCase()))) {
			return true;
		}
		return false;
	}

	public boolean getTwitterDefaultBoolean() {
		if (null != twitterdefault && 
				("1".equals(twitterdefault) || "true".equals(twitterdefault.toLowerCase()))) {
			return true;
		}
		return false;
	}

	public void setTwitterAccessToken(String string) {
        twitterAccessToken = string;		
	}

	public void setFacebookAccessToken(String string) {
        facebookAccessToken = string;		
	}

	public void setFoursquareAccessToken(String string) {
        foursquareAccessToken = string;		
	}
	public void setTwitterTokenSecret(String string) {
         twitterTokenSecret = string;		
	}
	public void setFoursquareTokenSecret(String string) {
        foursquareTokenSecret = string;		
	}

	public String getTwitterAccessToken() {
		return twitterAccessToken;
	}

	public String getFacebookAccessToken() {
		return facebookAccessToken;
	}

	public String getFoursquareAccessToken() {
		return foursquareAccessToken;
	}

	public String getTwitterTokenSecret() {
		return twitterTokenSecret;
	}

	public String getFoursquareTokenSecret() {
		return foursquareTokenSecret;
	}

	public boolean isFacebookAuthorized() {
		// TODO Auto-generated method stub
		return false;
	}

	/* returns true if the oauth credentials are present 
	 * Does not guarantee they are still valid
	 */
	public boolean isTwitterAuthorized() {
		if (null != twitterAccessToken && !"".equals(twitterAccessToken.trim())
				 && null != twitterTokenSecret && !"".equals(twitterTokenSecret.trim()))
            return true;
		else
			return false;
	} //isTwitterAuthorized

	/* returns true if the oauth credentials are present 
	 * Does not guarantee they are still valid
	 */
	public boolean isFoursquareAuthorized() {
		if (null != foursquareAccessToken && !"".equals(foursquareAccessToken.trim())
				 && null != foursquareTokenSecret && !"".equals(foursquareTokenSecret.trim()))
           return true;
 		else
			return false;
	}  //isFoursquareAuthorized
}






