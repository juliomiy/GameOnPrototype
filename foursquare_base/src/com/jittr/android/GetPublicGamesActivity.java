package com.jittr.android;

import java.util.ArrayList;
import java.util.HashMap;

import com.jittr.android.api.betsquared.BSClientAPIImpl;
import com.jittr.android.bs.adapters.BSGetPublicGamesAdapter;
import com.jittr.android.fs.impl.FSClientAPIImpl;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class GetPublicGamesActivity extends ListActivity {

	private BSClientAPIImpl bs;
	private BSGetPublicGamesAdapter adapter;
	private Button cancelButton;
	private Spinner teamFilterSpinner;
	private Spinner timeFilterSpinner;
	private Spinner sportFilterSpinner;
	private Button goButton;
	public GetPublicGamesActivity() {
		// TODO Auto-generated constructor stub
	}
	@SuppressWarnings("unchecked")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getpublicgames);
        
        setUpViews();
        bs = new BSClientAPIImpl("xml", "9259485368", "findme3366");
        HashMap hm = new HashMap();
        hm.put("team", "New York Jets");
        adapter = new BSGetPublicGamesAdapter(this ,(ArrayList) bs.getPublicGames(hm));
        setListAdapter(adapter);
    } //onCreate

	private void setUpViews() {
		timeFilterSpinner = (Spinner)findViewById(R.id.timeFilterSpinner);
		teamFilterSpinner = (Spinner)findViewById(R.id.teamFilterSpinner);
		sportFilterSpinner = (Spinner)findViewById(R.id.sportFilterSpinner);
		goButton = (Button)findViewById(R.id.goButton);
		goButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
                getPublicGames();
			}
		});
		cancelButton = (Button)findViewById(R.id.cancel_button);
		cancelButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
                finish();				
			}
		});
	}
	protected void getPublicGames() {
		// TODO Auto-generated method stub
		
	}

} //class
