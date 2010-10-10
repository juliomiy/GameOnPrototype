/**
 * 
 */
package com.jittr.android;

import java.util.ArrayList;
import java.util.HashMap;

import com.jittr.android.api.betsquared.BSClientAPIAsync;
import com.jittr.android.api.betsquared.BSClientAPIImpl;
import com.jittr.android.bs.adapters.BSGetUserGamesAdapter;
import com.jittr.android.bs.dto.BSUserDetails;
import com.jittr.android.bs.dto.UserGamesDetails;

import android.os.Bundle;

/**
 * @author juliomiyares
 * @version 1.0
 * 
 */
public class GameOnUserBetsListActivity extends GameOnBaseListActivity {

	/**
	 * 
	 */
	private static final String TAG = "GameOnUserBetsListActivity";
//	private BSClientAPIAsync bsAsync;
	private BSClientAPIImpl bs;
	private BSGetUserGamesAdapter adapter;
	
	
	public GameOnUserBetsListActivity() {
		// TODO Auto-generated constructor stub
	}  //default Constructor
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String userID=null;
        setContentView(R.layout.gameonuserbetslistactivity);
        
        setUpViews();
        setBottomBar(R.id.betsButton);
    //    bsAsync = new BSClientAPIAsync();  //new
       // bsAsync.
        bs = new BSClientAPIImpl();
        userID = String.valueOf(getAppContext().getLoginID());
        HashMap<String, String> params = new HashMap();
        params.put("userid", userID);
        UserGamesDetails detail = bs.getUserGames(params);
        if (null != detail && detail.getStatus_code() == 200) {
        	adapter = new BSGetUserGamesAdapter(this,detail.getUsergames());
        	setListAdapter(adapter);
        }
	}  //OnCreate

	protected void setUpViews() {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see com.jittr.android.fs.examples.DataFetchingCallBack#dataLoaded(java.lang.Object)
	 */
	@Override
	public void dataLoaded(Object response) {
		// TODO Auto-generated method stub

	}

}
