package com.jittr.android;

import com.jittr.android.bs.adapters.BSBaseAdapter;
import com.jittr.android.fs.examples.DataFetchingCallBack;

import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
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
import android.widget.TextView;

public abstract class GameOnBaseListActivity extends ListActivity implements DataFetchingCallBack {
	private static final String TAG = "GameOnBaseListActivity";
	protected TextView windowTitle;
	private BetSquaredApplication appContext;
	private ImageView windowIcon;
	private Button betsButton;
	private Button friendsButton;
	private Button meButton;
	private Button placesButton;
	protected ProgressDialog progressDialog;
	private Button doneButton;

	public GameOnBaseListActivity() {
		super();
	    appContext = getStuffApplication();	
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
	@Override
	public void dataLoadCancelled() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dataLoadException(String message) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dataLoading() {
		Log.d(TAG,"Data Loading Cacll Back Triggered");
		showDialog(1);
	}
	@Override
	public void preDataLoading() {
		// TODO Auto-generated method stub
		
	}

	protected void setUpViews() {
	    doneButton = (Button)findViewById(R.id.windowTitleLeftButton);	
	    doneButton.setText("Done");
	    doneButton.setVisibility(View.VISIBLE);
	    
	    doneButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
                finish();
			}
		});
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);

        setContentView(R.layout.defaultlistview); //use defaultlistview to be able to create, will be overridden by the inheriting class on their onCreate

        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.windowtitle);

        windowTitle = (TextView) findViewById(R.id.windowtitle);
       
        //icon  = (ImageView) findViewById(R.id.icon);
	}
	/* @author juliomiyares
	 * @version 1.0
	 * @param int viewID , the ID of the view to disable on the bottomBar
	 * @returns boolean true 
	 */
	protected boolean setBottomBar(int viewID) {
	    betsButton = (Button)findViewById(R.id.betsButton);
	    if (null != betsButton) betsButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	    friendsButton = (Button)findViewById(R.id.friendsButton);
        if (viewID != R.id.friendsButton) {

		    friendsButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
				}
			});
        } else {
        	friendsButton.setEnabled(false);
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
        } else {
        	placesButton.setEnabled(false);
        }
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
        
        friendsButton = (Button)findViewById(R.id.friendsButton);
        if (viewID != R.id.friendsButton) {
        	friendsButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
                    friendsButtonClicked();					
				}
			});
        } //if
        
        return true;
	}  //setUpViews
	
	protected void friendsButtonClicked() {
		Intent intent = new Intent(this,GameOnUserFriendsListActivity.class);
		startActivity(intent);	
	}
	
	protected void betsButtonClicked() {
		Intent intent = new Intent(this,GameOnUserBetsListActivity.class);
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

	protected void meButtonClicked() {
		Intent intent = new Intent(this,GetUserDashBoardActivity.class);
		startActivity(intent);	
	}
	protected void placesButtonClicked() {
		Intent intent = new Intent(this,GetNearbyVenuesActivity.class);
		startActivity(intent);
	}
	protected BetSquaredApplication getStuffApplication()
	{
		return (BetSquaredApplication)getApplication();
	}
	protected BetSquaredApplication getAppContext() {
		 if (null != appContext) return appContext;
		 else {
			 appContext = getStuffApplication();
			 return appContext;
		 }
	}

}  //class
