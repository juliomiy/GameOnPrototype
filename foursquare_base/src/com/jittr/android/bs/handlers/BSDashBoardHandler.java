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
import com.jittr.android.util.Consts;
import com.jittr.android.bs.dto.BSUserDashBoard;

public class BSDashBoardHandler extends DefaultHandler implements ParserInterface {

	String data = "";
	private StringBuilder builder;
    private BSUserDashBoard dashBoard = null;
    
    
	public BSDashBoardHandler(String data) {
		this.data = data;
	}
	
	
	public Object parse() {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(new DataInputStream(new ByteArrayInputStream(data.getBytes())), this);
            
            return dashBoard;
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
		if (localName.equalsIgnoreCase(Consts.XML_TAG_USER_DASH_BOARD)){
			dashBoard = new BSUserDashBoard();
		 }
	  }

	
    public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		builder.append(ch, start, length);
	}
    
    	
    public void endElement(String uri, String localName, String name)  throws SAXException {
		super.endElement(uri, localName, name);
		
		
		//System.out.println("end element called uri "+uri+ " localName "+localName+ " name "+name);
		if (this.dashBoard != null){
		    if (localName.equalsIgnoreCase(Consts.XML_TAG_USER_ID)){
		    	dashBoard.setUserid(builder.toString());
		    } 
		    else if (localName.equalsIgnoreCase(Consts.XML_TAG_TOTAL_BETS)){
		    	dashBoard.setTotalbets(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase(Consts.XML_TAG_TOTAL_BETS_INITIATED)){
		    	dashBoard.setTotalbetsinitiated(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase(Consts.XML_TAG_TOTAL_BETS_ACCPTED)){
		    	dashBoard.setTotalbetsaccepted(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase(Consts.XML_TAG_TOTAL_WINS)){
		    	dashBoard.setTotalwins(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase(Consts.XML_TAG_TOTAL_LOSES)){
		    	dashBoard.setTotalloses(builder.toString());
		    }
		    	    
		    builder.setLength(0);    
		}
	}
	
    
    public List<Object> parseList() {
		return null;
   }

}
