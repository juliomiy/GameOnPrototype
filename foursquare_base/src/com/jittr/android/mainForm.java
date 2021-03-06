package com.jittr.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.jittr.android.R.id;
import com.jittr.android.api.betsquared.BSClientAPIImpl;
import com.jittr.android.bs.dto.BSUserDetails;
import com.jittr.android.bs.dto.Friend;
import com.jittr.android.bs.dto.GameInvites;
import com.jittr.android.bs.dto.UserAddResponse;
import com.jittr.android.fs.dto.User;
import com.jittr.android.fs.dto.Venue;
import com.jittr.android.fs.impl.FSClientAPIImpl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
 

/* Main Form - this is the activity that is invoked first - if the user is not logged in
 * , the login Acitivty is started
 * @author: juliomiyares
 * @version: 1.0
 */
public class mainForm extends GameOnBaseActivity {
 	private Button cancelButton;
	private Button foursquareOauthButton;
	private Button getUserDetailsButton;
	private Button getNearbyVenuesButton;
	private Button getPublicGamesButton;
	private Button getUserDashBoardButton;
	private Button getBetSquareDetailsButton;
	private Button logoutButton;
	private Button twitterOAuthButton;
	private Button userBetButton;
	private Button testTwitterButton;
	private Button testFoursquareButton;
	private Button testGameInvitesButton;
	protected final static String TAG = "mainForm";
  //  private BetSquaredApplication appContext;
    
    public mainForm() {
    	super();
    }
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.windowTitle.setText("Main Menu");
        setContentView(R.layout.main);
        this.windowTitle.setText("Main Menu");
        setUpViews();
        setBottomBar(0);
        //System.out.println("Inside Create...");
        //System.setProperty("proxySet", "true");
    	//System.setProperty("proxyHost", "ftpproxy.wdc.cingular.net");
    	//System.setProperty("proxyPort", "8080");
    	
    }   //onCreate
    
    public void onResume() {
    	
		super.onResume();
		if ( getAppContext().isLoggedIn() ==0) {
			   Intent intent = new Intent(this,GameOnLoginActivity.class);
			   intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			   startActivity(intent);	
		}
    } //resume
    
    protected void setUpViews() {
		cancelButton = (Button) findViewById(R.id.cancel_button);
        foursquareOauthButton = (Button)findViewById(R.id.foursquareoauth);
        twitterOAuthButton = (Button)findViewById(R.id.twitterOAuthButton);
        getUserDetailsButton = (Button)findViewById(R.id.getuserdetails_button);
        getBetSquareDetailsButton = (Button)findViewById(R.id.getbetsuserdetails_button);
        getNearbyVenuesButton = (Button)findViewById(R.id.getnearbyvenues_button);
        getPublicGamesButton = (Button)findViewById(R.id.getpublicgames_button);
        getUserDashBoardButton=(Button)findViewById(R.id.getuserdashboard_button);
        logoutButton = (Button)findViewById(R.id.logout_button);
        userBetButton = (Button)findViewById(R.id.userBetButton); 
        testTwitterButton = (Button)findViewById(R.id.testTwitterButton);
        testFoursquareButton = (Button)findViewById(R.id.testFoursquareButton);
        testGameInvitesButton = (Button)findViewById(R.id.gameInviteButton);

      testGameInvitesButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
                testGameInvitesButtonClicked();				
			}
		});

       testFoursquareButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (!getAppContext().getUserSettings().isFoursquareAuthorized()) {
					foursquareOauthButtonClicked(v);
				}
				else testFoursquareButtonClicked();				
			}
		});

        testTwitterButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (!getAppContext().getUserSettings().isTwitterAuthorized()) {
					twitterOAuthButtonClicked();
				}
				else testTwitterButtonClicked();				
			}
		});
        userBetButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
		         userBetButtonClicked();
			}
		});
        
        twitterOAuthButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
               twitterOAuthButtonClicked();				
			}
		});
        
        logoutButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				logoutButtonClicked();
				
			}

			
		});
        getBetSquareDetailsButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
		    //   viewUserDetails();
				testApproveDeclineFriends();
			}
		});
        
        getUserDashBoardButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
               getUserDashBoardButtonClicked();
			}
		});
        
        getPublicGamesButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
		        getPublicGameButtonClicked();		
			}
		});
        
        getNearbyVenuesButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
			    getNearbyVenuesButtonClicked(); {
			    }
			}
		});
        
        getUserDetailsButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				getUserDetailsButtonClicked();
			}
		});
        cancelButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				cancelButtonClicked(v);
			}
		});
        foursquareOauthButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				foursquareOauthButtonClicked(v);
			}
		});

    } //setupViews
    protected void testGameInvitesButtonClicked() {
       	Intent intent = new Intent(this,GameOnGameInvitesListActivity.class);
		startActivity(intent);				
	}
	protected void testApproveDeclineFriends() {
       	Intent intent = new Intent(this,GameOnApproveDeclineListActivity.class);
		startActivity(intent);				
	}
	protected void testTwitterButtonClicked() {
    	Intent intent = new Intent(this,GameOnInviteFriendsTwitterListActivity.class);
		startActivity(intent);				
		
	}
	protected void userBetButtonClicked() {
    	Intent intent = new Intent(this,GameOnUserBetActivity.class);
		startActivity(intent);				
	}
	protected void twitterOAuthButtonClicked() {
     	Intent intent = new Intent(this,GameOnTwitterOAuthActivity.class);
		startActivity(intent);				
	}
	private void logoutButtonClicked() {
		boolean rv = getAppContext().logout();
		Intent intent = new Intent(this ,GameOnLoginActivity.class); 
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
	    startActivity(intent); 
	} //logoutButtonClicked
    
	protected void viewUserDetails() {
       	Intent intent = new Intent(this,GetBetSquareUserDetailsActivity.class);
		startActivity(intent);		
	} //viewUserDetails

	protected void getUserDashBoardButtonClicked() {
       	Intent intent = new Intent(this,GetUserDashBoardActivity.class);
		startActivity(intent);		
	}
	protected void testFoursquareButtonClicked() {
      	Intent intent = new Intent(this,GameOnInviteFriendsFoursquareListActivity.class);
		startActivity(intent);		
	}
    protected void getPublicGameButtonClicked() {
    	
    	Log.d(" ", "getUserDashBoardButtonClicked ");
		
    	Intent intent = new Intent(this,GetPublicGamesActivity.class);
		startActivity(intent);		
	}

	protected void getNearbyVenuesButtonClicked() {
    	Intent intent = new Intent(this,GetNearbyVenuesActivity.class);
		startActivity(intent);		}

	protected void getUserDetailsButtonClicked() {
    	Intent intent = new Intent(this,GetUserDetailsActivity.class);
		startActivity(intent);	
	}

	// finish the app 
    private void cancelButtonClicked(View v) {
    	finish();
    	
    }  //cancelButton_pressed
    
    //start foursquare oauth 
    private void foursquareOauthButtonClicked(View v) {
//		Intent intent = new Intent(this,FoursquareOauthActivity.class);
		Intent intent = new Intent(this,GameOnFoursquareAuthActivity.class);
		startActivity(intent);
    }
} //class