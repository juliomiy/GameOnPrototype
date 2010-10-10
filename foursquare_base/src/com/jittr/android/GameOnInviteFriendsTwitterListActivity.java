/**
 * 
 */
package com.jittr.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map.Entry;

import twitter4j.TwitterException;

import com.jittr.android.api.betsquared.BSClientAPIImpl;
import com.jittr.android.bs.adapters.BSBaseAdapter;
import com.jittr.android.bs.dto.BSFriendRequest;
import com.jittr.android.bs.dto.BSFriendRequests;
import com.jittr.android.bs.dto.SocialNetworkFriend;
import com.jittr.android.twitter.twitterOAuth;
import com.jittr.android.util.Consts;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author juliomiyares
 *
 */
public class GameOnInviteFriendsTwitterListActivity extends
		GameOnBaseListActivity {

	private twitterOAuth twitter;
	private BSBaseAdapter<SocialNetworkFriend> adapter;
	private TextView loggedInAsTextView;
	private Button inviteFriendsButton;
	private TextView instructionTextView;
	private ImageView networkLogoImageView;
	private static final String TAG = "GameOnInviteFriendsActivity";

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
	 	    adapter = new BSBaseAdapter<SocialNetworkFriend>(this,(ArrayList<SocialNetworkFriend>) twitter.getFriends(),Consts.LAYOUT_SELECT_BY_CHECKEDTEXTVIEW);
    	    if (null != adapter) { 
    	    	setListAdapter(adapter);
    	    } else {
    	    	//TODO Deal with Error state where adapter is null
    	    }
    	    loggedInAsTextView.setText(loggedInAsTextView.getText() + " " + getAppContext().getUserSettings().getTwitterSN());
		} //if
		
	}  //onResume
	
	protected void setUpViews() {

		super.setUpViews();
		instructionTextView = (TextView)findViewById(R.id.instructionTextView);
		instructionTextView.setText(R.string.invitetwitterfriends);
		networkLogoImageView = (ImageView)findViewById(R.id.networkLogoImageView);
		networkLogoImageView.setImageResource(R.drawable.twittericon);
		loggedInAsTextView = (TextView)findViewById(R.id.loggedInAsTextView);
        inviteFriendsButton = (Button)findViewById(R.id.inviteFriendsButton);  //will be enabled if selected
        inviteFriendsButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
                  inviteFriendsButtonClicked();				
			}
		});
	}  //setUpViews

	/* process the selected list items to invite to be Betsquared Friends
	 * TODO - change to use the Async Version of the API
	 * TODO - send Twitter Direct Message to invited Friend - may want to make this a HOST based activity
	 */
	protected void inviteFriendsButtonClicked() {
         HashMap<Integer,SocialNetworkFriend> selectedFriends = adapter.getListSelectedMap();
         HashMap<String,String> hm = new HashMap<String,String>();
         
         hm.put("operation", "invite");
         hm.put("socialnetworkname", "TWITTER");
         hm.put("socialnetworkid", String.valueOf(Consts.TWITTER_NETWORK));
         hm.put("invitetoruserid", String.valueOf( getAppContext().getLoginID()));
         hm.put("invitetorusername", getAppContext().getUserName());
         StringBuffer invites = new StringBuffer();
         
         String delimiter = "";
   		 for (Entry<Integer, SocialNetworkFriend> entry : selectedFriends.entrySet()) {
			  SocialNetworkFriend friend = (SocialNetworkFriend) entry.getValue();
			  invites.append(delimiter + "inviteeusername=" + friend.getUserName() + "|" );
			  invites.append("inviteename=" + friend.getName() );
			  delimiter="^";
		//	  queryPart.append(string);
   		 }
   		 hm.put("invites", invites.toString());
   		 BSClientAPIImpl bs = new BSClientAPIImpl();
   		 BSFriendRequests response = bs.postInvite(hm); //call Invite Host API
   		 List<BSFriendRequest> responseList = response.getRequests();   //retrieve Response
         ListIterator<BSFriendRequest> iterator = responseList.listIterator();
           while ( iterator.hasNext()) {
        	   BSFriendRequest friendResponse = iterator.next();
        	   Log.d(TAG,friendResponse.toString());
        	   if (friendResponse.getStatus_message().equals("Ok")) {
        		   try {
        			String userName = friendResponse.getUser_name();   
					if (null != userName) twitter.getTwitter4j().sendDirectMessage(userName, "Invites you to BetSquared");
				} catch (TwitterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	   } //if
           } //while
   		 //response.
         //selected.
	} //inviteFriendsButtonClicked
	
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

	/* (non-Javadoc)
	 * @see com.jittr.android.fs.examples.DataFetchingCallBack#dataLoaded(java.lang.Object)
	 */
	@Override
	public void dataLoaded(Object response) {
		// TODO Auto-generated method stub

	}

}
