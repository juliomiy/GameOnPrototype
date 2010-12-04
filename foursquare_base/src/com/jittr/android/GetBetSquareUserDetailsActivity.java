package com.jittr.android;

import java.util.HashMap;

import com.jittr.android.api.betsquared.BSClientAPIImpl;
import com.jittr.android.bs.dto.BSUserDetails;
import com.jittr.android.util.Consts;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class GetBetSquareUserDetailsActivity extends GameOnBaseActivity implements OnCheckedChangeListener, OnClickListener {
private EditText userNameEditText;
private Button updateButton;
private BSClientAPIImpl bs;
private BSUserDetails bsUser;
private EditText firstNameEditText;
private EditText lastNameEditText;
private EditText emailEditText;
private HashMap<String, String> params;
//private BetSquaredApplication appObject;
private final String  TAG = "GetBetSquarerUserDetails";
private Integer userID;
private ToggleButton facebookDefaultToggle;
private ToggleButton foursquareDefaultToggle;
private ToggleButton twitterDefaultToggle;

private boolean facebookDefault;
private boolean twitterDefault;
private boolean foursquareDefault;
private Button twitterLogInButton;
private Button foursquareLogInButton;
private TextView twitterNameTextView;
private TextView foursquareNameTextView;
	/* 
<usersettings>
<status_code>410</status_code>
<status_message>User Not Found</status_message>
<userid/>
<username/>
<firstname/>
<lastname/>
<email/>
<facebookuserid/>
<facebookprofileimageurl/>
<twitteruserid/>
<twitterprofileimageurl/>
<foursquareuserid/>
<foursquareprofileimageurl/>
<defaultsettings>
<facebookdefault/>
<twitterdefault/>
<foursquaredefault/>
</defaultsettings>
</usersettings>
*/
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getbetsquareduserdetails);
        setUpViews();
        setBottomBar(0);
    }   //onCreate

	protected void onResume() {
		super.onResume();
		if (bs == null ) {
			bs = new BSClientAPIImpl();
            params = new HashMap();
            userID = getAppContext().getLoginID();           
			params.put("userid", userID.toString());
			bsUser = bs.getUserDetails(params);
            if (bsUser != null) {
            	Log.d(TAG , bsUser.toString());
            	emailEditText.setText(bsUser.getEmail());
            	userNameEditText.setText(bsUser.getUsername());
    			firstNameEditText.setText(bsUser.getFirstname());
                lastNameEditText.setText(bsUser.getLastname());
                setFacebookDefault(bsUser.getFacebookDefaultBoolean());
                setTwitterDefault(bsUser.getTwitterDefaultBoolean());
                setFoursquareDefault(bsUser.getFoursquareDefaultBoolean());

                foursquareDefaultToggle.setChecked(bsUser.getFoursquareDefaultBoolean());
                facebookDefaultToggle.setChecked(bsUser.getFacebookDefaultBoolean());
                twitterDefaultToggle.setChecked(bsUser.getTwitterDefaultBoolean());

                if (bsUser.isTwitterAuthorized()) {
                	twitterLogInButton.setText(R.string.logoutbuttontext);
                	twitterNameTextView.setText(bsUser.getTwitteruserid());
                } else {
                	twitterLogInButton.setText(R.string.loginbuttontext);
                }
                if (bsUser.isFoursquareAuthorized()) {
                	foursquareLogInButton.setText(R.string.logoutbuttontext);
                } else {
                	foursquareLogInButton.setText(R.string.loginbuttontext);
                }
         //        twitterEditText.setText(fsUser.getTwitter());
        //        faceBookEditText.setText(fsUser.getFacebook());
            } //if
		} //if
		//release no longer needed objects
		params=null;
		bs=null;
		bsUser=null;
	} //onResume
	
	protected void setUpViews() {
		super.setUpViews(Consts.LAYOUT_ADD_DONE);
	    userNameEditText = (EditText)findViewById(R.id.userNameEditText);	
	    firstNameEditText = (EditText)findViewById(R.id.firstNameEditText);	
	    lastNameEditText = (EditText)findViewById(R.id.lastNameEditText);	
	    emailEditText = (EditText)findViewById(R.id.emailEditText);	

        twitterLogInButton = (Button)findViewById(R.id.twitterLoginButton);
        foursquareLogInButton = (Button)findViewById(R.id.foursquareLoginButton);

        twitterLogInButton.setOnClickListener(this);
        foursquareLogInButton.setOnClickListener(this);
       
        facebookDefaultToggle = (ToggleButton)findViewById(R.id.facebookToggleButton);
	    facebookDefaultToggle.setOnCheckedChangeListener(this);

	    foursquareDefaultToggle = (ToggleButton)findViewById(R.id.foursquareToggleButton);
	    foursquareDefaultToggle.setOnCheckedChangeListener(this);

	    twitterDefaultToggle = (ToggleButton)findViewById(R.id.twitterToggleButton);
	    twitterDefaultToggle.setOnCheckedChangeListener(this);

	    twitterNameTextView = (TextView)findViewById(R.id.twitterNameTextView);
    	foursquareNameTextView = (TextView)findViewById(R.id.foursquareNameTextView);

//	   setBottomBar();
     
	} //setUpViews

	/* added JHM 11/22/2010 - handle widget clicks view listener implemented at class level
	 * 
	 */
	@Override
	public void onClick(View arg0) {
		
	} //onClick
	
	protected void onPause() {
		super.onPause();
	}
	public GetBetSquareUserDetailsActivity() {
		// TODO Auto-generated constructor stub
	}
	public boolean isFacebookDefault() {
		return facebookDefault;
	}
	public void setFacebookDefault(boolean facebookDefault) {
		this.facebookDefault = facebookDefault;
	}
	public boolean isTwitterDefault() {
		return twitterDefault;
	}
	public void setTwitterDefault(boolean twitterDefault) {
		this.twitterDefault = twitterDefault;
	}
	public boolean isFoursquareDefault() {
		return foursquareDefault;
	}
	public void setFoursquareDefault(boolean foursquareDefault) {
		this.foursquareDefault = foursquareDefault;
	}

	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		if (arg0 == facebookDefaultToggle) {
	        if (arg1 != facebookDefault) {
	        	getAppContext().updateSocialNetworkSetting(Consts.FACEBOOK_NETWORK, arg1);		
                setFacebookDefault(arg1);
	        }
		} else
		if (arg0 == twitterDefaultToggle) {
	        if (arg1 != twitterDefault) { 
	        	getAppContext().updateSocialNetworkSetting(Consts.TWITTER_NETWORK, arg1);		
                setTwitterDefault(arg1);
	        }
		} else
		if (arg0 == foursquareDefaultToggle) {
	        if (arg1 != foursquareDefault) { 
	        	getAppContext().updateSocialNetworkSetting(Consts.FOURSQUARE_NETWORK, arg1);		
                setFoursquareDefault(arg1);
	        }
		}
		
	}  //onCheckedChanged

}   //class
