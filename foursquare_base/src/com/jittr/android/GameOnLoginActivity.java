package com.jittr.android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GameOnLoginActivity extends GameOnBaseActivity {

	private Button cancelButton;
    private Button loginButton;
//    private BetSquaredApplication appContext;
	private EditText userNameEditText;
	private EditText passwordEditText;
	private Button registerButton;
    
	private static boolean inRegister = false;
	
	public GameOnLoginActivity() {
         super();
	}
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // appContext = (BetSquaredApplication) getAppContext();
        setContentView(R.layout.gameonloginlayout);
        setUpViews();
    }   //onCreate

	protected void onResume() {
		super.onResume();
		if (inRegister) {
			inRegister = false;
			finish();
		}
	}	

	private void setUpViews() {
		userNameEditText = (EditText)findViewById(R.id.userNameEditText);
		passwordEditText = (EditText)findViewById(R.id.userPasswordEditText);
		
		registerButton = (Button)findViewById(R.id.registerButton);
		registerButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
              registerButtonClicked();				
			}
		});
		
		loginButton = (Button)findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
		
			@Override
			public void onClick(View v) {
			
			   loginClickButton();	
			}
		});
		cancelButton = (Button)findViewById(R.id.cancelButton);
		//TODO - if canceled from Login Screen - the entire Activity Stack should be cleared 
		//To allow application to close - otherwise endless loop occurs as you cant do much 
        cancelButton.setOnClickListener( new View.OnClickListener() {
			   public void onClick(View v) {
                finish();				
			   }
 	     });   		
	}  //setUpViews

	private void registerButtonClicked() {
		inRegister = true;
		Intent intent = new Intent(this,GameOnRegisterActivity.class);
		startActivity(intent);
	}
	
	private void loginClickButton() {
		 Toast.makeText(this, "Logging In...", Toast.LENGTH_SHORT).show();
		 String userName = userNameEditText.getText().toString().trim();
		 String password =  passwordEditText.getText().toString().trim();
		 boolean rv = getAppContext().login(userName, password);
		 
		 if (rv) finish();
	} //loginClickButton
}  //class
