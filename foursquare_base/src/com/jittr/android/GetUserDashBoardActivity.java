package com.jittr.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jittr.android.api.betsquared.BSClientAPIImpl;
import com.jittr.android.fs.dto.BSUserDashBoard;

public class GetUserDashBoardActivity extends Activity {

	private BSClientAPIImpl bs;
	private BSUserDashBoard dashBoard;
	private Button cancelButton;
	private TextView userIDTextView;
	private TextView totalBetsTextView;
	private TextView betsAcceptedTextView;
	private TextView betsInitiatedTextView;
	private TextView totalWinsTextView;
	private TextView totalLosesTextView;

	public GetUserDashBoardActivity() {
		// TODO Auto-generated constructor stub
	}
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getuserdashboard);
        setUpViews();
    } //onCreate
	
	private void setUpViews() {
		cancelButton = (Button)findViewById(R.id.cancelButton);
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
	        bs = new BSClientAPIImpl("xml", "9259485368", "findme3366");
            dashBoard = bs.getUserDashBoard("1");
            userIDTextView.setText("UserID: " + dashBoard.getUserid());
            totalBetsTextView.setText("Total Bets: " + dashBoard.getTotalbets());
            betsAcceptedTextView.setText("Total Bets Accepted: " + dashBoard.getTotalbetsaccepted());
            betsInitiatedTextView.setText("Bets Initiated: " + dashBoard.getTotalbetsinitiated());
            totalWinsTextView.setText("Total Wins: " + dashBoard.getTotalwins());
            totalLosesTextView.setText("Total Loses: " + dashBoard.getTotalloses());
	    } //if
    } //onResume
}  //class
