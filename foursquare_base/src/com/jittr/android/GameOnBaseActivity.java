package com.jittr.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public abstract class GameOnBaseActivity extends Activity {

    protected BetSquaredApplication appContext;
    protected TextView windowTitle;
	private ImageView windowIcon;
	
	public GameOnBaseActivity() {
		super();
	    appContext = getStuffApplication();	
	}  //constructor
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);

        setContentView(R.layout.main);

        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.windowtitle);

        windowTitle = (TextView) findViewById(R.id.windowtitle);
        //icon  = (ImageView) findViewById(R.id.icon);
	}
	
	public TextView getWindowTitle() {
		if (null == windowTitle) {
			windowTitle = (TextView) findViewById(R.id.windowtitle);
		}  //if
		return windowTitle;
	}

	public ImageView getWindowIcon() {
		if (null == windowIcon) {
			windowIcon = (ImageView) findViewById(R.id.windowicon);
		}	//if	
		return windowIcon;
	}

	protected BetSquaredApplication getStuffApplication()
	{
		return (BetSquaredApplication)getApplication();
	}
    
	public BetSquaredApplication getAppContext() {
		 if (null != appContext) return appContext;
		 else {
			 appContext = getStuffApplication();
			 return appContext;
		 }
	}
}  //class
