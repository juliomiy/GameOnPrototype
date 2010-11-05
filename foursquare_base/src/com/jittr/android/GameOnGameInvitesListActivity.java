/**
 * 
 */
package com.jittr.android;

import java.util.HashMap;

import com.jittr.android.api.betsquared.BSClientAPIImpl;
import com.jittr.android.bs.adapters.BSBaseAdapter;
import com.jittr.android.bs.adapters.BSGameInviteAdapter;
import com.jittr.android.bs.dto.GameInvites;
import com.jittr.android.util.Consts;

import android.os.Bundle;

/**
 * @author juliomiyares
 *
 */
public class GameOnGameInvitesListActivity extends GameOnBaseListActivity {
	private static final String TAG = "GameOnGameInvitesListActivity";
//    private BSBaseAdapter<GameInvites> adapter; 
    private BSGameInviteAdapter<GameInvites> adapter; 
	/**
	 * 
	 */
	public GameOnGameInvitesListActivity() {
		// TODO Auto-generated constructor stub
	}
	@Override
    public void onCreate(Bundle savedInstanceState) {

	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.defaultlistview);
	    appContext = (BetSquaredApplication) getAppContext();
	    
	    setUpViews();
	    setBottomBar(0);
        BSClientAPIImpl bs = new BSClientAPIImpl();
        String userID = String.valueOf(getAppContext().getUserIDString());
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("userid", userID);
        GameInvites gi = bs.getGameInvites(params);
        
        //adapter = new BSBaseAdapter(this,gi.getGameinvites(),Consts.LAYOUT_SELECT_BY_TEXTVIEW);
        adapter = new BSGameInviteAdapter(this,gi.getGameinvites(),Consts.LAYOUT_SELECT_BY_TEXTVIEW);
        if (null != adapter) { 
        	setListAdapter(adapter);
        } else {
        	//TODO handle error messaging
        }  //if

	}
	protected void onResume() {
		super.onResume();
	} //onResume
	
	protected void setUpViews() {
		super.setUpViews(Consts.LAYOUT_ADD_DONE,Consts.LAYOUT_GAMEONGAMEINVITES_LISTACTIVITY);
	} //setUpViews

	/* (non-Javadoc)
	 * @see com.jittr.android.api.betsquared.DataFetchingCallBack#dataLoaded(java.lang.Object)
	 */
	@Override
	public void dataLoaded(Object response) {
		// TODO Auto-generated method stub

	}

}
