/**
 * 
 */
package com.jittr.android.bs.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
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
	public BSBaseApproveDeclineAdapter adapter;
	public ArrayList<V>list;
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

	protected void declineInvite() {
         Toast.makeText(this.getContext(), "Declined " + listViewText.getListViewText(), 2000);
    //     list.remove(1);
         list.remove(listViewText);
         adapter.notifyDataSetChanged();
        // adapter.
	}

	protected void approveInvite() {
          Toast.makeText(this.getContext(), "Approved " + listViewText.getListViewText(), 2000);		
	}

	public void setEvent(BSListViewable <V> lvi) {
		listViewText = lvi;
		if (null != listViewText) textView.setText(listViewText.getListViewText());
	}
	
	public BSListViewable <V> getEvent() {
	    return listViewText;
	}

}
