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

import com.jittr.android.bs.dto.BSFriendRequest;
import com.jittr.android.bs.dto.BSFriendRequests;
import com.jittr.android.bs.dto.GameAddResponse;
import com.jittr.android.bs.dto.GameInvite;
import com.jittr.android.bs.dto.GameInvites;
import com.jittr.android.fs.core.ParserInterface;


public class BSFriendRequestHandler extends DefaultHandler implements ParserInterface {

	String data = "";
	private StringBuilder builder;
    private  BSFriendRequests frnd_requests = null;
    private BSFriendRequest friend_request = null;
    private ArrayList<BSFriendRequest> temp_requests = null;
    
	public BSFriendRequestHandler(String data) {
		this.data = data;
	}
	
	
	public Object parse() {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(new DataInputStream(new ByteArrayInputStream(data.getBytes())), this);
            
            return frnd_requests;
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
		if (localName.equalsIgnoreCase("friend_invite")){
			frnd_requests = new BSFriendRequests();
			temp_requests = new ArrayList<BSFriendRequest>();
		 }
		
		if (localName.equalsIgnoreCase("invite")){
			friend_request = new BSFriendRequest();
		 }
		
	  }

	
    public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		builder.append(ch, start, length);
	}
    
    	
    public void endElement(String uri, String localName, String name)  throws SAXException {
		super.endElement(uri, localName, name);
		
		
		//System.out.println("end element called uri "+uri+ " localName "+localName+ " name "+name);
		if (this.frnd_requests != null){
		    if (localName.equalsIgnoreCase("status_code")){
		    	frnd_requests.setStatus_code(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase("status_message")){
		    	if(friend_request !=null) {
		    		friend_request.setStatus_message(builder.toString());
		    	}
		    	else {
		    		frnd_requests.setStatus_message(builder.toString());
		    	}
		    }
		    else if (localName.equalsIgnoreCase("numberofinvites")){
		    	if(builder.toString() !=null && !builder.toString().equals(""))
		    	  frnd_requests.setNumberofRequests(Integer.parseInt(builder.toString()));
		    }
		   
		    if (localName.equalsIgnoreCase("invite")){
		    	temp_requests.add(friend_request);
				friend_request = null;
			}
		    
		    //added Julio - left out in original , the userName of invited user
		    if (localName.equalsIgnoreCase("username")){
		        friend_request.setUser_name(builder.toString());
		    }
		    
		    if (localName.equalsIgnoreCase("friend_invite")){
		    	frnd_requests.setRequests(temp_requests);
		    }
		    builder.setLength(0);    
		}
	}
	
    
    public List<Object> parseList() {
		return null;
   }

}
