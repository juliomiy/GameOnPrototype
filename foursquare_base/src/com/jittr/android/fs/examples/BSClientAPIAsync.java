package com.jittr.android.fs.examples;

import java.util.HashMap;
import java.util.List;

import com.jittr.android.bs.dto.Game;



public class BSClientAPIAsync {

	public void getPublicGames(HashMap<String , String> criteria,DataFetchingCallBack mcallBack) {
		
		//Instantiate Asynch Loader and start in backgroud
		//Callback methods will be posted to UI thread via mcallback
		AsyncDataLoader adl = new AsyncDataLoader(criteria,mcallBack);
		String operation = "GET_BS_PUBLIC_GAMES";  //To be deifned in Constants file
		String params [] = {operation};
		adl.execute(params);
		
	}
	
	public void getUserDashBoard(HashMap<String , String> criteria,DataFetchingCallBack mcallBack) {
		//Instantiate Asynch Loader and start in backgroud
		//Callback methods will be posted to UI thread via mcallback
		AsyncDataLoader adl = new AsyncDataLoader(criteria,mcallBack);
		String operation = "GET_BS_USER_DASH";  //To be deifned in Constants file
		String params [] = {operation};
		adl.execute(params);
		
	}
	
}
