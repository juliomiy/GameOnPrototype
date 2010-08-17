package com.jittr.android.api.betsquared;

import java.util.HashMap;
import java.util.List;

import com.jittr.android.fs.dto.Game;
import com.jittr.android.fs.dto.BSUserDashBoard;

public interface BSClientInterface {
	
		//List<Game> getPublicGames(String league, String team,String sportCategory,String latitude, String longitude, int timeframe);
     	List<Game> getPublicGames(HashMap<String, String> criteria);
		BSUserDashBoard getUserDashBoard(String userid);
		

}
