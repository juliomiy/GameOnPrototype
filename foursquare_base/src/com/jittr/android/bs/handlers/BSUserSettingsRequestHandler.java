/**
 * 
 */
package com.jittr.android.bs.handlers;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.jittr.android.bs.dto.BSUserBankStatement;
import com.jittr.android.bs.dto.GameOnUserSettings;
import com.jittr.android.fs.core.ParserInterface;
import com.jittr.android.util.Consts;

/**
 * @author juliomiyares
 * @version 1.0
 * @date October 27,2010
 *
 */
public class BSUserSettingsRequestHandler extends DefaultHandler implements
		ParserInterface {

	String data = "";
	private StringBuilder builder;
	private GameOnUserSettings userSettings;

	/**
	 * 
	 */
	public BSUserSettingsRequestHandler() {
		// TODO Auto-generated constructor stub
	}
	public BSUserSettingsRequestHandler(String data) {
		this.data=data;
	} //constructor

	/* (non-Javadoc)
	 * @see com.jittr.android.fs.core.ParserInterface#parse()
	 */
	@Override
	public Object parse() {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(new DataInputStream(new ByteArrayInputStream(data.getBytes())), this);
            
        } catch (Exception e) {
        	userSettings = new GameOnUserSettings();
        	userSettings.setStatus_message(e.getMessage());
        	userSettings.setStatus_code(Consts.BS_ERROR_PARSING_RESPONSE);
           // throw new RuntimeException(e);
        }
        return userSettings;
	}
	public void startDocument() throws SAXException {
		super.startDocument();
		System.out.println("Start document");
		builder = new StringBuilder();
    }  //startDocument

	public void startElement(String uri, String localName, String name,Attributes attributes) throws SAXException {
		super.startElement(uri, localName, name, attributes);
		
		System.out.println("Start Element called,  uri= " + uri +  " localName = " + localName + " name = " + name);
		
		if (localName.equalsIgnoreCase("usersettings")){
			userSettings = new GameOnUserSettings();
		 }
	  }   //startElement

	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		builder.append(ch, start, length);
	}  //characters

    public void endElement(String uri, String localName, String name)  throws SAXException {
		super.endElement(uri, localName, name);
		
		
		//System.out.println("end element called uri "+uri+ " localName "+localName+ " name "+name);
		    if (localName.equalsIgnoreCase("status_code")){
		    	userSettings.setStatus_code(builder.toString());
		    }  //status_code
		    else 
		    if (localName.equalsIgnoreCase("status_message")){
		    	userSettings.setStatus_message(builder.toString());
		    }  //status_message
		    else 
            if (localName.equalsIgnoreCase("userid")){
		    	userSettings.setUserID(builder.toString());
		    }
		    else 
            if (localName.equalsIgnoreCase("username")){
		    	userSettings.setUserName(builder.toString());
		    }
		    else 
            if (localName.equalsIgnoreCase("firstname")){
		    	userSettings.setFirstName(builder.toString());
 		    }
		    else 
            if (localName.equalsIgnoreCase("lastname")){
		    	userSettings.setLastName(builder.toString());
 		    }
		    else 
            if (localName.equalsIgnoreCase("email")){
		    	userSettings.setEmail(builder.toString());
 		    }
		    else 
            if (localName.equalsIgnoreCase("primarynetworkname")){
		    	userSettings.setPrimaryNetworkName(builder.toString());
 		    }
		    else 
            if (localName.equalsIgnoreCase("primarynetworkid")){
		    	userSettings.setPrimaryNetworkID(builder.toString());
 		    }
		    builder.setLength(0);
    }  //endElement

	/* (non-Javadoc)
	 * @see com.jittr.android.fs.core.ParserInterface#parseList()
	 */
	@Override
	public List<Object> parseList() {
		// TODO Auto-generated method stub
		return null;
	}

}
