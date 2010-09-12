package com.jittr.android;

import java.util.ArrayList;
import java.util.HashMap;

import com.jittr.android.api.betsquared.BSClientAPIImpl;
import com.jittr.android.bs.dto.BSUserDetails;
import com.jittr.android.bs.dto.GameInvite;
import com.jittr.android.bs.dto.GameInvites;
import com.jittr.android.fs.impl.FSClientAPIImpl;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GetBetSquareUserDetailsActivity extends GameOnBaseActivity {
private EditText userNameEditText;
private Button cancelButton;
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
private Button testButton;

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
	public GetBetSquareUserDetailsActivity() {
		// TODO Auto-generated constructor stub
	}
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        appObject = (BetSquaredApplication) getApplication();
        setContentView(R.layout.getbetsquareduserdetails);
        setUpViews();
        setBottomBar(0);
    }   //onCreate

	protected void onResume() {
		super.onResume();
		if (bs == null ) {
			bs = new BSClientAPIImpl("xml", "9259485368", "findme3366");
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
        //        twitterEditText.setText(fsUser.getTwitter());
        //        faceBookEditText.setText(fsUser.getFacebook());
            } //if
		} //if
	} //onResume
	
	private void setUpViews() {
	    userNameEditText = (EditText)findViewById(R.id.userNameEditText);	
	    firstNameEditText = (EditText)findViewById(R.id.firstNameEditText);	
	    lastNameEditText = (EditText)findViewById(R.id.lastNameEditText);	
	    emailEditText = (EditText)findViewById(R.id.emailEditText);	

	    cancelButton = (Button)findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener( new View.OnClickListener() {
			   public void onClick(View v) {
                finish();				
			}
 	   }); 
        updateButton = (Button)findViewById(R.id.updateButton);
        updateButton.setOnClickListener( new View.OnClickListener() {
			   public void onClick(View v) {
                finish();				
			}
 	   });      
//	   setBottomBar();
     
	} //setUpViews

}   //class
