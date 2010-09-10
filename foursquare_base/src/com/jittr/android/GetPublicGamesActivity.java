package com.jittr.android;

import static com.jittr.android.util.Consts.INTENT_VIEW_PUBLIC_GAME;

import java.util.ArrayList;
import java.util.HashMap;

import com.jittr.android.api.betsquared.BSClientAPIImpl;
import com.jittr.android.bs.adapters.BSGetPublicGamesAdapter;
import com.jittr.android.bs.dto.Game;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

public class GetPublicGamesActivity extends GameOnBaseListActivity {

	private BSClientAPIImpl bs;
	private BSGetPublicGamesAdapter adapter;
	private Button cancelButton;
	private Spinner teamFilterSpinner;
	private Spinner timeFilterSpinner;
	private Spinner sportFilterSpinner;
	private Button goButton;
	private Game game;
	
	public GetPublicGamesActivity() {
       super();
	}
	@SuppressWarnings("unchecked")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getpublicgames);
        
        setUpViews();
        bs = new BSClientAPIImpl();
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
		setBottomBar();
	}
	protected void getPublicGames() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		game = adapter.getItem(position);
	
		if (null != game) {
			Log.d("Game", game.toString());
		    Intent intent = new Intent(GetPublicGamesActivity.this, GameOnCustomizePublicGameActivity.class);
		    intent.putExtra(INTENT_VIEW_PUBLIC_GAME,game);
		 	startActivity(intent);			
		}
	} //onListItem

} //class
