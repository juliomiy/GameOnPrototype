/**
 * 
 */
package com.jittr.android;

import java.util.ArrayList;

import com.jittr.android.bs.adapters.BSBaseAdapter;
import com.jittr.android.bs.dto.SocialNetworkFriend;
import com.jittr.android.foursquare.GameOnFoursquare;
import com.jittr.android.util.Consts;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author juliomiyares
 * @version 1.0
 *
 */
public class GameOnInviteFriendsFoursquareListActivity extends
		GameOnBaseListActivity {

	private TextView loggedInAsTextView;
	private Button inviteFriendsButton;
    private GameOnFoursquare foursquare;
	private BSBaseAdapter<SocialNetworkFriend> adapter;
	private TextView instructionTextView;
	private ImageView networkLogoImageView;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameoninvitefriendslistactivity);
        appContext = (BetSquaredApplication) getAppContext();
        
        setUpViews();
        setBottomBar(0);
	} //onCreate

	protected void onResume() {
		super.onResume();
		if (null == foursquare) {
		   String token = appContext.getUserSettings().getFoursquareOAuthToken();
		   String secret = appContext.getUserSettings().getFoursquareOAuthTokenSecret();
		   foursquare = new GameOnFoursquare(appContext,Consts.FOURSQUARE_NETWORK,token,secret);	
	 	    adapter = new BSBaseAdapter<SocialNetworkFriend>(this,(ArrayList<SocialNetworkFriend>) foursquare.friendsList(),Consts.LAYOUT_SELECT_BY_CHECKEDTEXTVIEW);
    	    if (null != adapter) { 
    	    	setListAdapter(adapter);
    	    } else {
    	    	//TODO Deal with Error state where adapter is null
    	    }

		} //if
	} //onReume
	
	protected void setUpViews() {

		super.setUpViews(Consts.LAYOUT_ADD_DONE);
		instructionTextView = (TextView)findViewById(R.id.instructionTextView);
		instructionTextView.setText(R.string.invitefoursquarefriends);
		networkLogoImageView = (ImageView)findViewById(R.id.networkLogoImageView);
		networkLogoImageView.setImageResource(R.drawable.foursquareicon20x20);

		loggedInAsTextView = (TextView)findViewById(R.id.loggedInAsTextView);
        inviteFriendsButton = (Button)findViewById(R.id.inviteFriendsButton);  //will be enabled if selected
        inviteFriendsButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
                  inviteFriendsButtonClicked();				
			}
		});
	}  //setUpViews
	/* returns true if passed edits - in this case , at least one list item selected to enable the invite button
	 * 
	 */
	private boolean passEdits() {
		if (!adapter.isItemSelected()) return false;
		return true;
	} //passEdits

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		boolean state = adapter.toggleSelection(position); //returns true if turned on, false if turned off
		CheckedTextView ctv = (CheckedTextView) v.findViewById(android.R.id.text2);
		//v.findViewById(android.R.id.text2);
		ctv.setChecked(state);
	    inviteFriendsButton.setEnabled(passEdits());
	}

	/* process the selected list items to invite to be Betsquared Friends
	 * TODO - change to use the Async Version of the API
	 * TODO - send Twitter Direct Message to invited Friend - may want to make this a HOST based activity
	 */
	protected void inviteFriendsButtonClicked() {
	}
	
	/* (non-Javadoc)
	 * @see com.jittr.android.fs.examples.DataFetchingCallBack#dataLoaded(java.lang.Object)
	 */
	@Override
	public void dataLoaded(Object response) {
		// TODO Auto-generated method stub

	}

}
