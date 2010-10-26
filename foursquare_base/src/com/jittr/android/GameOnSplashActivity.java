/**
 * 
 */
package com.jittr.android;

import com.jittr.android.util.Consts;

import android.os.Bundle;
import android.widget.TextView;

/**
 * @author juliomiyares
 * @version 1.0
 * @data October 20,2010
 * @purpose Display a splash screen on startup and also when 
 * as part of About 
 */
public class GameOnSplashActivity extends GameOnBaseActivity {

	private TextView betsquaredVersionTextView;
	private TextView copyrightTextView;

	/**
	 * 
	 */
	public GameOnSplashActivity() {
		// TODO Auto-generated constructor stub
	}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameonsplash);
        setUpViews();
     //   setBottomBar(0);
    }   //onCreate
    
    public void onResume() {
		super.onResume();
    } //onResume
    
    protected void setUpViews() {
    	super.setUpViews(Consts.LAYOUT_ADD_DONE);
    	betsquaredVersionTextView = (TextView)findViewById(R.id.betsquatedVersionTextView);
    	betsquaredVersionTextView.setText(this.getAppContext().getAppVersionCodeMajorMinor());
    	copyrightTextView = (TextView)findViewById(R.id.copyrightTextView);
    	copyrightTextView.setText("Copyright 2010 jittr.com");
    }  //setUpViews
} //GameOnSplashActivity
