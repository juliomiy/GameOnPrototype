package com.jittr.android.fs.handlers;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.jittr.android.fs.core.ParserInterface;
import com.jittr.android.fs.dto.User;
import com.jittr.android.fs.utils.Constants;

/**
 * @author rg230v
 *
 */

public class UserHandler extends DefaultHandler implements ParserInterface {

	String data = "";
	private StringBuilder builder;
    private User user = null;
    
	public UserHandler(String data) {
		this.data = data;
	}
	
	
	public Object parse() {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(new DataInputStream(new ByteArrayInputStream(data.getBytes())), this);
            
            return user;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	public void startDocument() throws SAXException {
		super.startDocument();
		user = new User();
		builder = new StringBuilder();
    }
	
    public void startElement(String uri, String localName, String name,Attributes attributes) throws SAXException {
		super.startElement(uri, localName, name, attributes);
		System.out.println("selement uri= " + uri +  " localName = " + localName + " name = " + name);
		if (localName.equalsIgnoreCase(Constants.FS_USER)){
		   this.user = new User();
		 }
	  }

	
    public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		builder.append(ch, start, length);
	}
    
	
    public void endElement(String uri, String localName, String name)  throws SAXException {
		super.endElement(uri, localName, name);
		System.out.println("end element called uri "+uri+ " localName "+localName+ " name "+name);
		if (this.user != null){
		    if (localName.equalsIgnoreCase(Constants.FS_ID)){
		        user.setId(builder.toString());
		    } 
		    else if (localName.equalsIgnoreCase(Constants.FS_FNAME)){
		        user.setFirstName(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase(Constants.FS_LAST_NAME)){
		        user.setLastName(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase(Constants.FS_EMAIL)){
		        user.setEmail(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase(Constants.FS_PHONE)){
		        user.setPhone(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase(Constants.FS_TWITTER)){
		        user.setTwitter(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase(Constants.FS_FACEBOOK)){
		        user.setFacebook(builder.toString());
		    }
		    
		    builder.setLength(0);    
		}
	}
	
    
    public List<Object> parseList() {
		return null;
	}
	
		
}



	
	
	


