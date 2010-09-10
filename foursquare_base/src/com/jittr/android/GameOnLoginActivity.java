package com.jittr.android;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GameOnLoginActivity extends GameOnBaseActivity {

	private Button cancelButton;
    private Button loginButton;
//    private BetSquaredApplication appContext;
	private EditText userNameEditText;
	private String userName;
	private String password;
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
		userNameEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				 setUserName(s.toString());
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
		
		passwordEditText = (EditText)findViewById(R.id.userPasswordEditText);
        passwordEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				setPassword(s.toString());
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
		
		registerButton = (Button)findViewById(R.id.registerButton);
		registerButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
              registerButtonClicked();				
			}
		});
		
		loginButton = (Button)findViewById(R.id.loginButton);
		loginButton.setEnabled(false);
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
		if (!passEdits() ) { 
			return;
		}
		 Toast.makeText(this, "Logging In...", Toast.LENGTH_SHORT).show();
		// String userName = userNameEditText.getText().toString().trim();
		// String password =  passwordEditText.getText().toString().trim();
		 boolean rv = getAppContext().login(userName, password);
		 
		 if (rv)  { 
			 Log.d(TAG,getAppContext().getUserSettings().toString());
			 finish();
		 } else {
			 Toast.makeText(this, "Error Logging in", Toast.LENGTH_LONG).show();
			 
		 } //if
	} //loginClickButton

	public void setUserName(String userName) {
		if (null != userName )
		this.userName = userName.trim();
		loginButton.setEnabled(passEdits());
	}
	public void setPassword(String password) {
		if (null != password)
		this.password = password.trim();
		loginButton.setEnabled(passEdits());
	}
	//are we good to continue forward to login?
	private boolean passEdits() {
		if ( null == userName || null == password || "".equals(userName) || "".equals(password)) return false;
		return true;
	} //passEdits
}  //class
