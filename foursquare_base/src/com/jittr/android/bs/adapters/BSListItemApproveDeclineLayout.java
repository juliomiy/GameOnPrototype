/**
 * 
 */
package com.jittr.android.bs.adapters;

import java.util.ArrayList;
import java.util.HashMap;

import com.jittr.android.api.betsquared.BSClientAPIImpl;
import com.jittr.android.bs.dto.Friend;
import com.jittr.android.util.Consts;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author juliomiyares
 * @version 1.0
 * @param <V>
 * @date october 16,2010
 * @purpose - listitem for approving/declining
 * generally used for invite operations - friends/games(bets)
 */
public class BSListItemApproveDeclineLayout<V> extends LinearLayout {

	private BSListViewable <V> listViewText;
	private TextView textView;
	private Button approveButton;
	private Button declineButton;
	//TODO - bad form that I have to include these pointers here
	//TODO should be able to surface the list item interaction at the activity level
	public BSBaseApproveDeclineAdapter<V> adapter;
	public ArrayList<V>list;
	public int userID;
	/**
	 * @param context
	 */
	public BSListItemApproveDeclineLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	} //constructor

	/**
	 * @param context
	 * @param attrs
	 */
	public BSListItemApproveDeclineLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}  //constructor
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		textView = (TextView)findViewById(android.R.id.text1);
		approveButton = (Button)findViewById(android.R.id.button1);
		approveButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				approveInvite();
			}
		});
		declineButton = (Button)findViewById(android.R.id.button2);
		declineButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
               declineInvite();				
			}
		});
	}

	/* Decline a Betsquare Friend  invite 
	 * TODO - move out of this class
	 */
	protected void declineInvite() {
		 boolean rv = false;
		 BSClientAPIImpl bs = new BSClientAPIImpl();
		 rv = bs.processFriendInvite((Friend) listViewText,Consts.FRIEND_INVITE_DECLINE,userID);
         if (rv) {
		     list.remove(listViewText);
             adapter.notifyDataSetChanged();
         } //if
        // adapter.
	}  //declineInvite

	/* Approve a Betsquare Friend Invite
	 * TODO - move out of this class
	 */
	protected void approveInvite() {
		 boolean rv = false;
		 BSClientAPIImpl bs = new BSClientAPIImpl();
		 rv = bs.processFriendInvite((Friend) listViewText,Consts.FRIEND_INVITE_APPROVE,userID);
         if (rv) {
		     list.remove(listViewText);
             adapter.notifyDataSetChanged();
         } //if
	} //approveInvite

	public void setEvent(BSListViewable <V> lvi) {
		listViewText = lvi;
		if (null != listViewText) textView.setText(listViewText.getListViewText());
	}
	
	public BSListViewable <V> getEvent() {
	    return listViewText;
	}

}
