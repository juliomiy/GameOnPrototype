package com.jittr.android.api.betsquared;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.util.Log;

import com.jittr.android.fs.dto.Game;
import com.jittr.android.fs.dto.User;
import com.jittr.android.fs.dto.BSUserDashBoard;
import com.jittr.android.fs.handlers.BSDashBoardHandler;
import com.jittr.android.fs.handlers.GameHandler;

import com.jittr.android.fs.utils.Constants;
import com.jittr.android.fs.utils.FSConnectionHandler;
import com.jittr.android.fs.utils.NVPair;
import com.jittr.android.fs.utils.URLBuilder;

public class BSClientAPIImpl implements BSClientInterface {

	
	FSConnectionHandler fsc = null;
	
	public BSClientAPIImpl(String type, String user, String pwd) {
		fsc = new FSConnectionHandler(user,pwd);
	}

	
	public List<Game> getPublicGames(HashMap<String , String> criteria) {
		
		try {
			
			/*
			NVPair nvps [] = {new NVPair("league",league),
				  new NVPair("team",team),
				  new NVPair("sport", sportCategory),
				  new NVPair("latitude", latitude), 
				  new NVPair("longitude", longitude),
				  new NVPair("timeframe", String.valueOf(timeframe)),
				 };
			*/
			 
			String url = URLBuilder.createUrl(Constants.Public_Games_URL,criteria);
			Log.d("","Url :"+url);
	
			String data = fsc.getContent(new URL(url));
			System.out.println("data "+data);
			GameHandler gh = new GameHandler(data);
			List<Object> games = gh.parseList();
			
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
			return null;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public BSUserDashBoard getUserDashBoard(String userid) {
		// TODO Auto-generated method stub
		
		try {
			NVPair nvps [] = {new NVPair("userid",userid)};
			
			String url = URLBuilder.createUrl(Constants.User_DashBoard_URL,nvps);
			Log.d("","Url :"+url);
	
			String data = fsc.getContent(new URL(url));
			System.out.println("data "+data);
			
			
			
			BSDashBoardHandler gh = new BSDashBoardHandler(data);
			BSUserDashBoard userstats = (BSUserDashBoard)gh.parse();
			System.out.println("userstats User ID :"+userstats.getUserid());
			System.out.println("userstats getTotalbets:"+userstats.getTotalbets());
			System.out.println("userstats getTotalwins :"+userstats.getTotalwins());
			System.out.println("userstats getTotalloses :"+userstats.getTotalloses());
			
			
			return null;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;

		
		
		
	}
	
	
	
}
