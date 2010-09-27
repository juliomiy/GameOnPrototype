/**
 * 
 */
package com.jittr.android.bs.adapters;

import java.util.ArrayList;

import com.jittr.android.R;
import com.jittr.android.util.Consts;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

/**
 * @author juliomiyares
 * @version 1.0
 * @purpose - Adapter class for ListActivity
 */
public class BSBaseAdapter <L>  extends BaseAdapter {
	private ArrayList <L> activityList;
	private Context context;
	private int[] layoutAttributes;
	/**
	 * 
	 */
   public BSBaseAdapter(Context context, ArrayList <L> list, int...attributes) {
    	this.context = context;
    	activityList = list;
    	layoutAttributes = attributes;
    } //constructor

	public BSBaseAdapter() {
	} //default constructor

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		return (null == activityList) ? null : activityList.size();
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		return (null == activityList) ? null : activityList.get(position);	
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		BSListItemLayout  tli;
		if (null == convertView) {
			tli = (BSListItemLayout)View.inflate(context, R.layout.bsbaselistitemlayout,null);
			if (null != layoutAttributes) {
				for (int attribute: layoutAttributes) {
				   switch (attribute) {
                     case Consts.LAYOUT_SELECT_BY_CHECKBOX :
		    		    tli.setSelectByCheckBox();
		    		    break;
		    	     case Consts.LAYOUT_SELECT_BY_BUTTON :
		    		    tli.setSelectByButton();
		    		   break; 
				   }  //switch
				} //for
			}
		} else {
			tli = (BSListItemLayout)convertView;
		}
		tli.setEvent(  (BSListViewable) activityList.get(position));
		return tli;
	}  //getView

	public void forceReload() {
		notifyDataSetChanged();
	}
}  //class
