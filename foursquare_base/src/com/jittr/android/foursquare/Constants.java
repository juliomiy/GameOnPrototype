package com.jittr.android.foursquare;

import java.util.ArrayList;

/**
 * 
 * @author System Integration
 *
 */
public abstract class Constants {
	public static final String CONSUMER_KEY = "VYSV4AGFMWLWXNCTVMUNC3TVK3NZSP2VRL305GEYCK34YTQ0";
	public static final String CONSUMER_SECRET = "QQ0ADIL4NZICDWGBOYI2AI0ANKROT2RJCEFSMSPWM0AMURNX";
	public static final String CALLBACK_URL  = "icecondor-android-app:///";
	public static final String OAUTH_REQUEST_URL = "http://foursquare.com/oauth/request_token";
	public static final String OAUTH_ACCESS_URL = "http://foursquare.com/oauth/access_token";
	public static final String OAUTH_AUTHORIZATION_URL = "http://foursquare.com/oauth/authorize";
	public static final String URL_API_USER = "http://api.foursquare.com/v1/user";
	public static String REQUEST_TOKEN = "";
	public static String OAUTH_VERIFIER = "";
	public static String ACCESS_TOKEN = "";
	public static String OAUTH_TOKEN_SECRET = "";
	public static String XML_URL = "http://api.foursquare.com/v1/user";
	public static String FRIENDS_XML_URL = "http://api.foursquare.com/v1/friends";
	public static String USER_NAME = "Guest";
	public static ArrayList<UserModel> userList = new ArrayList<UserModel>();
}
