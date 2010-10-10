package com.jittr.android;

import java.util.ArrayList;
import java.util.HashMap;

import com.jittr.android.api.betsquared.BSClientAPIImpl;
import com.jittr.android.bs.adapters.BSBaseAdapter;
import com.jittr.android.bs.dto.Friend;
import com.jittr.android.bs.dto.GameAddResponse;
import com.jittr.android.util.Consts;

import static com.jittr.android.util.Consts.*;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
/*
 * @author juliomiyares
 * @version 1.0
 * Pass the Layout Attributes to the adapter instantiation to configure selection by the appropriate widget
 */
public final class GameOnUserBetActivity extends GameOnBaseListActivity {

	private Button betButton;
	private EditText betEditText;
	private String wagerUnits;
	private String wagerType;
	private String eventName;
	private String typeName ="user";
	private String typeID = "2";
	private EditText wagerTypeEditText;
	private EditText wagerUnitsEditText;
	protected BSBaseAdapter <Friend> adapter;


	public GameOnUserBetActivity() {
	} //default constructor

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameonuserbetlayout);
        setUpViews();
        setBottomBar(0);
        adapter = new BSBaseAdapter(this ,(ArrayList) getAppContext().getFriends(),Consts.LAYOUT_SELECT_BY_CHECKEDTEXTVIEW);
        if (null != adapter) { 
        	setListAdapter(adapter);
        } else {
        	//TODO handle error messaging
        }  //if
	} //onCreate

	private boolean passEdits() {
		if ( null == wagerType  || null == wagerUnits || null == eventName || !adapter.isItemSelected()) return false;
		return true;
	}
	protected void setUpViews() {
		super.setUpViews(Consts.LAYOUT_ADD_DONE);
		betButton = (Button)findViewById(R.id.betButton);
		betButton.setEnabled(passEdits());
		betEditText = (EditText)findViewById(R.id.betEditText);
		betEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
                  setEventName(s.toString());				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				
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
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        wagerUnitsEditText = (EditText)findViewById(R.id.wagerUnitsEditText);
        wagerUnitsEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
                  setWagerUnits(s.toString());				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        //betEditText.
		betButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
                betButtonClicked();
			}
		});
		
	}  //setUpViews

	protected void betButtonClicked() {
		HashMap<String, String> queryParams = new HashMap<String, String>();

		eventName = betEditText.getText().toString();
		wagerType = wagerTypeEditText.getText().toString();
		wagerUnits = wagerUnitsEditText.getText().toString();
		queryParams.put("eventname", eventName);
//		queryParams.put("eventdatetime", game.getEventdatetime());
		queryParams.put("wagerunits", wagerUnits);
		queryParams.put("wagertype", wagerType);
        queryParams.put("createdbyuserid",String.valueOf( getAppContext().getLoginID()));
        queryParams.put("createdbyusername",getAppContext().getUserName());
        queryParams.put("typename",BS_TYPENAME_USER);
        queryParams.put("type",String.valueOf(BS_TYPEID_USER ));
		
        //grab invited Friends
        //currently returned as space delimited list 
        String selectedFriends = adapter.getSelectedKeys();
        queryParams.put("invitees", selectedFriends);
		BSClientAPIImpl bs = new BSClientAPIImpl();
		GameAddResponse response = bs.addGame(queryParams);
		if (null != response && response.getStatus_code().equals("200")) {
             finish();
		} else {
  		   Toast.makeText(this, "Error Creating bet " + (response != null ? response.getStatus_message() : null), Toast.LENGTH_LONG).show();
		}  //if
	} //betButtonClicked

	protected void OnResume() {
	   super.onResume();
	   //adapter.
	} //OnResume

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
	}
	
	@Override
	public void dataLoaded(Object response) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return the wagerUnits
	 */
	public String getWagerUnits() {
		return wagerUnits;
	}

	/**
	 * @param wagerUnits the wagerUnits to set
	 */
	public void setWagerUnits(String wagerUnits) {
		if (null!= wagerUnits && !"".equals(wagerUnits))
		   this.wagerUnits = wagerUnits;
		betButton.setEnabled(passEdits());
	}

	/**
	 * @return the wagerType
	 */
	public String getWagerType() {
		return wagerType;
	}

	/**
	 * @param wagerType the wagerType to set
	 */
	public void setWagerType(String wagerType) {
		if (null != wagerType && !"".equals(wagerType))
		   this.wagerType = wagerType;
		betButton.setEnabled(passEdits());
	}

	/**
	 * @return the eventName
	 */
	public String getEventName() {
		return eventName;
	}

	/**
	 * @param eventName the eventName to set
	 */
	public void setEventName(String eventName) {
		if (null != eventName && !"".equals(eventName))
  		    this.eventName = eventName;
		betButton.setEnabled(passEdits());
	}
	

}  //GameOnUserBetActivity
