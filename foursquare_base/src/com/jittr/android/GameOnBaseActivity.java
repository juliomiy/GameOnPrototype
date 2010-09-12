package com.jittr.android;

import java.util.ArrayList;
import java.util.HashMap;

import com.jittr.android.api.betsquared.BSClientAPIImpl;
import com.jittr.android.bs.dto.GameInvite;
import com.jittr.android.bs.dto.GameInvites;

import android.app.Activity;
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

public abstract class GameOnBaseActivity extends Activity {

    protected static final String TAG = "GameOnBaseActivity";
	protected BetSquaredApplication appContext;
    protected TextView windowTitle;
 //   protected TextWatcher textWatcher;
	private ImageView windowIcon;
	private LinearLayout bottomBarLayout;
	private Button testButton;
	private Button meButton;
	private Button placesButton;
	private Button betsButton;
	private Button friendsButton;
	
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
/*		bottomBarLayout = (LinearLayout)findViewById(R.id.bottomBarLayout);
		int views = bottomBarLayout.getChildCount();
		for (int x = views; x<views; x++) {
		   View v = bottomBarLayout.getChildAt(x);
		   switch (v.getId()) {
		   case R.id.testButton:
			    testButton = (Button) v;
		        break;
		   }  //switch
		}   //for
		View v = bottomBarLayout.getChildAt(0);
*/		
		//bottomBarLayout.
		    betsButton = (Button)findViewById(R.id.betsButton);
		    if (null != betsButton) betsButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
				}
			});
		    friendsButton = (Button)findViewById(R.id.friendsButton);
		    friendsButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
				}
			});
		    
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
		    placesButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
                     placesButtonClicked();					
				}
			});
   	        testButton = (Button)findViewById(R.id.testButton);
		    testButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					  HashMap hm = new HashMap();
	                  BSClientAPIImpl bs = new BSClientAPIImpl();
	                  hm.put("userid", "110");
	                  GameInvites gameInvites = bs.getGameInvites(hm);
	                  ArrayList<GameInvite> invites = gameInvites.getGameinvites();
	                  
	                  for ( GameInvite gi :  invites) {
	                      Log.d(TAG,gi.toString());
	                	  
	                  }
	                  //for each()
	                  
				}
			});
		return true;
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
}  //class
