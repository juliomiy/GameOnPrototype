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
import com.jittr.android.fs.core.ParserInterface;
import com.jittr.android.util.Consts;


public class AddGameResponseHandler extends DefaultHandler implements ParserInterface {

	String data = "";
	private StringBuilder builder;
    private  GameAddResponse response = null;
    
    
	public AddGameResponseHandler(String data) {
		this.data = data;
	}
	
	
	public Object parse() {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(new DataInputStream(new ByteArrayInputStream(data.getBytes())), this);
        } catch (Exception e) {
            response = new GameAddResponse();
            response.setStatus_code(Consts.BS_ERROR_PARSING_RESPONSE);
            response.setStatus_message(e.getMessage());
        }
        return response;
	}

	public void startDocument() throws SAXException {
		super.startDocument();
		//System.out.println("Start document");
		builder = new StringBuilder();
    }
	
    public void startElement(String uri, String localName, String name,Attributes attributes) throws SAXException {
		super.startElement(uri, localName, name, attributes);
		
		//System.out.println("Start Element called,  uri= " + uri +  " localName = " + localName + " name = " + name);
		if (localName.equalsIgnoreCase("insert_game")){
			response = new GameAddResponse();
		 }
	  }

	
    public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		builder.append(ch, start, length);
	}
    
    	
    public void endElement(String uri, String localName, String name)  throws SAXException {
		super.endElement(uri, localName, name);
		
		
		//System.out.println("end element called uri "+uri+ " localName "+localName+ " name "+name);
		if (this.response != null){
		    if (localName.equalsIgnoreCase(Consts.XML_TAG_GAME_ID)){
		    	response.setGameid(builder.toString());
		    } 
		    else if (localName.equalsIgnoreCase(Consts.XML_TAG_STATUS_CODE)){
		    	response.setStatus_code(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase(Consts.XML_TAG_STATUS_MSG)){
		    	response.setStatus_message(builder.toString());
		    }
		    
		    builder.setLength(0);    
		}
	}
	
    
    public List<Object> parseList() {
		return null;
   }

}
