package com.jittr.android.bs.adapters;

/*
 * @author juliomiyares
 * @date September 2010
 * @version 1.0
 * Layout for individal listitem - expected to work with xml layout bsbaselistiemlayout
 * 
 */
import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BSListItemLayout <V> extends LinearLayout {

	private BSListViewable <V> listViewText;
	private TextView textView;
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
		selectByButton = (Button)findViewById(android.R.id.button1);
		selectByCheckBox=(CheckBox)findViewById(android.R.id.checkbox);
		//textView.setText("Game/Event Item");
	}
	public void setSelectByCheckBox() {
        selectByCheckBox.setVisibility(View.VISIBLE);		
	}
	public void setSelectByButton() {
		selectByButton.setVisibility(View.VISIBLE);
	}
	public void setEvent(BSListViewable <V> lvi) {
		listViewText = lvi;
		textView.setText(listViewText.getListViewText());
	}

	public BSListViewable <V> getEvent() {
	    return listViewText;
	}

}  //class
