package com.jittr.android;

import com.jittr.android.fs.dto.User;
import com.jittr.android.fs.impl.FSClientAPIImpl;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class GetUserDetailsActivity extends Activity {

	private FSClientAPIImpl fs;
	private User fsUser;
	private EditText emailEditText;
	
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
		    
			fsUser = fs.getUserDetails("9259485368", "juliomiy", true, true);
		} //if
		emailEditText.setText(  fsUser.getEmail());

	} //onResume
	
	 private void setUpViews() {
		   emailEditText = (EditText)findViewById(R.id.emailEditText);
	 }//setUpViews
} //class
