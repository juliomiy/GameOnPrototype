package com.jittr.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameOnLoginActivity extends Activity {

	private Button cancelButton;
    private Button loginButton;
    
	public GameOnLoginActivity() {
		// TODO Auto-generated constructor stub
	}
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameonloginlayout);
        setUpViews();
    }   //onCreate

	protected void onResume() {
		super.onResume();
	}	
	private void setUpViews() {
		loginButton = (Button)findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			  login();	
			}
		});
		cancelButton = (Button)findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener( new View.OnClickListener() {
			   public void onClick(View v) {
                finish();				
			   }
 	     });   		
	}  //setUpViews

	private void login() {
		// TODO Auto-generated method stub
		finish();
	}
}  //class
