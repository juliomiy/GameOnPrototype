package com.jittr.android;

import java.util.HashMap;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.jittr.android.api.betsquared.BSClientAPIAsync;
import com.jittr.android.api.betsquared.BSClientAPIImpl;
import com.jittr.android.api.betsquared.DataFetchingCallBack;
import com.jittr.android.bs.dto.BSFriendRequests;
import com.jittr.android.bs.dto.BSUserDashBoard;
import com.jittr.android.util.Consts;

public class GetUserDashBoardActivity extends GameOnBaseActivity  {

	private BSClientAPIImpl bs;
	//private BSUserDashBoard dashBoard;
	private ImageButton cancelButton;
	private TextView userIDTextView;
	private TextView totalBetsTextView;
	private TextView betsAcceptedTextView;
	private TextView betsInitiatedTextView;
	private TextView totalWinsTextView;
	private TextView totalLosesTextView;

	public GetUserDashBoardActivity() {
        super();
	}
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getuserdashboard);
        setUpViews();
        setBottomBar(R.id.meButton);
    } //onCreate
	
	protected void setUpViews() {
		setUpViews(Consts.LAYOUT_ADD_DONE);
//		cancelButton = (ImageButton)findViewById(R.id.cancelButton);
		userIDTextView = (TextView)findViewById(R.id.userIDTextView);
		totalBetsTextView = (TextView)findViewById(R.id.totalBetsTextView);
		betsAcceptedTextView = (TextView)findViewById(R.id.betsAcceptedTextView);
		betsInitiatedTextView = (TextView)findViewById(R.id.betsInitiatedTextView);
		totalWinsTextView = (TextView)findViewById(R.id.totalWinsTextView);
		totalLosesTextView = (TextView)findViewById(R.id.totalLosesTextView);

	/*	cancelButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
		        finish();
		    }
		}); */
	} //setUpViews
    
	protected void onResume() {
	    super.onResume();
	    if (null == bs ) {
	        //Commnted by Ravi
	    	//bs = new BSClientAPIImpl("xml", "9259485368", "findme3366");
            //dashBoard = bs.getUserDashBoard(String.valueOf(getAppContext().getLoginID()));
	    	BSClientAPIAsync newbs_async_client = new BSClientAPIAsync();
	        HashMap<String, String> criteria = new HashMap<String, String>();
	        criteria.put("userid", String.valueOf(getAppContext().getLoginID()));
	        
	        
	        newbs_async_client.getUserDashBoard(criteria, this);
	        
	        
            //Below needs to be in dataLoaded Call Back
	        /*
            if (null != dashBoard) {
	            userIDTextView.setText("UserID: " + dashBoard.getUserid());
	            totalBetsTextView.setText("Total Bets: " + dashBoard.getTotalbets());
	            betsAcceptedTextView.setText("Total Bets Accepted: " + dashBoard.getTotalbetsaccepted());
	            betsInitiatedTextView.setText("Bets Initiated: " + dashBoard.getTotalbetsinitiated());
	            totalWinsTextView.setText("Total Wins: " + dashBoard.getTotalwins());
	            totalLosesTextView.setText("Total Loses: " + dashBoard.getTotalloses());

            }*/ //if
	    } //if
    } //onResume
	
	@Override
	public void dataLoaded(Object response) {
		Log.d(TAG,"Call back method Triggered, dataLoaded, now we can render ");
		if(progressDialog !=null) {
			progressDialog.dismiss();
		}
		
		if(response !=null) {
			
			BSUserDashBoard dashBoard = (BSUserDashBoard)response;
			userIDTextView.setText("UserID: " + dashBoard.getUserid());
	        totalBetsTextView.setText("Total Bets: " + dashBoard.getTotalbets());
	        betsAcceptedTextView.setText("Total Bets Accepted: " + dashBoard.getTotalbetsaccepted());
	        betsInitiatedTextView.setText("Bets Initiated: " + dashBoard.getTotalbetsinitiated());
	        totalWinsTextView.setText("Total Wins: " + dashBoard.getTotalwins());
	        totalLosesTextView.setText("Total Loses: " + dashBoard.getTotalloses());
	        
		}
		
	}
}  //class
