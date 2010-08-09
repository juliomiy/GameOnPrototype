package com.jittr.android.fs.handlers;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.helpers.DefaultHandler;

import com.jittr.android.fs.core.ParserInterface;
import com.jittr.android.fs.dto.CheckIn;
import com.jittr.android.fs.utils.FSConnectionHandler;

/**
 * @author rg230v
 *
 */

public class CheckInHandler extends DefaultHandler implements ParserInterface  {

	CheckIn ch = null;
	private CheckInHandler() {
		
	}
	

	public Object parse() {
		// TODO Auto-generated method stub
		
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            CheckInHandler handler = new CheckInHandler();
            //parser.parse(this.getInputStream(), handler);
            return ch;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } 
	}

	public List<Object> parseList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//Parser methods TBD
	

}
