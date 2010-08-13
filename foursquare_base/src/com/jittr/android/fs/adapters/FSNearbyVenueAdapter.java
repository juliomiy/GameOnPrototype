package com.jittr.android.fs.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.jittr.android.R;
import com.jittr.android.fs.dto.Venue;

/* Adapter to manage the ArrayList of Venues returned - by Nearby Venues
 * 
 */
public class FSNearbyVenueAdapter extends BaseAdapter {

	private ArrayList<Venue> venues;
	private Context context;
	
	public FSNearbyVenueAdapter(Context context, ArrayList<Venue> venues ) {
	     this.venues = venues;
	     this.context=context;
	}

	@Override
	public int getCount() {
		int size = venues.size();
		return venues.size();
	}

	@Override
	public Venue getItem(int position) {
		return (null == venues) ? null : venues.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		VenueListItemLayout tli;
		if (null == convertView) {
			tli = (VenueListItemLayout)View.inflate(context, R.layout.venuelistitem, null);
		} else {
			tli = (VenueListItemLayout)convertView;
		}
		tli.setVenue(venues.get(position));
		return tli;	
	}

	public void forceReload() {
		notifyDataSetChanged();
	}

}
