package com.jittr.android.bs.handlers;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.jittr.android.bs.dto.Game;
import com.jittr.android.fs.core.ParserInterface;
import com.jittr.android.fs.dto.User;
import com.jittr.android.fs.utils.Constants;

public class GameHandler extends DefaultHandler implements ParserInterface {

	String data = "";
	private StringBuilder builder;
    private Game game = null;
    private List games = null;
    
	public GameHandler(String data) {
		this.data = data;
	}
	
	
	public Object parse() {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(new DataInputStream(new ByteArrayInputStream(data.getBytes())), this);
            
            return game;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	public void startDocument() throws SAXException {
		super.startDocument();
		//System.out.println("Start document");
		games = new ArrayList();
		builder = new StringBuilder();
    }
	
    public void startElement(String uri, String localName, String name,Attributes attributes) throws SAXException {
		super.startElement(uri, localName, name, attributes);
		
		//System.out.println("Start Element called,  uri= " + uri +  " localName = " + localName + " name = " + name);
		if (localName.equalsIgnoreCase("game")){
		   game = new Game();
		 }
	  }

	
    public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		builder.append(ch, start, length);
	}
    
	
    public void endElement(String uri, String localName, String name)  throws SAXException {
		super.endElement(uri, localName, name);
		
		
		//System.out.println("end element called uri "+uri+ " localName "+localName+ " name "+name);
		if (this.game != null){
		    if (localName.equalsIgnoreCase("gameid")){
		        game.setId(builder.toString());
		    } 
		    else if (localName.equalsIgnoreCase("sportname")){
		    	game.setSportname(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase("leaguename")){
		    	game.setLeaguename(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase("seasonweek")){
		    	game.setSeasonweek(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase("eventname")){
		    	game.setEventname(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase("eventdatetime")){
		    	game.setEventdatetime(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase("stadiumname")){
		    	game.setStadiumname(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase("address")){
		    	game.setAddress(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase("city")){
		    	game.setCity(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase("state")){
		    	game.setState(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase("fsvenueid")){
		    	game.setFsvenueid(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase("latitude")){
		    	game.setLatitude(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase("longitude")){
		    	game.setLongitude(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase("teamname")){  //May need to revisit/verify this
		    	if(game.getTeam1() !=null)
		    		game.setTeam2(builder.toString());
		    	else
		    		game.setTeam1(builder.toString());
		    }
		   
		    if (localName.equalsIgnoreCase("game")){
		    	//System.out.println("Adding to Games List ");
		    	games.add(game);  
		    	game=null;
			}
		    
		    builder.setLength(0);    
		}
	}
	
    
    public List<Object> parseList() {
		
    	try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(new DataInputStream(new ByteArrayInputStream(data.getBytes())), this);
            
            return games;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    	
	}
	
	
}
