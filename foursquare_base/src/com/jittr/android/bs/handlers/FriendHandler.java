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

import com.jittr.android.bs.dto.Friend;
import com.jittr.android.bs.dto.GameAddResponse;
import com.jittr.android.bs.dto.GameInvite;
import com.jittr.android.bs.dto.GameInvites;
import com.jittr.android.fs.core.ParserInterface;


public class FriendHandler extends DefaultHandler implements ParserInterface {

	String data = "";
	private StringBuilder builder;
    private  Friend friend = null;
    private ArrayList<Friend> friends = null;
    
	public FriendHandler(String data) {
		this.data = data;
	}
	
	
	public Object parse() {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(new DataInputStream(new ByteArrayInputStream(data.getBytes())), this);
            
            return friends;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	public void startDocument() throws SAXException {
		super.startDocument();
		//System.out.println("Start document");
		builder = new StringBuilder();
    }
	
	
    public void startElement(String uri, String localName, String name,Attributes attributes) throws SAXException {
		super.startElement(uri, localName, name, attributes);
		
		//System.out.println("Start Element called,  uri= " + uri +  " localName = " + localName + " name = " + name);
		if (localName.equalsIgnoreCase("go_friends")){
			friends = new ArrayList<Friend>();
		}
		
		if (localName.equalsIgnoreCase("friend")){
			friend = new Friend();
		 }
		
	  }

	
    public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		builder.append(ch, start, length);
	}
    
    	
    public void endElement(String uri, String localName, String name)  throws SAXException {
		super.endElement(uri, localName, name);
		
		
		//System.out.println("end element called uri "+uri+ " localName "+localName+ " name "+name);
		if (this.friend != null){
			//System.out.println("localName "+localName +" Data "+builder.toString());
		    if (localName.equalsIgnoreCase("status_code")){
		    	friend.setStatus_code(Integer.parseInt(builder.toString()));
		    }
		    else if (localName.equalsIgnoreCase("status_message")){
		    	friend.setStatus_message(builder.toString());
		    }
		    
		    else if (localName.equalsIgnoreCase("frienduserid")){
		    	
		    	friend.setFrienduserid(Integer.parseInt(builder.toString()));
		    }
		    else if (localName.equalsIgnoreCase("friendusername")){
		    	friend.setFriendusername(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase("friendname")) {
		    	friend.setFriendname(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase("friendImageUrl")){
		    	friend.setFriendImageUrl(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase("numberofbets")){
		    	friend.setNumberofbets(Integer.parseInt(builder.toString())); 
		    }
		    
		    else if (localName.equalsIgnoreCase("friend")){
		    	friends.add(friend);
		    	friend = null;
		    }
		    
		       
		}
		builder.setLength(0); 
	}
	
    
    public List<Object> parseList() {
		return null;
   }

}
