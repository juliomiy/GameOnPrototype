package com.jittr.android;

import com.facebook.android.Facebook;
import com.jittr.android.api.betsquared.BSClientAPIImpl;
import com.jittr.android.bs.dto.UserAddResponse;

import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.LoginFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/*
 * 
 */
public class GameOnRegisterActivity extends GameOnBaseActivity {

	private EditText userNameEditText;
	private EditText passwordEditText;
	private Button registerButton;
	private Button cancelButton;
	private EditText firstNameEditText;
	private EditText lastNameEditText;
	private EditText emailEditText;
	private EditText verifyPasswordEditText;

	private String userName;
	private String password;
	private String passwordVerify;
	private CheckBox facebookCheckBox;
	private CheckBox twitterCheckBox;
	private CheckBox foursquareCheckBox;
	private Facebook facebook;
	
	public GameOnRegisterActivity() {
        super();
	}  //constructor

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameonregisterlayout);
        setUpViews();
    }   //onCreate

	protected void onResume() {
		super.onResume();
	}  //onResume	

	private void setUpViews() {
		facebookCheckBox = (CheckBox)findViewById(R.id.facebookCheckBox);
		twitterCheckBox = (CheckBox)findViewById(R.id.twitterCheckBox);
		foursquareCheckBox = (CheckBox)findViewById(R.id.foursquareCheckBox);
		
		userNameEditText = (EditText)findViewById(R.id.userNameEditText);
	    userNameEditText.setFilters(new UserNameInputFilter().setUpFilter());
		
		userNameEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				setUserName(s.toString());
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
		firstNameEditText = (EditText)findViewById(R.id.firstNameEditText);
		lastNameEditText = (EditText)findViewById(R.id.lastNameEditText);
		emailEditText = (EditText)findViewById(R.id.emailEditText);

		passwordEditText = (EditText)findViewById(R.id.passwordEditText);
		passwordEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
                setPassword(s.toString());				
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
//		verifyPasswordEditText = (EditText)findViewById(R.id.verifyPasswordEditText);
		
		registerButton = (Button)findViewById(R.id.registerButton);
		registerButton.setEnabled(false);
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
		
		BSClientAPIImpl bs = new BSClientAPIImpl();
		if (null == bs ) return;  //TODO Deal with error
		UserAddResponse newUser = bs.addUser(hm);  //attempt to add New User to host
		if (null == newUser) return;  //TODO Deal with Error 
        Log.d(TAG,newUser.toString());
        
        if (newUser.getStatus_code().equals("200")) {
	       int newUserID = Integer.parseInt(newUser.getUserid());
	       getAppContext().registerNewUser(newUserID,newUserName,password,firstName,lastName,email);
        } else {
            if (newUser.getStatus_code().equals("410")) {
        	   Toast.makeText(this, newUserName + " already taken. Try something else", Toast.LENGTH_LONG).show();
        	   
            } else {
     		   Toast.makeText(this, "Error Registering " + newUser.getStatus_message() , Toast.LENGTH_LONG).show();
            } //else
        	return;
        } //if
		if (twitterCheckBox.isChecked()) {
		    authorizeTwitter();
	     }
		if (facebookCheckBox.isChecked()) {
		    authorizeFacebook();
	     }
		if (foursquareCheckBox.isChecked()) {
		    authorizeFoursquare();
	     }
         finish();
 	}  //registerButton
	

	public void setUserName(String userName) {
		if (null != userName && !"".equals(userName.trim()))
		   this.userName = userName;
		registerButton.setEnabled(passEdits());
	}

	private boolean passEdits() {
		if (null == userName || null == password || "".equals(userName) || "".equals(password))
			return false;
		return true;
	}  //passEdits

	public void setPassword(String password) {
		if (null != password && ! "".equals(password.trim() ))
		   this.password = password;
		registerButton.setEnabled(passEdits());
	}

	public void setPasswordVerify(String passwordVerify) {
		if (null != passwordVerify && ! "".equals(passwordVerify.trim() ))
		   this.passwordVerify = passwordVerify;
	}

	protected void cancelButtonClicked() {
          finish();		
	}
	
	private boolean authorizeTwitter() {
    	Intent intent = new Intent(this,GameOnTwitterOAuthActivity.class);
		startActivity(intent);		
		return true;
	}
	private boolean authorizeFacebook() {
	   	Intent intent = new Intent(this,com.jittr.android.facebook.Example.class);
		startActivity(intent);	
		return true;
	}
	private boolean authorizeFoursquare() {
	    return false;	
	}
	
	public class UserNameInputFilter {
		
		private InputFilter[] setUpFilter() {
		   InputFilter[] filterArray = new InputFilter[2];

		   filterArray[0] = new InputFilter.LengthFilter(15);
		   filterArray[1] = new LoginFilter.UsernameFilterGeneric();
		   return filterArray;
		}
	}
} //class
