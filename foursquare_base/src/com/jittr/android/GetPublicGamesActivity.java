package com.jittr.android;

import static com.jittr.android.util.Consts.INTENT_VIEW_PUBLIC_GAME;

import java.util.ArrayList;
import java.util.HashMap;

import com.jittr.android.api.betsquared.BSClientAPIImpl;
import com.jittr.android.bs.adapters.BSGetPublicGamesAdapter;
import com.jittr.android.bs.dto.Game;
import com.jittr.android.fs.examples.BSClientAPIAsync;
import com.jittr.android.fs.examples.DataFetchingCallBack;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;


public class GetPublicGamesActivity extends GameOnBaseListActivity {

	private BSClientAPIImpl bs;
	private BSClientAPIAsync bsAsync;   //new
	private BSGetPublicGamesAdapter adapter;
	private ImageButton cancelButton;
	private Spinner teamFilterSpinner;
	private Spinner timeFilterSpinner;
	private Spinner sportFilterSpinner;
	private Button goButton;
	private Game game;
	private ProgressDialog progressDialog;
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
        bs = new BSClientAPIImpl();
        bsAsync = new BSClientAPIAsync();  //new
        
        HashMap hm = new HashMap();
        hm.put("team", "New York Jets");
        bsAsync.getPublicGames(hm,this);   //new
      //  adapter = new BSGetPublicGamesAdapter(this ,(ArrayList) bs.getPublicGames(hm));
      //  setListAdapter(adapter);
    } //onCreate

	private void setUpViews() {
		timeFilterSpinner = (Spinner)findViewById(R.id.timeFilterSpinner);
		teamFilterSpinner = (Spinner)findViewById(R.id.teamFilterSpinner);
		sportFilterSpinner = (Spinner)findViewById(R.id.sportFilterSpinner);
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
		setBottomBar();
	}
	protected void getPublicGamesButtonClicked() {
		  HashMap hm = new HashMap();
		//  String search = teamFilterSpinner.
	       hm.put("team", "New York Mets");
	       bsAsync.getPublicGames(hm,this);
	}
	
	@Override
    protected Dialog onCreateDialog(int id) {
		Log.d("", "Inside onCreateDialog" );
		ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait while loading...");
        dialog.setIndeterminate(true);
        dialog.setCancelable(true);
        progressDialog =dialog; 
        return dialog;
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
	@Override
	public void dataLoadCancelled() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dataLoadException(String message) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dataLoaded(Object response) {
		if(progressDialog !=null) {
			progressDialog.dismiss();
		}
		if (null != response)  { 
			adapter = new BSGetPublicGamesAdapter(this, (ArrayList) response);
            setListAdapter(  adapter);
		} //if
	}
	@Override
	public void dataLoading() {
		Log.d(TAG,"Data Loading Cacll Back Triggered");
		showDialog(1);
	}
	@Override
	public void preDataLoading() {
		// TODO Auto-generated method stub
		
	}

} //class
