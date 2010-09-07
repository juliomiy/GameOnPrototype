package com.jittr.android;

import java.util.ArrayList;
import java.util.HashMap;

import com.jittr.android.api.betsquared.BSClientAPIImpl;
import com.jittr.android.bs.dto.GameInvite;
import com.jittr.android.bs.dto.GameInvites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
	private ImageView windowIcon;
	private LinearLayout bottomBarLayout;
	private Button testButton;
	
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
	
	protected boolean setBottomBar() {
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
			   break;
		}  //switch
		
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
