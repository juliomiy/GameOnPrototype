/**
 * 
 */
package com.jittr.android.bs.adapters;

import java.util.HashMap;

import com.jittr.android.util.Consts;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author juliomiyares
 * @version 1.0
 * @date November 3,2010
 * @purpose list item layout class for Game/Bet Items that show up on the Game/Bets a user has been invited to
 * ListActivity. Needed a more sophisticated class then the generic one for
 * most listItem Layouts
 */
public class BSGameInviteListItemLayout<V> extends RelativeLayout {
	private BSListViewable <V> listViewText;
	private TextView eventNameTextView;
	private TextView eventDateTextView;
	private TextView creatorUserNameTextView;
	private ImageView avatarImageView;
	/**
	 * @param context
	 */
	public BSGameInviteListItemLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public BSGameInviteListItemLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public BSGameInviteListItemLayout(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		eventNameTextView = (TextView)findViewById(com.jittr.android.R.id.eventNameTextView);
		eventDateTextView = (TextView)findViewById(com.jittr.android.R.id.eventDateTextView);
		creatorUserNameTextView = (TextView)findViewById(com.jittr.android.R.id.betCreatorNameTextView);
		avatarImageView = (ImageView)findViewById(com.jittr.android.R.id.avatarImageView);
	}

	public void setEvent(BSListViewable bsListViewable) {
		listViewText = bsListViewable;
		if (null != listViewText) {
			HashMap<String,String> hm = listViewText.getListViewArray();
			if (null != hm) {
			   String value;
			   value = hm.get("eventname");
			   eventNameTextView.setText(value != null ? value : "");
			   value = hm.get("eventdatetime");
			   eventDateTextView.setText(value != null ? value : "");
			   value = hm.get("createdbyusername");
			   creatorUserNameTextView.setText(value != null ? value : "");
			   value = hm.get("leagueid");
			   int leagueID = (null != value ? Integer.parseInt(value) : 0); 
        //select appropriate league LOGO
			   switch( leagueID ){
			     case Consts.NFL_LEAGUE :
					avatarImageView.setImageResource(com.jittr.android.R.drawable.nfllogo_50x50);
			      	break;
			     case Consts.NHL_LEAGUE :
					avatarImageView.setImageResource(com.jittr.android.R.drawable.nhllogo_50x50);
			    	break;
			     case Consts.MLB_LEAGUE :
				   avatarImageView.setImageResource(com.jittr.android.R.drawable.mlblogo_30x50);
			    	break; 
			     case Consts.NBA_LEAGUE :
 				    avatarImageView.setImageResource(com.jittr.android.R.drawable.nbalogo_50x50);
			    	break; 
			     default :   
 				    avatarImageView.setImageResource(com.jittr.android.R.drawable.mlblogo_30x50);
   }  //swtich
		    }
			hm=null;
		} //if
	}
}
