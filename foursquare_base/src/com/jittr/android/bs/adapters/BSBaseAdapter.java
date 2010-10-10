/**
 * 
 */
package com.jittr.android.bs.adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.jittr.android.R;
import com.jittr.android.bs.dto.Friend;
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
	private HashMap<Integer,L> listSelection;
	/**
	 * 
	 */
   public BSBaseAdapter(Context context, ArrayList <L> list, int...attributes) {
    	this.context = context;
    	activityList = list;
    	layoutAttributes = attributes;
    	listSelection = new HashMap<Integer,L>();
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
		    	     case Consts.LAYOUT_SELECT_BY_CHECKEDTEXTVIEW :
			    		tli.setSelectByCheckedTextView();
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

	/* toggle user selection using position as key.
	 * if key exists, remove , else add
	 */
	public boolean toggleSelection(int position) {
       if (null == listSelection.get(position)) {  //toggle on the selection
    	   listSelection.put(position, (L) getItem(position));
    	   return true;
       } else {
    	   listSelection.remove(position);  //toggle off the selection
    	   return false;
       } //if
	} //toggleSelection
	
	/* 
	 * @author juliomiyares
	 * @version 1.0
	 * @purpose - returns true if there are selected listItems, false otherwise
	 */
	public boolean isItemSelected() {
		if (null != listSelection && listSelection.size() > 0) return true;
        return false;
	}   //itemsSelected
	
	/* return the keys (userIDs) of the game/bet invitees
	 * TODO - make Generic instead of having to explicity 
	 * set to FRIEND Object
	 */
	public String getSelectedKeys() {
		
		if (! isItemSelected() ) return null;
		StringBuffer sb = new StringBuffer();
		//listSelection.
		for (Entry<Integer, L> entry : listSelection.entrySet()) {
			Friend value = (Friend) entry.getValue();
		    sb.append(value.getFrienduserid()  + " ");
		} //for
		sb.trimToSize();
		return sb.toString();
	}
	
	/* Return map of selected list items*/
	public HashMap<Integer,L> getListSelectedMap() {
		return listSelection;
	}
}  //class
