/**
 * 
 */
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
import com.jittr.android.bs.dto.BSUserBankStatement;
import com.jittr.android.fs.core.ParserInterface;
import com.jittr.android.util.Consts;

/**
 * @author juliomiyares
 *
 */
public class BSUserBankStatementRequestHandler extends DefaultHandler implements
		ParserInterface {

	String data = "";
	private StringBuilder builder;
    private BSUserBankStatement bankStatement;
	/**
	 * 
	 */
	public BSUserBankStatementRequestHandler(String data) {
		this.data=data;
	}  //constructor

	/* (non-Javadoc)
	 * @see com.jittr.android.fs.core.ParserInterface#parse()
	 */
	@Override
	public Object parse() {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(new DataInputStream(new ByteArrayInputStream(data.getBytes())), this);
            
            return bankStatement;
        } catch (Exception e) {
        	bankStatement = new BSUserBankStatement();
        	bankStatement.setStatus_message(e.getMessage());
        	bankStatement.setStatus_code(Consts.BS_ERROR_PARSING_RESPONSE);
           // throw new RuntimeException(e);
        }
		return bankStatement;
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
			bankStatement = new BSUserBankStatement();
		 }
	  }   //startElement

    public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		builder.append(ch, start, length);
	}
    public void endElement(String uri, String localName, String name)  throws SAXException {
		super.endElement(uri, localName, name);
		
		
		//System.out.println("end element called uri "+uri+ " localName "+localName+ " name "+name);
		    if (localName.equalsIgnoreCase("status_code")){
		    	bankStatement.setStatus_code(builder.toString());
		    }  //status_code
		    else 
		    if (localName.equalsIgnoreCase("status_message")){
		    	bankStatement.setStatus_message(builder.toString());
		    }  //status_message
		    else		   
		    if (localName.equalsIgnoreCase("duckettbankbalanceavailable"))  {
		    	int tmpInt = Integer.parseInt(builder.toString());
                bankStatement.setDuckettsBankBalance(tmpInt);
		    } //duckettbankbalanceavailable
		    else
		    if (localName.equalsIgnoreCase("duckettsinplay")){
		    	int tmpInt = Integer.parseInt(builder.toString());
                bankStatement.setDuckettsInPlay(tmpInt);
		    }  //duckettsinplay
		    else
		    if (localName.equalsIgnoreCase("duckettoverdraftavailable")){
		    	int tmpInt = Integer.parseInt(builder.toString());
                bankStatement.setDuckettOverDraftAvailable(tmpInt);
		    }  //duckettsoverdraftavailable
		    else
		    if (localName.equalsIgnoreCase("duckettoverdraftlimit")){
		    	int tmpInt = Integer.parseInt(builder.toString());
                bankStatement.getDuckettOverDraftLimit();
		    }  //duckettoverdraftlimit
		    else
		    //added Julio - left out in original , the userName of invited user
		    if (localName.equalsIgnoreCase("userid")){
		    	int tmpInt = Integer.parseInt(builder.toString());
                bankStatement.setUserID(tmpInt);
		    }  //userid
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
