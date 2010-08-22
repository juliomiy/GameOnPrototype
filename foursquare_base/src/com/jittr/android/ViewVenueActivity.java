package com.jittr.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import  com.jittr.android.fs.dto.Venue;
import com.jittr.android.util.Consts;
import com.jittr.android.GameOnLocationManager;

import static com.jittr.android.util.Consts.*;
public class ViewVenueActivity extends Activity {

	private Button cancelButton;
	private Intent callingIntent;
	private Venue venue;
	private TextView venueNameTextView;
	private TextView addressTextView;
	private TextView cityStateTextView;
	private TextView latLongTextView;
	private TextView currentGeoTextView;

	public ViewVenueActivity() {
		// TODO Auto-generated constructor stub
	}
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewvenue);
        setUpViews();
    }
	
	@Override
	protected void onResume() {
	   super.onResume();
	   callingIntent = super.getIntent();
       if (callingIntent != null) {
          venue  = callingIntent.getParcelableExtra(INTENT_VIEW_VENUE);
       	  if (venue != null) {
       		  venueNameTextView.setText("Venue Name: " + venue.getName());
       		  addressTextView.setText("Address: " + venue.getAddress());
       		  cityStateTextView.setText("City,State: " + venue.getCity() + "," + venue.getState());
       		  latLongTextView.setText("Lat: " + venue.getGeolat() + " Long: " + venue.getGeolong());
       		  currentGeoTextView.setText("Current Lat/Long: " + GameOnLocationManager.getLatitude() + "/" + GameOnLocationManager.getLongitude());
       	  } //if
       } //if
	} //onResume

	private void setUpViews() {
		cancelButton = (Button)findViewById(R.id.cancelButton);
		currentGeoTextView = (TextView)findViewById(R.id.currentGeoTextView);
		venueNameTextView = (TextView)findViewById(R.id.venueNameTextView);
		addressTextView = (TextView)findViewById(R.id.addressTextView);
		cityStateTextView = (TextView)findViewById(R.id.cityStateTextView);
		latLongTextView = (TextView)findViewById(R.id.latLongTextView);
		cancelButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
	}  //setupviews

}
