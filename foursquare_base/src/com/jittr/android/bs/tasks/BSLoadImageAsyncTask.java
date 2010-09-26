/**
 * 
 */
package com.jittr.android.bs.tasks;

import java.io.IOException;
import java.net.URL;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

/**
 * @author juliomiyares
 *
 */
public class BSLoadImageAsyncTask extends AsyncTask<URL, Void, Drawable> {

	  public interface BSLoadImageAsyncTaskResponder {
		    public void imageLoading();
		    public void imageLoadCancelled();
		    public void imageLoaded(Drawable drawable);
		  }  //interface

		  private BSLoadImageAsyncTaskResponder responder;

		  public BSLoadImageAsyncTask(BSLoadImageAsyncTaskResponder responder) {
		    this.responder = responder;
		  }  //constructor

     @Override
  	 protected Drawable doInBackground(URL... args) {
	   try {
		      return Drawable.createFromStream(args[0].openStream(), args[0].toString());
		    } catch (IOException e) {
		      Log.e(getClass().getName(), "Could not load image.", e);
		      return null;
		    }
		  }

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
} //class
