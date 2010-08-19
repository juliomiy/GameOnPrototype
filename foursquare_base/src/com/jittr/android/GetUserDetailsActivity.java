package com.jittr.android;

import com.jittr.android.fs.dto.User;
import com.jittr.android.fs.impl.FSClientAPIImpl;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GetUserDetailsActivity extends Activity {

	private FSClientAPIImpl fs;
	private User fsUser;
	private EditText emailEditText;
	private TextView lastNameEditText;
	private TextView twitterEditText;
	private TextView faceBookEditText;
	private Button cancelButton;
	private EditText firstNameEditText;
	
	public GetUserDetailsActivity() {
		// TODO Auto-generated constructor stub
	}
	public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.getuserdetails);
	        setUpViews();
	 }   //onCreate
	
	protected void onResume() {
		super.onResume();
		if (fs == null ) {
			fs = new FSClientAPIImpl("xml", "9259485368", "findme3366");
		    
			fsUser = fs.getUserDetails("", "juliomiy", true, true);
            if (fsUser != null) {
            	emailEditText.setText(fsUser.getEmail());
    			firstNameEditText.setText(fsUser.getFirstName());
                lastNameEditText.setText(fsUser.getLastName());
                twitterEditText.setText(fsUser.getTwitter());
                faceBookEditText.setText(fsUser.getFacebook());
            }
		} //if
	} //onResume
	
	 private void setUpViews() {
		   emailEditText = (EditText)findViewById(R.id.emailEditText);
		   firstNameEditText = (EditText)findViewById(R.id.firstNameEditText);
		   lastNameEditText = (EditText)findViewById(R.id.lastNameEdiTtext);
           twitterEditText = (EditText)findViewById(R.id.twitterEditText);
           faceBookEditText = (EditText)findViewById(R.id.faceBookEditText);
           cancelButton = (Button)findViewById(R.id.cancelButton);
           cancelButton.setOnClickListener( new View.OnClickListener() {
   			   public void onClick(View v) {
                   finish();				
   			}
    	   });      
	 }//setUpViews
} //class
