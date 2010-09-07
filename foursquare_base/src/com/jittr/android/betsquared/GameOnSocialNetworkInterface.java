/**
 * 
 */
package com.jittr.android.betsquared;

/**
 * @author juliomiyares
 *
 */
public interface GameOnSocialNetworkInterface {

	public boolean isAuthorized();
	public String getAuthUrl();
	public boolean saveUserAuthCredentials(int userID, int network, String token, String tokenSecret,
			                  String networkUserID, String networkScreenName, String networkName);
	public void signRequest();
}  //interface
