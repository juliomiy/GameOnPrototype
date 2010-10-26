package com.jittr.android.api.betsquared;

import java.util.HashMap;
import java.util.List;

import com.jittr.android.bs.dto.BSFriendRequests;
import com.jittr.android.bs.dto.BSUserBankStatement;
import com.jittr.android.bs.dto.BSUserDetails;
import com.jittr.android.bs.dto.Friend;
import com.jittr.android.bs.dto.Game;
import com.jittr.android.bs.dto.GameAddResponse;
import com.jittr.android.bs.dto.GameInvites;
import com.jittr.android.bs.dto.UserAddResponse;
import com.jittr.android.bs.dto.BSUserDashBoard;
import com.jittr.android.bs.dto.UserGamesDetails;

public interface BSClientInterface {
	
		//List<Game> getPublicGames(String league, String team,String sportCategory,String latitude, String longitude, int timeframe);
     	List<Game> getPublicGames(HashMap<String, String> criteria);
		BSUserDashBoard getUserDashBoard(HashMap<String, String> params) throws Exception;
		UserAddResponse addUser(HashMap<String , String> params);
		BSUserDetails getUserDetails(HashMap<String , String> params);
		GameAddResponse addGame(HashMap<String , String> params);
		GameInvites getGameInvites(HashMap<String , String> params);
		UserGamesDetails getUserGames(HashMap<String, String> params);
		List<Friend>  getUserFriends(HashMap<String, String> params);
		BSFriendRequests postInvite(HashMap<String, String> params);
		BSUserBankStatement getUserBankBalance(HashMap<String,String> params);
		BSUserBankStatement updUserBankBalance(HashMap<String,String> params);
}  //BSClientInterface
