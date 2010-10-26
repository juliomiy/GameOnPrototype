/**
 * 
 */
package com.jittr.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.jittr.android.api.betsquared.BSClientAPIImpl;
import com.jittr.android.bs.adapters.BSBaseAdapter;
import com.jittr.android.bs.adapters.BSBaseApproveDeclineAdapter;
import com.jittr.android.bs.dto.BSFriendRequests;
import com.jittr.android.bs.dto.Friend;
import com.jittr.android.bs.dto.SocialNetworkFriend;
import com.jittr.android.util.Consts;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author juliomiyares
 * @version 1.0
 *
 */
public class GameOnInviteFriendsBetsquareListActivity extends
		GameOnBaseListActivity {

	private static final String TAG = "GameOnInviteFriendsBetsquareListActivity";
	private BSClientAPIImpl bs;
	private TextView instructionTextView;
	private ImageView networkLogoImageView;
	private Button inviteFriendsButton;
	private BSBaseAdapter<Friend> adapter;
	private EditText searchQueryEditText ;
	private TextView loggedInAsTextView;

	/**
	 * 
	 */
	public GameOnInviteFriendsBetsquareListActivity() {
		// TODO Auto-generated constructor stub
	}

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameoninvitefriendslistactivity);
        appContext = (BetSquaredApplication) getAppContext();
        setUpViews();
        setBottomBar(0);
        bs = new BSClientAPIImpl();
        String userID = String.valueOf(getAppContext().getLoginID());
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("userid", userID);
        params.put("operation", "search");
        params.put("query","julio");
 
        ArrayList<Friend> friendsInviteList = bs.searchBetsquaredUsers(params);
        if (null != friendsInviteList ) {
            	adapter = new BSBaseAdapter<Friend>(this,(ArrayList<Friend>) friendsInviteList,Consts.LAYOUT_SELECT_BY_CHECKEDTEXTVIEW);
           	    setListAdapter(adapter);
        } //if
	    loggedInAsTextView.setText(loggedInAsTextView.getText() + " " + getAppContext().getUserSettings().getUserName());

	} //onCreate

	protected void setUpViews() {
		super.setUpViews(Consts.LAYOUT_ADD_DONE);
		LinearLayout searchLinearLayout = (LinearLayout)findViewById(R.id.searchLayout);
		if (null != searchLinearLayout) searchLinearLayout.setVisibility(View.VISIBLE);
		searchQueryEditText =(EditText)findViewById(R.id.searchQueryEditText);
		searchQueryEditText.setVisibility(View.VISIBLE);
		instructionTextView = (TextView)findViewById(R.id.instructionTextView);
		instructionTextView.setText(R.string.invitebetsquaredfriends);
		loggedInAsTextView = (TextView)findViewById(R.id.loggedInAsTextView);
		networkLogoImageView = (ImageView)findViewById(R.id.networkLogoImageView);
		networkLogoImageView.setImageResource(R.drawable.betsquaredicon_25x25);
        inviteFriendsButton = (Button)findViewById(R.id.inviteFriendsButton);  //will be enabled if selected
        inviteFriendsButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
                  inviteFriendsButtonClicked();				
			}
		});

	} //setUpViews
	
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
         HashMap<Integer,Friend> selectedFriends = adapter.getListSelectedMap();
         HashMap<String,String> hm = new HashMap<String,String>();
         
         hm.put("operation", "invite");
         hm.put("socialnetworkname", "BETSQUARED");
         hm.put("socialnetworkid", String.valueOf(Consts.BETSQUARED_NETWORK));
         hm.put("invitetoruserid", String.valueOf( getAppContext().getLoginID()));
         hm.put("invitetorusername", getAppContext().getUserName());
         StringBuffer invites = new StringBuffer();
         
         String delimiter = "";
   		 for (Entry<Integer, Friend> entry : selectedFriends.entrySet()) {
			  Friend friend = (Friend) entry.getValue();
			  invites.append(delimiter + "inviteeusername=" + friend.getFriendusername() + "|" );
			  invites.append("inviteebsuserid=" + friend.getFrienduserid() );
			  invites.append("inviteename=" + friend.getFriendname() );
			  delimiter="^";
		//	  queryPart.append(string);
  		 }  //for each
         invites.trimToSize();
   		 hm.put("invites", invites.toString());
   		 BSClientAPIImpl bs = new BSClientAPIImpl();
   		 BSFriendRequests response = bs.postInvite(hm); //call Invite Host API
   		 hm = null;
   		 selectedFriends=null;
	} //inviteFriendsButtonClicked
         /* (non-Javadoc)
	 * @see com.jittr.android.api.betsquared.DataFetchingCallBack#dataLoaded(java.lang.Object)
	 */
	@Override
	public void dataLoaded(Object response) {
		// TODO Auto-generated method stub

	}

}
