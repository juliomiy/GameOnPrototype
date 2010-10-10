package com.jittr.android;

import java.util.HashMap;

import com.jittr.android.api.betsquared.DataFetchingCallBack;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public abstract class GameOnBaseActivity extends Activity implements DataFetchingCallBack {

    protected static final String TAG = "GameOnBaseActivity";
	protected BetSquaredApplication appContext;
    protected TextView windowTitle;
 //   protected TextWatcher textWatcher;
	private ImageView windowIcon;
	private LinearLayout bottomBarLayout;
	private Button meButton;
	private Button placesButton;
	private Button betsButton;
	private Button friendsButton;
	protected ProgressDialog progressDialog;
	
	public GameOnBaseActivity() {
		super();
	    appContext = getStuffApplication();	
	}  //constructor
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);

        setContentView(R.layout.main);

        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.windowtitle);

        windowTitle = (TextView) findViewById(R.id.windowtitle);
       
        //icon  = (ImageView) findViewById(R.id.icon);
	}
	
	protected void OnActivityResult(int requestCode, int resultCode, Intent data) {
		Log.d(TAG, "In OnActivtyResult " + requestCode + " " + resultCode);
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	/* Sets up the bottomBar
	 * @params
	 * @returns true
	 */
	protected boolean setBottomBar(int  viewID) {
		//bottomBarLayout.
	       betsButton = (Button)findViewById(R.id.betsButton);
	        if (viewID != R.id.betsButton) {
		        betsButton.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
		              betsButtonClicked();				
					}
				});
	        } else {
	        	betsButton.setEnabled(false);
	        }
		    
		    meButton = (Button)findViewById(R.id.meButton);
            if (viewID != R.id.meButton) {
	   		    meButton.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						meButtonClicked();
					}
				});
            } else {
            	meButton.setEnabled(false);
            }
		    placesButton = (Button)findViewById(R.id.placesButton);
            if (viewID != R.id.placesButton) {
			    placesButton.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
	                     placesButtonClicked();					
					}
				});
            } //if
	        friendsButton = (Button)findViewById(R.id.friendsButton);
	        if (viewID != R.id.betsButton) {
	        	friendsButton.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
	                    friendsButtonClicked();					
					}
				});
	        } //if

		return true;
	}
	protected void friendsButtonClicked() {
		Intent intent = new Intent(this,GameOnUserFriendsListActivity.class);
		startActivity(intent);	
	}

	protected void betsButtonClicked() {
		Intent intent = new Intent(this,GameOnUserBetsListActivity.class);
		startActivity(intent);	
	}

	protected void meButtonClicked() {
		Intent intent = new Intent(this,GetUserDashBoardActivity.class);
		startActivity(intent);	
	}

	protected void placesButtonClicked() {
		Intent intent = new Intent(this,GetNearbyVenuesActivity.class);
		startActivity(intent);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
			
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.defaultmenu, menu);
	    return true;
	} //onCreateOptionsMenu
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
	
		Intent intent;
		switch (item.getItemId())  {
		   case R.id.update_user_settings :
			   intent = new Intent(this,GetBetSquareUserDetailsActivity.class);
			   break;
	     default:
	    	    return false;
		}  //switch
		
	   startActivity(intent);	
       return true;
	}  //onMenuItemSelected
	
	public TextView getWindowTitle() {
		if (null == windowTitle) {
			windowTitle = (TextView) findViewById(R.id.windowtitle);
		}  //if
		return windowTitle;
	}

	public ImageView getWindowIcon() {
		if (null == windowIcon) {
			windowIcon = (ImageView) findViewById(R.id.windowicon);
		}	//if	
		return windowIcon;
	}

	protected BetSquaredApplication getStuffApplication()
	{
		return (BetSquaredApplication)getApplication();
	}
    
	public BetSquaredApplication getAppContext() {
		 if (null != appContext) return appContext;
		 else {
			 appContext = getStuffApplication();
			 return appContext;
		 }
	}
    protected Dialog onCreateDialog(int id) {
		Log.d("", "Inside onCreateDialog" );
		ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait while loading...");
        dialog.setIndeterminate(true);
        dialog.setCancelable(true);
        progressDialog =dialog; 
        return dialog;
    }

	public void dataLoaded(Object response) {

	}
	
	public void dataLoadCancelled() {
		// TODO Auto-generated method stub
		
	}
	public void dataLoadException(String message) {
		// TODO Auto-generated method stub
	}
	public void dataLoading() {
		Log.d(TAG,"Data Loading Call Back Triggered");
		showDialog(1);
		
	}
	public void preDataLoading() {
		Log.d(TAG,"Pre Data Loading Call Back Triggered");
	}

}  //class
