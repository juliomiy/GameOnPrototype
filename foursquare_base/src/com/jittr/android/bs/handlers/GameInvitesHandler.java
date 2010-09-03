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
import com.jittr.android.bs.dto.GameAddResponse;
import com.jittr.android.bs.dto.GameInvite;
import com.jittr.android.bs.dto.GameInvites;
import com.jittr.android.fs.core.ParserInterface;


public class GameInvitesHandler extends DefaultHandler implements ParserInterface {

	String data = "";
	private StringBuilder builder;
    private  GameInvites invites = null;
    private GameInvite game_invite = null;
    private ArrayList<GameInvite> temp_invites = null;
    
	public GameInvitesHandler(String data) {
		this.data = data;
	}
	
	
	public Object parse() {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(new DataInputStream(new ByteArrayInputStream(data.getBytes())), this);
            
            return invites;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	public void startDocument() throws SAXException {
		super.startDocument();
		System.out.println("Start document");
		builder = new StringBuilder();
    }
	
    public void startElement(String uri, String localName, String name,Attributes attributes) throws SAXException {
		super.startElement(uri, localName, name, attributes);
		
		System.out.println("Start Element called,  uri= " + uri +  " localName = " + localName + " name = " + name);
		if (localName.equalsIgnoreCase("game_invites")){
			invites = new GameInvites();
			temp_invites = new ArrayList<GameInvite>();
		 }
		
		if (localName.equalsIgnoreCase("gameinvite")){
			game_invite = new GameInvite();
		 }
		
	  }

	
    public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		builder.append(ch, start, length);
	}
    
    	
    public void endElement(String uri, String localName, String name)  throws SAXException {
		super.endElement(uri, localName, name);
		
		
		System.out.println("end element called uri "+uri+ " localName "+localName+ " name "+name);
		if (this.invites != null){
		    if (localName.equalsIgnoreCase("status_code")){
		    	invites.setStatus_code(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase("status_message")){
		    	invites.setStatus_message(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase("openinvites")){
		    	if(builder.toString() !=null && !builder.toString().equals(""))
		    	invites.setOpeninvites(Integer.parseInt(builder.toString()));
		    }
		    else if (localName.equalsIgnoreCase("gameid")){
		    	game_invite.setGameid(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase("createdbyuserid")){
		    	game_invite.setCreatedByUserID(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase("createdbyusername")) {
		    	game_invite.setCreatedByUserName(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase("eventname")){
		    	game_invite.setEventname(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase("eventdatetime")){
		    	game_invite.setEventdatetime(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase("closedatetime")){
		    	game_invite.setClosedatetime(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase("wagertype")){
		    	game_invite.setWagertype(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase("wagerunits")){
		    	game_invite.setWagerunits(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase("gameinvite")){
		    	temp_invites.add(game_invite);
		    }
		    else if (localName.equalsIgnoreCase("game_invites")){
		    	invites.setGameinvites(temp_invites);
		    }
		    builder.setLength(0);    
		}
	}
	
    
    public List<Object> parseList() {
		return null;
   }

}
