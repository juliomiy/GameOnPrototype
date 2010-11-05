package com.jittr.android.api.betsquared;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.util.Log;

import com.jittr.android.bs.dto.BSFriendRequests;
import com.jittr.android.bs.dto.BSUserBankStatement;
import com.jittr.android.bs.dto.BSUserDetails;
import com.jittr.android.bs.dto.Friend;
import com.jittr.android.bs.dto.Game;
import com.jittr.android.bs.dto.GameAddResponse;
import com.jittr.android.bs.dto.GameInvites;
import com.jittr.android.bs.dto.GameOnUserSettings;
import com.jittr.android.bs.dto.UserAddResponse;
import com.jittr.android.bs.dto.UserGamesDetails;
import com.jittr.android.bs.handlers.BSDashBoardHandler;
import com.jittr.android.bs.handlers.BSFriendRequestHandler;
import com.jittr.android.bs.handlers.BSUserBankStatementRequestHandler;
import com.jittr.android.bs.handlers.BSUserSettingsRequestHandler;
import com.jittr.android.bs.handlers.FriendHandler;
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

	/* searches betsquared users - intended to generate list of betsquared users who are not current friends
	 * 
	 */
	public ArrayList<Friend> searchBetsquaredUsers(
			HashMap<String, String> params) {
		ArrayList<Friend> friends = new ArrayList<Friend>();
		try {
			
			String url = URLBuilder.createUrl(Consts.BS_GET_FRIENDS_INVITES_ENDPOINT_URL,params);
			Log.d("","Url :"+url);
	
			String data = htppClient.getContent(new URL(url));
			System.out.println("data "+data);
			
			FriendHandler fh = new FriendHandler(data);
			friends = (ArrayList<Friend>)fh.parse();
			//System.out.println("friends  :"+friends);
			//return friends; 
		}
		catch (Exception e) {
			e.printStackTrace();
			Log.w("", "Exception while getFriendInvites "+e.getMessage());
		}
		
		return friends; 
	} //searchBetsquaredUsers

	//process Friend Invite operations - Approve/Decline
    public boolean processFriendInvite(Friend friend , int operation,int loginID) {
    	String operationStr = null;
    	String inviteeBSUserIDStr= null;
    	String invitetorBSUserIDStr = null;
    	String invites = null;
    	
    	if (null == friend ) return false;
    	
    	invitetorBSUserIDStr = Integer.toString(friend.getFrienduserid());
    	inviteeBSUserIDStr = Integer.toString(loginID);
    	HashMap<String,String> params = new HashMap<String,String>();
        switch (operation) {
           case  Consts.FRIEND_INVITE_DECLINE :
        	   params.put("inviteeuserid", inviteeBSUserIDStr);
        	   operationStr="decline";
        	   invites = "invitetoruserid=" + invitetorBSUserIDStr;
        	   break;
           case  Consts.FRIEND_INVITE_APPROVE:
           	   params.put("inviteeuserid", inviteeBSUserIDStr);
           	   operationStr="approve";
        	   invites = "invitetoruserid=" + invitetorBSUserIDStr;
        	   break;
        default: return false;	   
        }  //switch
    	params.put("operation", operationStr);
    	params.put("socialnetworkid", Integer.toString(Consts.BETSQUARED_NETWORK));
    	params.put("socialnetworkname", "betsquared");
    	params.put("invites", invites);
    	BSFriendRequests response = this.postInvite(params);
       	params = null;  //reset
        
    	if  (response.getStatus_code().equals("200")  )
       	   return true;
    	else return false;
    }  //processFriendInvite
	
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

	/* create a game/wager - 
	 * (non-Javadoc)
	 * @see com.jittr.android.api.betsquared.BSClientInterface#addGame(java.util.HashMap)
	 */
	@Override
	public GameAddResponse addGame(HashMap<String, String> params) {
		AddGameResponseHandler gh;
		GameAddResponse ur;
		
		try {
			String querStr = URLBuilder.createQueryStr(params);
			
			Log.d("","querStr:"+querStr);
			Log.d("", "Url :"+Consts.BS_ADD_GAME_ENDPOINT_URL);
			String data = htppClient.submitPostToServer(new URL(Consts.BS_ADD_GAME_ENDPOINT_URL), querStr); 
			System.out.println("data "+data);
			gh = new AddGameResponseHandler(data);
			
			ur = (GameAddResponse)gh.parse();
		}
		catch (Exception e) {
			e.printStackTrace();
			ur = new GameAddResponse();
			ur.setStatus_code(Consts.BS_ERROR_PARSING_RESPONSE);
			ur.setStatus_message(e.getMessage());
		} finally {
			gh = null;
		}
		return ur;
	}

	/* Get Games/bet invites for a user
	 * 
	 * (non-Javadoc)
	 * @see com.jittr.android.api.betsquared.BSClientInterface#getGameInvites(java.util.HashMap)
	 */
	@Override
	public GameInvites getGameInvites(HashMap<String, String> params) {
		GameInvitesHandler gh=null;
		GameInvites game_invites = null;
		
		try {
			
			String url = URLBuilder.createUrl(Consts.BS_GAME_INVITES_ENDPOINT_URL,params);
			Log.d("","Url :"+url);
	
			String data = htppClient.getContent(new URL(url));
			System.out.println("data "+data);
			
			gh = new GameInvitesHandler(data);
			game_invites = (GameInvites)gh.parse();
			System.out.println("game_invites  :"+game_invites);
		}
		catch (Exception e) {
			e.printStackTrace();
			game_invites = new GameInvites();
			game_invites.setStatus_code(Consts.BS_ERROR_PARSING_RESPONSE);
			game_invites.setStatus_message(e.getMessage());
		} finally {
			gh = null;
		}
		return game_invites;
	} //getGameInvites

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

	
	@Override
	public ArrayList<Friend> getUserFriends(HashMap<String, String> params) {
		// TODO Auto-generated method stub
		ArrayList<Friend> friends = new ArrayList<Friend>();
		try {
			
			String url = URLBuilder.createUrl(Consts.BS_GET_USER_FRIENDS_ENDPOINT_URL,params);
			Log.d("","Url :"+url);
	
			String data = htppClient.getContent(new URL(url));
			System.out.println("data "+data);
			
			FriendHandler fh = new FriendHandler(data);
			friends = (ArrayList<Friend>)fh.parse();
			//System.out.println("friends  :"+friends);
			//return friends; 
		}
		catch (Exception e) {
			e.printStackTrace();
			Log.w("", "Exception while getUserFriends "+e.getMessage());
		}
		
		return friends; 
	}


	/* added by @author Julio Hernandez-Miyares
	 * @date October 16,2010
	 * get friend invites 
	 * uses go_getfriends.php operation = invites
	 * returns a set of friends who have invited you to be connected via betsquare
	 * This is an internal api - the invitee and invitetor are both already betsquare users
	 * and possess a betsquared userID 
	 */
	public ArrayList<Friend> getFriendInvites(HashMap<String, String> params) {
		ArrayList<Friend> friends = new ArrayList<Friend>();
		try {
			
			String url = URLBuilder.createUrl(Consts.BS_GET_FRIENDS_INVITES_ENDPOINT_URL,params);
			Log.d("","Url :"+url);
	
			String data = htppClient.getContent(new URL(url));
			System.out.println("data "+data);
			
			FriendHandler fh = new FriendHandler(data);
			friends = (ArrayList<Friend>)fh.parse();
			//System.out.println("friends  :"+friends);
			//return friends; 
		}
		catch (Exception e) {
			e.printStackTrace();
			Log.w("", "Exception while getFriendInvites "+e.getMessage());
		}
		
		return friends; 
	}   //getFriendInvites

	@Override
	public BSFriendRequests postInvite(HashMap<String, String> params) {
		try {
			String querStr = URLBuilder.createQueryStr(params);
			
			Log.d("","querStr:"+querStr);
			Log.d("", "Url :"+Consts.BS_POST_INVITE_ENDPOINT_URL);
			String data = htppClient.submitPostToServer(new URL(Consts.BS_POST_INVITE_ENDPOINT_URL), querStr); 
			Log.d("","data "+data);
			
			BSFriendRequestHandler  fh = new BSFriendRequestHandler(data);
			
			BSFriendRequests fr = (BSFriendRequests)fh.parse();
			Log.d(" ", " fr obj "+fr);
			return fr;
		}
		catch (Exception e) {
			e.printStackTrace();
			//return null;
		}
		return null;
		
	}

	/* Get user BankBalance record, returns at most 1 record tied to the userID
	 * (non-Javadoc)
	 * @see com.jittr.android.api.betsquared.BSClientInterface#getUserBankBalance(java.util.HashMap)
	 */
	@Override
	public BSUserBankStatement getUserBankBalance(HashMap<String, String> params) {
		try {
			String querStr = URLBuilder.createQueryStr(params);
			
			Log.d("","querStr:"+querStr);
			Log.d("", "Url :"+Consts.BS_GET_USER_ENDPOINT_URL);
			String data = htppClient.submitPostToServer(new URL(Consts.BS_GET_USER_ENDPOINT_URL), querStr); 
			Log.d("","data "+data);
			
 		    BSUserBankStatementRequestHandler  fh = new BSUserBankStatementRequestHandler(data);
			
			BSUserBankStatement bs = (BSUserBankStatement)fh.parse();
			Log.d(" ", " bs obj "+bs);
			return bs;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}  //getUserBankBalance
	
	/* update the user's bank balance based on a transaction, usually a purchase of additional ducketts
	 * (non-Javadoc)
	 * @see com.jittr.android.api.betsquared.BSClientInterface#updUserBankBalance(java.util.HashMap)
     * @version 1.0
     * @date October 25,2010
	 */
	public BSUserBankStatement updUserBankBalance(HashMap<String,String>params) {
		try {
			String querStr = URLBuilder.createQueryStr(params);
			
			Log.d("","querStr:"+querStr);
			Log.d("", "Url :"+Consts.BS_UPDATE_USER_BANKSTATEMENT_ENDPOINT_URL);
			String data = htppClient.submitPostToServer(new URL(Consts.BS_UPDATE_USER_BANKSTATEMENT_ENDPOINT_URL), querStr); 
			Log.d("","data "+data);
			
 		    BSUserBankStatementRequestHandler  fh = new BSUserBankStatementRequestHandler(data);
			
			BSUserBankStatement bs = (BSUserBankStatement)fh.parse();
			Log.d(" ", " bs obj "+bs);
			return bs;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}  //updUserBankBalance

	/* Login a user via the host - returns GameOnUserSettings object if successful
	 * 
	 */
	public GameOnUserSettings loginUser(HashMap<String, String> params) {
		BSUserSettingsRequestHandler  requestHandler;
		GameOnUserSettings userSettings = null;
		
		try {
			String querStr = URLBuilder.createQueryStr(params);
			
			Log.d("","querStr:"+querStr);
			Log.d("", "Url :"+Consts.BS_LOGIN_USER_ENDPOINT_URL);
			String data = htppClient.submitPostToServer(new URL(Consts.BS_LOGIN_USER_ENDPOINT_URL), querStr); 
			Log.d("","data "+data);
			
 		    requestHandler = new BSUserSettingsRequestHandler(data);
			
			userSettings = (GameOnUserSettings)requestHandler.parse();
			Log.d(" ", " bs obj "+userSettings);
		}
		catch (Exception e) {
			e.printStackTrace();
			userSettings = new GameOnUserSettings();
			userSettings.setStatus_message("");
			userSettings.setStatus_code("");
		} finally {
			requestHandler = null;
		}
		return userSettings;
	}  //loginUser
}  //class
