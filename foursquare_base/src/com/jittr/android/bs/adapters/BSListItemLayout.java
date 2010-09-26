package com.jittr.android.bs.adapters;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BSListItemLayout <V> extends LinearLayout {

	private BSListViewable <V> listViewText;
	private Button button;
	private TextView textView;

	public BSListItemLayout(Context context) {
		super(context);
	}  //constructor

	public BSListItemLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		textView = (TextView)findViewById(android.R.id.text1);
		button = (Button)findViewById(android.R.id.button1);
		//textView.setText("Game/Event Item");
	}
	public void setEvent(BSListViewable <V> lvi) {
		listViewText = lvi;
		textView.setText(listViewText.getListViewText());
	}

	public BSListViewable <V> getEvent() {
	    return listViewText;
	}

}
