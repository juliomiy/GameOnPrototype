package com.jittr.android;

import android.app.Activity;
import android.os.Bundle;

import com.jittr.android.api.betsquared.BSClientAPIImpl;
import com.jittr.android.api.betsquared.BSClientInterface;
import com.jittr.android.fs.dto.BSUserDashBoard;

public class GetUserDashBoardActivity extends Activity {

	private BSClientAPIImpl bs;
	private BSUserDashBoard dashBoard;

	public GetUserDashBoardActivity() {
		// TODO Auto-generated constructor stub
	}
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getuserdashboard);
        setUpViews();
    } //onCreate
	
	private void setUpViews() {
		
	} //setUpViews
    
	protected void onResume() {
	    super.onResume();
	    if (null == bs ) {
	        bs = new BSClientAPIImpl("xml", "9259485368", "findme3366");
            dashBoard = bs.getUserDashBoard("1");
            dashBoard.getUserid();
            dashBoard.getTotalbets();
            dashBoard.getTotalbetsaccepted();
            dashBoard.getTotalbetsinitiated();
            dashBoard.getTotalwins();
            dashBoard.getTotalloses();
	    } //if
    } //onResume
}  //class
