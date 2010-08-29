package com.jittr.android.betsquared.tasks;

import java.io.IOException;
import java.net.URL;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

public class LoadImageAsyncTask extends AsyncTask<URL, Void, Drawable> {
	  public interface LoadImageAsyncTaskResponder {
		    public void imageLoading();
		    public void imageLoadCancelled();
		    public void imageLoaded(Drawable drawable);
		  }

		  private LoadImageAsyncTaskResponder responder;

		  public LoadImageAsyncTask(LoadImageAsyncTaskResponder responder) {
		    this.responder = responder;
		  }


	@Override
	protected Drawable doInBackground(URL... params) {
	   try {
		      return Drawable.createFromStream(params[0].openStream(), params[0].toString());
		    } catch (IOException e) {
		      Log.e(getClass().getName(), "Could not load image.", e);
		      return null;
		    } //try/catch	
	 }  //doInBackGround
	
	 @Override
	  protected void onPreExecute() {
	    super.onPreExecute();
	    responder.imageLoading();
	  }

	  @Override
	  protected void onCancelled() {
	    super.onCancelled();
	    responder.imageLoadCancelled();
	  }

	  @Override
	  protected void onPostExecute(Drawable result) {
	    super.onPostExecute(result);
	    responder.imageLoaded(result);
	  }

}  //class
