package com.jittr.android.bs.handlers;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


import android.text.format.DateFormat;
import android.util.Log;

import com.jittr.android.bs.dto.UserGame;
import com.jittr.android.bs.dto.UserGamesDetails;
import com.jittr.android.fs.core.ParserInterface;
import com.jittr.android.fs.dto.User;
import com.jittr.android.fs.utils.Constants;

public class UserGamesHandler extends DefaultHandler implements ParserInterface {

	String data = "";
	private StringBuilder builder;
    private UserGame user_game = null;
    private ArrayList<UserGame> user_games = null;
    private UserGamesDetails userGameDetails = null;
    
	public UserGamesHandler(String data) {
		this.data = data;
	}
	
	
	public Object parse() {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(new DataInputStream(new ByteArrayInputStream(data.getBytes())), this);
            
            return userGameDetails;
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	public void startDocument() throws SAXException {
		super.startDocument();
		System.out.println("Start document");
		
		user_games = new ArrayList<UserGame>();
		userGameDetails = new UserGamesDetails(); 
		builder = new StringBuilder();
    }
	
    public void startElement(String uri, String localName, String name,Attributes attributes) throws SAXException {
		super.startElement(uri, localName, name, attributes);
		
		System.out.println("Start Element called,  uri= " + uri +  " localName = " + localName + " name = " + name);
		if (localName.equalsIgnoreCase("game")){
			user_game = new UserGame();
		 }
	  }

	
    public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		builder.append(ch, start, length);
	}
    
	
    public void endElement(String uri, String localName, String name)  throws SAXException {
		super.endElement(uri, localName, name);
		
		
		System.out.println("end element called uri "+uri+ " localName "+localName+ " name "+name);
		
			if (this.userGameDetails != null){
				if (localName.equalsIgnoreCase("status_code")){
					userGameDetails.setStatus_code(Integer.parseInt(builder.toString()));
				}
				else if (localName.equalsIgnoreCase("status_message")){
					userGameDetails.setStatus_message(builder.toString());
				}
				else if (localName.equalsIgnoreCase("numberofgames")){
					userGameDetails.setNumberOfGames(Integer.parseInt(builder.toString()));
				}
			}
			if(this.user_game !=null) {
				if (localName.equalsIgnoreCase("gameid")){
					user_game.setGameid(Integer.parseInt(builder.toString()));
				} 
			    else if (localName.equalsIgnoreCase("createdbyuserid")){
			    	user_game.setCreatedbyuserid(Integer.parseInt(builder.toString()));
			    }
			    else if (localName.equalsIgnoreCase("createdbyusername")){
			    	user_game.setCreatedbyusername(builder.toString());
			    }
				else if (localName.equalsIgnoreCase("publicgameid")){
			    	user_game.setPublicgameid(Integer.parseInt(builder.toString()));
			    }
			    else if (localName.equalsIgnoreCase("eventname")){
			    	user_game.setEventname(builder.toString());
			    }
			    else if (localName.equalsIgnoreCase("eventdatetime")){
			    	user_game.setEventdatetimeStr(builder.toString());
			    	try {
			    		//2010-09-06 13:00:00
			    		java.text.DateFormat df = new SimpleDateFormat("yyyy-MM-DD HH:MM:SS");
			    		Date dt = df.parse(builder.toString());
			    		user_game.setEventdatetime_Dt(dt);
			    		System.out.println("dt "+dt);
			    	}
			    	catch(Exception e) {
			    		Log.w("","Exception while Converting Str to Date"+e.getMessage());
			    	}
			    }
			    else if (localName.equalsIgnoreCase("wagertype")){
			    	user_game.setWagertype(builder.toString());
			    }
			    else if (localName.equalsIgnoreCase("wagerunits")){
			    	//user_game.setWagerunits(Integer.parseInt(builder.toString()));
			    	user_game.setWagerunits(builder.toString());
			    }
			    else if (localName.equalsIgnoreCase("typeid")){
			    	user_game.setTypeid(Integer.parseInt(builder.toString()));
			    }
			    else if (localName.equalsIgnoreCase("typename")){
			    	user_game.setTypename(builder.toString());
			    }
			    else if (localName.equalsIgnoreCase("sportid")){
			    	user_game.setSportid(Integer.parseInt(builder.toString()));
			    }
			    else if (localName.equalsIgnoreCase("sportname")){
			    	user_game.setSportname(builder.toString());
			    }
			    else if (localName.equalsIgnoreCase("numberofsubscribers")){
			    	user_game.setNumberofsubscribers(Integer.parseInt(builder.toString()));
			    }
			    
			}   
			if (localName.equalsIgnoreCase("game")){
			    	System.out.println("Adding to Games List ");
			    	user_games.add(user_game);  
			    	user_game=null;
			}
		    
		    if (localName.equalsIgnoreCase("games")){
		    	userGameDetails.setUsergames(user_games);
		    }
		    
		    builder.setLength(0);    
		
	}
	
    
    public List<Object> parseList() {
		
    	try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(new DataInputStream(new ByteArrayInputStream(data.getBytes())), this);
            
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    	
	}
	
	
}

