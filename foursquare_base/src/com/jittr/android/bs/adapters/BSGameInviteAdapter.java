/**
 * 
 */
package com.jittr.android.bs.adapters;

import java.util.ArrayList;

import com.jittr.android.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author juliomiyares
 * @param <L>
 *
 */
public class BSGameInviteAdapter<L> extends BSBaseAdapter<L> {

	  public BSGameInviteAdapter(Context context, ArrayList <L> list, int...attributes) {
           super(context,list,attributes);
	  }  //constructor
	  
	  public BSGameInviteAdapter() {
		  
	  }  //constructor

      @Override 
	  public View getView(int position, View convertView, ViewGroup arg2) {
		  BSGameInviteListItemLayout  tli;
		  if (null == convertView) {
				tli = (BSGameInviteListItemLayout)View.inflate(context, R.layout.bsgameinvitelistitemlayout,null);

		  } else {
				tli = (BSGameInviteListItemLayout)convertView;
			  //if
		  }
		  tli.setEvent(  (BSListViewable) activityList.get(position));
		  return tli;
  	  }  //getView
}  //class