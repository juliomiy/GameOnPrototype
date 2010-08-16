package com.jittr.android;

import java.util.ArrayList;

import com.jittr.android.fs.impl.FSClientAPIImpl;
import com.jittr.android.fs.adapters.FSNearbyVenueAdapter;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;

public class GetNearbyVenuesActivity extends ListActivity {

	private Button cancelButton;
	private FSNearbyVenueAdapter adapter;
	FSClientAPIImpl fs;;
	
	public GetNearbyVenuesActivity() {
		// TODO Auto-generated constructor stub
	}
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getnearbyvenues);
        
        setUpViews();
        fs = new FSClientAPIImpl("xml", "9259485368", "findme3366");
        adapter = new FSNearbyVenueAdapter(this ,(ArrayList)fs.getNearByVenues("40.7204","-73.9933",10,""));
        setListAdapter(adapter);

        
     //   app = (GameManagerApplication)getApplication();
     //   adapter = new GameListAdapter(this, app.getCurrentGames());
     //   setListAdapter(adapter);
    }
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
