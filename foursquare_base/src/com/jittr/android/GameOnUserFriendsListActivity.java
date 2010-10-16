/**
 * 
 */
package com.jittr.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jittr.android.api.betsquared.BSClientAPIImpl;
import com.jittr.android.bs.adapters.BSBaseAdapter;
import com.jittr.android.bs.dto.Friend;
import com.jittr.android.util.Consts;

import android.os.Bundle;
import android.util.Log;

/**
 * @author juliomiyares
 * @version 1.0
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
        ArrayList<Friend> friendsList = bs.getUserFriends(params);
        
        if (null != friendsList ) {
     //       adapter = new BSBaseAdapter(this ,(ArrayList) getAppContext().getFriends(),Consts.LAYOUT_SELECT_BY_CHECKEDTEXTVIEW);
        	appContext.updateBetsquaredFriends( friendsList);
    	    adapter = new BSBaseAdapter<Friend>(this,(ArrayList<Friend>) friendsList,Consts.LAYOUT_SELECT_BY_CHECKEDTEXTVIEW);
    	    setListAdapter(adapter);
        } //if
        //TEST CODE - remove
        HashMap<String,String> testhm = new HashMap<String,String>();
        testhm.put("operation", "invites");
        testhm.put("userid", userID);
        ArrayList<Friend> testlist = bs.getFriendInvites(testhm);
        Log.d(TAG,"test");
	}  //onCreate

	protected void setUpViews() {
		super.setUpViews(Consts.LAYOUT_ADD_DONE);
	} //setUpViews

	/* (non-Javadoc)
	 * @see com.jittr.android.fs.examples.DataFetchingCallBack#dataLoaded(java.lang.Object)
	 */
	@Override
	public void dataLoaded(Object response) {
		// TODO Auto-generated method stub

	}

}
