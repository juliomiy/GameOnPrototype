/**
 * 
 */
package com.jittr.android.bs.adapters;

import java.util.ArrayList;
import java.util.HashMap;

import com.jittr.android.R;
import com.jittr.android.bs.dto.Friend;
import com.jittr.android.util.Consts;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * @author juliomiyares
 *
 */
public class BSBaseApproveDeclineAdapter <L> extends BaseAdapter {
	private ArrayList <L> activityList;
	private Context context;
	private int[] layoutAttributes;
	private HashMap<Integer,L> listSelection;
	private int userID;

    public BSBaseApproveDeclineAdapter(Context context, ArrayList <L> list,int userID) {
    	this.context = context;
    	activityList = list;
    	this.userID=userID;
     } //constructor

	/**
	 * 
	 */
	public BSBaseApproveDeclineAdapter() {
		// TODO Auto-generated constructor stub
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
		BSListItemApproveDeclineLayout  tli;
		if (null == convertView) {
			tli = (BSListItemApproveDeclineLayout)View.inflate(context, R.layout.bsbaselistitemapprovedecline,null);
            //TODO = not happy that I need to do the following lines of code
			tli.adapter = this;
			tli.list = activityList;
			tli.userID=userID;
			//END TODO
		/*	if (null != layoutAttributes) {
				for (int attribute: layoutAttributes) {
				   switch (attribute) {
                     case Consts.LAYOUT_SELECT_BY_CHECKBOX :
		    		    tli.setSelectByCheckBox();
		    		    break;
		    	     case Consts.LAYOUT_SELECT_BY_BUTTON :
		    		    tli.setSelectByButton();
		    		   break; 
		    	     case Consts.LAYOUT_SELECT_BY_CHECKEDTEXTVIEW :
			    		tli.setSelectByCheckedTextView();
			    	    break; 
				   }  //switch
				} //for
			} */
		} else {
			tli = (BSListItemApproveDeclineLayout)convertView;
		}

		tli.setEvent(  (BSListViewable) activityList.get(position));
		return tli;
	}  //getView

}
