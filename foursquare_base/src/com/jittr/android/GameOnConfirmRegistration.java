/**
 * 
 */
package com.jittr.android;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author juliomiyares
 * @version 1.0
 * 
 *
 */
public final class GameOnConfirmRegistration extends GameOnBaseActivity {

	private Button cancelButton;
	private TextView confirmRegistrationTextView;

	/**
	 * 
	 */
	public GameOnConfirmRegistration() {
		// TODO Auto-generated constructor stub
	}
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameonconfirmregistration);
        setUpViews();
    }   //onCreate

	protected void onResume() {
		super.onResume();
	}  //onResume	

	protected void setUpViews() {
		  confirmRegistrationTextView = (TextView)findViewById(R.id.confirmRegistrationTextView);
	      cancelButton = (Button)findViewById(R.id.windowTitleLeftButton);
	      cancelButton.setVisibility(View.VISIBLE);
	      cancelButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				cancelButtonClicked();
			}
		});
	}  //setUpViews
	protected void cancelButtonClicked() {
       finish();	
	}
}
