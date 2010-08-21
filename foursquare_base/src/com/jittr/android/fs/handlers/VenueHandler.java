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
import com.jittr.android.fs.dto.Stats;
import com.jittr.android.fs.dto.User;
import com.jittr.android.fs.dto.Venue;
import com.jittr.android.fs.utils.Constants;


/**
 * @author rg230v
 *
 */

public class VenueHandler extends DefaultHandler implements ParserInterface{

	String data = null;
	List venues = null;
	Venue venue = null;
	Stats stats = null;
	String groupType = null;
	
	Category primaryCategory =null;
	User user = null;
	List users = null;
	
	private StringBuilder builder;
	
	public VenueHandler(String data) {
		this.data = data;
	}
	
	public Object parse() {
		
	  try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(new DataInputStream(new ByteArrayInputStream(data.getBytes())), this);
            
            return venue;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	public List<Object> parseList() {
		
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(new DataInputStream(new ByteArrayInputStream(data.getBytes())), this);
            
            return venues;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
	
	public void startDocument() throws SAXException {
		super.startDocument();
		System.out.println("Start document");
		
		venues = new ArrayList();
		users = new ArrayList();
		builder = new StringBuilder();
    }
	
    public void startElement(String uri, String localName, String name,Attributes attributes) throws SAXException {
		super.startElement(uri, localName, name, attributes);
		
		//System.out.println("Start Element called,  uri= " + uri +  " localName = " + localName + " name = " + name);
		
		if (localName.equalsIgnoreCase(Constants.FS_Group)){
			groupType = attributes.getValue("type");
			System.out.println("Group type "+groupType);
		}
		
		if (localName.equalsIgnoreCase(Constants.FS_Venue)){
			venue = new Venue();
			venue.setGroupType(groupType);
			stats = new Stats();
		 }
		
		if (localName.equalsIgnoreCase(Constants.FS_Primary_Category)){
			primaryCategory = new Category();
		}
		
		if (localName.equalsIgnoreCase(Constants.FS_USER)){
			user = new User();
		}
		 
    }

	
    public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		builder.append(ch, start, length);
	}
    
	
    public void endElement(String uri, String localName, String name)  throws SAXException {
		super.endElement(uri, localName, name);
		
		//System.out.println("end element called uri "+uri+ " localName "+localName+ " name "+name);
		if (this.venue != null){
			
		    if (localName.equalsIgnoreCase(Constants.FS_ID)){ //id can be venue or category  
		    	if(primaryCategory !=null) {
		    		primaryCategory.setId(builder.toString());
		    	}
		    	else if (user !=null) {
		    		user.setId(builder.toString());
		    	}
		    	else {
		    	  venue.setId(builder.toString());
		    	}
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
		    else  if (localName.equalsIgnoreCase(Constants.FS_Here_NOW)) {
		    	stats.setCheckins(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase(Constants.FS_Stats)) {
		    	venue.setStats(stats);
		    	stats = null;
		    }
		    else if (localName.equalsIgnoreCase(Constants.FS_Distance)) {
		    	venue.setDistance(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase(Constants.FS_Twitter)) {
		    	venue.setTwitter(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase(Constants.FS_Full_Path)) {
		    	if(primaryCategory !=null) {
		    		primaryCategory.setFullpathName(builder.toString());
		    	}
		    }
		    else if (localName.equalsIgnoreCase(Constants.FS_Node_Name)) {
		    	if(primaryCategory !=null) {
		    		primaryCategory.setNodeName(builder.toString());
		    	}
		    }
		    else if (localName.equalsIgnoreCase(Constants.FS_Primary_Category)){
		    	venue.setCategory(primaryCategory);
		    	primaryCategory = null;
			}
		    
		    else if (localName.equalsIgnoreCase(Constants.FS_FNAME)){
		        user.setFirstName(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase(Constants.FS_LAST_NAME)){
		        user.setLastName(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase(Constants.FS_FRND_STATUS)){
		        user.setFriendStatus(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase(Constants.FS_HOME_CITY)){
		        user.setHomecity(builder.toString());
		    }
		    
		    if (localName.equalsIgnoreCase(Constants.FS_USER)){
				//ADD 
		    	users.add(user);
		    	user = null;
				
			}
		    
		    
		    if (localName.equalsIgnoreCase(Constants.FS_Venue)){
		    	System.out.println("Adding to venue to List ");
		    	venue.setCurrentCheckedInUsers(users);
		    	venues.add(venue);  
		    	
			}
		    
		    builder.setLength(0);    
		}
	}
	
    
    


}
