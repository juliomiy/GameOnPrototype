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

import com.jittr.android.bs.dto.BSUserDetails;
import com.jittr.android.bs.dto.Game;
import com.jittr.android.bs.dto.UserAddResponse;
import com.jittr.android.fs.core.ParserInterface;
import com.jittr.android.fs.dto.BSUserDashBoard;

public class UserDetailsHandler extends DefaultHandler implements ParserInterface {

	String data = "";
	private StringBuilder builder;
    private  BSUserDetails userDetails = null;
    
    
	public UserDetailsHandler(String data) {
		this.data = data;
	}
	
	
	public Object parse() {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(new DataInputStream(new ByteArrayInputStream(data.getBytes())), this);
            
            return userDetails;
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
		if (localName.equalsIgnoreCase("usersettings")){
			userDetails = new BSUserDetails();
		 }
	  }

	
    public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		builder.append(ch, start, length);
	}
    
    	
    public void endElement(String uri, String localName, String name)  throws SAXException {
		super.endElement(uri, localName, name);
		
		
		System.out.println("end element called uri "+uri+ " localName "+localName+ " name "+name);
		if (this.userDetails != null){
		    if (localName.equalsIgnoreCase("userid")){
		    	userDetails.setUserid(builder.toString());
		    } 
		    else if (localName.equalsIgnoreCase("status_code")){
		    	userDetails.setStatus_code(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase("status_message")){
		    	userDetails.setStatus_message(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase("userid")){
		    	userDetails.setUserid(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase("username")){
		    	userDetails.setUsername(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase("firstname")){
		    	userDetails.setFirstname(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase("lastname")){
		    	userDetails.setLastname(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase("email")){
		    	userDetails.setEmail(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase("facebookuserid")){
		    	userDetails.setFacebookuserid(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase("twitteruserid")){
		    	userDetails.setTwitteruserid(builder.toString());
		    }	
		    else if (localName.equalsIgnoreCase("foursquareuserid")){
		    	userDetails.setTwitteruserid(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase("facebookdefault")){
		    	userDetails.setFacebookdefault(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase("twitterdefault")){
		    	userDetails.setTwitterdefault(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase("foursquaredefault")){
		    	userDetails.setFoursquaredefault(builder.toString());
		    }
		    builder.setLength(0);    
		}
	}
	
    
    public List<Object> parseList() {
		return null;
   }

}
