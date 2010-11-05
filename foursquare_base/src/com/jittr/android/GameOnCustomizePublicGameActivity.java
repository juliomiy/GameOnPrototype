package com.jittr.android;

import static com.jittr.android.util.Consts.INTENT_VIEW_PUBLIC_GAME;

import java.util.ArrayList;
import java.util.HashMap;

import com.jittr.android.api.betsquared.BSClientAPIImpl;
import com.jittr.android.bs.adapters.BSBaseAdapter;
import com.jittr.android.bs.dto.Friend;
import com.jittr.android.bs.dto.Game;
import com.jittr.android.bs.dto.GameAddResponse;
import com.jittr.android.util.Consts;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

/* @author juliomiyares
 * @version 1.0
 * @date May 2010
 */
public class GameOnCustomizePublicGameActivity extends GameOnBaseListActivity {

	private RadioButton visitingTeamRadioButton;
	private RadioButton homeTeamRadioButton;
	private Button betButton;
	private ImageButton cancelButton;
    private Game game;
	private Intent callingIntent;
	private TextView eventNameTextView;
	private TextView eventDateTimeTextView;
	private RadioGroup teamRadioGroup;
	private OnCheckedChangeListener teamSelectionListener;
	protected String selectedTeam;
	private EditText wagerTypeEditText;
	private EditText wagerUnitsEditText;
	private String wagerType;
	private String wagerUnits;
	protected BSBaseAdapter <Friend> adapter;
    
	public GameOnCustomizePublicGameActivity() {
		super();
	}
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameoncustomizepublicgame);
        setUpViews();
        setBottomBar(0);  //no exclusion of bottom buttons
        adapter = new BSBaseAdapter(this ,(ArrayList) getAppContext().getFriends(),Consts.LAYOUT_SELECT_BY_CHECKEDTEXTVIEW);
        if (null != adapter) { 
        	setListAdapter(adapter);
        } else {
        	//TODO handle error messaging
        }  //if

    }   //onCreate

	protected void onResume() {
		callingIntent = super.getIntent();
	    if (callingIntent != null) {
	          game  = callingIntent.getParcelableExtra(INTENT_VIEW_PUBLIC_GAME);
	    }
	    if (null != game) {
	    	game.setTypeID(1);  //TODO Remove Temp
	    	game.setTypeName("team"); //TODO Remove Temp
			visitingTeamRadioButton.setText(game.getTeam1());
			homeTeamRadioButton.setText(game.getTeam2());
			eventNameTextView.setText(game.getEventname());
			eventDateTimeTextView.setText(game.getEventdatetime());
			
		} //if
		super.onResume();
	}  //onResume	

	protected void setUpViews() {
		setUpViews(Consts.LAYOUT_ADD_DONE);
		teamRadioGroup = (RadioGroup)findViewById(R.id.teamsRadioGroup);
        teamSelectionListener = new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {
                   RadioButton r = (RadioButton) group.findViewById(checkedId);
                   selectedTeam  = (String) r.getText();
                   Log.d(TAG,"Team selected = " + selectedTeam);
                   betButton.setEnabled(passEdits());
			}
        };
        wagerUnitsEditText = (EditText)findViewById(R.id.wagerUnitsEditText);
        wagerUnitsEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				setWagerUnits(s.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}
        	
        });
        wagerTypeEditText = (EditText)findViewById(R.id.wagerTypeEditText);
        wagerTypeEditText.setText(R.string.ducketts);
        
        wagerTypeEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
	                setWagerType(s.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        teamRadioGroup.setOnCheckedChangeListener(teamSelectionListener);
		visitingTeamRadioButton = (RadioButton)findViewById(R.id.visitingTeamRadioButton);
		homeTeamRadioButton = (RadioButton)findViewById(R.id.homeTeamRadioButton);
        eventNameTextView = (TextView)findViewById(R.id.eventNameTextView);
        eventDateTimeTextView = (TextView)findViewById(R.id.eventDateTimeTextView);
/*		cancelButton=(ImageButton)findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
                cancelButtonClicked();				
			}
		});
		*/
		betButton=(Button)findViewById(R.id.betButton);
		betButton.setEnabled(false);
		betButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
                 betButtonClickedI();
				
			}
		});
		setWagerType(getString(R.string.ducketts));
	 }  //setupViews

	public void setWagerType(String wagerType) {
		this.wagerType = wagerType;
		betButton.setEnabled(passEdits());
	}

	public void setWagerUnits(String wagerUnits) {
		this.wagerUnits = wagerUnits;
		betButton.setEnabled(passEdits());
	}

	public String getSelectedTeam() {
		return selectedTeam;
	}

	public void setSelectedTeam(String selectedTeam) {
		this.selectedTeam = selectedTeam;
	}

	public String getWagerType() {
		return wagerType;
	}

	public String getWagerUnits() {
		return wagerUnits;
	}

	private boolean passEdits() {
		if (null == wagerUnits || null == wagerType || null == selectedTeam || "".equals(selectedTeam) || !adapter.isItemSelected()) return false;
		return true;
	} //passEdits
	
	protected void betButtonClickedI() {
		HashMap<String, String> queryParams = new HashMap<String,String>();
		queryParams.put("eventname", game.getEventname());
		queryParams.put("eventdatetime", game.getEventdatetime());
		queryParams.put("wagerunits", wagerUnits);
		queryParams.put("wagertype", wagerType);
        queryParams.put("createdbyuserid",String.valueOf( getAppContext().getLoginID()));
        queryParams.put("createdbyusername",getAppContext().getUserName());
        queryParams.put("typename",game.getTypeName());
        queryParams.put("type",String.valueOf(game.getTypeID()));
        queryParams.put("teamname", selectedTeam);
        queryParams.put("publicgameid",game.getId());
     //   queryParams.put("sportname", game.getSportname());
        //grab invited Friends
        //currently returned as space delimited list 
        String selectedFriends = adapter.getSelectedKeys();
        queryParams.put("invitees", selectedFriends);

        //TOOO - convert to Async method
		BSClientAPIImpl bs = new BSClientAPIImpl();
		GameAddResponse response = bs.addGame(queryParams);
		if (null != response && response.getStatus_code().equals("200")) {
             finish();
		} else {
  		   Toast.makeText(this, "Error Creating bet " + (response != null ? response.getStatus_message() : null), Toast.LENGTH_LONG).show();
		}  //if
	}  //betButton

	protected void cancelButtonClicked() {
 		finish();
	} //cancelButton

	/* @author juliomiyares
	 * (non-Javadoc)
	 * @see android.app.ListActivity#onListItemClick(android.widget.ListView, android.view.View, int, long)
	 * @version 1.0
	 * select from list of friends to invite to a bet
	 * TODO - make more general and not tied only to the checkedTextView
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		boolean state = adapter.toggleSelection(position); //returns true if turned on, false if turned off
		CheckedTextView ctv = (CheckedTextView) v.findViewById(android.R.id.text2);
		ctv.setChecked(state);
		betButton.setEnabled(passEdits());
	} //onListItemClick

	@Override
	public void dataLoaded(Object response) {
		// TODO Auto-generated method stub
		
	}
} //class
