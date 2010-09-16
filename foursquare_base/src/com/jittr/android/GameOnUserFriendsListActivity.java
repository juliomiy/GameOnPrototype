/**
 * 
 */
package com.jittr.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jittr.android.api.betsquared.BSClientAPIImpl;
import com.jittr.android.bs.adapters.BSBaseAdapter;
import com.jittr.android.bs.adapters.BSGetUserGamesAdapter;
import com.jittr.android.bs.dto.Friend;

import android.os.Bundle;

/**
 * @author juliomiyares
 *
 */
public final class GameOnUserFriendsListActivity extends GameOnBaseListActivity {

	private static final String TAG = "GameOnUserFriendsListActivity";
	private BSClientAPIImpl bs;
	protected BSBaseAdapter <Friend> adapter;

	/**
	 * 
	 */
	public GameOnUserFriendsListActivity() {
		// TODO Auto-generated constructor stub
	}

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String userID=null;
        setContentView(R.layout.gameonuserfriendslistactivity);
        
        setUpViews();
        setBottomBar(R.id.friendsButton);
        bs = new BSClientAPIImpl();
        userID = String.valueOf(getAppContext().getLoginID());
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("userid", userID);
        List<Friend> friendsList = bs.getUserFriends(params);
        if (null != friendsList ) {
    	    adapter = new BSBaseAdapter<Friend>(this,(ArrayList<Friend>) friendsList);
    	    setListAdapter(adapter);
        } //if
	}  //onCreate

	private void setUpViews() {
		// TODO Auto-generated method stub
		
	} //setUpViews

	/* (non-Javadoc)
	 * @see com.jittr.android.fs.examples.DataFetchingCallBack#dataLoaded(java.lang.Object)
	 */
	@Override
	public void dataLoaded(Object response) {
		// TODO Auto-generated method stub

	}

}
