package com.jittr.android;

import static com.jittr.android.util.Consts.INTENT_VIEW_PUBLIC_GAME;

import java.util.ArrayList;
import java.util.HashMap;

import com.jittr.android.api.betsquared.BSClientAPIAsync;
import com.jittr.android.api.betsquared.DataFetchingCallBack;
import com.jittr.android.bs.adapters.BSGetPublicGamesAdapter;
import com.jittr.android.bs.dto.Game;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.AdapterView.OnItemSelectedListener;

/* 
 * @author juliomiyares
 * @version 1.0
 * @purpose display available public games from go_publicgames table on host
 * use various spinner objects to filter publicgames by time, league, team
 */
public class GetPublicGamesActivity extends GameOnBaseListActivity {

	private BSClientAPIAsync bsAsync;   //new
	private BSGetPublicGamesAdapter adapter;
	private ImageButton cancelButton;
	private Spinner teamFilterSpinner;
	private Spinner timeFilterSpinner;
	private Spinner sportFilterSpinner;
	private Button goButton;
	private Game game;
	private String selectedTeam;
	private String selectedSport;
	private int timeFrame;  //today + timeframe 
	public String defaultTeamSelection="New York Mets";
	//private ProgressDialog progressDialog;
    private final String TAG = "GetPublicGamesActivity";
    
	public GetPublicGamesActivity() {
       super();
	}
	@SuppressWarnings("unchecked")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getpublicgames);
        
        setUpViews();
        setBottomBar(0);
 
        bsAsync = new BSClientAPIAsync();  //new
        
        HashMap hm = new HashMap();
        hm.put("team", "New York Jets");
        hm.put("timeframe", "0");
        bsAsync.getPublicGames(hm,this);   //new
      //  adapter = new BSGetPublicGamesAdapter(this ,(ArrayList) bs.getPublicGames(hm));
      //  setListAdapter(adapter);
    } //onCreate

	protected void setUpViews() {
		timeFilterSpinner = (Spinner)findViewById(R.id.timeFilterSpinner);
		timeFilterSpinner.setOnItemSelectedListener(new TimeOnItemSelectedListener());
		
		teamFilterSpinner = (Spinner)findViewById(R.id.teamFilterSpinner);
		teamFilterSpinner.setOnItemSelectedListener(new TeamOnItemSelectedListener());
		
		sportFilterSpinner = (Spinner)findViewById(R.id.sportFilterSpinner);
		sportFilterSpinner.setOnItemSelectedListener(new SportOnItemSelectedListener());
		goButton = (Button)findViewById(R.id.goButton);
		goButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
                getPublicGamesButtonClicked();
			}
		});
		cancelButton = (ImageButton)findViewById(R.id.cancel_button);
		cancelButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
                finish();				
			}
		});
		setBottomBar(0);
	}
	protected void getPublicGamesButtonClicked() {
		  HashMap<String,String> hm = new HashMap<String,String>();
		//  String search = teamFilterSpinner.
	       if (null != selectedSport) hm.put("league",selectedSport);
	       hm.put("team", (selectedTeam == null ? defaultTeamSelection : selectedTeam));
	       bsAsync.getPublicGames(hm,this);
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
	public void dataLoaded(Object response) {
		if(progressDialog !=null) {
			progressDialog.dismiss();
		}
		if (null != response)  { 
			adapter = new BSGetPublicGamesAdapter(this, (ArrayList) response);
            setListAdapter(  adapter);
		} //if
	}

	public class SportOnItemSelectedListener implements OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> parent, View arg1, int pos,
				long arg3) {
			setSelectedSport( parent.getItemAtPosition(pos).toString());
		}

 		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
		}
		
	}
	public class TimeOnItemSelectedListener implements OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> parent, View arg1, int pos,
				long arg3) {
        //     setSelectedTimeFrame( parent.getItemAtPosition(pos));
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
		}
		
	}
	/* Nested class acting as listener for selection from Team Spinner 
	 * 
	 */
	public class TeamOnItemSelectedListener implements OnItemSelectedListener {
		@Override
		public void onItemSelected(AdapterView<?> parent, View arg1, int pos,
				long arg3) {
			setSelectedTeam( parent.getItemAtPosition(pos).toString());
			getPublicGamesButtonClicked();
		} //onItemSelected

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
		}
	}   //Class TeamOnItemSelectedListener

	private void setSelectedTeam(String selection) {
            selectedTeam = selection;
 		
	}  //setSelectedTeam
	private void setSelectedSport(String selection) {
 		    selectedSport = selection.toLowerCase();
 		    ArrayAdapter<CharSequence> adapter = null;
			if (selectedSport.equals("nfl"))
 		         adapter = ArrayAdapter.createFromResource(
		             this,R.array.nfl, android.R.layout.simple_spinner_item);
			else if (selectedSport.equals("mlb"))
		         adapter = ArrayAdapter.createFromResource(
			             this,R.array.mlb, android.R.layout.simple_spinner_item);
			else if (selectedSport.equals("nba"))
		         adapter = ArrayAdapter.createFromResource(
			             this,R.array.nba, android.R.layout.simple_spinner_item);
				
			if (null != adapter) {
				adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		        teamFilterSpinner.setAdapter(adapter);
			}
	}  //setSelectedSport
	
	private String getSelectedSport() {
		return selectedSport;
	}
} //class
