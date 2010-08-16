package com.jittr.android.fs.handlers;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.jittr.android.fs.core.ParserInterface;
import com.jittr.android.fs.dto.Category;
import com.jittr.android.fs.dto.CheckIn;
import com.jittr.android.fs.dto.CheckinResponse;
import com.jittr.android.fs.dto.Mayor;
import com.jittr.android.fs.dto.Stats;
import com.jittr.android.fs.dto.User;
import com.jittr.android.fs.dto.Venue;
import com.jittr.android.fs.utils.Constants;
import com.jittr.android.fs.utils.FSConnectionHandler;

/**
 * @author rg230v
 *
 */

public class CheckInHandler extends DefaultHandler implements ParserInterface  {

	CheckinResponse chResponse = null;
	Venue venue = null;
	Mayor mayor = null;
	User user = null;
	Category primaryCategory =null;
	
	String data = null;
	private StringBuilder builder;
	
	public CheckInHandler(String data) {
		this.data = data;
	}
	

	public Object parse() {
		// TODO Auto-generated method stub
		
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(new DataInputStream(new ByteArrayInputStream(data.getBytes())), this);
            return chResponse;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } 
	}

	public List<Object> parseList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void startDocument() throws SAXException {
		super.startDocument();
		System.out.println("Start document");
		builder = new StringBuilder();
    }

	public void startElement(String uri, String localName, String name,Attributes attributes) throws SAXException {
		super.startElement(uri, localName, name, attributes);
		
		System.out.println("Start Element called,  uri= " + uri +  " localName = " + localName + " name = " + name);
		if (localName.equalsIgnoreCase("checkin")){
			chResponse = new CheckinResponse();
		 }
		if (localName.equalsIgnoreCase(Constants.FS_Venue)){
			venue = new Venue();
		}
		
		if (localName.equalsIgnoreCase("mayor")){
			mayor = new Mayor();
		}
		if (localName.equalsIgnoreCase("user")){
			user = new User();
		}
		if (localName.equalsIgnoreCase(Constants.FS_Primary_Category)){
			primaryCategory = new Category();
		}
	}
	
	 public void characters(char[] ch, int start, int length) throws SAXException {
			super.characters(ch, start, length);
			builder.append(ch, start, length);
	 }
	 
	 public void endElement(String uri, String localName, String name)  throws SAXException {
			super.endElement(uri, localName, name);
			
			System.out.println("end element called uri "+uri+ " localName "+localName+ " name "+name);
			if (chResponse != null){
			    if (localName.equalsIgnoreCase(Constants.FS_ID)){
			    	if(venue !=null) 
			    		venue.setId(builder.toString());
			    	else if (user !=null)
			    		user.setId(builder.toString());
			    	else if(primaryCategory !=null) 
			    		primaryCategory.setId(builder.toString());
			    	else
			    		chResponse.setId(builder.toString());
			    } 	
			    else if (localName.equalsIgnoreCase("created")){
			    	chResponse.setCreated(builder.toString());
			    }
			    else if (localName.equalsIgnoreCase("message")){
			    	if(mayor !=null)
			    		mayor.setMessage(builder.toString());
			    	else
			    		chResponse.setMessage(builder.toString());
			    }
			    else if (localName.equalsIgnoreCase("specials")){
			    	chResponse.setSpecials(builder.toString());
			    }
			    else if (localName.equalsIgnoreCase(Constants.FS_Name)){
			    	venue.setName(builder.toString());
			    }
			    else if (localName.equalsIgnoreCase(Constants.FS_Address)) {
			    	venue.setAddress(builder.toString());
			    }
			    else if (localName.equalsIgnoreCase(Constants.FS_CrossStreet)) {
			    	venue.setCrossstreet(builder.toString());
			    }
			    else if (localName.equalsIgnoreCase(Constants.FS_city)) {
			    	venue.setCity(builder.toString());
			    }
			    else if (localName.equalsIgnoreCase(Constants.FS_State)) {
			    	venue.setState(builder.toString());
			    }
			    else if (localName.equalsIgnoreCase(Constants.FS_Zip)) {
			    	venue.setZip(builder.toString());
			    }
			    else if (localName.equalsIgnoreCase(Constants.FS_Geo_Lat)) {
			    	venue.setGeolat(builder.toString());
			    }
			    else if (localName.equalsIgnoreCase(Constants.FS_Geo_Lang)) {
			    	venue.setGeolong(builder.toString());
			    }
			    else if (localName.equalsIgnoreCase(Constants.FS_Venue)) {
			    	chResponse.setVenue(venue);
			    	venue = null;
			    }
			    
			    else if (localName.equalsIgnoreCase("type")) {
			    	mayor.setType(builder.toString());
			    }
			    else if (localName.equalsIgnoreCase("mayor")) {
			    	chResponse.setMayor(mayor);
			    	mayor= null;
			    }
			    else if (localName.equalsIgnoreCase(Constants.FS_FNAME)){
			        user.setFirstName(builder.toString());
			    }
			    else if (localName.equalsIgnoreCase(Constants.FS_LAST_NAME)){
			        user.setLastName(builder.toString());
			    }
			    else if (localName.equalsIgnoreCase("gender")){
			        user.setGender(builder.toString());
			    }
			    else if (localName.equalsIgnoreCase("user")){
			    	mayor.setUser(user);
			    	user =null;
			    }
			    else if (localName.equalsIgnoreCase(Constants.FS_Primary_Category)){
			    	venue.setCategory(primaryCategory);
			    	primaryCategory = null;
				}
			    else if (localName.equalsIgnoreCase(Constants.FS_Full_Path)) {
			    	primaryCategory.setFullpathName(builder.toString());
			    }
			    else if (localName.equalsIgnoreCase(Constants.FS_Node_Name)) {
			    	primaryCategory.setNodeName(builder.toString());
			    }

			    
			    builder.setLength(0);    
			}
	 }

}
