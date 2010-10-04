package com.jittr.android.fs.examples;

import java.util.HashMap;
import java.util.List;

import com.jittr.android.bs.dto.Game;
import com.jittr.android.util.Consts;



public class BSClientAPIAsync {

	public void getPublicGames(HashMap<String , String> criteria,DataFetchingCallBack mcallBack) {
		
		//Instantiate Asynch Loader and start in backgroud
		//Callback methods will be posted to UI thread via mcallback
		AsyncDataLoader adl = new AsyncDataLoader(criteria,mcallBack);
		String params [] = {Consts.GET_BS_PUBLIC_GAMES};
		adl.execute(params);
		
	}
	
	public void getUserDashBoard(HashMap<String , String> criteria,DataFetchingCallBack mcallBack) {
		//Instantiate Asynch Loader and start in backgroud
		//Callback methods will be posted to UI thread via mcallback
		AsyncDataLoader adl = new AsyncDataLoader(criteria,mcallBack);
		String params [] = {Consts.GET_BS_USER_DASH};
		adl.execute(params);
		
	}
	
	public void postInvite(HashMap<String , String> criteria,DataFetchingCallBack mcallBack) {
		//Instantiate Asynch Loader and start in backgroud
		//Callback methods will be posted to UI thread via mcallback
		AsyncDataLoader adl = new AsyncDataLoader(criteria,mcallBack);
		String params [] = {Consts.POST_BS_INVITE};
		adl.execute(params);
		
	}

	
}
