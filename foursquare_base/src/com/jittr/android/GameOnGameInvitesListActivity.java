/**
 * 
 */
package com.jittr.android;

import static com.jittr.android.util.Consts.INTENT_VIEW_PUBLIC_GAME;
import static com.jittr.android.util.Consts.INTENT_ACCEPT_DECLINE_BET;
import static com.jittr.android.util.Consts.INTENT_VIEW_GAME;

import java.util.HashMap;

import com.jittr.android.api.betsquared.BSClientAPIImpl;
import com.jittr.android.bs.adapters.BSBaseAdapter;
import com.jittr.android.bs.adapters.BSGameInviteAdapter;
import com.jittr.android.bs.dto.Game;
import com.jittr.android.bs.dto.GameInvite;
import com.jittr.android.bs.dto.GameInvites;
import com.jittr.android.util.Consts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * @author juliomiyares
 *
 */
public class GameOnGameInvitesListActivity extends GameOnBaseListActivity {
	private static final String TAG = "GameOnGameInvitesListActivity";
//    private BSBaseAdapter<GameInvites> adapter; 
    private BSGameInviteAdapter<GameInvites> adapter; 
    private ListView listView;
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
            listView = (ListView)findViewById(android.R.id.list);
            listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				 processSelection(position);
			}
		});
        } else {
        	//TODO handle error messaging
        }  //if

	}
	
	//process List Selection - boolean , true , successfully invoke, false, not invoked
	protected boolean processSelection(int position) {
		 GameInvite gi = (GameInvite) adapter.getItem(position);
         Game game = null;
		 if (null != gi) {
  		    game = new Game(gi);
		    Intent intent = new Intent(this, GameOnCustomizePublicGameActivity.class);

		    intent.putExtra(INTENT_VIEW_PUBLIC_GAME,game);
		    intent.putExtra(INTENT_VIEW_GAME, INTENT_ACCEPT_DECLINE_BET);
		    intent.putExtra("WAGER_AMOUNT", gi.getWagerunitsInt());
		    
		 	startActivityForResult(intent, position);					 } //if
			// When clicked, show a toast with the TextView text
		 Toast.makeText(getApplicationContext(), gi.getEventname(), Toast.LENGTH_SHORT).show();
		 return true;
	} //processSelection
	
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
