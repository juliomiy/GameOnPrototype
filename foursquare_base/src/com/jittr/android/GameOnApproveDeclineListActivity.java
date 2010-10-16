/**
 * 
 */
package com.jittr.android;

import java.util.ArrayList;
import java.util.HashMap;

import com.jittr.android.api.betsquared.BSClientAPIImpl;
import com.jittr.android.bs.adapters.BSBaseAdapter;
import com.jittr.android.bs.adapters.BSBaseApproveDeclineAdapter;
import com.jittr.android.bs.dto.Friend;
import com.jittr.android.util.Consts;

import android.os.Bundle;

/**
 * @author juliomiyares
 *
 */
public class GameOnApproveDeclineListActivity extends GameOnBaseListActivity {

	private static final String TAG = "GameOnApproveDeclineListActivity";
	private BSClientAPIImpl bs;
    private BSBaseApproveDeclineAdapter<Friend> adapter;
    
	/**
	 * 
	 */
	public GameOnApproveDeclineListActivity() {
		// TODO Auto-generated constructor stub
	}

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.defaultlistview);
        appContext = (BetSquaredApplication) getAppContext();
        
        setUpViews();
        setBottomBar(0);

        bs = new BSClientAPIImpl();
        String userID = String.valueOf(getAppContext().getLoginID());
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("userid", userID);
  //      params.put("operation", "invites");
 
        ArrayList<Friend> friendsInviteList = bs.getUserFriends(params);
        if (null != friendsInviteList ) {
            	adapter = new BSBaseApproveDeclineAdapter<Friend>(this,(ArrayList<Friend>) friendsInviteList);
           	    setListAdapter(adapter);
        } //if

	} //onCreate

	protected void onResume() {
		super.onResume();
	} //onResume
	
	protected void setUpViews() {
		super.setUpViews(Consts.LAYOUT_ADD_DONE);
	} //setUpViews

	/* (non-Javadoc)
	 * @see com.jittr.android.api.betsquared.DataFetchingCallBack#dataLoaded(java.lang.Object)
	 */
	@Override
	public void dataLoaded(Object response) {
		// TODO Auto-generated method stub

	}

}
