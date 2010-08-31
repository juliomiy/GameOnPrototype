package com.jittr.android;

import static com.jittr.android.util.Consts.INTENT_VIEW_PUBLIC_GAME;

import com.jittr.android.bs.dto.Game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class GameOnCustomizePublicGameActivity extends GameOnBaseActivity {

	private RadioButton visitingTeamRadioButton;
	private RadioButton homeTeamRadioButton;
	private Button betButton;
	private Button cancelButton;
    private Game game;
	private Intent callingIntent;
	private TextView eventNameTextView;
	private TextView eventDateTimeTextView;
    
	public GameOnCustomizePublicGameActivity() {
		super();
	}
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameoncustomizepublicgame);
        setUpViews();
    }   //onCreate

	protected void onResume() {
		callingIntent = super.getIntent();
	    if (callingIntent != null) {
	          game  = callingIntent.getParcelableExtra(INTENT_VIEW_PUBLIC_GAME);
	    }
	    if (null != game) {
			visitingTeamRadioButton.setText(game.getTeam1());
			homeTeamRadioButton.setText(game.getTeam2());
			eventNameTextView.setText(game.getEventname());
			eventDateTimeTextView.setText(game.getEventdatetime());
		} //if
		super.onResume();
	}  //onResume	

	private void setUpViews() {
		visitingTeamRadioButton = (RadioButton)findViewById(R.id.visitingTeamRadioButton);
		homeTeamRadioButton = (RadioButton)findViewById(R.id.homeTeamRadioButton);
        eventNameTextView = (TextView)findViewById(R.id.eventNameTextView);
        eventDateTimeTextView = (TextView)findViewById(R.id.eventDateTimeTextView);
		cancelButton=(Button)findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
                cancelButtonClicked();				
			}
		});
		betButton=(Button)findViewById(R.id.betButton);
		betButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
                 betButtonClickedI();
				
			}
		});
	}  //setupViews

	protected void betButtonClickedI() {
        finish();		
	}

	protected void cancelButtonClicked() {
 
		finish();
	}
} //class
