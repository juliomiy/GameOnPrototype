/**
 * 
 */
package com.jittr.android;

import com.jittr.android.bs.dto.GameOnUserSettings;
import com.jittr.android.util.Consts;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * @author juliomiyares
 *
 */
public class GameOnSignUpActivity extends GameOnBaseActivity {
    private Button cancelButton;
	private ImageButton signUpMailButton;
	private ImageButton signUpFoursquareButton;
	private ImageButton signUpFacebookButton;
	private ImageButton signUpTwitterButton;
	private GameOnUserSettings userSettings;
	/**
	 * 
	 */
	public GameOnSignUpActivity() {
		// TODO Auto-generated constructor stub
	}
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameonsignupmain);
        setUpViews();
	} //onCreate

	protected void OnResume() {
	   super.onResume();
	} //OnResume
	
	protected void OnActivityResult(int requestCode, int resultCode, Intent data) {
		Log.d(TAG, "In OnActivtyResult " + requestCode + " " + resultCode);
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_CANCELED) return;   //need to flesh this out further, for now, just return
		userSettings = data.getParcelableExtra(Consts.INTENT_USER_SETTINGS);
		Log.d(TAG, (null != userSettings) ? userSettings.toString() : "UserSettings is null");
        Log.d(TAG,"Resultcode = " + resultCode + " requestCode = " + requestCode);
		if (userSettings == null) return; //need to flesh out further -  this should not hapen
		getAppContext().registerNewUser(userSettings);
		//data.
	} //onActivityResult
	
	private void setUpViews() {
         cancelButton = (Button)findViewById(R.id.windowTitleLeftButton);
         cancelButton.setVisibility(View.VISIBLE);		
         cancelButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
                finish();
			}
		});
         signUpMailButton = (ImageButton)findViewById(R.id.signUpByEmailButton);
         signUpMailButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
                signUp(Consts.BETSQUARED_NETWORK);				
			}
		});
         signUpFoursquareButton = (ImageButton)findViewById(R.id.signUpByFoursquareButton);
         signUpFoursquareButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
               signUp(Consts.FOURSQUARE_NETWORK);				
			}
		});
         signUpFacebookButton = (ImageButton)findViewById(R.id.signUpByFacebookButton);
         signUpFacebookButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
                signUp(Consts.FACEBOOK_NETWORK);
			}
		});
         signUpTwitterButton = (ImageButton)findViewById(R.id.signUpByTwitterButton);
         signUpTwitterButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				signUp(Consts.TWITTER_NETWORK);
			}
		});
	} //setUpViews

	protected void signUp(int network) {
		Intent intent = null;
		int requestCode = network;
		switch (network) {
	   	   case Consts.TWITTER_NETWORK:
 	    	    intent = new Intent(GameOnSignUpActivity.this,GameOnTwitterOAuthActivity.class);
 	    	   // intent.se
	   		    break; 
		   case Consts.FACEBOOK_NETWORK:
	 	   	    intent = new Intent(this,GameOnRegisterEmailActivity.class); //change to Facebook
		   	    break; 
		   case Consts.FOURSQUARE_NETWORK:	
	 	   		intent = new Intent(this,GameOnRegisterEmailActivity.class);  //change to Foursquare
		   		break; 
		   case Consts.BETSQUARED_NETWORK:	   
	 	   		intent = new Intent(GameOnSignUpActivity.this,GameOnRegisterEmailActivity.class);
		   		break; 
		   default: return;		
		} //switch
		intent.putExtra(Consts.NEW_REGISTRATION, true);
		startActivityForResult(intent, requestCode);
		return;
	} //signup
	
}  //class