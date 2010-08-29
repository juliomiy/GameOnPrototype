package com.jittr.android;

import android.app.Activity;

public abstract class GameOnBaseActivity extends Activity {

    protected BetSquaredApplication appContext;
    
	public GameOnBaseActivity() {
		super();
	    appContext = getStuffApplication();	
	}  //constructor
	
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
