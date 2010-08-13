package com.jittr.android.fs.adapters;

import com.jittr.android.fs.dto.Venue;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class VenueListItemLayout extends LinearLayout {

	private Venue venue;
	private TextView textView;
	public VenueListItemLayout(Context context) {
		super(context);
	}

	public VenueListItemLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		textView = (TextView)findViewById(android.R.id.text1);
		textView.setText("Venue Item");
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
		textView.setText(venue.getName());
	}

	public Venue getVenue() {
	    return venue;
	}
}
