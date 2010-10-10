package com.jittr.android.foursquare;

import static com.jittr.android.util.Consts.TWITTER_NETWORK;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.oauth.OAuth;
import net.oauth.OAuthAccessor;
import net.oauth.OAuthConsumer;
import net.oauth.OAuthMessage;
import net.oauth.OAuthProblemException;
import net.oauth.OAuthServiceProvider;
import net.oauth.client.OAuthClient;
import net.oauth.client.httpclient4.HttpClient4;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.jittr.android.BetSquaredApplication;
import com.jittr.android.GameOnFoursquareAuthActivity;
import com.jittr.android.betsquared.GameOnSocialNetworkBase;
import com.jittr.android.bs.dto.SocialNetworkFriend;
import com.jittr.android.util.Consts;

import android.util.Log;

/**
 * 
 * @author System Integration
 * @author juliomiyares
 * @date October 7,2010
 * @version 1.0
 * @purpose Foursquare API class - all implemented using OAuth
 * 
 */

public class GameOnFoursquare extends GameOnSocialNetworkBase {

	private static final String TAG = "GameOnFoursquare";
//	private static OAuthClient oAuthClient = null;
//	private static OAuthMessage oAuthMessage = null;
	private  OAuthClient oAuthClient = null;
	private  OAuthMessage oAuthMessage = null;
	// protected final static DefaultHttpClient mHttpClient =
	// AbstractHttpApi.createHttpClient();
	
	public GameOnFoursquare() {
	    super();	
	} //default constructor
	
	public GameOnFoursquare(BetSquaredApplication appObject,int socialNetworkID,String token, String secret) {
		super(appObject,socialNetworkID,token,secret);
	}
	
    public static OAuthServiceProvider defaultProvider()
	{
		//return new OAuthServiceProvider(Constants.OAUTH_REQUEST_URL,Constants.OAUTH_AUTHORIZATION_URL,Constants.OAUTH_ACCESS_URL);
		return new OAuthServiceProvider(Consts.FOURSQUARE_REQUEST_TOKEN_ENDPOINT_URL,Consts.FOURSQUARE_AUTHORIZE_WEBSITE_URL,Consts.FOURSQUARE_ACCESS_TOKEN_ENDPOINT_URL);
	}

	/* generate the Foursquare Token and token secret after user has approved the app*/
	public  void convertToAccessToken() {
		try {
			oAuthClient = new OAuthClient(new HttpClient4());
			ArrayList<Map.Entry<String, String>> params = new ArrayList<Map.Entry<String, String>>();
			params.add(new OAuth.Parameter("oauth_verifier",
					Constants.OAUTH_VERIFIER));
			oAuthMessage = oAuthClient.getAccessToken(GameOnFoursquareAuthActivity.accessor,
					"GET", params);
			setAccessToken(oAuthMessage.getParameter("oauth_token"));
			setAccessTokenSecret( oAuthMessage.getParameter("oauth_token_secret"));
			//temporary
			Constants.ACCESS_TOKEN=getAccessToken();
			Constants.OAUTH_TOKEN_SECRET=getAccessTokenSecret();
			//end temporary
		} catch (OAuthProblemException error) {
			error.printStackTrace();
		} catch (Exception error) {
			error.printStackTrace();
		}
		saveUserAuthCredentials(appContext.getLoginID(),Consts.FOURSQUARE_NETWORK, getAccessToken(), getAccessTokenSecret(),null,null,null);
	}

	protected ArrayList<SocialNetworkFriend> readFriendsDocument(InputStream data)
			throws ParserConfigurationException, SAXException, IOException {
		ArrayList<SocialNetworkFriend>friendList = new ArrayList<SocialNetworkFriend>();
		SocialNetworkFriend userData;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(data);
		doc.getDocumentElement().normalize();
		NodeList nodeLst = doc.getElementsByTagName("friends");
		
		Node fstNode = nodeLst.item(0);
		Element fstElmnt = (Element) fstNode;
		NodeList demo = fstElmnt.getElementsByTagName("user");
		
		for (int i = 0; i < demo.getLength(); i++) {
			userData = new SocialNetworkFriend();
			Node fstNode1 = demo.item(i);
			if (fstNode1.getNodeType() == Node.ELEMENT_NODE) {
				Element fstElmnt1 = (Element) fstNode1;
				userData.setFirstname(getUserElement(fstElmnt1, "firstname"));
				userData.setLastname(getUserElement(fstElmnt1, "lastname"));
				userData.setUserID(getUserElement(fstElmnt1, "id"));
				userData.setFriendstatus(getUserElement(fstElmnt1,"friendstatus"));
				userData.setHomecity(getUserElement(fstElmnt1, "homecity"));
//				userData.setPhoto(getUserElement(fstElmnt1, "photo"));
				userData.setGender(getUserElement(fstElmnt1, "gender"));
				userData.setPhone(getUserElement(fstElmnt1, "phone"));
				userData.setEmail(getUserElement(fstElmnt1, "email"));
				friendList.add(userData);
//				Constants.userList.add(userData);
			} //if
		} //for
		return friendList;
	}  //readFriendsDocument

	public static void readXMLFile() {
		try {
			ArrayList<Map.Entry<String, String>> params = new ArrayList<Map.Entry<String, String>>();
			OAuthAccessor accessor = null;
			OAuthServiceProvider provider = defaultProvider();
			OAuthConsumer consumer = new OAuthConsumer(Constants.CALLBACK_URL,
					Constants.CONSUMER_KEY, Constants.CONSUMER_SECRET, provider);
			accessor = new OAuthAccessor(consumer);
			accessor.accessToken = Constants.ACCESS_TOKEN;
			accessor.tokenSecret = Constants.OAUTH_TOKEN_SECRET;
			params.add(new OAuth.Parameter("oauth_token",
					Constants.ACCESS_TOKEN));
		//	oAuthClient = new OAuthClient(new HttpClient4());
		//	oAuthMessage = oAuthClient.invoke(accessor, "GET",
//					Constants.XML_URL, params);

		//	InputStream inputStream = oAuthMessage.getBodyAsStream();

//			readDocument(inputStream);

		} catch (Exception error) {
			error.printStackTrace();
		}
	}

	public ArrayList<SocialNetworkFriend> friendsList() {
        ArrayList<SocialNetworkFriend> friendList = appContext.getSocialNetworkFriends(Consts.FOURSQUARE_NETWORK);
        if (null != friendList) return friendList;

		try {
			ArrayList<Map.Entry<String, String>> params = new ArrayList<Map.Entry<String, String>>();
			OAuthAccessor accessor = null;
			OAuthServiceProvider provider = defaultProvider();
			OAuthConsumer consumer = new OAuthConsumer(Constants.CALLBACK_URL,
					Constants.CONSUMER_KEY, Constants.CONSUMER_SECRET, provider);
			accessor = new OAuthAccessor(consumer);
			accessor.accessToken = getAccessToken();
			accessor.tokenSecret = getAccessTokenSecret();
			params.add(new OAuth.Parameter("oauth_token",
					getAccessToken()));
			oAuthClient = new OAuthClient(new HttpClient4());
			oAuthMessage = oAuthClient.invoke(accessor, "GET",
					Consts.FOURSQUARE_FRIENDS_URL, params);

			InputStream inputStream = oAuthMessage.getBodyAsStream();
			friendList = readFriendsDocument(inputStream);	
			appContext.updateSocialNetworkFriends(Consts.FOURSQUARE_NETWORK,friendList);

			/*
			 * if (inputStream != null) { StringBuilder sb = new
			 * StringBuilder(); String line; BufferedReader reader = new
			 * BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			 * while ((line = reader.readLine()) != null) {
			 * sb.append(line).append("\n"); } Log.i(TAG, "Demo>>>"+sb); }
			 */

		} catch (Exception error) {
			error.printStackTrace();
		}
		return friendList;
	}  //friendsList

/*	public static OAuthServiceProvider defaultProvider() {
		return new OAuthServiceProvider(Constants.OAUTH_REQUEST_URL,
				Constants.OAUTH_AUTHORIZATION_URL, Constants.OAUTH_ACCESS_URL);
	}
*/
	public static void readDocument(InputStream data)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(data);
		doc.getDocumentElement().normalize();
		NodeList nodeLst = doc.getElementsByTagName("user");
		Node fstNode = nodeLst.item(0);
		Element fstElmnt = (Element) fstNode;

		Constants.USER_NAME = getUserElement(fstElmnt,"firstname") + " " + getUserElement(fstElmnt, "lastname");
	}
	
	public static String getUserElement(Element fstElmnt, String element) {
		String str = "";
		NodeList fstNmElmntLst = fstElmnt.getElementsByTagName(element);
		Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
		if (fstNmElmnt != null) {
			NodeList fstNm = fstNmElmnt.getChildNodes();
			str = ((Node) fstNm.item(0)).getNodeValue();
			Log.i(TAG, ">>>>>>>" + str);
		}
		return str;
	}

	@Override
	public void signRequest() {
		// TODO Auto-generated method stub
		
	}
}
