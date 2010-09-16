package com.jittr.android.bs.adapters;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BSListItemLayout <V> extends LinearLayout {

	private BSListViewable <V> listViewText;
	private TextView textView;

	public BSListItemLayout(Context context) {
		super(context);
	}

	public BSListItemLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		textView = (TextView)findViewById(android.R.id.text1);
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
