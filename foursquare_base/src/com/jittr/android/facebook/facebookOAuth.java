/**
 * 
 */
package com.jittr.android.facebook;

import com.jittr.android.BetSquaredApplication;
import com.jittr.android.betsquared.GameOnSocialNetworkBase;

/**
 * @author juliomiyares
 *
 */
public class facebookOAuth extends GameOnSocialNetworkBase {

	/**
	 * 
	 */
	public facebookOAuth() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param network
	 * @param token
	 * @param secret
	 */
	public facebookOAuth(BetSquaredApplication appObject,int network, String token, String secret) {
		super(appObject, network, token, secret);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.jittr.android.betsquared.GameOnSocialNetworkInterface#signRequest()
	 */
	@Override
	public void signRequest() {
		// TODO Auto-generated method stub

	}

}
