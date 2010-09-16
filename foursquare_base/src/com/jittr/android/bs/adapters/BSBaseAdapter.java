/**
 * 
 */
package com.jittr.android.bs.adapters;

import java.util.ArrayList;

import com.jittr.android.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * @author juliomiyares
 * @version 1.0
 */
public class BSBaseAdapter <L>  extends BaseAdapter {
	private ArrayList <L> activityList;
	private Context context;
	/**
	 * 
	 */
   public BSBaseAdapter(Context context, ArrayList <L> list) {
    	this.context = context;
    	activityList = list;
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
			tli = (BSListItemLayout)View.inflate(context, R.layout.bsbaselistitemlayout, null);
		} else {
			tli = (BSListItemLayout)convertView;
		}
		tli.setEvent(  (BSListViewable) activityList.get(position));
		return tli;
	}

	public void forceReload() {
		notifyDataSetChanged();
	}
}  //class
