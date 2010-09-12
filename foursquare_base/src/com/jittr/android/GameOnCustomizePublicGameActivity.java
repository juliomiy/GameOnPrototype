package com.jittr.android;

import static com.jittr.android.util.Consts.INTENT_VIEW_PUBLIC_GAME;

import java.util.HashMap;

import com.jittr.android.api.betsquared.BSClientAPIImpl;
import com.jittr.android.bs.dto.Game;
import com.jittr.android.bs.dto.GameAddResponse;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class GameOnCustomizePublicGameActivity extends GameOnBaseActivity {

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
	protected CharSequence selectedTeam;
	private EditText wagerTypeEditText;
	private EditText wagerUnitsEditText;
	private String wagerType;
	private String wagerUnits;
    
	public GameOnCustomizePublicGameActivity() {
		super();
	}
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameoncustomizepublicgame);
        setUpViews();
        setBottomBar(0);  //no exclusion of bottom buttons
    }   //onCreate

	public void setWagerType(String wagerType) {
		this.wagerType = wagerType;
		betButton.setEnabled(passEdits());
	}

	public void setWagerUnits(String wagerUnits) {
		this.wagerUnits = wagerUnits;
		betButton.setEnabled(passEdits());
	}

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

	private void setUpViews() {
		teamRadioGroup = (RadioGroup)findViewById(R.id.teamsRadioGroup);
        teamSelectionListener = new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {
                   RadioButton r = (RadioButton) group.findViewById(checkedId);
                   selectedTeam  = r.getText();
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
		cancelButton=(ImageButton)findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
                cancelButtonClicked();				
			}
		});
		betButton=(Button)findViewById(R.id.betButton);
		betButton.setEnabled(false);
		betButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
                 betButtonClickedI();
				
			}
		});
	}  //setupViews

	private boolean passEdits() {
		if (null == wagerUnits || null == wagerType || null == selectedTeam || "".equals(selectedTeam)) return false;
		return true;
	} //passEdits
	
	protected void betButtonClickedI() {
		HashMap queryParams = new HashMap();
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
} //class
