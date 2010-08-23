package com.jittr.android.util;

public final class Consts {

// Intents
public static final String INTENT_VIEW_VENUE = "VIEW_VENUE"; 	
//OAuth Constants
	
//Bitly Constants
	
//Google Maps
public static final String GOOGLE_MAP_API_KEY = "ABQIAAAAkkbA1TOcucOZig_ctpQNSRQH3Cvd4D3k_1VFIvdkDzxWu_QC3hQMauhlyVDyEB5rQFm5ddDmGwWIDg";	

//Bet Square API
	
//FourSquare API 
public final static String FOURSQUARE_REQUEST_TOKEN_ENDPOINT_URL="http://foursquare.com/oauth/request_token";
public final static String FOURSQUARE_ACCESS_TOKEN_ENDPOINT_URL="http://foursquare.com/oauth/access_token";
public final static String FOURSQUARE_AUTHORIZE_WEBSITE_URL="http://foursquare.com/oauth/authorize";
//Facebook
public final static String FACEBOOK_REQUEST_TOKEN_ENDPOINT_URL= "http://foursquare.com/oauth/request_token";
public final static String FACEBOOK_ACCESS_TOKEN_ENDPOINT_URL="https://graph.facebook.com/oauth/access_token";
public final static String FACEBOOK_AUTHORIZE_ENDPOINT_URL= "https://graph.facebook.com/oauth/authorize";
//Twitter
public final static String TWITTER_REQUEST_TOKEN_ENDPOINT_URL="http://twitter.com/oauth/request_token";
public final static String TWITTER_ACCESS_TOKEN_ENDPOINT_URL="http://twitter.com/oauth/access_token";
public final static String TWITTER_AUTHORIZE_ENDPOINT_URL="http://twitter.com/oauth/authorize";

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
