package com.jittr.android.util;

public final class Consts {

// Intents
public static final String INTENT_VIEW_VENUE = "VIEW_VENUE"; 	
//OAuth Constants
	
//Bitly Constants
	
//Google Maps
	
//Bet Square API
	
//FourSquare API 
	
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
