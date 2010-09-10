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

import com.jittr.android.api.betsquared.BSClientAPIImpl;
import com.jittr.android.bs.dto.BSUserDashBoard;
import com.jittr.android.fs.examples.BSClientAPIAsync;
import com.jittr.android.fs.examples.DataFetchingCallBack;

public class GetUserDashBoardActivity extends GameOnBaseActivity implements DataFetchingCallBack {

	private BSClientAPIImpl bs;
	//private BSUserDashBoard dashBoard;
	private ImageButton cancelButton;
	private TextView userIDTextView;
	private TextView totalBetsTextView;
	private TextView betsAcceptedTextView;
	private TextView betsInitiatedTextView;
	private TextView totalWinsTextView;
	private TextView totalLosesTextView;
	
	ProgressDialog mdialog;
	
	public GetUserDashBoardActivity() {
        super();
	}
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getuserdashboard);
        setUpViews();
    } //onCreate
	
	private void setUpViews() {
		cancelButton = (ImageButton)findViewById(R.id.cancelButton);
		userIDTextView = (TextView)findViewById(R.id.userIDTextView);
		totalBetsTextView = (TextView)findViewById(R.id.totalBetsTextView);
		betsAcceptedTextView = (TextView)findViewById(R.id.betsAcceptedTextView);
		betsInitiatedTextView = (TextView)findViewById(R.id.betsInitiatedTextView);
		totalWinsTextView = (TextView)findViewById(R.id.totalWinsTextView);
		totalLosesTextView = (TextView)findViewById(R.id.totalLosesTextView);

		cancelButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
		        finish();
		    }
		});
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
	
	//Following added by ravi
	@Override
    protected Dialog onCreateDialog(int id) {
		Log.d("", "Inside onCreateDialog" );
		ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait while loading...");
        dialog.setIndeterminate(true);
        dialog.setCancelable(true);
        mdialog =dialog; 
        return dialog;
    }
	
	
	@Override
	public void dataLoadCancelled() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dataLoadException(String message) {
		// TODO Auto-generated method stub
		System.out.println("Call back method Triggered, dataLoadException, Show exeption "+message);
		/*
		//Dismiss Current Progress bar Dialog
		if(mdialog !=null) {
			System.out.println("mdialog ...");
			mdialog.dismiss();
		}
		//Show Exception a Toaster Notification / or show a dialogue message..This wil be removed, just testing pupose
		Context context = getApplicationContext();
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(context, message, duration);
		toast.show();
		*/
				
	}
	@Override
	public void dataLoaded(Object response) {
		// TODO Auto-generated method stub
		System.out.println("Call back method Triggered, dataLoaded, now we can render ");
		if(mdialog !=null) {
			mdialog.dismiss();
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
	@Override
	public void dataLoading() {
		// TODO Auto-generated method stub
		System.out.println("Data Loading Cacll Back Triggered");
		showDialog(1);
		
	}
	@Override
	public void preDataLoading() {
		// TODO Auto-generated method stub
		System.out.println("Pre Data Loading Cacll Back Triggered");
	}
}  //class
