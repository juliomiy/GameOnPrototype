package com.jittr.android.api.betsquared;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.util.Log;

import com.jittr.android.bs.dto.Game;
import com.jittr.android.bs.dto.UserAddResponse;
import com.jittr.android.bs.handlers.BSDashBoardHandler;
import com.jittr.android.bs.handlers.GameHandler;
import com.jittr.android.bs.handlers.UserResponseHandler;
import com.jittr.android.fs.dto.User;
import com.jittr.android.fs.dto.BSUserDashBoard;

import com.jittr.android.fs.utils.Constants;
import com.jittr.android.fs.utils.FSConnectionHandler;
import com.jittr.android.fs.utils.NVPair;
import com.jittr.android.fs.utils.URLBuilder;
import com.jittr.android.util.Consts;
import com.jittr.android.util.HttpConnectionHandler;

public class BSClientAPIImpl implements BSClientInterface {

	
	HttpConnectionHandler htppClient = null;
	
	public BSClientAPIImpl(String type, String user, String pwd) {
		htppClient = new HttpConnectionHandler(user,pwd);
	}

	
	public List<Game> getPublicGames(HashMap<String , String> criteria) {
		
		try {
			
			String url = URLBuilder.createUrl(Consts.BS_GET_PUBLIC_GAMES_ENDPOINT_URL,criteria);
			Log.d("","Url :"+url);
	
			String data = htppClient.getContent(new URL(url));
			System.out.println("data "+data);
			GameHandler gh = new GameHandler(data);
			List<Game> games = (List) gh.parseList();
			
			System.out.println("Games "+games.size());
			
			for(int i=0;i<games.size();i++) {
				Game temp = (Game)games.get(i);
				Log.d("", " i  "+i);
				Log.d("", "Game ID "+temp.getId());
				Log.d("", "Game Address "+temp.getAddress());
				Log.d("", "Game Event Name "+temp.getEventname());
				Log.d("", "Game Team1 "+temp.getTeam1());
				Log.d("", "Game Team2 "+temp.getTeam2());
			}
			return games;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public BSUserDashBoard getUserDashBoard(String userid) {
		
		try {
			NVPair nvps [] = {new NVPair("userid",userid)};
			
			String url = URLBuilder.createUrl(Consts.BS_GET_USER_DASHBOARD_ENDPOINT_URL,nvps);
			Log.d("","Url :"+url);
	
			String data = htppClient.getContent(new URL(url));
			System.out.println("data "+data);
			
			
			
			BSDashBoardHandler gh = new BSDashBoardHandler(data);
			BSUserDashBoard userstats = (BSUserDashBoard)gh.parse();
			System.out.println("userstats User ID :"+userstats.getUserid());
			System.out.println("userstats getTotalbets:"+userstats.getTotalbets());
			System.out.println("userstats getTotalwins :"+userstats.getTotalwins());
			System.out.println("userstats getTotalloses :"+userstats.getTotalloses());
			
			
			return userstats;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;

		
		
		
	}


	@Override
	public UserAddResponse addUser(HashMap<String , String> params) {
		
		try {
			//NVPair nvps [] = {new NVPair("newusername",userName)};
			String querStr = URLBuilder.createQueryStr(params);
			
			Log.d("","querStr:"+querStr);
			Log.d("", "Url :"+Consts.BS_ADD_USER_ENDPOINT_URL);
			String data = htppClient.submitPostToServer(new URL(Consts.BS_ADD_USER_ENDPOINT_URL), querStr); 
			System.out.println("data "+data);
			UserResponseHandler uh = new UserResponseHandler(data);
			UserAddResponse ur = (UserAddResponse)uh.parse();
			return ur;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	
}
