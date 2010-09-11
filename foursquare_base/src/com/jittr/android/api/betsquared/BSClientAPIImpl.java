package com.jittr.android.api.betsquared;

import java.net.URL;
import java.util.HashMap;
import java.util.List;

import android.util.Log;

import com.jittr.android.bs.dto.BSUserDetails;
import com.jittr.android.bs.dto.Game;
import com.jittr.android.bs.dto.GameAddResponse;
import com.jittr.android.bs.dto.GameInvites;
import com.jittr.android.bs.dto.UserAddResponse;
import com.jittr.android.bs.dto.UserGamesDetails;
import com.jittr.android.bs.handlers.BSDashBoardHandler;
import com.jittr.android.bs.handlers.PublicGamesHandler;
import com.jittr.android.bs.handlers.GameInvitesHandler;
import com.jittr.android.bs.handlers.AddGameResponseHandler;
import com.jittr.android.bs.handlers.UserDetailsHandler;
import com.jittr.android.bs.handlers.AddUserResponseHandler;
import com.jittr.android.bs.handlers.UserGamesHandler;
import com.jittr.android.bs.dto.BSUserDashBoard;

import com.jittr.android.fs.utils.URLBuilder;
import com.jittr.android.util.Consts;
import com.jittr.android.util.HttpConnectionHandler;

public class BSClientAPIImpl implements BSClientInterface {

	
	HttpConnectionHandler htppClient = null;
	
	public BSClientAPIImpl(String type, String user, String pwd) {
		htppClient = new HttpConnectionHandler(user,pwd);
	}

	public BSClientAPIImpl() {
		//default constructor
		this("test","juliomiy","test");
	}  //constructor
	
	public List<Game> getPublicGames(HashMap<String , String> criteria) {
		
		try {
			
			String url = URLBuilder.createUrl(Consts.BS_GET_PUBLIC_GAMES_ENDPOINT_URL,criteria);
			Log.d("","Url :"+url);
	
			String data = htppClient.getContent(new URL(url));
			System.out.println("data "+data);
			PublicGamesHandler gh = new PublicGamesHandler(data);
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
	
		
/*
 * (non-Javadoc)
 * @author jittr.com
 * @version 1.0
 * @see com.jittr.android.api.betsquared.BSClientInterface#getUserDashBoard(java.util.HashMap)
 * @params HashMap<String key, String value>
 * @return BSUserDashBoard - if exception during call the object will be populated with statusCode and statusMessage
 * 
 */
	public BSUserDashBoard getUserDashBoard(HashMap<String , String> params)  {
		BSUserDashBoard userstats = null;
		String errorMessage=null;
		try {
			//NVPair nvps [] = {new NVPair("userid",userid)};
			
			String url = URLBuilder.createUrl(Consts.BS_GET_USER_DASHBOARD_ENDPOINT_URL,params);
			Log.d("","Url :"+url);
	
			String data = htppClient.getContent(new URL(url));
			System.out.println("data "+data);
			
			
			
			BSDashBoardHandler gh = new BSDashBoardHandler(data);
			userstats = (BSUserDashBoard)gh.parse();
			System.out.println("userstats User ID :"+userstats.getUserid());
			System.out.println("userstats getTotalbets:"+userstats.getTotalbets());
			System.out.println("userstats getTotalwins :"+userstats.getTotalwins());
			System.out.println("userstats getTotalloses :"+userstats.getTotalloses());
		}
		catch (Exception e) {
			 e.printStackTrace();
			 errorMessage = e.getMessage();
		} finally {
			if (null == userstats)  {
				userstats = new BSUserDashBoard();
   			    userstats.setStatusCode("500");
			    userstats.setStatusMessage(errorMessage);
			} //if
		} //finally
		return userstats;
   }


	@Override
	public UserAddResponse addUser(HashMap<String , String> params) {
		String errorMessage = null;
		UserAddResponse ur = null;
		try {
			//NVPair nvps [] = {new NVPair("newusername",userName)};
			String querStr = URLBuilder.createQueryStr(params);
			
			Log.d("","querStr:"+querStr);
			Log.d("", "Url :"+Consts.BS_ADD_USER_ENDPOINT_URL);
			String data = htppClient.submitPostToServer(new URL(Consts.BS_ADD_USER_ENDPOINT_URL), querStr); 
			System.out.println("data "+data);
			AddUserResponseHandler uh = new AddUserResponseHandler(data);
			ur = (UserAddResponse)uh.parse();
		}
		catch (Exception e) {
			e.printStackTrace();
            errorMessage = e.getMessage();
		} finally {
            if (null == ur) { 
            	ur = new UserAddResponse();			
    			ur.setStatus_code("500");
    			ur.setStatus_message(errorMessage);
            } //if
		}  //finally
		return ur;
	}


	@Override
	public BSUserDetails getUserDetails(HashMap<String , String> params) {
		
		try {
		
			String url = URLBuilder.createUrl(Consts.BS_GET_USER_DETAILS_ENDPOINT_URL,params);
			Log.d("","Url :"+url);
	
			String data = htppClient.getContent(new URL(url));
			System.out.println("data "+data);
			
			
			
			UserDetailsHandler uh = new UserDetailsHandler(data);
			BSUserDetails user = (BSUserDetails)uh.parse();
			System.out.println("BSUserDetails User  :"+user);
			return user;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public GameAddResponse addGame(HashMap<String, String> params) {
		// TODO Auto-generated method stub
		
		try {
			String querStr = URLBuilder.createQueryStr(params);
			
			Log.d("","querStr:"+querStr);
			Log.d("", "Url :"+Consts.BS_ADD_GAME_ENDPOINT_URL);
			String data = htppClient.submitPostToServer(new URL(Consts.BS_ADD_GAME_ENDPOINT_URL), querStr); 
			System.out.println("data "+data);
			AddGameResponseHandler gh = new AddGameResponseHandler(data);
			
			GameAddResponse ur = (GameAddResponse)gh.parse();
			return ur;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public GameInvites getGameInvites(HashMap<String, String> params) {
		
		try {
			
			String url = URLBuilder.createUrl(Consts.BS_GAME_INVITES_ENDPOINT_URL,params);
			Log.d("","Url :"+url);
	
			String data = htppClient.getContent(new URL(url));
			System.out.println("data "+data);
			
			GameInvitesHandler gh = new GameInvitesHandler(data);
			GameInvites game_invites = (GameInvites)gh.parse();
			System.out.println("game_invites  :"+game_invites);
			return game_invites;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public UserGamesDetails getUserGames(HashMap<String, String> params) {
		String errorMessage ="" ;
		UserGamesDetails uDetails;
		try {
			String url = URLBuilder.createUrl(Consts.BS_GET_USER_GAMES_ENDPOINT_URL,params);
			Log.d("","Url :"+url);
	
			String data = htppClient.getContent(new URL(url));
			System.out.println("data "+data);
			UserGamesHandler uh = new UserGamesHandler(data);
			uDetails = (UserGamesDetails)uh.parse();
			
			System.out.println("uDetails "+uDetails);
			
			return uDetails;
		}
		catch (Exception e) {
			e.printStackTrace();
			errorMessage = e.getMessage();
		}
		finally {
			uDetails = new UserGamesDetails();
			uDetails.setStatus_code(Consts.ERROR);
			uDetails.setStatus_message(errorMessage);
		}
		return uDetails;
		
	} //end of getUserGames 
	
	
	
}
