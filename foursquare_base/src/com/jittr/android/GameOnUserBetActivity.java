package com.jittr.android;

import java.util.ArrayList;
import java.util.HashMap;

import com.jittr.android.api.betsquared.BSClientAPIImpl;
import com.jittr.android.bs.adapters.BSBaseAdapter;
import com.jittr.android.bs.dto.Friend;
import com.jittr.android.bs.dto.GameAddResponse;
import static com.jittr.android.util.Consts.*;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/*
 * @author juliomiyares
 * @version 1.0
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
        adapter = new BSBaseAdapter(this ,(ArrayList) getAppContext().getFriends());
        setListAdapter(adapter);
	} //onCreate

	protected void setUpViews() {
		super.setUpViews();
		betButton = (Button)findViewById(R.id.betButton);
		betEditText = (EditText)findViewById(R.id.betEditText);
        wagerTypeEditText = (EditText)findViewById(R.id.wagerTypeEditText);
        wagerUnitsEditText = (EditText)findViewById(R.id.wagerUnitsEditText);
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
	} //OnResume

	@Override
	public void dataLoaded(Object response) {
		// TODO Auto-generated method stub
		
	}
	

}  //GameOnUserBetActivity
