package com.jittr.android.api.betsquared;

import java.util.HashMap;
import java.util.List;

import com.jittr.android.bs.dto.BSFriendRequests;
import com.jittr.android.bs.dto.BSUserDashBoard;
import com.jittr.android.bs.dto.Game;
import com.jittr.android.util.Consts;

import android.os.AsyncTask;
import android.util.Log;

public class AsyncDataLoader extends AsyncTask<String, Integer, Object>{

	DataFetchingCallBack mcallback = null;
	HashMap<String , String> mcriteria = null;
	
	public AsyncDataLoader(HashMap<String , String> criteria,DataFetchingCallBack calback ){
		mcriteria = criteria;
		mcallback = calback;
	
	}
	
	@Override
	protected Object doInBackground(String... params) {
		
		String operation_code = params[0];
		Log.d("" , " Operation "+operation_code);
		try {
			if(Consts.GET_BS_PUBLIC_GAMES.equals(operation_code)){
				Log.d("" , " Executing in Asynch MOde. "+operation_code);
				publishProgress(1);
				BSClientAPIImpl bs = new BSClientAPIImpl();
				List<Game> list = bs.getPublicGames(mcriteria);
				return list;
			}
			else if (Consts.GET_BS_USER_DASH.equals(operation_code) ){
				Log.d("" , " Executing in Asynch MOde. "+operation_code);
				publishProgress(1);
				BSClientAPIImpl bs = new BSClientAPIImpl();
				BSUserDashBoard dashboard =  bs.getUserDashBoard(mcriteria);
				return dashboard;
			}
			else if (Consts.POST_BS_INVITE.equals(operation_code)) {
				Log.d("" , " Executing in Asynch MOde.."+operation_code);
				publishProgress(1);
				BSClientAPIImpl bs = new BSClientAPIImpl();
				BSFriendRequests fr = bs.postInvite(mcriteria);
				Log.d("" , " in Asynch Mode fr.. "+fr);
				return fr;
			}
				
			else {
				return null;
			}
		}
		catch (Exception e) {
			Log.d("" , " Exception while executing Requeste method "+operation_code);
			mcallback.dataLoadException(e.getMessage());
			return null;
		}

	} 

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		Log.d("", "onPreExecute Started " );
		mcallback.dataLoading();
	}
	
	@Override
	protected void onProgressUpdate(Integer... progress) {
        //setProgressPercent(progress[0]);
		super.onProgressUpdate(progress);
		mcallback.dataLoading();
		System.out.println("Inside onProgressUpdate ");
    }
	
	@Override
	 protected void onPostExecute(Object a) {
		super.onPostExecute(a);
		System.out.println("Inside onPostExecute ");
		mcallback.dataLoaded(a);
		
	 }
	
	
}
