package com.jittr.android;

import java.util.ArrayList;
import java.util.HashMap;

import com.jittr.android.api.betsquared.BSClientAPIImpl;
import com.jittr.android.bs.dto.GameInvite;
import com.jittr.android.bs.dto.GameInvites;
import com.jittr.android.fs.examples.DataFetchingCallBack;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public abstract class GameOnBaseListActivity extends ListActivity implements DataFetchingCallBack {
	private static final String TAG = "GameOnBaseListActivity";
	protected TextView windowTitle;
	private ImageView windowIcon;
	private Button testButton;
	  
	public GameOnBaseListActivity() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);

        setContentView(R.layout.defaultlistview); //use defaultlistview to be able to create, will be overridden by the inheriting class on their onCreate

        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.windowtitle);

        windowTitle = (TextView) findViewById(R.id.windowtitle);
       
        //icon  = (ImageView) findViewById(R.id.icon);
	}
	protected boolean setBottomBar() {
   	        testButton = (Button)findViewById(R.id.testButton);
		    testButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					  HashMap hm = new HashMap();
	                  BSClientAPIImpl bs = new BSClientAPIImpl();
	                  hm.put("userid", "110");
	                  GameInvites gameInvites = bs.getGameInvites(hm);
	                  ArrayList<GameInvite> invites = gameInvites.getGameinvites();
	                  
	                  for ( GameInvite gi :  invites) {
	                      Log.d(TAG,gi.toString());
	                	  
	                  }
	                  //for each()
	                  
				}
			  });
		      return true;
	}
}  //class
