package com.jittr.android.util;

public final class Consts {

// Intents
public static final String INTENT_VIEW_VENUE = "VIEW_VENUE"; 	
public static final String INTENT_VIEW_PUBLIC_GAME = "VIEW_PUBLIC_GAME";
public static final String INTENT_USER_SETTINGS = "VIEW_USER_SETTINGS";

public static final int FACEBOOK_NETWORK = 1;
public static final int TWITTER_NETWORK = 2;
public static final int FOURSQUARE_NETWORK = 3;
public static final int BETSQUARED_NETWORK = 4;

public static final int ERROR = 500; 

//OAuth Constants
	
//Bitly Constants
	
//Google Maps
public static final String GOOGLE_MAP_API_KEY = "ABQIAAAAkkbA1TOcucOZig_ctpQNSRQH3Cvd4D3k_1VFIvdkDzxWu_QC3hQMauhlyVDyEB5rQFm5ddDmGwWIDg";	

//Bet Square API
public final static String BS_ADD_USER_ENDPOINT_URL = "http://jittr.com/jittr/gameon/go_postnewuser_post.php";
public final static String BS_GET_PUBLIC_GAMES_ENDPOINT_URL ="http://api.betsquared.com/v1/go_getpublicgames.php"; 
public final static String BS_GET_USER_DASHBOARD_ENDPOINT_URL = "http://api.betsquared.com/v1/go_getuserdashboard.php";
public final static String BS_GET_USER_DETAILS_ENDPOINT_URL = "http://api.betsquared.com/v1/go_getuser.php";
public final static String BS_ADD_GAME_ENDPOINT_URL = "http://jittr.com/jittr/gameon/go_postnewgame.php";
public final static String BS_GAME_INVITES_ENDPOINT_URL = "http://jittr.com/jittr/gameon/go_getgameinvites.php";
public final static String BS_GET_USER_GAMES_ENDPOINT_URL = "http://jittr.com/jittr/gameon/go_getusergames.php";
public final static String BS_GET_USER_FRIENDS_ENDPOINT_URL = "http://jittr.com/jittr/gameon/go_getfriends.php";



//FourSquare API 
public final static String FOURSQUARE_REQUEST_TOKEN_ENDPOINT_URL="http://foursquare.com/oauth/request_token";
public final static String FOURSQUARE_ACCESS_TOKEN_ENDPOINT_URL="http://foursquare.com/oauth/access_token";
public final static String FOURSQUARE_AUTHORIZE_WEBSITE_URL="http://foursquare.com/oauth/authorize";
public final static String FOURSQUARE_CONSUMER_KEY="AROV4OCBZMPAMSHLCF3LLYWQQ0W0F2WH1K1BDZDGBW1OFJSM";
public final static String FOURSQUARE_CONSUMER_SECRET="IT5NN1BSTKCORZ3YVC32BRAJD5O4201TCCAGAK2KAMUYHOQD";

public final static String FOURSQUARE_CALLBACK_URL = "123";

//Facebook
public final static String FACEBOOK_REQUEST_TOKEN_ENDPOINT_URL= "http://foursquare.com/oauth/request_token";
public final static String FACEBOOK_ACCESS_TOKEN_ENDPOINT_URL="https://graph.facebook.com/oauth/access_token";
public final static String FACEBOOK_AUTHORIZE_WEBSITE_URL= "https://graph.facebook.com/oauth/authorize";
public final static String FACEBOOK_CONSUMER_KEY="113817188649294";
public final static String FACEBOOK_CONSUMER_SECRET="d0e1c39b00814c1cb4819f5133338c89";
public final static String FACEBOOK_CALLBACK_URL = "123";
public final static String FACEBOOK_API_KEY="2cdd2ba949d2d45e184f9814230ee20f";
public final static String[] FACEBOOK_PERMISSIONS =
    new String[] {"publish_stream", "read_stream", "offline_access"};

//Twitter
public final static String TWITTER_REQUEST_TOKEN_ENDPOINT_URL="http://twitter.com/oauth/request_token";
public final static String TWITTER_ACCESS_TOKEN_ENDPOINT_URL="http://twitter.com/oauth/access_token";
public final static String TWITTER_AUTHORIZE_WEBSITE_URL="http://twitter.com/oauth/authorize";
public final static String TWITTER_CONSUMER_KEY="EZmDfd0Sw0fPinp7CTNmNQ";
public final static String TWITTER_CONSUMER_SECRET="xSY73z0TCs0drt00kFVFEG6LPNoH1yByRElWejrLE";
public final static String TWITTER_CALLBACK_URL = "123";
public static final String NEW_REGISTRATION = "NEW_REGISTRATION";


//XML Tags
public final static String XML_TAG_GAME_ID = "gameid";
public final static String XML_TAG_STATUS_CODE = "status_code";
public final static String XML_TAG_STATUS_MSG  = "status_message";

public final static String XML_TAG_USER_ID = "userid";
public final static String XML_TAG_NETWORK_NAME = "networkname";

public final static String XML_TAG_INSERT_USER = "insert_user";
public final static String XML_TAG_USER_DASH_BOARD ="userdashboard";

public final static String XML_TAG_TOTAL_BETS="totalbets";
public final static String XML_TAG_TOTAL_BETS_INITIATED = "totalbetsinitiated";
public final static String XML_TAG_TOTAL_BETS_ACCPTED= "totalbetsaccepted";
public final static String XML_TAG_TOTAL_WINS = "totalwins";
public final static String XML_TAG_TOTAL_LOSES = "totalloses";

	







//
	  // PRIVATE //

	  /**
	   The caller references the constants using <tt>Consts.EMPTY_STRING</tt>, 
	   and so on. Thus, the caller should be prevented from constructing objects of 
	   this class, by declaring this private constructor. 
	  */
	  private Consts(){
	    //this prevents even the native class from 
	    //calling this ctor as well :
	    throw new AssertionError();
	  } //private Consructor
} //Consts class
