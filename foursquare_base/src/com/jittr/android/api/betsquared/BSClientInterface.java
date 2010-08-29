package com.jittr.android.api.betsquared;

import java.util.HashMap;
import java.util.List;

import com.jittr.android.bs.dto.BSUserDetails;
import com.jittr.android.bs.dto.Game;
import com.jittr.android.bs.dto.UserAddResponse;
import com.jittr.android.bs.dto.BSUserDashBoard;

public interface BSClientInterface {
	
		//List<Game> getPublicGames(String league, String team,String sportCategory,String latitude, String longitude, int timeframe);
     	List<Game> getPublicGames(HashMap<String, String> criteria);
		BSUserDashBoard getUserDashBoard(String userid);
		UserAddResponse addUser(HashMap<String , String> params);
		BSUserDetails getUserDetails(HashMap<String , String> params);

}
