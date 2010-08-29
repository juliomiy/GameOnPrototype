package com.jittr.android;

import java.net.MalformedURLException;
import java.net.URL;

import com.jittr.android.betsquared.tasks.LoadImageAsyncTask.LoadImageAsyncTaskResponder;
import com.jittr.android.betsquared.tasks.LoadImageAsyncTask;
import com.jittr.android.fs.dto.User;
import com.jittr.android.fs.impl.FSClientAPIImpl;
import android.os.AsyncTask;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class GetUserDetailsActivity extends GameOnBaseActivity implements LoadImageAsyncTaskResponder {

	private FSClientAPIImpl fs;
	private User fsUser;
	private EditText emailEditText;
	private TextView lastNameEditText;
	private TextView twitterEditText;
	private TextView faceBookEditText;
	private Button cancelButton;
	private EditText firstNameEditText;
	private ImageView foursquareAvatarImageView;
	private AsyncTask<URL, Void, Drawable> imageLoadTask;
	private TextView foursquareAvatarUrlTextView;
	private final String TAG="GetUserDetailsActivity";
	
	public GetUserDetailsActivity() {
            super();
	}
	public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
 	        setContentView(R.layout.getuserdetails);
	        setUpViews();
	 }   //onCreate
	
	protected void onResume() {
		super.onResume();
		if (fs == null ) {
			
			//temporary 
			String foursquareID = getAppContext().getUserSettings().getFoursquareID();
			String foursquarePW = getAppContext().getUserSettings().getFoursquarePassword();

			fs = new FSClientAPIImpl("xml", foursquareID, foursquarePW);
		    
			fsUser = fs.getUserDetails("", "juliomiy", true, true);
            if (fsUser != null) {
            	Log.d(TAG,fsUser.toString());
            	emailEditText.setText(fsUser.getEmail());
    			firstNameEditText.setText(fsUser.getFirstName());
                lastNameEditText.setText(fsUser.getLastName());
                twitterEditText.setText(fsUser.getTwitter());
                faceBookEditText.setText(fsUser.getFacebook());
                foursquareAvatarUrlTextView.setText("Avatar URL " + fsUser.getPhoto());
                foursquareAvatarUrlTextView.setText(fsUser.getPhoto()); 
                if (null != imageLoadTask) {
                	imageLoadTask.cancel(true);
                } //if
                URL url;
				try {
					url = new URL(fsUser.getPhoto());
					 imageLoadTask = new LoadImageAsyncTask(this).execute(url);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
		} //if
	} //onResume
	
	 private void setUpViews() {
		   foursquareAvatarUrlTextView = (TextView)findViewById(R.id.foursquareAvatarUrlTextView);
		   foursquareAvatarImageView = (ImageView)findViewById(R.id.foursquareAvatarUrlImageView);
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
	@Override
	public void imageLoadCancelled() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void imageLoaded(Drawable drawable) {
		   foursquareAvatarImageView.setImageDrawable(drawable);		
	}
	@Override
	public void imageLoading() {
		   foursquareAvatarImageView.setImageDrawable(null);		
	}

} //class
