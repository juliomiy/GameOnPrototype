package com.jittr.android.bs.adapters;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
/*
 * @uathor juliomiyares
 * @version 1.0
 * @purpose used as layout for single view in a ListActivity
 */
public class BSListItemLayout <V> extends LinearLayout {

	private BSListViewable <V> listViewText;
	private TextView textView;
	private CheckedTextView checkedTextView;
	private CheckBox selectByCheckBox;
	private Button selectByButton;
	private int[] layoutAttributes;


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
		checkedTextView = (CheckedTextView)findViewById(android.R.id.text2);
		selectByButton = (Button)findViewById(android.R.id.button1);
		selectByCheckBox=(CheckBox)findViewById(android.R.id.checkbox);
		//textView.setText("Game/Event Item");
	}
	
	public void setSelectByCheckedTextView() {
		checkedTextView.setVisibility(View.VISIBLE);
	}
	public void setSelectByCheckBox() {
        selectByCheckBox.setVisibility(View.VISIBLE);		
	}
	public void setSelectByButton() {
		selectByButton.setVisibility(View.VISIBLE);
	}
	public void setEvent(BSListViewable <V> lvi) {
		listViewText = lvi;
		if (textView.getVisibility() == View.VISIBLE) textView.setText(listViewText.getListViewText());
		else { 
			checkedTextView.setText(listViewText.getListViewText());
			//checkedTextView.setChecked(true);
		}
	}

	public BSListViewable <V> getEvent() {
	    return listViewText;
	}

}  //class
