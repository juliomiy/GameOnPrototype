package com.jittr.android;

import java.util.ArrayList;

import com.jittr.android.fs.impl.FSClientAPIImpl;
import com.jittr.android.fs.adapters.FSNearbyVenueAdapter;
import com.jittr.android.fs.dto.Venue;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import static com.jittr.android.util.Consts.*;
import com.jittr.android.GameOnLocationManager;

public class GetNearbyVenuesActivity extends ListActivity {

	private Button cancelButton;
	private FSNearbyVenueAdapter adapter;
	FSClientAPIImpl fs;
	private Venue venue;;
	
	public GetNearbyVenuesActivity() {
		// TODO Auto-generated constructor stub
	}
	@SuppressWarnings("unchecked")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getnearbyvenues);
        
        setUpViews();
        fs = new FSClientAPIImpl("xml", "9259485368", "findme3366");
        adapter = new FSNearbyVenueAdapter(this ,(ArrayList)fs.getNearByVenues( GameOnLocationManager.getLatitudeString(),GameOnLocationManager.getLongitudeString(),10,""));
        setListAdapter(adapter);
    }  //onCreate
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		venue = adapter.getItem(position);
	
		if (null != venue) {
			Log.d("Venue", venue.toString());
			Intent intent = new Intent(GetNearbyVenuesActivity.this, ViewVenueActivity.class);
			intent.putExtra(INTENT_VIEW_VENUE,venue);
			startActivity(intent);			
		}
	} //onListItem
	@Override
	protected void onResume() {
		super.onResume();
		adapter.forceReload();
		ListAdapter test = this.getListAdapter();
	    int items = test.getCount();
	}
	
	private void setUpViews() {
		cancelButton = (Button)findViewById(R.id.cancel_button);
		cancelButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
                finish();				
			}
		});
	}
}
