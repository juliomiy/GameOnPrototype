package com.jittr.android.bs.dto;

/*changed by Julio Hernandez-Miyares
 * selected StringBuilder to generate toString
 * added firstName, lastName, userName email as class properites including setters/getters
 */
public class UserAddResponse {
   
	public String status_code;
	String status_message;
	String userid;
	String username;
	String firstName;
	String lastName;
	String email;
	String networkname;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserAddResponse() {
		
	}

	public String getStatus_code() {
		return status_code.substring(0, 3);
	}

	public void setStatus_code(String statusCode) {
		status_code = statusCode.trim();
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
/*
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
*/
	public String getNetworkname() {
		return networkname;
	}

	public void setNetworkname(String networkname) {
		this.networkname = networkname;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserAddResponse [email=");
		builder.append(email);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", networkname=");
		builder.append(networkname);
		builder.append(", status_code=");
		builder.append(status_code);
		builder.append(", status_message=");
		builder.append(status_message);
		builder.append(", userid=");
		builder.append(userid);
		builder.append(", username=");
		builder.append(username);
		builder.append("]");
		return builder.toString();
	}
	
	
}
