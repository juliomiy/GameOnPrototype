package com.jittr.android;

import com.jittr.android.api.betsquared.BSClientAPIImpl;
import com.jittr.android.api.betsquared.BSClientInterface;
import com.jittr.android.bs.dto.UserAddResponse;

import java.util.HashMap;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GameOnRegisterActivity extends GameOnBaseActivity {

	private EditText userNameEditText;
	private EditText passwordEditText;
	private Button registerButton;
	private Button cancelButton;
	private EditText firstNameEditText;
	private EditText lastNameEditText;
	private EditText emailEditText;
	private EditText verifyPasswordEditText;

	public GameOnRegisterActivity() {
        super();
	}  //constructor

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // appContext = (BetSquaredApplication) getAppContext();
        setContentView(R.layout.gameonregisterlayout);
        setUpViews();
    }   //onCreate

	protected void onResume() {
		super.onResume();
	}  //onResume	

	private void setUpViews() {
		userNameEditText = (EditText)findViewById(R.id.userNameEditText);
		firstNameEditText = (EditText)findViewById(R.id.firstNameEditText);
		lastNameEditText = (EditText)findViewById(R.id.lastNameEditText);
		emailEditText = (EditText)findViewById(R.id.emailEditText);

		passwordEditText = (EditText)findViewById(R.id.passwordEditText);
//		verifyPasswordEditText = (EditText)findViewById(R.id.verifyPasswordEditText);
		
		registerButton = (Button)findViewById(R.id.registerButton);
		registerButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			/* Register user - if successful, need to make the calling activity know so it can drop the 
			 * login challenge
			 */
			public void onClick(View v) {
			   registerButtonClicked();	
			}
		});
		cancelButton = (Button)findViewById(R.id.cancelButton);
		cancelButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				cancelButtonClicked();
			}
		});
	}  //setUpViews

	/* Register a new user on Host - if successful, register the user on the handset device and login with the provided 
	 * credentials */
	protected void registerButtonClicked() {
		HashMap hm = new HashMap();
		String newUserName = userNameEditText.getText().toString();
		String password = passwordEditText.getText().toString();
		String firstName = firstNameEditText.getText().toString();
		String lastName = lastNameEditText.getText().toString();
		String email = emailEditText.getText().toString();
		
		hm.put( (String) "newusername", (String) newUserName);
		hm.put("password", password);
		if (! "".equals(firstName))
			hm.put("firstname", firstName);
        if (! "".equals(lastName))
		    hm.put("lastname", lastName);
        if (! "".equals(email))
		    hm.put("email", email);
		
		BSClientAPIImpl bs = new BSClientAPIImpl("bs",newUserName,password);
		if (null != bs) {
		   UserAddResponse newUser = bs.addUser(hm);
		  // if ((null != newUser) && (newUser.getStatus_code() == "200")) {
			  int newUserID = Integer.parseInt(newUser.getUserid());
			  getAppContext().registerNewUser(newUserID,newUserName,password,null,null);
		   } //if
		//} //if
        finish();		
	}

	protected void cancelButtonClicked() {
          finish();		
	}
} //class
