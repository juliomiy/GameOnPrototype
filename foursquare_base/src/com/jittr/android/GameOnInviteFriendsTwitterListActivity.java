/**
 * 
 */
package com.jittr.android;

import java.util.ArrayList;

import com.jittr.android.betsquared.GameOnSocialNetworkBase.SocialNetworkFriend;
import com.jittr.android.bs.adapters.BSBaseAdapter;
import com.jittr.android.twitter.twitterOAuth;

import android.os.Bundle;
import android.widget.TextView;

/**
 * @author juliomiyares
 *
 */
public class GameOnInviteFriendsTwitterListActivity extends
		GameOnBaseListActivity {

	twitterOAuth twitter;
	private BSBaseAdapter<SocialNetworkFriend> adapter;
	private TextView loggedInAsTextView;
	/**
	 * 
	 */
	public GameOnInviteFriendsTwitterListActivity() {
		// TODO Auto-generated constructor stub
	}
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameoninvitefriendslistactivity);
        
        setUpViews();
        setBottomBar(0);
	} //onCreate

	protected void onResume() {
		super.onResume();
		if (null == twitter) {
			twitter = new twitterOAuth(getAppContext());
	 	    adapter = new BSBaseAdapter<SocialNetworkFriend>(this,(ArrayList<SocialNetworkFriend>) twitter.getFriends());
    	    setListAdapter(adapter);
    	    loggedInAsTextView.setText(loggedInAsTextView.getText() + " " + getAppContext().getUserSettings().getTwitterSN());
		} //if
		
	}  //onResume
	
	protected void setUpViews() {

		super.setUpViews();
		loggedInAsTextView = (TextView)findViewById(R.id.loggedInAsTextView);

	}  //setUpViews
	
	/* (non-Javadoc)
	 * @see com.jittr.android.fs.examples.DataFetchingCallBack#dataLoaded(java.lang.Object)
	 */
	@Override
	public void dataLoaded(Object response) {
		// TODO Auto-generated method stub

	}

}
